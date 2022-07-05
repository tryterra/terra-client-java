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

import co.tryterra.terraclient.api.TerraWebhookPayload;
import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.ReauthData;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TerraWebhookPayloadImpl implements TerraWebhookPayload {
    private static final Logger logger = LoggerFactory.getLogger(TerraWebhookPayloadImpl.class);

    private final JsonNode rawBody;
    private final ObjectMapper objectMapper;

    public TerraWebhookPayloadImpl(JsonNode rawBody, ObjectMapper objectMapper) {
        this.rawBody = rawBody;
        this.objectMapper = objectMapper;
    }

    private <T> T jsonNodeToObject(JsonNode node, Class<T> parseTo) {
        try {
            return objectMapper.treeToValue(node, parseTo);
        } catch (JsonProcessingException ex) {
            logger.debug("Could not parse node to object successfully", ex);
            return null;
        }
    }

    private <T> List<T> parseDataAsList(JsonNode node, Class<T> parseTo) {
        if (node == null || !node.isArray()) {
            return Collections.emptyList();
        }

        return StreamSupport.stream(node.spliterator(), false)
                .map((jn) -> jsonNodeToObject(jn, parseTo))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public JsonNode getRaw() {
        return rawBody;
    }

    @Override
    public String getType() {
        return rawBody.get("type").asText();
    }

    @Override
    public Optional<User> getUser() {
        if (rawBody.get("user") == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(jsonNodeToObject(rawBody.get("user"), UserImpl.class));
    }

    @Override
    public Optional<ReauthData> asReauthData() {
        if (!getType().equals("user_reauth")) {
            return Optional.empty();
        }
        return Optional.of(new ReauthData(
                jsonNodeToObject(rawBody.get("old_user"), UserImpl.class),
                jsonNodeToObject(rawBody.get("new_user"), UserImpl.class)
        ));
    }

    @Override
    public Optional<Athlete> asAthlete() {
        if (!getType().equals("athlete")) {
            return Optional.empty();
        }
        return Optional.ofNullable(jsonNodeToObject(rawBody.get("athlete"), Athlete.class));
    }

    @Override
    public Optional<List<Activity>> asActivityV2() {
        if (!getType().equals("activity")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Activity.class));
    }

    @Override
    public Optional<List<Body>> asBodyV2() {
        if (!getType().equals("body")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Body.class));
    }

    @Override
    public Optional<List<Daily>> asDailyV2() {
        if (!getType().equals("daily")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Daily.class));
    }

    @Override
    public Optional<List<Menstruation>> asMenstruationV2() {
        if (!getType().equals("menstruation")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Menstruation.class));
    }

    @Override
    public Optional<List<Nutrition>> asNutritionV2() {
        if (!getType().equals("nutrition")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Nutrition.class));
    }

    @Override
    public Optional<List<Sleep>> asSleepV2() {
        if (!getType().equals("sleep")) {
            return Optional.empty();
        }
        return Optional.of(parseDataAsList(rawBody.get("data"), Sleep.class));
    }
}
