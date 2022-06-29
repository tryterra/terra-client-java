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

package co.tryterra.terraclient.api;

import co.tryterra.terraclient.RequestConfig;
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

public interface TerraClientV2 {
    @NotNull
    User userFromId(@NotNull String id);

    @NotNull
    List<User> getAllUsers();

    @NotNull
    Future<TerraApiResponse<Athlete>> getAthleteForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Activity>> getActivityForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Body>> getBodyForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Daily>> getDailyForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Nutrition>> getNutritionForUser(@NotNull User user, @NotNull RequestConfig requestConfig);

    @NotNull
    Future<TerraApiResponse<Sleep>> getSleepForUser(@NotNull User user, @NotNull RequestConfig requestConfig);
}
