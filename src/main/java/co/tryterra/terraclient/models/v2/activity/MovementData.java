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

import co.tryterra.terraclient.models.v2.samples.CadenceSample;
import co.tryterra.terraclient.models.v2.samples.SpeedSample;
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
public class MovementData {
    @JsonProperty("normalized_speed_meters_per_second")
    private Double normalizedSpeedMetersPerSecond;
    @JsonProperty("max_cadence_rpm")
    private Double maxCadenceRpm;
    @JsonProperty("avg_speed_meters_per_second")
    private Double avgSpeedMetersPerSecond;
    @JsonProperty("avg_pace_minutes_per_kilometer")
    private Double avgPaceMinutesPerKilometer;
    @JsonProperty("max_velocity_meters_per_second")
    private Double maxVelocityMetersPerSecond;
    @JsonProperty("max_pace_minutes_per_kilometer")
    private Double maxPaceMinutesPerKilometer;
    @JsonProperty("max_torque_newton_meters")
    private Double maxTorqueNewtonMeters;
    @JsonProperty("avg_cadence_rpm")
    private Double avgCadenceRpm;
    @JsonProperty("avg_velocity_meters_per_second")
    private Double avgVelocityMetersPerSecond;
    @JsonProperty("avg_torque_newton_meters")
    private Double avgTorqueNewtonMeters;
    @JsonProperty("max_speed_meters_per_second")
    private Double maxSpeedMetersPerSecond;
    @JsonProperty("cadence_samples")
    private List<CadenceSample> cadenceSamples;
    @JsonProperty("speed_samples")
    private List<SpeedSample> speedSamples;
}
