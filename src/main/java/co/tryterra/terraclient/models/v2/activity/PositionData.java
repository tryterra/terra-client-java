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

package co.tryterra.terraclient.models.v2.activity;

import co.tryterra.terraclient.models.v2.samples.PositionSample;
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
public class PositionData {
    @JsonProperty("centre_pos_lat_lng_deg")
    private List<Double> centrePosLatLngDeg;
    @JsonProperty("start_pos_lat_lng_deg")
    private List<Double> startPosLatLngDeg;
    @JsonProperty("end_pos_lat_lng_deg")
    private List<Double> endPosLatLngDeg;
    @JsonProperty("position_samples")
    private List<PositionSample> positionSamples;
}