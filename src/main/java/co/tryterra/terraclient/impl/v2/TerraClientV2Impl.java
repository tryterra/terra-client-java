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
import co.tryterra.terraclient.impl.UserImpl;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Future;

public class TerraClientV2Impl implements TerraClientV2 {
    private final RestClientV2 restClient;

    public TerraClientV2Impl(String xApiKey, String devId) {
        this.restClient = new RestClientV2(xApiKey, devId);
    }

    @NotNull
    @Override
    public User userFromId(@NotNull String id) {
        return new UserImpl(id);
    }

    @NotNull
    @Override
    public List<User> getAllUsers() {
        return restClient.getAllUsers();
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Athlete>> getAthleteForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getAthleteForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Activity>> getActivityForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getActivityForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Body>> getBodyForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getBodyForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Daily>> getDailyForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getDailyForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Menstruation>> getMenstruationForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getMenstruationForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Nutrition>> getNutritionForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getNutritionForUser(user, requestConfig);
    }

    @NotNull
    @Override
    public Future<TerraApiResponse<Sleep>> getSleepForUser(@NotNull User user, @NotNull RequestConfig requestConfig) {
        return restClient.getSleepForUser(user, requestConfig);
    }
}
