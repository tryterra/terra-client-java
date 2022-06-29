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

import co.tryterra.terraclient.models.v2.samples.HeartRateDataSample;
import co.tryterra.terraclient.models.v2.samples.HeartRateVariabilityDataSampleSdnn;
import co.tryterra.terraclient.models.v2.samples.HeartRateVariablilitySampleRmssd;
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
public class HeartRateData {
    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        @JsonProperty("max_hr_bpm")
        public Integer maxHrBpm;
        @JsonProperty("resting_hr_bpm")
        public Integer restingHrBpm;
        @JsonProperty("avg_hrv_rmssd")
        public Double avgHrvRmssd;
        @JsonProperty("min_hr_bpm")
        public Integer minHrBpm;
        @JsonProperty("user_max_hr_bpm")
        public Integer userMaxHrBpm;
        @JsonProperty("avg_hrv_sdnn")
        public Double avgHrvSdnn;
        @JsonProperty("avg_hr_bpm")
        public Integer avgHrBpm;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Detailed {
        @JsonProperty("hr_samples")
        private List<HeartRateDataSample> hrSamples;
        @JsonProperty("hrv_samples_sdnn")
        private List<HeartRateVariabilityDataSampleSdnn> hrvSamplesSdnn;
        @JsonProperty("hrv_samples_rmssd")
        private List<HeartRateVariablilitySampleRmssd> hrvSamplesRmssd;
    }

    private Summary summary;
    private Detailed detailed;
}
