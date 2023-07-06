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

import co.tryterra.terraclient.models.v2.samples.DistanceSample;
import co.tryterra.terraclient.models.v2.samples.ElevationSample;
import co.tryterra.terraclient.models.v2.samples.FloorsClimbedSample;
import co.tryterra.terraclient.models.v2.samples.StepSample;
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
public class DistanceData {
    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        @Data
        @Setter(AccessLevel.NONE)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Swimming {
            @JsonProperty("num_strokes")
            private Integer numStrokes;
            @JsonProperty("num_laps")
            private Integer numLaps;
            @JsonProperty("pool_length_meters")
            private Double poolLengthMeters;
        }

        @Data
        @NoArgsConstructor
        @Setter(AccessLevel.NONE)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Elevation {
            @JsonProperty("loss_actual_meters")
            private Double lossActualMeters;
            @JsonProperty("min_meters")
            private Double minMeters;
            @JsonProperty("avg_meters")
            private Double avgMeters;
            @JsonProperty("gain_actual_meters")
            private Double gainActualMeters;
            @JsonProperty("max_meters")
            private Double maxMeters;
            @JsonProperty("gain_planned_meters")
            private Double gainPlannedMeters;
        }

        @JsonProperty("floors_climbed")
        private Integer floorsClimbed;
        @JsonProperty("steps")
        private Integer steps;
        @JsonProperty("distance_meters")
        private Double distanceMeters;
        @JsonProperty("swimming")
        private Swimming swimming;
        @JsonProperty("elevation")
        private Elevation elevation;
    }

    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Detailed {
        @JsonProperty("distance_samples")
        private List<DistanceSample> distanceSamples;
        @JsonProperty("elevation_samples")
        private List<ElevationSample> elevationSamples;
        @JsonProperty("step_samples")
        private List<StepSample> stepSamples;
        @JsonProperty("floors_climbed_samples")
        private List<FloorsClimbedSample> floorsClimbedSample;
    }

    private Summary summary;
    private Detailed detailed;
}
