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
    private Double seleniumMg;
    @JsonProperty("niacin_mg")
    private Double niacinMg;
    @JsonProperty("magnesium_mg")
    private Double magnesiumMg;
    @JsonProperty("copper_mg")
    private Double copperMg;
    @JsonProperty("vitamin_B12_mg")
    private Double vitaminB12Mg;
    @JsonProperty("vitamin_B6_mg")
    private Double vitaminB6Mg;
    @JsonProperty("vitamin_C_mg")
    private Double vitaminCMg;
    @JsonProperty("zinc_mg")
    private Double zincMg;
    @JsonProperty("vitamin_E_mg")
    private Double vitaminEMg;
    @JsonProperty("manganese_mg")
    private Double manganeseMg;
    @JsonProperty("vitamin_D_mg")
    private Double vitaminDMg;
    @JsonProperty("iodine_mg")
    private Double iodineMg;
    @JsonProperty("chloride_mg")
    private Double chlorideMg;
    @JsonProperty("folate_mg")
    private Double folateMg;
    @JsonProperty("calcium_mg")
    private Double calciumMg;
    @JsonProperty("molybdenum_mg")
    private Double molybdenumMg;
    @JsonProperty("vitamin_A_mg")
    private Double vitaminAMg;
    @JsonProperty("riboflavin_mg")
    private Double riboflavinMg;
    @JsonProperty("folic_acid_mg")
    private Double folicAcidMg;
    @JsonProperty("iron_mg")
    private Double ironMg;
    @JsonProperty("thiamin_mg")
    private Double thiaminMg;
    @JsonProperty("pantothenic_acid_mg")
    private Double pantothenicAcid_mg;
    @JsonProperty("caffeine_mg")
    private Double caffeineMg;
    @JsonProperty("vitamin_K_mg")
    private Double vitaminKMg;
    @JsonProperty("chromium_mg")
    private Double chromiumMg;
    @JsonProperty("potassium_mg")
    private Double potassiumMg;
    @JsonProperty("biotin_mg")
    private Double biotinMg;
    @JsonProperty("phosphorus_mg")
    private Double phosphorusMg;
}
