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

import com.github.vedenin.gwt.dnd.client.DragContext;
import com.github.vedenin.gwt.dnd.client.VetoDragException;
import com.github.vedenin.gwt.dnd.client.drop.SimpleDropController;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample SimpleDropController which discards draggable widgets which are dropped on it.
 */
final class BinDropController extends SimpleDropController {

  private static final String CSS_DEMO_BIN_DRAGGABLE_ENGAGE = "demo-bin-draggable-engage";

  private Bin bin;

  public BinDropController(Bin bin) {
    super(bin);
    this.bin = bin;
  }

  @Override
  public void onDrop(DragContext context) {
    for (Widget widget : context.selectedWidgets) {
      bin.eatWidget(widget);
    }
    super.onDrop(context);
  }

  @Override
  public void onEnter(DragContext context) {
    super.onEnter(context);
    for (Widget widget : context.selectedWidgets) {
      widget.addStyleName(CSS_DEMO_BIN_DRAGGABLE_ENGAGE);
    }
    bin.setEngaged(true);
  }

  @Override
  public void onLeave(DragContext context) {
    for (Widget widget : context.selectedWidgets) {
      widget.removeStyleName(CSS_DEMO_BIN_DRAGGABLE_ENGAGE);
    }
    bin.setEngaged(false);
    super.onLeave(context);
  }

  @Override
  public void onPreviewDrop(DragContext context) throws VetoDragException {
    super.onPreviewDrop(context);
    if (!bin.isWidgetEater()) {
      throw new VetoDragException();
    }
  }
}
