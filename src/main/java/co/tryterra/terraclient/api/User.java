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
 * Interface representing a full Terra user.
 */
public interface User extends PartialUser{
    /**
     * The provider that this user is registered with.
     *
     * @return the user's provider
     */
    String getProvider();

    /**
     * The last time the user's data was attempted to be updated by Terra.
     *
     * @return the user's last webhook update
     */
    OffsetDateTime getLastWebhookUpdate();

    /**
     * The scopes that this user allowed.
     *
     * @return the user's scopes
     */
    String getScopes();

    String getReferenceId();
}
