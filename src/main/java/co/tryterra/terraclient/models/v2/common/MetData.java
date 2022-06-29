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

import co.tryterra.terraclient.models.v2.samples.MetSample;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetData {
    @JsonProperty("num_low_intensity_minutes")
    private Double numLowIntensityMinutes;
    @JsonProperty("num_high_intensity_minutes")
    private Double numHighIntensityMinutes;
    @JsonProperty("num_inactive_minutes")
    private Double numInactiveMinutes;
    @JsonProperty("num_moderate_intensity_minutes")
    private Double numModerateIntensityMinutes;
    @JsonProperty("avg_level")
    private Double avgLevel;
    @JsonProperty("MET_samples")
    private List<MetSample> metSamples;
}
