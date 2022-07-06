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
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Class which provides various utilities to assist in handling webhook
 * payloads received from Terra.
 */
public class WebhookHandlerUtility {
    private static final Logger logger = LoggerFactory.getLogger(WebhookHandlerUtility.class);
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    private final ExecutorService executorService;
    private final HashMap<String, Consumer<TerraWebhookPayload>> payloadHandlers;
    private final ObjectMapper objectMapper;
    private final String secret;

    /**
     * Constructor for this class
     *
     * @param secret the value of your webhook signing secret - found on the developer dashboard
     */
    public WebhookHandlerUtility(String secret) {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
        this.payloadHandlers = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        this.secret = secret;
    }

    static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    static Mac getMacInstance(String algorithm) {
        try {
            return Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Missing algorithm: " + algorithm, ex);
        }
    }

    /**
     * Verify the signature of the incoming webhook to ensure that it was sent by Terra.
     *
     * @param signatureHeader the value of the {@code terra-signature} header sent with the webhook request
     * @param requestBody the raw string request body sent with the webhook request
     * @return boolean indicating whether the signature could be verified successfully
     * @throws IllegalStateException if your jdk installation is missing the HmacSHA256 algorithm
     * @throws IllegalStateException if your secret key is invalid
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

        SecretKeySpec secretKeySpec = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"
        );

        Mac digest = getMacInstance("HmacSHA256");
        try {
            digest.init(secretKeySpec);
        } catch (InvalidKeyException ex) {
            throw new IllegalStateException("Secret key is invalid", ex);
        }

        digest.update(timestamp.getBytes(StandardCharsets.UTF_8));
        digest.update(".".getBytes(StandardCharsets.UTF_8));
        digest.update(requestBody.getBytes(StandardCharsets.UTF_8));

        String encodedSignature = bytesToHex(digest.doFinal()).toLowerCase();
        return encodedSignature.equals(signature);
    }

    /**
     * Parses the raw payload sent with a webhook request into a {@link TerraWebhookPayload}.
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

    /**
     * Parses the raw payload sent with a webhook request into a {@link TerraWebhookPayload} and
     * then dispatches it to the correct handler method for its event type. If no handler exists
     * for the event type then it will be dispatched to the default handler, or discarded if no
     * default handler exists.
     *
     * @see #addEventHandler(String, Consumer)
     * @see #addDefaultEventHandler(Consumer)
     *
     * @param rawPayload the raw string payload to parse and dispatch to a handler
     */
    public void parseAndDispatchWebhookPayload(String rawPayload) {
        var parsedPayload = parseWebhookPayload(rawPayload);
        if (parsedPayload == null) {
            return;
        }

        var handler = payloadHandlers.containsKey(parsedPayload.getType())
                ? payloadHandlers.get(parsedPayload.getType()) : payloadHandlers.get("default");
        if (handler == null) {
            logger.debug("No handler found for event type {}", parsedPayload.getType());
            return;
        }

        executorService.submit(() -> handler.accept(parsedPayload));
    }

    /**
     * Registers a handler method for the given event type. This will be called with an instance
     * of {@link TerraWebhookPayload} when an event of that type is received
     * by {@link #parseAndDispatchWebhookPayload(String)}.
     *
     * @param eventName the event to register the handler method for
     * @param consumer the handler method to call when the event is received
     * @return this instance, for method chaining
     */
    public WebhookHandlerUtility addEventHandler(String eventName, Consumer<TerraWebhookPayload> consumer) {
        payloadHandlers.put(eventName, consumer);
        return this;
    }

    /**
     * Registers a handler method that will be called if no other handler method can be
     * resolved for a received event type.
     *
     * @param consumer the handler method to call with previously unhandled events
     * @return this instance, for method chaining
     */
    public WebhookHandlerUtility addDefaultEventHandler(Consumer<TerraWebhookPayload> consumer) {
        payloadHandlers.put("default", consumer);
        return this;
    }
}
