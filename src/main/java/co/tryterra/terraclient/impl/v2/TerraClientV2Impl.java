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
import co.tryterra.terraclient.api.TerraClientV2;
import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;

import java.util.concurrent.Future;

public class TerraClientV2Impl implements TerraClientV2 {
    private final RestClientV2 restClient;

    private final RequestConfig defaultRequestConfig;

    public TerraClientV2Impl(String xApiKey, String devId, String apiUrl, RequestConfig requestConfig) {
        if (apiUrl == null) {
            this.restClient = new RestClientV2(xApiKey, devId);
        } else {
            this.restClient = new RestClientV2(xApiKey, devId, apiUrl);
        }
        this.defaultRequestConfig = requestConfig;
    }

    @Override
    public Future<? extends TerraApiResponse<? extends User>> getAllUsers() {
        return restClient.getAllUsers();
    }

    @Override
    public Future<? extends TerraApiResponse<? extends User>> getUser(String userId) {
        return restClient.getUser(userId);
    }

    @Override
    public Future<TerraApiResponse<Athlete>> getAthleteForUser(User user) {
        return this.getAthleteForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Athlete>> getAthleteForUser(User user, RequestConfig requestConfig) {
        return restClient.getAthleteForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Activity>> getActivityForUser(User user) {
        return this.getActivityForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Activity>> getActivityForUser(User user, RequestConfig requestConfig) {
        return restClient.getActivityForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Body>> getBodyForUser(User user) {
        return this.getBodyForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Body>> getBodyForUser(User user, RequestConfig requestConfig) {
        return restClient.getBodyForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Daily>> getDailyForUser(User user) {
        return this.getDailyForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Daily>> getDailyForUser(User user, RequestConfig requestConfig) {
        return restClient.getDailyForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user) {
        return this.getMenstruationForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user, RequestConfig requestConfig) {
        return restClient.getMenstruationForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user) {
        return this.getNutritionForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user, RequestConfig requestConfig) {
        return restClient.getNutritionForUser(user, requestConfig);
    }

    @Override
    public Future<TerraApiResponse<Sleep>> getSleepForUser(User user) {
        return this.getSleepForUser(user, this.defaultRequestConfig);
    }

    @Override
    public Future<TerraApiResponse<Sleep>> getSleepForUser(User user, RequestConfig requestConfig) {
        return restClient.getSleepForUser(user, requestConfig);
    }
}
