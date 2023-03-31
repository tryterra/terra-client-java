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

import co.tryterra.terraclient.models.v2.samples.SleepHypnogramSample;
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
public class SleepDurationsData {
    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Other {
        @JsonProperty("duration_in_bed_seconds")
        private Integer durationInBedSeconds;
        @JsonProperty("duration_unmeasureable_sleep_seconds")
        private Integer durationUnmeasureableSleepSeconds;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Awake {
        @JsonProperty("duration_short_interruption_seconds")
        private Double durationShortInterruptionSeconds;
        @JsonProperty("duration_awake_state_seconds")
        private Double durationAwakeStateSeconds;
        @JsonProperty("duration_long_interruption_seconds")
        private Double durationLongInterruptionSeconds;
        @JsonProperty("num_wakeup_events")
        private Integer numWakeupEvents;
        @JsonProperty("wake_up_latency_seconds")
        private Double wakeUpLatencySeconds;
        @JsonProperty("num_out_of_bed_events")
        private Integer numOutOfBedEvents;
        @JsonProperty("sleep_latency_seconds")
        private Double sleepLatencySeconds;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Asleep {
        @JsonProperty("duration_light_sleep_state_seconds")
        private Double durationLightSleepStateSeconds;
        @JsonProperty("duration_asleep_state_seconds")
        private Double durationAsleepStateSeconds;
        @JsonProperty("num_REM_events")
        private Integer numRemEvents;
        @JsonProperty("duration_REM_sleep_state_seconds")
        private Double durationRemSleepStateSeconds;
        @JsonProperty("duration_deep_sleep_state_seconds")
        private Double durationDeepSleepStateSeconds;
    }

    @JsonProperty("sleep_efficiency")
    private Double sleepEfficiency;
    private Other other;
    private Awake awake;
    private Asleep asleep;
    @JsonProperty("hypnogram_samples")
    private List<SleepHypnogramSample> hypnogramSamples;
}
