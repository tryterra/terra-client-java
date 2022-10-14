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

import co.tryterra.terraclient.models.Athlete;
import co.tryterra.terraclient.models.AuthData;
import co.tryterra.terraclient.models.DeauthData;
import co.tryterra.terraclient.models.ReauthData;
import co.tryterra.terraclient.models.RequestProcessing;
import co.tryterra.terraclient.models.v2.activity.Activity;
import co.tryterra.terraclient.models.v2.body.Body;
import co.tryterra.terraclient.models.v2.daily.Daily;
import co.tryterra.terraclient.models.v2.menstruation.Menstruation;
import co.tryterra.terraclient.models.v2.nutrition.Nutrition;
import co.tryterra.terraclient.models.v2.sleep.Sleep;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

/**
 * Interface representing a payload received to a developer's webhook URL
 * from Terra.
 */
public interface TerraWebhookPayload {
    /**
     * The raw, unparsed JSON body for the request.
     *
     * @return the request body
     */
    JsonNode getRaw();

    /**
     * Get the <a href="https://docs.tryterra.co/reference/webhooks#list-of-event-types">type</a>
     * of the webhook event.
     *
     * @return the event type
     */
    String getType();

    /**
     * Get the user that the webhook event is for. Note that this will not be present
     * for events of type {@code user_reauth}.
     *
     * @return {@link Optional} containing the user for the event
     */
    Optional<User> getUser();

    /**
     * Attempt to parse the raw payload as a {@link ReauthData} object. If
     * the event type is not {@code user_reauth} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<ReauthData> asReauthData();

    /**
     * Attempt to parse the raw payload as a {@link AuthData} object. If
     * the event type is not {@code auth} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<AuthData> asAuthData();

    /**
     * Attempt to parse the raw payload as a {@link DeauthData} object. If
     * the event type is not {@code deauth} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<DeauthData> asDeauthData();

    /**
     * Attempt to parse the raw payload as an {@link Athlete} object. If
     * the event type is not {@code athlete} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<Athlete> asAthlete();

    /**
     * Attempt to parse the raw payload as an {@link Activity} object (API version 2). If
     * the event type is not {@code activity} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Activity>> asActivityV2();

    /**
     * Attempt to parse the raw payload as a {@link Body} object (API version 2). If
     * the event type is not {@code body} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Body>> asBodyV2();

    /**
     * Attempt to parse the raw payload as a {@link Daily} object (API version 2). If
     * the event type is not {@code daily} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Daily>> asDailyV2();

    /**
     * Attempt to parse the raw payload as a {@link Menstruation} object (API version 2). If
     * the event type is not {@code menstruation} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Menstruation>> asMenstruationV2();

    /**
     * Attempt to parse the raw payload as a {@link Nutrition} object (API version 2). If
     * the event type is not {@code nutrition} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Nutrition>> asNutritionV2();

    /**
     * Attempt to parse the raw payload as an {@link Sleep} object (API version 2). If
     * the event type is not {@code sleep} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<List<Sleep>> asSleepV2();

    /**
     * Attempt to parse the raw payload as an {@link RequestProcessing} object. If
     * the event type is not {@code request_processing} then this will fail and an
     * empty {@link Optional} will be returned.
     *
     * @return {@link Optional} containing the parsed data, if available
     */
    Optional<RequestProcessing> asRequestProcessing();
}
