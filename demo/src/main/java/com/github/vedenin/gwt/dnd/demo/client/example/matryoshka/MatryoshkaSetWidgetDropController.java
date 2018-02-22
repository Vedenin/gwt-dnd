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

import com.github.vedenin.gwt.dnd.client.DragContext;
import com.github.vedenin.gwt.dnd.client.VetoDragException;
import com.github.vedenin.gwt.dnd.client.drop.SimpleDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * DropController which allows a widget to be dropped on a SimplePanel drop target when the drop
 * target does not yet have a child widget. Also pops the parent widget to the top of stack by
 * re-attaching it to the grandparent.
 */
public class MatryoshkaSetWidgetDropController extends SimpleDropController {

  private static final String CSS_DEMO_MATRYOSHKA_EXAMPLE_DROP_TARGET_ENGAGE = "demo-MatryoshkaExample-dropTarget-engage";

  private static void makeLastChild(Widget child) {
    Widget parent = child.getParent();
    if (parent instanceof AbsolutePanel) {
      AbsolutePanel p = (AbsolutePanel) child.getParent();
      p.add(child, p.getWidgetLeft(child), p.getWidgetTop(child));
    }
  }

  private final SimplePanel dropTarget;

  public MatryoshkaSetWidgetDropController(SimplePanel dropTarget) {
    super(dropTarget);
    this.dropTarget = dropTarget;
  }

  @Override
  public void onDrop(DragContext context) {
    makeLastChild(dropTarget);
    dropTarget.setWidget(context.draggable);
    super.onDrop(context);
  }

  @Override
  public void onEnter(DragContext context) {
    super.onEnter(context);
    if (dropTarget.getWidget() == null) {
      dropTarget.addStyleName(CSS_DEMO_MATRYOSHKA_EXAMPLE_DROP_TARGET_ENGAGE);
    }
  }

  @Override
  public void onLeave(DragContext context) {
    dropTarget.removeStyleName(CSS_DEMO_MATRYOSHKA_EXAMPLE_DROP_TARGET_ENGAGE);
    super.onLeave(context);
  }

  @Override
  public void onPreviewDrop(DragContext context) throws VetoDragException {
    if (dropTarget.getWidget() != null) {
      throw new VetoDragException();
    }
    super.onPreviewDrop(context);
  }
}
