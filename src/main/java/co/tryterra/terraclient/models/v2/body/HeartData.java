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

package co.tryterra.terraclient.models.v2.body;

import co.tryterra.terraclient.models.v2.common.HeartRateData;
import co.tryterra.terraclient.models.v2.samples.AfibClassificationSample;
import co.tryterra.terraclient.models.v2.samples.ECGReading;
import co.tryterra.terraclient.models.v2.samples.PulseVelocitySample;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartData {
    @JsonProperty("heart_rate_data")
    private HeartRateData heartRateData;
    @JsonProperty("afib_classification_samples")
    private List<AfibClassificationSample> afibClassificationSamples;
    @JsonProperty("pulse_wave_velocity_samples")
    private List<PulseVelocitySample> pulseWaveVelocitySamples;
    @JsonProperty("ecg_signal")
    private List<ECGReading> ecgSignal;
}
