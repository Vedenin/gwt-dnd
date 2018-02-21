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
package com.github.vedenin.gwt.dnd.demo.client.example.matryoshka;

import com.github.vedenin.gwt.dnd.client.PickupDragController;
import com.github.vedenin.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.FocusPanel;

/**
 * Colorful panel for dragging and dropping.
 */
public class RGBFocusPanel extends FocusPanel {

  private static final String CSS_DEMO_RGB_FOCUS_PANEL = "demo-rgb-focus-panel";

  private final PickupDragController dragController;

  private DropController dropController = new MatryoshkaSetWidgetDropController(this);

  public RGBFocusPanel(PickupDragController dragController, int minWidth, int minHeight, int red,
                       int green, int blue) {
    this.dragController = dragController;
    addStyleName(CSS_DEMO_RGB_FOCUS_PANEL);
    getElement().getStyle().setProperty("backgroundColor",
        "rgb(" + red + ", " + green + ", " + blue + ")");
    getElement().getStyle().setPropertyPx("minWidth", minWidth);
    getElement().getStyle().setPropertyPx("minHeight", minHeight);
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    dragController.registerDropController(dropController);
  }

  @Override
  protected void onUnload() {
    super.onUnload();
    dragController.unregisterDropController(dropController);
  }
}
