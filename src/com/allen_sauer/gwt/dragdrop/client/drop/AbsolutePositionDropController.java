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

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.dragdrop.client.DragController;
import com.allen_sauer.gwt.dragdrop.client.util.Area;
import com.allen_sauer.gwt.dragdrop.client.util.Location;

/**
 * A {@link com.allen_sauer.gwt.dragdrop.demo.client.drop.DropController} which
 * allows a draggable widget to be placed at specific (absolute) locations on an
 * {@link com.google.gwt.user.client.ui.AbsolutePanel} drop target.
 */
public class AbsolutePositionDropController extends AbstractPositioningDropController {

  private AbsolutePanel dropTarget;

  public AbsolutePositionDropController(AbsolutePanel dropTarget) {
    super(dropTarget);
    this.dropTarget = dropTarget;
  }

  public final void drop(Widget draggable) {
    throw new RuntimeException("single argument drop() not supported");
  }

  public void drop(Widget widget, int left, int top) {
    super.drop(widget, left, top);
    DragController dragController = DragController.getDragController(widget);
    Location location = new Location(dropTarget, dragController.getBoundryPanel());
    dragController.getBoundryPanel().add(widget, location.getLeft() + left, location.getTop() + top);
    constrainedWidgetMove(widget, widget, widget, dragController);
  }

  public String getDropTargetStyleName() {
    return super.getDropTargetStyleName() + " dragdrop-absolute-positioning-drop-target";
  }

  public boolean onDrop(Widget reference, Widget draggable, DragController dragController) {
    boolean result = constrainedWidgetMove(reference, draggable, draggable, dragController);
    super.onDrop(reference, draggable, dragController);
    return result;
  }

  public void onLeave(Widget draggable, DragController dragController) {
    super.onLeave(draggable, dragController);
  }

  public void onMove(Widget reference, Widget draggable, DragController dragController) {
    super.onMove(reference, draggable, dragController);
    constrainedWidgetMove(reference, draggable, getPositioner(), dragController);
  }

  /**
   * If possible, move widget as close to the desired reference location as the
   * constraints of this DropController allow.
   * 
   * @param reference widget whose location is the desired drop location
   * @param draggable TODO
   * @param widget either the positioner or the draggable widget to be moved
   * @param dragController the DragController for this operation
   * @return true if widget was moved successfully
   */
  protected boolean constrainedWidgetMove(Widget reference, Widget draggable, Widget widget, DragController dragController) {
    AbsolutePanel boundryPanel = dragController.getBoundryPanel();
    Area dropArea = new Area(dropTarget, boundryPanel);
    Area draggableArea = new Area(reference, boundryPanel);
    Location location = new Location(reference, dropTarget);
    location.constrain(0, 0, dropArea.getInternalWidth() - draggableArea.getWidth(), dropArea.getInternalHeight()
        - draggableArea.getHeight());
    dropTarget.add(widget, location.getLeft(), location.getTop());
    return true;
  }

}