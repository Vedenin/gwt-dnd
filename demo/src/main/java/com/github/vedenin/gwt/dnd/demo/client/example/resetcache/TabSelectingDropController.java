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
package com.github.vedenin.gwt.dnd.demo.client.example.resetcache;

import com.github.vedenin.gwt.dnd.client.AbstractDragController;
import com.github.vedenin.gwt.dnd.client.DragContext;
import com.github.vedenin.gwt.dnd.client.drop.AbstractDropController;
import com.github.vedenin.gwt.dnd.client.util.DOMUtil;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabSelectingDropController extends AbstractDropController {

  private final int tabIndex;

  private final TabPanel tabPanel;

  public TabSelectingDropController(Widget tabWidgetDropTarget, TabPanel tabPanel, int tabIndex) {
    super(tabWidgetDropTarget);
    this.tabPanel = tabPanel;
    this.tabIndex = tabIndex;
  }

  @Override
  public void onDrop(DragContext context) {
    // assume content widget is an AbsolutePanel for now
    AbsolutePanel absolutePanel = (AbsolutePanel) tabPanel.getWidget(tabIndex);

    for (Widget widget : context.selectedWidgets) {
      // temporarily (invisibly) add draggable to get its dimensions
      widget.getElement().getStyle().setProperty("visibility", "hidden");
      absolutePanel.add(widget, 0, 0);

      // move widget to random location, and restore visibility
      int left = Random.nextInt(DOMUtil.getClientWidth(absolutePanel.getElement())
          - widget.getOffsetWidth());
      int top = Random.nextInt(DOMUtil.getClientHeight(absolutePanel.getElement())
          - widget.getOffsetHeight());
      absolutePanel.add(widget, left, top);
      widget.getElement().getStyle().setProperty("visibility", "");
    }
    super.onDrop(context);
  }

  @Override
  public void onEnter(DragContext context) {
    super.onEnter(context);
    tabPanel.selectTab(tabIndex);
    ((AbstractDragController) context.dragController).resetCache();
  }
}
