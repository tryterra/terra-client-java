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
public class Micros {
    @JsonProperty("selenium_mg")
    private Float seleniumMg;
    @JsonProperty("niacin_mg")
    private Float niacinMg;
    @JsonProperty("magnesium_mg")
    private Float magnesiumMg;
    @JsonProperty("copper_mg")
    private Float copperMg;
    @JsonProperty("vitamin_B12_mg")
    private Float vitaminB12Mg;
    @JsonProperty("vitamin_B6_mg")
    private Float vitaminB6Mg;
    @JsonProperty("vitamin_C_mg")
    private Float vitaminCMg;
    @JsonProperty("zinc_mg")
    private Float zincMg;
    @JsonProperty("vitamin_E_mg")
    private Float vitaminEMg;
    @JsonProperty("manganese_mg")
    private Float manganeseMg;
    @JsonProperty("vitamin_D_mg")
    private Float vitaminDMg;
    @JsonProperty("iodine_mg")
    private Float iodineMg;
    @JsonProperty("chloride_mg")
    private Float chlorideMg;
    @JsonProperty("folate_mg")
    private Float folateMg;
    @JsonProperty("calcium_mg")
    private Float calciumMg;
    @JsonProperty("molybdenum_mg")
    private Float molybdenumMg;
    @JsonProperty("vitamin_A_mg")
    private Float vitaminAMg;
    @JsonProperty("riboflavin_mg")
    private Float riboflavinMg;
    @JsonProperty("folic_acid_mg")
    private Float folicAcidMg;
    @JsonProperty("iron_mg")
    private Float ironMg;
    @JsonProperty("thiamin_mg")
    private Float thiaminMg;
    @JsonProperty("pantothenic_acid_mg")
    private Float pantothenicAcid_mg;
    @JsonProperty("caffeine_mg")
    private Float caffeineMg;
    @JsonProperty("vitamin_K_mg")
    private Float vitaminKMg;
    @JsonProperty("chromium_mg")
    private Float chromiumMg;
    @JsonProperty("potassium_mg")
    private Float potassiumMg;
    @JsonProperty("biotin_mg")
    private Float biotinMg;
    @JsonProperty("phosphorus_mg")
    private Float phosphorusMg;
}
