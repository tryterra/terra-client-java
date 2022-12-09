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
import co.tryterra.terraclient.api.User;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;

import java.util.List;

public class TerraApiResponseImpl<T> implements TerraApiResponse<T> {
    private final Response response;
    private final List<T> parsed;
    private final JsonNode rawBody;
    private final User user;

    public TerraApiResponseImpl(Response response, List<T> parsed, JsonNode rawBody, User user) {
        this.response = response;
        this.parsed = parsed;
        this.rawBody = rawBody;
        this.user = user;
    }

    @Override
    public int getResponseCode() {
        return response.code();
    }

    @Override
    public boolean isSuccessful() {
        return response.isSuccessful();
    }

    @Override
    public String getType() {
        return rawBody.get("type") == null ? null : rawBody.get("type").asText();
    }

    @Override
    public String getMessage() {
        return rawBody.get("message") == null ? null : rawBody.get("message").asText();
    }

    @Override
    public JsonNode getRawBody() {
        return rawBody;
    }

    @Override
    public List<T> getParsedData() {
        return parsed;
    }

    @Override
    public User getUser() {
        return user;
    }
}
