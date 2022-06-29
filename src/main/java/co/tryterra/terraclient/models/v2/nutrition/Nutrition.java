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

package co.tryterra.terraclient.models.v2.nutrition;

import co.tryterra.terraclient.models.v2.common.Macros;
import co.tryterra.terraclient.models.v2.common.Metadata;
import co.tryterra.terraclient.models.v2.common.Micros;
import co.tryterra.terraclient.models.v2.samples.Meal;
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
public class Nutrition {
    @Data
    @NoArgsConstructor
    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        private Macros macros;
        private Micros micros;
        @JsonProperty("water_ml")
        private Double waterMl;
    }

    private Metadata metadata;
    private Summary summary;
    private List<Meal> meals;
}
