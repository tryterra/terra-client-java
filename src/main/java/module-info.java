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

module co.tryterra.terraclient {
    requires static lombok;

    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;

    exports co.tryterra.terraclient;
    exports co.tryterra.terraclient.api;
    exports co.tryterra.terraclient.api.annotations;
    exports co.tryterra.terraclient.exceptions;
    exports co.tryterra.terraclient.models;
    exports co.tryterra.terraclient.models.v2.activity;
    exports co.tryterra.terraclient.models.v2.body;
    exports co.tryterra.terraclient.models.v2.common;
    exports co.tryterra.terraclient.models.v2.daily;
    exports co.tryterra.terraclient.models.v2.menstruation;
    exports co.tryterra.terraclient.models.v2.nutrition;
    exports co.tryterra.terraclient.models.v2.samples;
    exports co.tryterra.terraclient.models.v2.sleep;

    // Allow jackson to deserialize to our models using reflection
    opens co.tryterra.terraclient.models to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.activity to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.body to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.common to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.daily to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.menstruation to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.nutrition to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.samples to com.fasterxml.jackson.databind;
    opens co.tryterra.terraclient.models.v2.sleep to com.fasterxml.jackson.databind;
}