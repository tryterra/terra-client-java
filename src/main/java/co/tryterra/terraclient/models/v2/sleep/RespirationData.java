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

package co.tryterra.terraclient.models.v2.sleep;

import co.tryterra.terraclient.models.v2.samples.BreathSample;
import co.tryterra.terraclient.models.v2.samples.OxygenSaturationSample;
import co.tryterra.terraclient.models.v2.samples.SnoringSample;
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
public class RespirationData {
    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BreathsData {
        @JsonProperty("min_breaths_per_min")
        private Float minBreathsPerMin;
        @JsonProperty("avg_breaths_per_min")
        private Float avgBreathsPerMin;
        @JsonProperty("max_breaths_per_min")
        private Float maxBreathsPerMin;
        @JsonProperty("on_demand_reading")
        private Boolean onDemandReading;
        @JsonProperty("start_time")
        private String startTime;
        @JsonProperty("end_time")
        private String endTime;
        private List<BreathSample> samples;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SnoringData {
        @JsonProperty("num_snoring_events")
        private Integer numSnoringEvents;
        @JsonProperty("total_snoring_duration_seconds")
        private Integer totalSnoringDurationSeconds;
        @JsonProperty("start_time")
        private String startTime;
        @JsonProperty("end_time")
        private String endTime;
        private List<SnoringSample> samples;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OxygenSaturationData {
        @JsonProperty("start_time")
        private String startTime;
        @JsonProperty("end_time")
        private String endTime;
        private List<OxygenSaturationSample> samples;
    }

    @JsonProperty("breaths_data")
    private BreathsData breathsData;
    @JsonProperty("snoring_data")
    private SnoringData snoringData;
    @JsonProperty("oxygen_saturation_data")
    private OxygenSaturationData oxygenSaturationData;
}
