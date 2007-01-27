/*
 * Copyright 2006 Fred Sauer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.allen_sauer.gwt.dragdrop.client.drop;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.dragdrop.client.DragAndDropController;

/**
 * A
 * {@link com.allen_sauer.gwt.dragdrop.demo.client.drop.AbstractDropController}
 * which allows a draggable widget to be placed anywhere on an
 * {@link com.google.gwt.user.client.ui.AbsolutePanel} drop target.
 */
public abstract class AbstractPositioningDropController extends
    AbstractDropController {

  public AbstractPositioningDropController(Panel dropTargetPanel) {
    super(dropTargetPanel);
  }

  public void onDrop(DragAndDropController dragAndDropController,
      Widget draggable) {
    super.onDrop(dragAndDropController, draggable);
  }

  public void onPreDropEnter(DragAndDropController dragAndDropController,
      Widget draggable) {
    super.onPreDropEnter(dragAndDropController, draggable);
    // TODO add positioning box to DOM instead
    dragAndDropController.getPostioningBox().removeStyleName("dragdrop-hidden");
  }

  public void onPreDropLeave(DragAndDropController dragAndDropController,
      Widget draggable) {
    super.onPreDropLeave(dragAndDropController, draggable);
    // TODO remove positioning box from DOM instead
    dragAndDropController.getPostioningBox().addStyleName("dragdrop-hidden");
  }

}