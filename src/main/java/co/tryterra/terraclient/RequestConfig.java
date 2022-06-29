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

import okhttp3.HttpUrl;

import java.time.Instant;

public class RequestConfig {
    private final Instant startTime;
    private final Instant endTime;
    private final boolean toWebhook;
    private final Boolean withSamples;

    RequestConfig(Instant startTime, Instant endTime, boolean toWebhook, Boolean withSamples) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.toWebhook = toWebhook;
        this.withSamples = withSamples;
    }

    public static class Builder {
        private Instant startTime = null;
        private Instant endTime = null;
        private boolean toWebhook = false;
        private Boolean withSamples = null;

        Builder() {}

        public RequestConfig build() {
            return new RequestConfig(
                    startTime, endTime, toWebhook, withSamples
            );
        }

        public Builder startTime(Instant value) {
            this.startTime = value;
            return this;
        }

        public Builder endTime(Instant value) {
            this.endTime = value;
            return this;
        }

        public Builder toWebhook(boolean value) {
            this.toWebhook = value;
            return this;
        }

        public Builder withSamples(boolean value) {
            this.withSamples = value;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static RequestConfig defaultConfig(Instant startTime) {
        return RequestConfig.builder().startTime(startTime).build();
    }

    public static RequestConfig defaultConfig() {
        return RequestConfig.builder().build();
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Boolean getWithSamples() {
        return withSamples;
    }

    public boolean isToWebhook() {
        return toWebhook;
    }

    public void addQueryToUrl(HttpUrl.Builder builder) {
        if (startTime == null) {
            throw new RuntimeException("startTime cannot be null for this request");
        }

        builder
                .addQueryParameter("start_date", String.valueOf(this.startTime.getEpochSecond()))
                .addQueryParameter("to_webhook", this.toWebhook ? "true" : "false");

        if (this.endTime != null) {
            builder.addQueryParameter("end_date", String.valueOf(this.endTime.getEpochSecond()));
        }
        if (this.withSamples != null) {
            builder.addQueryParameter("with_samples", this.withSamples ? "true" : "false");
        }
    }
}
