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

import co.tryterra.terraclient.models.v2.samples.ActivityLevelSample;
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
public class ActiveDurationsData {
    @JsonProperty("activity_seconds")
    private Double activitySeconds;
    @JsonProperty("rest_seconds")
    private Double restSeconds;
    @JsonProperty("low_intensity_seconds")
    private Double lowIntensitySeconds;
    @JsonProperty("vigorous_intensity_seconds")
    private Double vigorousIntensitySeconds;
    @JsonProperty("inactivity_seconds")
    private Double inactivitySeconds;
    @JsonProperty("moderate_intensity_seconds")
    private Double moderateIntensitySeconds;
    @JsonProperty("num_continuous_inactive_periods")
    private Integer numContinuousInactivePeriods;
    @JsonProperty("activity_levels_samples")
    private List<ActivityLevelSample> activityLevelsSamples;
    @JsonProperty("standing_seconds")
    private Double standingSeconds;
    @JsonProperty("standing_hours_count")
    private Double standingHoursCount;
}
