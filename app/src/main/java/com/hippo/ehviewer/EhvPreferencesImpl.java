/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer;

/*
 * Created by Hippo on 1/20/2017.
 */

import static com.hippo.ehviewer.EhvPreferencesImpl.LIST_MODE_DETAIL;

import android.content.Context;
import android.preference.PreferenceManager;
import com.hippo.preferences.Preferences;
import com.hippo.preferences.annotation.BooleanItem;
import com.hippo.preferences.annotation.DecimalIntItem;
import com.hippo.preferences.annotation.IntItem;
import com.hippo.preferences.annotation.Items;
import com.hippo.preferences.annotation.StringItem;

@Items(
    booleanItems = {
        @BooleanItem(key = "show_warning", defValue = true),
        @BooleanItem(key = "ask_analytics", defValue = true),
        @BooleanItem(key = "enable_analytics", defValue = false),
        @BooleanItem(key = "need_sign_in", defValue = true),
        @BooleanItem(key = "select_site", defValue = true),
    },
    intItems = {
        @IntItem(key = "gallery_site", defValue = com.hippo.ehviewer.client.EhUrl.SITE_E),
    },
    stringItems = {
        @StringItem(key = "display_name", defValue = {}),
        @StringItem(key = "avatar", defValue = {}),
    },
    decimalIntItems = {
        // 0: detail, 1: brief
        @DecimalIntItem(key = "list_mode", defValue = LIST_MODE_DETAIL),
        // unit dp
        @DecimalIntItem(key = "list_detail_size", defValue = 320),
        // unit dp
        @DecimalIntItem(key = "list_brief_size", defValue = 120),
    }
)
abstract class EhvPreferencesImpl extends Preferences {

  public static final int LIST_MODE_DETAIL = 0;
  public static final int LIST_MODE_BRIEF = 1;

  private EhvApp app;

  public EhvPreferencesImpl(Context context) {
    super(PreferenceManager.getDefaultSharedPreferences(context));
    app = (EhvApp) context.getApplicationContext();
  }
}