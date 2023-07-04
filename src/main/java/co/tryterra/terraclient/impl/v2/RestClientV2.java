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

package co.tryterra.terraclient.impl.v2;

import co.tryterra.terraclient.RequestConfig;
import co.tryterra.terraclient.api.PartialUser;
import co.tryterra.terraclient.api.TerraApiResponse;
import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.impl.OkHttp3AsyncCall;
import co.tryterra.terraclient.impl.ResponseBodyParser;
import co.tryterra.terraclient.impl.UserImpl;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RestClientV2 {
    private static final String DEFAULT_API_URL = "https://api.tryterra.co/v2";

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;

    private final String xApiKey;
    private final String devId;
    private final String baseUrl;

    public RestClientV2(String xApiKey, String devId) {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

        this.xApiKey = xApiKey;
        this.devId = devId;
        this.baseUrl = DEFAULT_API_URL;
    }

    public RestClientV2(String xApiKey, String devId, String apiUrl) {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

        this.xApiKey = xApiKey;
        this.devId = devId;
        this.baseUrl = apiUrl.endsWith("/") ? apiUrl.substring(0, apiUrl.length() - 1) : apiUrl;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    Request.Builder addAuthHeadersToBuilder(Request.Builder builder) {
        return builder
                .addHeader("X-API-Key", xApiKey)
                .addHeader("dev-id", devId);
    }

    void addQueryParametersToBuilder(HttpUrl.Builder builder, RequestConfig requestConfig, Instant startTime, Instant endTime) {
        Objects.requireNonNull(startTime, "startTime cannot be null for this request");

        builder
                .addQueryParameter("start_date", String.valueOf(startTime.getEpochSecond()))
                .addQueryParameter("to_webhook", requestConfig.isToWebhook() ? "true" : "false")
                .addQueryParameter("retry_if_rate_limited", requestConfig.isRetryIfRateLimited() ? "true": "false");

        if (endTime != null) {
            builder.addQueryParameter("end_date", String.valueOf(endTime.getEpochSecond()));
        }
        if (!requestConfig.getWithSamples().equals(RequestConfig.Samples.ACCOUNT_DEFAULT)) {
            builder.addQueryParameter(
                    "with_samples",
                    requestConfig.getWithSamples().equals(RequestConfig.Samples.INCLUDE) ? "true" : "false"
            );
        }
    }

    <T> CompletableFuture<TerraApiResponse<T>> performAsyncCall(Request request, PartialUser user, String key, Class<T> parseTo) {
        return new OkHttp3AsyncCall(httpClient.newCall(request))
                .asCompletionStage()
                .thenApplyAsync(response -> new ResponseBodyParser<>(user, key, parseTo, this)
                        .toTerraApiResponse(response), executorService)
                .toCompletableFuture();
    }

    Future<? extends TerraApiResponse<? extends User>> getAllUsers() {
        var url = HttpUrl.parse(baseUrl + "/subscriptions");
        assert url != null;
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).build();
        return performAsyncCall(request, null, "users", UserImpl.class);
    }

    Future<? extends TerraApiResponse<? extends User>> getUser(String userId) {
        var url = HttpUrl.parse(baseUrl + "/userInfo").newBuilder()
                .addQueryParameter("user_id", userId)
                .build();
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).build();
        return performAsyncCall(request, null, "user", UserImpl.class);
    }

    Future<TerraApiResponse<Void>> generateAuthenticationURL(String resource, String reference_id, String auth_success_redirect_url, String auth_failure_redirect_url) {
        var url = HttpUrl.parse(baseUrl + "/auth/authenticateUser").newBuilder()
                .addQueryParameter("resource", resource)
                .addQueryParameter("reference_id", reference_id)
                .addQueryParameter("auth_success_redirect_url", auth_success_redirect_url)
                .addQueryParameter("auth_failure_redirect_url", auth_failure_redirect_url)
                .build();
        RequestBody body = RequestBody.create(new byte[0]);
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).post(body).build();
        return performAsyncCall(request, null, null, Void.class);
    }

    Future<TerraApiResponse<Void>> generateWidgetSession(String providers, String reference_id, String auth_success_redirect_url, String auth_failure_redirect_url, String language, boolean show_disconnect) {
        var url = HttpUrl.parse(baseUrl + "/auth/generateWidgetSession").newBuilder()
                .addQueryParameter("providers", providers)
                .addQueryParameter("reference_id", reference_id)
                .addQueryParameter("auth_success_redirect_url", auth_success_redirect_url)
                .addQueryParameter("auth_failure_redirect_url", auth_failure_redirect_url)
                .addQueryParameter("language", language) 
                .addQueryParameter("show_disconnect", Boolean.toString(show_disconnect))
                .build();

        RequestBody body = RequestBody.create(new byte[0]);
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).post(body).build();
        return performAsyncCall(request, null, null, Void.class);
    }

    Future<TerraApiResponse<Void>> deauthenticateUser(PartialUser user) {
        var url = HttpUrl.parse(baseUrl + "/auth/deauthenticateUser").newBuilder()
                .addQueryParameter("user_id", user.getId())
                .build();
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).delete().build();
        return performAsyncCall(request, null, null, Void.class);
    }

    Future<TerraApiResponse<Athlete>> getAthleteForUser(PartialUser user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/athlete").newBuilder()
                .addQueryParameter("user_id", user.getId())
                .addQueryParameter("to_webhook", requestConfig.isToWebhook() ? "true" : "false")
                .build();
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).build();
        return performAsyncCall(request, user, "athlete", Athlete.class);
    }

    Future<TerraApiResponse<Activity>> getActivityForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/activity").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Activity.class);
    }

    Future<TerraApiResponse<Body>> getBodyForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/body").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Body.class);
    }

    Future<TerraApiResponse<Daily>> getDailyForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/daily").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Daily.class);
    }

    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/menstruation").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Menstruation.class);
    }

    Future<TerraApiResponse<Nutrition>> getNutritionForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/nutrition").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Nutrition.class);
    }

    Future<TerraApiResponse<Sleep>> getSleepForUser(PartialUser user, Instant startTime, Instant endTime, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/sleep").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig, startTime, endTime);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Sleep.class);
    }
}
