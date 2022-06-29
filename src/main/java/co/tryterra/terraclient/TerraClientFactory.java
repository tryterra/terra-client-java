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

package co.tryterra.terraclient;

import co.tryterra.terraclient.api.TerraClientV2;
import co.tryterra.terraclient.impl.v2.TerraClientV2Impl;
import org.jetbrains.annotations.NotNull;

public class TerraClientFactory {
    @NotNull
    public static TerraClientV2 getClientV2(String xApiKey, String devId) {
        return new TerraClientV2Impl(xApiKey, devId);
    }
}
