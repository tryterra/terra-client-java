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

package co.tryterra.terraclient;

import co.tryterra.terraclient.api.TerraWebhookPayload;
import co.tryterra.terraclient.impl.TerraWebhookPayloadImpl;
import co.tryterra.terraclient.impl.UserImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestWebhookHandlerUtility {
    @Test
    void testVerifySignatureReturnsFalseForMalformedHeader() {
        var handler = new WebhookHandlerUtility("foo");
        assertThat(handler.verifySignature("bar", "baz")).isFalse();
    }

    @Test
    void testVerifySignatureReturnsFalseForInvalidHeader() {
        var handler = new WebhookHandlerUtility("foo");
        assertThat(handler.verifySignature("t=1234,s=aef6655db12", "bar")).isFalse();
    }

    @Test
    void testVerifySignatureReturnsTrueForValidHeader() {
        var handler = new WebhookHandlerUtility("foo");
        assertThat(handler.verifySignature(
                "t=12345678,v1=599aa529690b885a43f184327c582375068d70705b7a471af15e10682c6f5313",
                "{'bar':'baz'}"
        )).isTrue();
    }

    @Test
    void testParseWebhookPayloadReturnsObjectWithCorrectType() {
        var handler = new WebhookHandlerUtility("foo");
        assertThat(handler.parseWebhookPayload("{\"type\":\"bar\"}").getType()).isEqualTo("bar");
    }

    @Test
    void testParseWebhookPayloadReturnsObjectWithUser() {
        var handler = new WebhookHandlerUtility("foo");
        var parsed = handler.parseWebhookPayload(
                "{\"user\":{\"user_id\":\"bar\",\"provider\":\"baz\",\"last_webhook_update\":null}}");
        assertThat(parsed.getUser().isPresent()).isTrue();
        assertThat(parsed.getUser().get().getId()).isEqualTo("bar");
    }
}
