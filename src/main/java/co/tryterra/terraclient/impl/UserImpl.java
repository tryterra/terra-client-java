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

import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.api.annotations.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserImpl implements User {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private final String id;
    private final String provider;
    @Nullable
    private final OffsetDateTime lastWebhookUpdate;
    @Nullable
    private final String scopes;
    @Nullable
    private final String referenceId;

    @JsonCreator
    public UserImpl(
            @JsonProperty("user") JsonNode user,
            @JsonProperty("user_id") String id,
            @JsonProperty("provider") String provider,
            @JsonProperty("last_webhook_update") String lastWebhookUpdate,
            @JsonProperty("scopes") String scopes,
            @JsonProperty("reference_id") String referenceId
    ) {

        this.id = id == null ? user.get("user_id").asText() : id;
        this.provider = provider == null ? user.get("provider").asText() : provider;

        String referenceIdTemp = referenceId;
        if (referenceIdTemp == null && user != null && !(user.get("reference_id").isNull())) {
            referenceIdTemp = user.get("reference_id").textValue();
        }
        this.referenceId = referenceIdTemp;
        
        String lastWhUpdate = lastWebhookUpdate;
        if (lastWhUpdate == null && user != null && !(user.get("last_webhook_update").isNull())) {
            lastWhUpdate = user.get("last_webhook_update").textValue();
        }
        this.lastWebhookUpdate = lastWhUpdate == null ? null : OffsetDateTime.parse(lastWhUpdate, dateTimeFormatter);

        String scopesTemp = scopes;
        if (scopesTemp == null && user != null && !(user.get("scopes").isNull())) {
            scopesTemp = user.get("scopes").textValue();
        }
        this.scopes = scopesTemp;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getProvider() {
        return this.provider;
    }

    @Override
    public OffsetDateTime getLastWebhookUpdate() {
        return this.lastWebhookUpdate;
    }

    @Override
    public String getScopes() {
        return this.scopes;
    }

    @Override
    public String getReferenceId() {
        return this.referenceId;
    }
}
