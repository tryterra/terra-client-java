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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RestClientV2 {
    private static final String DEFAULT_API_URL = "https://api.tryterra.co/v2";

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ExecutorService executorService = Executors.newFixedThreadPool(30);

    private final String xApiKey;
    private final String devId;
    private final String baseUrl;

    public RestClientV2(String xApiKey, String devId) {
        this.xApiKey = xApiKey;
        this.devId = devId;
        this.baseUrl = DEFAULT_API_URL;
    }

    public RestClientV2(String xApiKey, String devId, String apiUrl) {
        this.xApiKey = xApiKey;
        this.devId = devId;
        this.baseUrl = apiUrl.endsWith("/") ? apiUrl.substring(0, apiUrl.length() - 1) : apiUrl;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private Request.Builder addAuthHeadersToBuilder(Request.Builder builder) {
        return builder
                .addHeader("X-API-Key", xApiKey)
                .addHeader("dev-id", devId);
    }

    private void addQueryParametersToBuilder(HttpUrl.Builder builder, RequestConfig requestConfig) {
        if (requestConfig.getStartTime() == null) {
            throw new RuntimeException("startTime cannot be null for this request");
        }

        builder
                .addQueryParameter("start_date", String.valueOf(requestConfig.getStartTime().getEpochSecond()))
                .addQueryParameter("to_webhook", requestConfig.isToWebhook() ? "true" : "false");

        if (requestConfig.getEndTime() != null) {
            builder.addQueryParameter("end_date", String.valueOf(requestConfig.getEndTime().getEpochSecond()));
        }
        if (!requestConfig.getWithSamples().equals(RequestConfig.Samples.ACCOUNT_DEFAULT)) {
            builder.addQueryParameter(
                    "with_samples",
                    requestConfig.getWithSamples().equals(RequestConfig.Samples.INCLUDE) ? "true" : "false"
            );
        }
    }

    private <T> CompletableFuture<TerraApiResponse<T>> performAsyncCall(Request request, User user, String key, Class<T> parseTo) {
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

    Future<TerraApiResponse<Athlete>> getAthleteForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/athlete").newBuilder()
                .addQueryParameter("user_id", user.getId())
                .addQueryParameter("to_webhook", requestConfig.isToWebhook() ? "true" : "false")
                .build();
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).build();
        return performAsyncCall(request, user, "athlete", Athlete.class);
    }

    Future<TerraApiResponse<Activity>> getActivityForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/activity").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Activity.class);
    }

    Future<TerraApiResponse<Body>> getBodyForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/body").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Body.class);
    }

    Future<TerraApiResponse<Daily>> getDailyForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/daily").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Daily.class);
    }

    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/menstruation").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Menstruation.class);
    }

    Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/nutrition").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Nutrition.class);
    }

    Future<TerraApiResponse<Sleep>> getSleepForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(baseUrl + "/sleep").newBuilder()
                .addQueryParameter("user_id", user.getId());
        addQueryParametersToBuilder(url, requestConfig);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();
        return performAsyncCall(request, user, "data", Sleep.class);
    }
}
