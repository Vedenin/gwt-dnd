package com.allen_sauer.gwt.dragdrop.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.dragdrop.client.drop.DropController;
import com.allen_sauer.gwt.dragdrop.client.util.Location;

/**
 * Helper class to deal with draggable widget mouse events.
 */
class MouseDragHandler implements MouseListener {

  private AbsolutePanel boundryPanel;
  private transient Widget capturingWidget;
  private DragController dragController;
  private transient Widget draggableProxy;
  private boolean dragging;
  private transient DropController dropController;
  private int initialMouseX;
  private int initialMouseY;

  public MouseDragHandler(DragController dragController) {
    this.dragController = dragController;
    boundryPanel = dragController.getBoundryPanel();
  }

  public void onMouseDown(Widget sender, int x, int y) {
    capturingWidget = sender;
    initialMouseX = x;
    initialMouseY = y;

    try {
      dragController.previewDragStart(capturingWidget);
    } catch (VetoDragException ex) {
      return;
    }
    dragController.dragStart(capturingWidget);

    draggableProxy = dragController.getDraggableOrProxy();

    DOM.setCapture(capturingWidget.getElement());
    dragging = true;
    try {
      move(x, y);
    } catch (RuntimeException ex) {
      cancelDrag();
      throw ex;
    }
  }

  public void onMouseEnter(Widget sender) {
  }

  public void onMouseLeave(Widget sender) {
  }

  public void onMouseMove(Widget sender, int x, int y) {
    if (!dragging) {
      return;
    }
    try {
      move(x, y);
    } catch (RuntimeException ex) {
      cancelDrag();
      throw ex;
    }
  }

  public void onMouseUp(Widget sender, int x, int y) {
    if (!dragging) {
      return;
    }
    try {
      DOM.releaseCapture(capturingWidget.getElement());

      move(x, y);
      dragging = false;

      // Determine the interested DropController at our present location
      DropController newDropController = dragController.getIntersectDropController(draggableProxy);

      // Is the DropController at this location different than the last one?
      if (dropController != newDropController) {
        if (dropController != null) {
          dropController.onLeave(capturingWidget, dragController);
        }
        dropController = newDropController;
      }

      // Is there a DropController willing to handle our request?
      if (dropController == null) {
        cancelDrag();
        return;
      }

      // Does the DragController allow the drop?
      try {
        dragController.previewDragEnd(capturingWidget, dropController.getDropTarget());
      } catch (VetoDragException ex) {
        cancelDrag();
        return;
      }

      // Does the DropController allow the drop?
      if (!dropController.onPreviewDrop(draggableProxy, capturingWidget, dragController)) {
        cancelDrag();
        return;
      }

      dragController.dragEnd(capturingWidget, dropController.getDropTarget());
      dropController.onDrop(draggableProxy, capturingWidget, dragController);
      dragController.notifyDragEnd(capturingWidget, dropController.getDropTarget());

    } catch (RuntimeException ex) {
      // cleanup in case anything goes wrong
      cancelDrag();
      throw ex;
    } finally {
      dropController = null;
    }
  }

  private void cancelDrag() {
    // Do this first so it always happens
    DOM.releaseCapture(capturingWidget.getElement());
    dragging = false;

    if (dropController != null) {
      dropController.onLeave(capturingWidget, dragController);
    }
    dropController = null;

    dragController.dragEnd(capturingWidget, null);
    dragController.notifyDragEnd(capturingWidget, null);
  }

  private void move(int x, int y) {
    Location senderLocation = new Location(capturingWidget, boundryPanel);

    int desiredLeft = (x - initialMouseX) + senderLocation.getLeft();
    int desiredTop = (y - initialMouseY) + senderLocation.getTop();

    boundryPanel.setWidgetPosition(draggableProxy, desiredLeft, desiredTop);

    DropController newDropController = dragController.getIntersectDropController(draggableProxy);
    if (dropController == newDropController) {
      if (dropController != null) {
        dropController.onMove(draggableProxy, capturingWidget, dragController);
      }
    } else {
      if (dropController != null) {
        dropController.onLeave(capturingWidget, dragController);
      }
      dropController = newDropController;
      if (dropController != null) {
        dropController.onEnter(draggableProxy, capturingWidget, dragController);
        dropController.onMove(draggableProxy, capturingWidget, dragController);
      }
    }
  }
}