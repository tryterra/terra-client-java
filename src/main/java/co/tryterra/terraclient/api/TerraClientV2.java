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

import java.util.concurrent.Future;

/**
 * Interface for facilities to make requests to the Terra API.
 */
public interface TerraClientV2 {
    /**
     * Makes a request to the {@code /subscriptions} endpoint to fetch all users
     * registered with your developer ID.
     *
     * @return future that will contain the API response upon completion
     */
    Future<? extends TerraApiResponse<? extends User>> getAllUsers();

    /**
     * Makes a request to the {@code /userInfo} endpoint to fetch the details
     * for the user with the given ID. This {@link User} object can then subsequently
     * be used to fetch data through the other available methods.
     *
     * Note that the fetched user will both be available through {@link TerraApiResponse#getUser()},
     * and will also be the single item in the {@link TerraApiResponse#getParsedData()} list.
     *
     * @param userId ID of the user to get information for
     * @return future that will contain the fetched {@link User} object upon completion
     */
    Future<? extends TerraApiResponse<? extends User>> getUser(String userId);

    /**
     * Asynchronously makes a request to the {@code /athlete} endpoint to fetch the athlete data
     * for the given user.
     *
     * Note that if athlete data is available for the given user, it will be available as the single item
     * in the list returned by {@link TerraApiResponse#getParsedData()}.
     *
     * @param user the user to fetch the athlete data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Athlete>> getAthleteForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /athlete} endpoint to fetch the athlete data
     * for the given user. Note that only the {@code toWebhook} configuration property of
     * {@link co.tryterra.terraclient.RequestConfig} will be considered in this case.
     *
     * Note that if athlete data is available for the given user, it will be available as the single item
     * in the list returned by {@link TerraApiResponse#getParsedData()}.
     *
     * @param user the user to fetch the athlete data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Athlete>> getAthleteForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /activity} endpoint to fetch the activity data
     * for the given user.
     *
     * @param user the user to fetch the activity data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Activity>> getActivityForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /activity} endpoint to fetch the activity data
     * for the given user.
     *
     * @param user the user to fetch the activity data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Activity>> getActivityForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /body} endpoint to fetch the body data
     * for the given user.
     *
     * @param user the user to fetch the body data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Body>> getBodyForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /body} endpoint to fetch the body data
     * for the given user.
     *
     * @param user the user to fetch the body data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Body>> getBodyForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /daily} endpoint to fetch the daily data
     * for the given user.
     *
     * @param user the user to fetch the daily data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Daily>> getDailyForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /daily} endpoint to fetch the daily data
     * for the given user.
     *
     * @param user the user to fetch the daily data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Daily>> getDailyForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /menstruation} endpoint to fetch the menstruation data
     * for the given user.
     *
     * @param user the user to fetch the menstruation data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /menstruation} endpoint to fetch the menstruation data
     * for the given user.
     *
     * @param user the user to fetch the menstruation data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /nutrition} endpoint to fetch the nutrition data
     * for the given user.
     *
     * @param user the user to fetch the nutrition data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /nutrition} endpoint to fetch the nutrition data
     * for the given user.
     *
     * @param user the user to fetch the nutrition data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Nutrition>> getNutritionForUser(User user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /sleep} endpoint to fetch the sleep data
     * for the given user.
     *
     * @param user the user to fetch the sleep data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Sleep>> getSleepForUser(User user);

    /**
     * Asynchronously makes a request to the {@code /sleep} endpoint to fetch the sleep data
     * for the given user.
     *
     * @param user the user to fetch the sleep data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Sleep>> getSleepForUser(User user, RequestConfig requestConfig);
}
