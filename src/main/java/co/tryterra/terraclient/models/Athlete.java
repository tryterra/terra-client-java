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

package co.tryterra.terraclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Athlete {
    @Nullable
    @JsonProperty("first_name")
    private String firstName;
    @Nullable
    @JsonProperty("last_name")
    private String lastName;
    @Nullable
    private String gender;
    @Nullable
    private String sex;
    @Nullable
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @Nullable
    private String bio;
    @Nullable
    private String email;
    @Nullable
    private String city;
    @Nullable
    private String state;
    @Nullable
    private String country;
    @Nullable
    private Integer age;
}
