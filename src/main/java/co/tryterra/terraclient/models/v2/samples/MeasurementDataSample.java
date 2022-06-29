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

package co.tryterra.terraclient.models.v2.samples;

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
public class MeasurementDataSample {
    @JsonProperty("measurement_time")
    private String measurementTime;
    @JsonProperty("BMI")
    private Float bmi;
    @JsonProperty("BMR")
    private Float bmr;
    @JsonProperty("RMR")
    private Float rmr;
    @JsonProperty("estimated_fitness_age")
    private Integer estimatedFitnessAge;
    @JsonProperty("skin_fold_mm")
    private Float skinFoldMm;
    @JsonProperty("bodyfat_percentage")
    private Float bodyfatPercentage;
    @JsonProperty("weight_kg")
    private Float weightKg;
    @JsonProperty("height_cm")
    private Float heightCm;
    @JsonProperty("bone_mass_kg")
    private Float boneMassKg;
    @JsonProperty("muscle_mass_kg")
    private Float muscleMassKg;
    @JsonProperty("lean_mass_kg")
    private Float leanMassKg;
    @JsonProperty("water_percentage")
    private Float waterPercentage;
    @JsonProperty("insulin_units")
    private Float insulinUnits;
    @JsonProperty("insulin_type")
    private String insulinType;
    @JsonProperty("urine_color")
    private String urineColor;
}
