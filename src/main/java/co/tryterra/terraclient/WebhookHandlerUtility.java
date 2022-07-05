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
import co.tryterra.terraclient.api.annotations.Nullable;
import co.tryterra.terraclient.impl.TerraWebhookPayloadImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Class which provides various utilities to assist in handling webhook
 * payloads received from Terra.
 */
public class WebhookHandlerUtility {
    private static final Logger logger = LoggerFactory.getLogger(WebhookHandlerUtility.class);
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    private final ObjectMapper objectMapper;
    private final String secret;

    /**
     * Constructor for this class
     *
     * @param secret the value of your webhook signing secret - found on the developer dashboard
     */
    public WebhookHandlerUtility(String secret) {
        this.secret = secret;
        this.objectMapper = new ObjectMapper();
    }

    private static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    /**
     * Verify the signature of the incoming webhook to ensure that it was sent by Terra.
     *
     * @param signatureHeader the value of the {@code terra-signature} header sent with the webhook request
     * @param requestBody the raw string request body sent with the webhook request
     * @return boolean indicating whether the signature could be verified successfully
     */
    public boolean verifySignature(String signatureHeader, String requestBody) {
        var pattern = Pattern.compile("t=(?<t>\\d+),v1=(?<s>[\\da-f]+)");
        var matcher = pattern.matcher(signatureHeader);
        if (!matcher.matches()) {
            logger.debug("Signature header is malformed");
            return false;
        }

        String timestamp = matcher.group("t");
        String signature = matcher.group("s");
        if (timestamp == null || signature == null) {
            logger.debug("Timestamp or signature match group null after parsing");
            return false;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"
        );

        Mac digest;
        try {
            digest = Mac.getInstance("HmacSHA256");
            digest.init(secretKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            logger.error("HmacSHA256 algorithm not found or invalid secret key passed", ex);
            return false;
        }

        digest.update(timestamp.getBytes(StandardCharsets.UTF_8));
        digest.update(".".getBytes(StandardCharsets.UTF_8));
        digest.update(requestBody.getBytes(StandardCharsets.UTF_8));

        String encodedSignature = bytesToHex(digest.doFinal()).toLowerCase();
        return encodedSignature.equals(signature);
    }

    /**
     * Parse the raw payload sent with a webhook request into a {@link TerraWebhookPayload}.
     *
     * @param rawPayload the raw payload to parse
     * @return the created {@link TerraWebhookPayload}, or {@code null} if the payload was malformed
     */
    @Nullable
    public TerraWebhookPayload parseWebhookPayload(String rawPayload) {
        try {
            var parsedPayload = objectMapper.readTree(rawPayload);
            return new TerraWebhookPayloadImpl(parsedPayload, objectMapper);
        } catch (JsonProcessingException ex) {
            logger.debug("Payload could not be parsed to JsonNode", ex);
            return null;
        }
    }
}
