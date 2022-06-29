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
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TerraApiResponseImpl<T> implements TerraApiResponse<T> {
    private final Response response;
    private final List<T> parsed;
    private final JsonNode rawBody;

    public TerraApiResponseImpl(Response response, List<T> parsed, JsonNode rawBody) {
        this.response = response;
        this.parsed = parsed;
        this.rawBody = rawBody;
    }

    @Override
    public int getResponseCode() {
        return response.code();
    }

    @Override
    public boolean isSuccessful() {
        return response.isSuccessful();
    }

    @NotNull
    @Override
    public String getType() {
        return rawBody.get("type").asText();
    }

    @Override
    public String getMessage() {
        return rawBody.get("message").asText();
    }

    @NotNull
    @Override
    public JsonNode getRawBody() {
        return rawBody;
    }

    @Override
    public boolean hasParsedData() {
        return List.of(
                "athlete", "activity", "body", "daily",
                "menstruation", "nutrition", "sleep"
        ).contains(getType());
    }

    @Override
    public List<T> getParsedData() {
        return parsed;
    }
}
