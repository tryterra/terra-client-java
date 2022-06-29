/*
 * Copyright 2022 Terra Enabling Developers Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.tryterra.terraclient.impl;

import co.tryterra.terraclient.api.TerraApiResponse;
import co.tryterra.terraclient.impl.v2.RestClientV2;
import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.exceptions.ResponseParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ResponseBodyParserCallbackFuture<T> implements Callback {
    private static  final Logger logger = LoggerFactory.getLogger(ResponseBodyParserCallbackFuture.class);

    private final CompletableFuture<TerraApiResponse<T>> future = new CompletableFuture<>();

    private final User user;
    private final String key;
    private final Class<T> parseTo;
    private final RestClientV2 restClient;

    public ResponseBodyParserCallbackFuture(User user, String key, Class<T> parseTo, RestClientV2 restClient) {
        this.user = user;
        this.key = key;
        this.parseTo = parseTo;
        this.restClient = restClient;
    }

    public Future<TerraApiResponse<T>> getInner() {
        return future;
    }

    private T jsonNodeToObject(JsonNode node) {
        try {
            return restClient.getObjectMapper().treeToValue(node, parseTo);
        } catch (JsonProcessingException ex) {
            logger.debug("Could not parse node to {}", parseTo);
            return null;
        }
    }

    private ParsedResponse<T> parseResponse(Response response) throws ResponseParsingException {
        if (response.body() == null) {
            throw new ResponseParsingException("No body returned in response");
        }

        JsonNode rawBody;
        try {
            rawBody = restClient.getObjectMapper().readTree(response.body().string());
            response.close();
        } catch (IOException ex) {
            throw new ResponseParsingException(ex);
        }

        if (!response.isSuccessful()) {
            return new ParsedResponse<>(rawBody, null);
        }

        if (user != null && rawBody.get("user") != null) {
            var tempUser = UserImpl.fromJsonNode(rawBody, restClient);
            var castUser = (UserImpl) user;
            castUser.setProvider(tempUser.getProvider());
            castUser.setLastWebhookUpdate(tempUser.getLastWebhookUpdate());
        }

        if (rawBody.get(key) == null) {
            return new ParsedResponse<>(rawBody, null);
        }

        if (!rawBody.get(key).isArray()) {
            try {
                return new ParsedResponse<>(
                        rawBody,
                        List.of(restClient.getObjectMapper().treeToValue(rawBody.get(key), parseTo))
                );
            } catch (JsonProcessingException ex) {
                throw new ResponseParsingException(ex);
            }
        }

        if (rawBody.get(key).isEmpty()) {
            return new ParsedResponse<>(rawBody, Collections.emptyList());
        }

        var parsed =  StreamSupport.stream(rawBody.get(key).spliterator(), false)
                .map(this::jsonNodeToObject)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new ParsedResponse<>(rawBody, parsed);
    }

    @Data
    private static class ParsedResponse<T> {
        private final JsonNode rawBody;
        private final List<T> parsedBody;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        future.completeExceptionally(e);
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) {
        try {
            ParsedResponse<T> parsed = parseResponse(response);
            future.complete(new TerraApiResponseImpl<>(response, parsed.getParsedBody(), parsed.getRawBody()));
        } catch (ResponseParsingException ex) {
            future.completeExceptionally(ex);
        }
    }
}
