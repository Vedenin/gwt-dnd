/*
 * Copyright 2007 Fred Sauer
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
package com.allen_sauer.gwt.dragdrop.demo.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.allen_sauer.gwt.dragdrop.demo.client.example.Example;

/**
 * TabPanel which uses a VeriticalPanel to provide a description for each
 * example.
 */
public class ExampleTabPanel extends TabPanel {

  private static final String STYLE_DEMO_EXAMPLE_DESCRIPTION = "demo-example-description";

  /**
   * Describe an example in a consistent way by including a description and the name of
   * the {@link com.allen_sauer.gwt.dragdrop.client.drop.DropController} used in the example.
   * 
   * @param controllerClass the primary DropController used in this example
   * @param description a brief description of the example
   * @return HTML widget describing the example
   */
  public static HTML describe(Class controllerClass, String description) {
    String controllerClassName = controllerClass.toString();
    controllerClassName = controllerClassName.substring(controllerClassName.lastIndexOf('.') + 1);
    HTML html = new HTML("<code>" + controllerClassName + "</code><br>\n" + "<i>" + description + "</i>");
    html.addStyleName(STYLE_DEMO_EXAMPLE_DESCRIPTION);
    return html;
  }

  private int counter;

  /**
   * Add another example to demonstrate.
   * 
   * @param example the example panel to add
   */
  public void add(Example example) {
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.add(describe(example.getControllerClass(), example.getDescription()));
    verticalPanel.add(example);
    add(verticalPanel, "Example " + ++counter, true);
  }
}