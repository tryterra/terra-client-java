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

package co.tryterra.terraclient.models.v2.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Macros {
    private Double calories;
    @JsonProperty("protein_g")
    private Float proteinG;
    @JsonProperty("carbohydrates_g")
    private Float carbohydratesG;
    @JsonProperty("fat_g")
    private Float fatG;
    @JsonProperty("sugar_g")
    private Float sugarG;
    @JsonProperty("cholesterol_mg")
    private Float cholesterolMg;
    @JsonProperty("fiber_g")
    private Float fiberG;
    @JsonProperty("sodium_mg")
    private Float sodiumMg;
    @JsonProperty("alcohol_g")
    private Float alcoholG;
}
