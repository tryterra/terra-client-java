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

package co.tryterra.terraclient.models.v2.daily;

import co.tryterra.terraclient.models.v2.activity.ActiveDurationsData;
import co.tryterra.terraclient.models.v2.common.*;
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
public class Daily {
    private Metadata metadata;
    @JsonProperty("oxygen_data")
    private OxygenData oxygenData;
    @JsonProperty("device_data")
    private DeviceData deviceData;
    @JsonProperty("distance_data")
    private DistanceData distanceData;
    @JsonProperty("MET_data")
    private MetData metData;
    @JsonProperty("calories_data")
    private CaloriesData caloriesData;
    @JsonProperty("heart_rate_data")
    private HeartRateData heartRateData;
    @JsonProperty("active_durations_data")
    private ActiveDurationsData activeDurationsData;
    @JsonProperty("stress_data")
    private StressData stressData;
}
