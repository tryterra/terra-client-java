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

package co.tryterra.terraclient.models.v2.menstruation;

import co.tryterra.terraclient.models.v2.samples.MenstruationFlowSample;
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
public class MenstruationData {
    @JsonProperty("period_length_days")
    private Integer periodLengthDays;
    @JsonProperty("current_phase")
    private Integer currentPhase;
    @JsonProperty("length_of_current_phase_days")
    private Integer lengthOfCurrentPhaseDays;
    @JsonProperty("days_until_next_phase")
    private Integer daysUntilNextPhase;
    @JsonProperty("period_start_date")
    private String periodStartDate;
    @JsonProperty("predicted_cycle_length_days")
    private Integer predictedCycleLengthDays;
    @JsonProperty("day_in_cycle")
    private Integer dayInCycle;
    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;
    @JsonProperty("cycle_length_days")
    private Integer cycleLengthDays;
    @JsonProperty("is_predicted_cycle")
    private String isPredictedCycle;
    @JsonProperty("menstruation_flow")
    private List<MenstruationFlowSample> menstruationFlow;
}
