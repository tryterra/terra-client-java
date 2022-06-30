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

import co.tryterra.terraclient.api.User;

import java.time.Instant;

/**
 * Class representing request configuration parameters used when making calls to
 * the Terra API.
 */
public class RequestConfig {
    private final Instant startTime;
    private final Instant endTime;
    private final boolean toWebhook;
    private final Samples withSamples;

    RequestConfig(Builder builder) {
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
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
        private Instant startTime = null;
        private Instant endTime = null;
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
         * Set the start time to be used for API requests made using this configuration. This
         * <b>must</b> be set for all requests that allow configuration to be passed, other than
         * {@link co.tryterra.terraclient.api.TerraClientV2#getAthleteForUser(User, RequestConfig)}.
         *
         * @param value {@link Instant} to set the {@code start_date} parameter to
         * @return this builder object for method chaining
         */
        public Builder startTime(Instant value) {
            this.startTime = value;
            return this;
        }

        /**
         * Set the end time to be used for API requests made using this configuration.
         *
         * @param value {@link Instant} to set the {@code end_date} parameter to
         * @return this builder object for method chaining
         */
        public Builder endTime(Instant value) {
            this.endTime = value;
            return this;
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
            this.withSamples = value;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The unix timestamp that will be sent as the start time for the API request. This
     * will be used in the {@code start_date} query parameter.
     *
     * @return value for the {@code start_date} query parameter
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * The unix timestamp that will be sent as the end time for the API request. This
     * will be used in the {@code end_date} query parameter.
     *
     * @return value for the {@code end_date} query parameter
     */
    public Instant getEndTime() {
        return endTime;
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
     *
     * @return value for the {@code to_webhook} query parameter
     */
    public boolean isToWebhook() {
        return toWebhook;
    }
}
