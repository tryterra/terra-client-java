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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class TestUserImpl {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializationConstructorFromUserJsonNode() throws JsonProcessingException {
        var node = objectMapper.createObjectNode();
        node.put("user_id", "foo");
        node.put("provider", "bar");
        node.put("last_webhook_update", "2022-01-01T00:00:00+00:00");

        var user = objectMapper.treeToValue(node, UserImpl.class);
        assertThat(user.getId()).isEqualTo("foo");
        assertThat(user.getProvider()).isEqualTo("bar");
        assertThat(user.getLastWebhookUpdate())
                .isEqualTo(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1640995200L), ZoneId.of("UTC")));
    }

    @Test
    void testDeserializationConstructorFromUserSubJsonNode() throws JsonProcessingException {
        var subNode = objectMapper.createObjectNode();
        subNode.put("user_id", "foo");
        subNode.put("provider", "bar");
        subNode.put("last_webhook_update", "2022-01-01T00:00:00+00:00");
        var outerNode = objectMapper.createObjectNode();
        outerNode.set("user", subNode);

        var user = objectMapper.treeToValue(outerNode, UserImpl.class);
        assertThat(user.getId()).isEqualTo("foo");
        assertThat(user.getProvider()).isEqualTo("bar");
        assertThat(user.getLastWebhookUpdate())
                .isEqualTo(OffsetDateTime.ofInstant(Instant.ofEpochSecond(1640995200L), ZoneId.of("UTC")));
    }
}
