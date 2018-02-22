package com.github.vedenin.gwt.dnd.demo.client.example.clicktouch;

import com.github.vedenin.gwt.dnd.demo.client.DemoDragHandler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Button;

public class ClickTouchButton extends Button {

  private int leftClickCount = 0;
  private int middleClickCount = 0;
  private int rightClickCount = 0;
  private int doubleClickCount = 0;
  private DemoDragHandler demoDragHandler;

  public ClickTouchButton(DemoDragHandler demoDragHandler) {
    super("Draggable button");
    this.demoDragHandler = demoDragHandler;

    addDoubleClickHandler(new DoubleClickHandler() {
      @Override
      public void onDoubleClick(DoubleClickEvent event) {
        doubleClickCount++;
        setText("Double click count = " + doubleClickCount);
      }
    });

    addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        int nativeButton = event.getNativeButton();
        switch (nativeButton) {
          case NativeEvent.BUTTON_LEFT:
            leftClickCount++;
            setText("LEFT click count = " + leftClickCount);
            break;
          case NativeEvent.BUTTON_MIDDLE:
            middleClickCount++;
            setText("MIDDLE click count = " + middleClickCount);
            break;
          case NativeEvent.BUTTON_RIGHT:
            rightClickCount++;
            setText("RIGHT click count = " + rightClickCount);
            break;
          default:
            setText("Unknown button clicked");
        }
      }
    });

    addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        setText("onMouseDown()");
      }
    });

    addMouseUpHandler(new MouseUpHandler() {
      @Override
      public void onMouseUp(MouseUpEvent event) {
        setText("onMouseUp()");
      }
    });

    addMouseOverHandler(new MouseOverHandler() {
      @Override
      public void onMouseOver(MouseOverEvent event) {
        setText("onMouseOver()");
      }
    });

    addMouseOutHandler(new MouseOutHandler() {
      @Override
      public void onMouseOut(MouseOutEvent event) {
        setText("onMouseOut()");
      }
    });

    addTouchStartHandler(new TouchStartHandler() {
      @Override
      public void onTouchStart(TouchStartEvent event) {
        setText("onTouchStart()");
      }
    });

    addTouchEndHandler(new TouchEndHandler() {
      @Override
      public void onTouchEnd(TouchEndEvent event) {
        setText("onTouchEnd()");
      }
    });

    addTouchMoveHandler(new TouchMoveHandler() {
      @Override
      public void onTouchMove(TouchMoveEvent event) {
        setText("onTouchMove()");
      }
    });

    addTouchCancelHandler(new TouchCancelHandler() {
      @Override
      public void onTouchCancel(TouchCancelEvent event) {
        setText("onTouchCancel()");
      }
    });
  }

  @Override
  public void setText(String text) {
    demoDragHandler.log(text, DemoDragHandler.BLUE);
    super.setText(text);
  }
}
