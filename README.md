This project is fork of [this project](https://github.com/fredsa/gwt-dnd), that is not actively developed or maintained. In this project are fixed some bug, use the latest GWT version (2.8.2) and new maven build system. And this project will be actively developed and maintained. 

# Summary #
Library providing easy to use mouse or touch (for mobile devices) based drag-and-drop capabilities to [Google Web Toolkit](http://www.gwtproject.org/) (GWT) projects.

# Download #
You can find artifacts to [Maven central](https://search.maven.org/#artifactdetails%7Ccom.github.vedenin%7Cgwt_dnd%7C1.0%7Cjar), just add 

    <dependency>
      <groupId>com.github.vedenin</groupId>
      <artifactId>gwt_dnd</artifactId>
      <version>1.0</version>
      <classifier>sources</classifier>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>com.github.vedenin</groupId>
      <artifactId>gwt_dnd</artifactId>
      <version>1.0</version>
    </dependency>

To you dependency in Maven (or the same dependency to gradle, ivi and so on, of just download [jar file (gwt_dnd-1.0.jar)](https://repo1.maven.org/maven2/com/github/vedenin/gwt_dnd/1.0/)) and add to your project.

# Working examples #
Try the [working demo](https://gwtdnd.herokuapp.com/#InsertPanelExample)

# Getting started with your own drag-and-drop projects #
Read the wiki here: https://github.com/fredsa/gwt-dnd/wiki/GettingStarted

# Features #
  * **Drag-and-Drop** - classic drag/drop operations for your existing widgets and panels
  * **Drag-and-Move** - allows user to rearrange widgets within a Panel
  * **Non-invasive** - No need to extend or implement special library classes or interfaces; just use your existing widgets and panels
    * Any Widget that implements `SourcesMouseEvents` is draggable. For today's version of GWT that means `FocusPanel`, `HTML`, `Image` and `Label` are immediately draggable. Listening for mouse events on other widgets is relatively straight forward.
    * Any `Panel` can become a drop target. If you need absolute positioning on the drop target, use `AbsolutePanel`.
  * **Quirks mode** and **Strict mode** fully support
  * **Inline** and **Block** elements are supported for dragging and as drop targets
  * `AbsolutePanel`, `IndexedPanel`, `FlowPanel` and `FlexTable` drop targets
  * **Drag Handles** - grab hold of small part of a larger widget
  * **Drag Proxies** - Leave the original widget in place while you drag a proxy widget around
  * **Veto Capability** - Prevent certain operations from happening, causing the draggable to snap back to its original location
  * `EventListener` for drag-and-drop events via `DragHandler` interface
  * **Many examples** with source code


# OOTB (Out of the Box) provided drag-and-drop or drag-and-move behaviors #
| `DropController`                 | **Description** | **Example Use** |
|:---------------------------------|:----------------|:----------------|
| `AbsolutePositionDropController` | Drag-and-Move widgets around an `AbsolutePanel`. | Moving drawing elements around on a flow chart. |
| `AbstractDropController`         | Create your own controllers from this base class. | Anything you can dream up. |
| `BoundaryDropController`          | All drag operations are ultimately constrained by a panel you specify. By default this panel is `RootPanel.get()` which means you can drag widgets over the entire page. | For use as part of the gwt-dnd implementation. |
| `FlexTableRowDropController`     | Rearrange rows in a `FlexTable`. | Users rearrange results of a query. |
| `FlowPanelDropController`       | Drop controller for instances of `FlowPanel`. | Moving elements around in flowed text. |
| `HorizontalPanelDropController` | Drop controller for instances of `HorizontalPanel`. | Moving widgets in a horizontal list. |
| `GridConstrainedDropController`  | Similar to `AbsolutePositionDropController`, but constrains the position of the draggable widgets to a specified grid. | Allows for 'snap to grid' functionality. |
| `SimpleDropController`           | For simple drop targets which allows a widget to be dropped on them. | A trash can icon. |
| `VerticalPanelDropController` | Drop controller for instances of `VerticalPanel`. | Moving widgets in a vertical list. |


# Feedback #
Please let me know what you think. Suggestions are always welcome.
