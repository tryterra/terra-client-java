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

import co.tryterra.terraclient.api.annotations.Nullable;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Interface for any response from Terra's API, containing the
 * returned data from the query (if available) as well as the raw, unparsed body
 * as a {@link JsonNode}.
 *
 * @param <T> the type of the response body data
 */
public interface TerraApiResponse<T> {
    /**
     * The HTTP response code that Terra returned from the request.
     *
     * @return the response code
     */
    int getResponseCode();

    /**
     * Whether the request to Terra returned a successful HTTP response code.
     *
     * @return whether the response was successful
     */
    boolean isSuccessful();

    /**
     * The value of the {@code type} key in the returned response body. This can be
     * used to check whether this response contains parsed data, or if a special case
     * occurred, such as the request being chunked due to being for more than 30 days of data.
     * <br>
     * This will only be {@code null} if the request was unsuccessful, i.e. returned a non 2xx
     * status code (accessible through {@link #getResponseCode()} or {@link #isSuccessful()}).
     *
     * @return the type of the response payload
     */
    @Nullable
    String getType();

    /**
     * The value of the {@code message} key in the returned response body. This is a
     * human-readable value denoting what occurred, however it may not always be provided.
     *
     * @return the message sent with the response payload
     */
    String getMessage();

    /**
     * The raw, unparsed JSON body returned with the response.
     *
     * @return the response body
     */
    JsonNode getRawBody();

    /**
     * The data parsed from the response body as a list of Java objects.
     *
     * @return the parsed data
     */
    @Nullable
    List<T> getParsedData();

    /**
     * The user that the request fetched data for.
     *
     * @return the request's user
     */
    @Nullable
    User getUser();
}
