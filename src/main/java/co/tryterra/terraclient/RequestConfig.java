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

import java.util.Objects;

/**
 * Class representing request configuration parameters used when making calls to
 * the Terra API.
 */
public class RequestConfig {
    private final boolean toWebhook;
    private final Samples withSamples;

    RequestConfig(Builder builder) {
        this.toWebhook = builder.toWebhook;
        this.withSamples = builder.withSamples;
    }

    /**
     * Enum representing all possible values for the {@code with_samples} query parameter.
     */
    public enum Samples {
        /**
         * Include samples in the response data.
         */
        INCLUDE,
        /**
         * Exclude samples from the response data.
         */
        EXCLUDE,
        /**
         * Use the account default. This is dictated by whether the developer account
         * has the {@code samples} scope enabled on the developer dashboard.
         */
        ACCOUNT_DEFAULT
    }

    /**
     * Builder class for {@link RequestConfig} instances.
     */
    public static class Builder {
        private boolean toWebhook = true;
        private Samples withSamples = Samples.ACCOUNT_DEFAULT;

        Builder() {}

        /**
         * Create a {@link RequestConfig} object from this builder.
         *
         * @return the created {@link RequestConfig} object
         */
        public RequestConfig build() {
            return new RequestConfig(this);
        }

        /**
         * Set whether the data returned from the API request will be in the response body
         * or sent to the developer webhook. If this is not specified, it will default to
         * {@code true} (the data will be sent to webhook).
         *
         * @param value boolean to set the {@code to_webhook} parameter to
         * @return this builder object for method chaining
         */
        public Builder toWebhook(boolean value) {
            this.toWebhook = value;
            return this;
        }

        /**
         * Set whether the data returned from the API request will include sample data. If
         * this is not specified, it will default to {@link Samples#ACCOUNT_DEFAULT}.
         *
         * @param value {@link Samples} enum value to set the {@code with_samples} parameter to
         * @return this builder object for method chaining
         */
        public Builder withSamples(Samples value) {
            Objects.requireNonNull(value, "Value must be an item from the Samples enum");
            this.withSamples = value;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The value that will be sent to indicate whether the API should return data
     * samples with the response body. This will be used in the {@code with_samples} query
     * parameter.
     *
     * @return value for the {@code with_samples} query parameter
     */
    public Samples getWithSamples() {
        return withSamples;
    }

    /**
     * The value that will be sent to indicate whether the API should send the data
     * directly to the developer's webhook URL. This will be used in the {@code to_webhook}
     * query parameter.
     * <br>
     * Note that if this is set, the future returned by API request methods will <b>never</b>
     * contain any parsed data objects.
     *
     * @return value for the {@code to_webhook} query parameter
     */
    public boolean isToWebhook() {
        return toWebhook;
    }
}
