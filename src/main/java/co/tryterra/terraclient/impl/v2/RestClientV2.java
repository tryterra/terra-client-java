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
import co.tryterra.terraclient.impl.ResponseBodyParserCallbackFuture;
import co.tryterra.terraclient.impl.UserImpl;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RestClientV2 {
    private static final String BASE_URL = "https://api.tryterra.co/v2";

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private final String xApiKey;
    private final String devId;

    public RestClientV2(String xApiKey, String devId) {
        this.xApiKey = xApiKey;
        this.devId = devId;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    private Request.Builder addAuthHeadersToBuilder(Request.Builder builder) {
        return builder
                .addHeader("X-API-Key", xApiKey)
                .addHeader("dev-id", devId);
    }

    private JsonNode makeRequestAndReturnBody(Request request) throws IOException {
        try (var resp = httpClient.newCall(request).execute()) {
            if (!resp.isSuccessful() || resp.body() == null) {
                throw new RuntimeException("Something went wrong: " + resp.code());
            }
            assert resp.body() != null;
            return objectMapper.readTree(resp.body().string());
        }
    }

    List<User> getAllUsers() {
        var request = addAuthHeadersToBuilder(new Request.Builder())
                .url(BASE_URL + "/subscriptions").get().build();

        try {
            var rawBody = makeRequestAndReturnBody(request);
            if (!rawBody.isArray() || rawBody.isEmpty()) {
                return Collections.emptyList();
            }

            return StreamSupport.stream(rawBody.get("users").spliterator(), false)
                    .map((node) -> UserImpl.fromJsonNode(node, this))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    Future<TerraApiResponse<Athlete>> getAthleteForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/athlete").newBuilder()
                .addQueryParameter("user_id", user.getId())
                .addQueryParameter("to_webhook", requestConfig.isToWebhook() ? "true" : "false")
                .build();
        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "athlete", Athlete.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Activity>> getActivityForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/activity").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Activity.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Body>> getBodyForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/body").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Body.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Daily>> getDailyForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/daily").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Daily.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/menstruation").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Menstruation.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/nutrition").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Nutrition.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }

    Future<TerraApiResponse<Sleep>> getSleepForUser(User user, RequestConfig requestConfig) {
        var url = HttpUrl.parse(BASE_URL + "/sleep").newBuilder()
                .addQueryParameter("user_id", user.getId());
        requestConfig.addQueryToUrl(url);

        var request = addAuthHeadersToBuilder(new Request.Builder()).url(url.build()).build();

        var callback = new ResponseBodyParserCallbackFuture<>(user, "data", Sleep.class, this);
        httpClient.newCall(request).enqueue(callback);
        return callback.getInner();
    }
}
