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

import co.tryterra.terraclient.api.TerraClientV2;
import co.tryterra.terraclient.impl.v2.TerraClientV2Impl;

/**
 * A factory for creating a Terra client instance, which will be used for all
 * requests to the Terra REST API.
 */
public class TerraClientFactory {
    /**
     * Creates a new {@link TerraClientV2} instance using the authentication information
     * provided, as well as the default request config. Requests will be made
     * to the default API base URL ({@code https://api.tryterra.co/v2}).
     *
     * @param xApiKey your Terra API key
     * @param devId your Terra developer ID
     * @return the created instance
     */
    public static TerraClientV2 getClientV2(String xApiKey, String devId) {
        return new TerraClientV2Impl(xApiKey, devId, null, RequestConfig.builder().build());
    }

    /**
     * Creates a new {@link TerraClientV2} instance using the authentication information
     * provided, as well as the given request config. Requests will be made
     * to the default API base URL ({@code https://api.tryterra.co/v2}).
     *
     * @param xApiKey your Terra API key
     * @param devId your Terra developer ID
     * @param requestConfig the per-request configuration to use
     * @return the created instance
     */
    public static TerraClientV2 getClientV2(String xApiKey, String devId, RequestConfig requestConfig) {
        return new TerraClientV2Impl(xApiKey, devId, null, requestConfig);
    }

    /**
     * Creates a new {@link TerraClientV2} instance using the authentication information
     * provided, as well as the default request config. Requests will be made to the provided API base URL.
     *
     * @param xApiKey your Terra API key
     * @param devId your Terra developer ID
     * @param apiUrl the API base url to make requests to
     * @return the created instance
     */
    public static TerraClientV2 getClientV2(String xApiKey, String devId, String apiUrl) {
        return new TerraClientV2Impl(xApiKey, devId, apiUrl, RequestConfig.builder().build());
    }

    /**
     * Creates a new {@link TerraClientV2} instance using the authentication information
     * provided, as well as the given request config. Requests will be made to the provided API base URL.
     *
     * @param xApiKey your Terra API key
     * @param devId your Terra developer ID
     * @param apiUrl the API base url to make requests to
     * @param requestConfig the per-request configuration to use
     * @return the created instance
     */
    public static TerraClientV2 getClientV2(String xApiKey, String devId, String apiUrl, RequestConfig requestConfig) {
        return new TerraClientV2Impl(xApiKey, devId, apiUrl, requestConfig);
    }
}
