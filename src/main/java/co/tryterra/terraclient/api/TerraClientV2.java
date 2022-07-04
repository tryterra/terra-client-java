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
import co.tryterra.terraclient.api.annotations.Nullable;
import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;

import java.time.Instant;
import java.util.concurrent.Future;

/**
 * Interface for facilities to make requests to the Terra API.
 */
public interface TerraClientV2 {
    /**
     * Create a new temporary {@link PartialUser} object, without making an API call, that
     * can be passed in to the {@code user} parameter of the data request methods of this class.
     * <br>
     * This allows you to fetch data without having to make an extra unnecessary API request to
     * the {@code /userInfo} endpoint.
     *
     * @param userId the ID of the user to be created
     * @return the created {@link PartialUser} object
     */
    PartialUser userFromId(String userId);

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
     * <br>
     * Note that the fetched user will both be available through {@link TerraApiResponse#getUser()},
     * and will also be the single item in the {@link TerraApiResponse#getParsedData()} list.
     *
     * @param userId ID of the user to get information for
     * @return future that will contain the fetched {@link User} object upon completion
     */
    Future<? extends TerraApiResponse<? extends User>> getUser(String userId);

    /**
     * Asynchronously makes a request to the {@code /userInfo} endpoint to fetch the details
     * for the user represented by the given {@link PartialUser}.
     * <br>
     * Note that the fetched user will both be available through {@link TerraApiResponse#getUser()},
     * and will also be the single item in the {@link TerraApiResponse#getParsedData()} list.
     *
     * @param user the {@link PartialUser} to get information for
     * @return future that will contain the fetched {@link User} object upon completion
     */
    Future<? extends TerraApiResponse<? extends User>> getUser(PartialUser user);

    /**
     * Asynchronously makes a request to the {@code /auth/deauthenticateUser} endpoint to
     * deauthenticate the given user with Terra, terminating the connection.
     * <br>
     * If successful, the response will have a 200 status code. Note that no data
     * will be returned with the API response.
     *
     * @param user the user to deauthenticate
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Void>> deauthenticateUser(PartialUser user);

    /**
     * Asynchronously makes a request to the {@code /athlete} endpoint to fetch the athlete data
     * for the given user.
     * <br>
     * Note that if athlete data is available for the given user, it will be available as the single item
     * in the list returned by {@link TerraApiResponse#getParsedData()}.
     *
     * @param user the user to fetch the athlete data for
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Athlete>> getAthleteForUser(PartialUser user);

    /**
     * Asynchronously makes a request to the {@code /athlete} endpoint to fetch the athlete data
     * for the given user. Note that only the {@code toWebhook} configuration property of
     * {@link co.tryterra.terraclient.RequestConfig} will be considered in this case.
     * <br>
     * Note that if athlete data is available for the given user, it will be available as the single item
     * in the list returned by {@link TerraApiResponse#getParsedData()}.
     *
     * @param user the user to fetch the athlete data for
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Athlete>> getAthleteForUser(PartialUser user, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /activity} endpoint to fetch the activity data
     * for the given user.
     *
     * @param user the user to fetch the activity data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Activity>> getActivityForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /activity} endpoint to fetch the activity data
     * for the given user.
     *
     * @param user the user to fetch the activity data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Activity>> getActivityForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /body} endpoint to fetch the body data
     * for the given user.
     *
     * @param user the user to fetch the body data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Body>> getBodyForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /body} endpoint to fetch the body data
     * for the given user.
     *
     * @param user the user to fetch the body data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Body>> getBodyForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /daily} endpoint to fetch the daily data
     * for the given user.
     *
     * @param user the user to fetch the daily data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Daily>> getDailyForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /daily} endpoint to fetch the daily data
     * for the given user.
     *
     * @param user the user to fetch the daily data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Daily>> getDailyForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /menstruation} endpoint to fetch the menstruation data
     * for the given user.
     *
     * @param user the user to fetch the menstruation data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /menstruation} endpoint to fetch the menstruation data
     * for the given user.
     *
     * @param user the user to fetch the menstruation data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Menstruation>> getMenstruationForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /nutrition} endpoint to fetch the nutrition data
     * for the given user.
     *
     * @param user the user to fetch the nutrition data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Nutrition>> getNutritionForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /nutrition} endpoint to fetch the nutrition data
     * for the given user.
     *
     * @param user the user to fetch the nutrition data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Nutrition>> getNutritionForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);

    /**
     * Asynchronously makes a request to the {@code /sleep} endpoint to fetch the sleep data
     * for the given user.
     *
     * @param user the user to fetch the sleep data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Sleep>> getSleepForUser(PartialUser user, Instant startTime, @Nullable Instant endTime);

    /**
     * Asynchronously makes a request to the {@code /sleep} endpoint to fetch the sleep data
     * for the given user.
     *
     * @param user the user to fetch the sleep data for
     * @param startTime the start time to fetch data since
     * @param endTime then end time to fetch data before
     * @param requestConfig the config to use for this request
     * @return future that will contain the API response upon completion
     */
    Future<TerraApiResponse<Sleep>> getSleepForUser(PartialUser user, Instant startTime, @Nullable Instant endTime, RequestConfig requestConfig);
}
