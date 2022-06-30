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

import java.time.OffsetDateTime;

/**
 * Interface representing a Terra user.
 */
public interface User {
    /**
     * The user's ID. This is sent as a query parameter to all API requests for
     * this user's data. It will always be formatted as a UUIDv4.
     *
     * @return the user's ID
     */
    String getId();

    /**
     * The provider that this user is registered with. Note that this will always be {@code null}
     * until the first request for any data for this user is made.
     *
     * @return the user's provider, or {@code null} if no data has been requested for the user yet
     */
    String getProvider();

    /**
     * The last time the user's data was attempted to be updated by Terra. Note that
     * this will always be {@code null} until the first request for any data for this user is made.
     *
     * @return the user's last webhook update, or {@code null} if no data has been requested for the user yet
     */
    OffsetDateTime getLastWebhookUpdate();
}
