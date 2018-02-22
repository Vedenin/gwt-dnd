/*
 * Copyright 2009 Fred Sauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.vedenin.gwt.dnd.demo.client.example.bin;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel which which does not 'eat' widgets.
 */
class Bin extends HTML {

  private static final String CSS_DEMO_BIN = "demo-bin";

  private static final String CSS_DEMO_BIN_ENGAGE = "demo-bin-engage";

  public Bin(int width, int height) {
    setPixelSize(width, height);
    updateText();
    addStyleName(CSS_DEMO_BIN);
  }

  public void eatWidget(Widget widget) {
  }

  public boolean isWidgetEater() {
    return false;
  }

  public void setEngaged(boolean engaged) {
    if (engaged) {
      addStyleName(CSS_DEMO_BIN_ENGAGE);
    } else {
      removeStyleName(CSS_DEMO_BIN_ENGAGE);
    }
  }

  protected void updateText() {
    setHTML("<b>Closed Bin</b><br>\n" + "(does not currently accept trash)<br>\n<br>\n"
        + "<i>try dropping something on me</i>");
  }
}
