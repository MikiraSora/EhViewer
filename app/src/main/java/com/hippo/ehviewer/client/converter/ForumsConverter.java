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

package com.hippo.ehviewer.client.converter;

/*
 * Created by Hippo on 2/12/2017.
 */

import android.support.annotation.NonNull;
import com.hippo.ehviewer.client.EhConverter;
import com.hippo.ehviewer.client.EhUrl;
import com.hippo.ehviewer.client.exception.ParseException;
import com.hippo.ehviewer.client.result.ForumsResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * A {@link retrofit2.Converter} to parse the html of forums
 * to get user profile url.
 */
public class ForumsConverter extends EhConverter<ForumsResult> {

  @NonNull
  @Override
  public ForumsResult convert(String body) throws ParseException {
    Document d = Jsoup.parse(body, EhUrl.URL_FORUMS);
    Element userLinks = d.getElementById("userlinks");
    try {
      Element child = userLinks.child(0).child(0).child(0);
      String url = child.attr("href");
      url = ConverterUtils.completeUrl(EhUrl.URL_FORUMS, url);
      return new ForumsResult(url);
    } catch (NullPointerException | IndexOutOfBoundsException e) {
      throw new ParseException("Can't get profile url", body);
    }
  }


  ////////////////
  // Pain part
  ////////////////

  @NonNull
  @Override
  public ForumsResult error(Throwable t) {
    return ForumsResult.error(t);
  }
}