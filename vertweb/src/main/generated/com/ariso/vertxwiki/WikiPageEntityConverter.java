/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ariso.vertxwiki;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link com.ariso.vertxwiki.WikiPageEntity}.
 *
 * NOTE: This class has been automatically generated from the {@link com.ariso.vertxwiki.WikiPageEntity} original class using Vert.x codegen.
 */
public class WikiPageEntityConverter {

  public static void fromJson(JsonObject json, WikiPageEntity obj) {
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("markdownText") instanceof String) {
      obj.setMarkdownText((String)json.getValue("markdownText"));
    }
    if (json.getValue("title") instanceof String) {
      obj.setTitle((String)json.getValue("title"));
    }
  }

  public static void toJson(WikiPageEntity obj, JsonObject json) {
    json.put("id", obj.getId());
    if (obj.getMarkdownText() != null) {
      json.put("markdownText", obj.getMarkdownText());
    }
    if (obj.getTitle() != null) {
      json.put("title", obj.getTitle());
    }
  }
}