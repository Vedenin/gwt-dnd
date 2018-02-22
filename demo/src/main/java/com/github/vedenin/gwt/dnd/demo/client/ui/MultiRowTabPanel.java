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
package com.github.vedenin.gwt.dnd.demo.client.ui;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.HashMap;

public class MultiRowTabPanel extends Composite {

  private static final String CSS_DEMO_MULTI_ROW_TAB_PANEL_BOTTOM = "demo-MultiRowTabPanel-bottom";

  private static final String CSS_DEMO_MULTI_ROW_TAB_PANEL_ROW = "demo-MultiRowTabPanel-row";

  private MultiRowTabHistoryTokens historyTokenMap = new MultiRowTabHistoryTokens();

  private DeckPanel masterDeckPanel;

  private int rows = 0;

  private int selectedRow = -1;

  private HashMap<TabBar, Integer> tabBarIndexOffsetMap = new HashMap<TabBar, Integer>();

  private StylableVerticalPanel tabBarsVerticalPanel;

  private int tabCount;

  private final int tabsPerRow;

  public MultiRowTabPanel(int tabsPerRow) {
    this.tabsPerRow = tabsPerRow;
    VerticalPanel containerPanel = new VerticalPanel();
    initWidget(containerPanel);

    tabBarsVerticalPanel = new StylableVerticalPanel();
    tabBarsVerticalPanel.setWidth("100%");
    masterDeckPanel = new DeckPanel();
    masterDeckPanel.addStyleName(CSS_DEMO_MULTI_ROW_TAB_PANEL_BOTTOM);
    containerPanel.add(tabBarsVerticalPanel);
    containerPanel.add(masterDeckPanel);
    History.addValueChangeHandler(new ValueChangeHandler<String>() {
      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        selectTabByHistoryToken(event.getValue());
      }
    });
  }

  public void add(Widget widget, Label tabLabel, String historyToken, String pageTitle) {
    int row = tabCount / tabsPerRow;
    while (row >= rows) {
      addRow();
    }
    tabCount++;
    masterDeckPanel.add(widget);
    TabBar tabBar = (TabBar) tabBarsVerticalPanel.getWidget(row);
    tabBar.addTab(tabLabel);
    historyTokenMap.add(historyToken, pageTitle);
  }

  public void addTabBarStyleName(String style) {
    tabBarsVerticalPanel.addStyleName(style);
  }

  public int getTabCount() {
    return tabCount;
  }

  public void selectTab(int index) {
    int row = index / tabsPerRow;
    int tabIndex = index % tabsPerRow;
    TabBar tabBar = (TabBar) tabBarsVerticalPanel.getWidget(row);
    tabBar.selectTab(tabIndex);
  }

  public void selectTabByHistoryToken(String historyToken) {
    if ("IndexedPanelExample".equals(historyToken)) {
      historyToken = "InsertPanelExample";
    }
    Integer tabIndex = historyTokenMap.getIndex(historyToken);
    if (tabIndex == null) {
      // select a random example
      tabIndex = Random.nextInt(getTabCount() - 1);
    }
    selectTab(tabIndex);
  }

  private void addRow() {
    TabBar tabBar = new TabBar();
    tabBarsVerticalPanel.add(tabBar);
    tabBar.addSelectionHandler(new SelectionHandler<Integer>() {
      @Override
      public void onSelection(SelectionEvent<Integer> event) {
        int row = tabBarsVerticalPanel.getWidgetIndex((TabBar) event.getSource());
        whenTabSelected(row, event.getSelectedItem());
      }
    });
    tabBarIndexOffsetMap.put(tabBar, Integer.valueOf(tabCount));
    tabBarsVerticalPanel.setCellStyleName(tabBar, CSS_DEMO_MULTI_ROW_TAB_PANEL_ROW);

    rows++;
  }

  private void whenTabSelected(int row, int tabIndex) {
    if (tabIndex == -1) {
      return;
    }
    if (row != selectedRow) {
      selectedRow = row;
      for (int i = 0; i < rows; i++) {
        if (i != row) {
          TabBar tabBar = (TabBar) tabBarsVerticalPanel.getWidget(i);
          tabBar.selectTab(-1);
        }
      }
    }
    TabBar tabBar = (TabBar) tabBarsVerticalPanel.getWidget(selectedRow);
    Integer widgetOffset = tabBarIndexOffsetMap.get(tabBar);
    int index = widgetOffset.intValue() + tabIndex;
    masterDeckPanel.showWidget(index);
    History.newItem(historyTokenMap.getHistoryToken(index));
    Window.setTitle(historyTokenMap.getPageTitle(historyTokenMap.getHistoryToken(index)));
  }
}
