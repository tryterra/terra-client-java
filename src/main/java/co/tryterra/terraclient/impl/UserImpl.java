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

import co.tryterra.terraclient.impl.v2.RestClientV2;
import co.tryterra.terraclient.api.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

public class UserImpl implements User {
    private final String id;

    private String provider;
    private OffsetDateTime lastWebhookUpdate;

    public UserImpl(String id) {
        this.id = id;
    }

    public synchronized void setProvider(String provider) {
        this.provider = provider;
    }

    public synchronized void setLastWebhookUpdate(OffsetDateTime lastWebhookUpdate) {
        this.lastWebhookUpdate = lastWebhookUpdate;
    }

    @NotNull
    @Override
    public String getId() {
        return this.id;
    }

    @NotNull
    @Override
    public String getProvider() {
        return this.provider;
    }

    @Nullable
    @Override
    public OffsetDateTime getLastWebhookUpdate() {
        return this.lastWebhookUpdate;
    }

    @NotNull
    public static UserImpl fromJsonNode(JsonNode node, RestClientV2 restClient) {
        var usr = node.get("user") == null ? node : node.get("user");

        var created = new UserImpl(usr.get("user_id").asText());
        created.setProvider(usr.get("provider").asText());

        var lastWhUpdate = usr.get("last_webhook_update");
        if (lastWhUpdate != null && !lastWhUpdate.isNull()) {
            created.setLastWebhookUpdate(OffsetDateTime.parse(lastWhUpdate.asText(), restClient.getDateTimeFormatter()));
        }

        return created;
    }
}
