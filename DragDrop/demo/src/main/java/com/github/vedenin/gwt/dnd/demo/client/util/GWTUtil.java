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
package com.github.vedenin.gwt.dnd.demo.client.util;

import com.github.vedenin.gwt.dnd.demo.client.DragDropDemo;

/**
 * Shared utility methods for examples.
 */
public class GWTUtil {

  /**
   * Base java package for demos (from {@link DragDropDemo}) for demo client code, used by {@link #getClassAnchorHTML(Class)}.
   */
  public static final String DEMO_CLIENT_PACKAGE = GWTUtil.getPackageName(DragDropDemo.class);

  /**
   * Base Subversion URL ({@value}) for DragDrop project.
   */
  private static final String GIT_BROWSE_HEAD = "https://github.com/fredsa/gwt_dnd/tree/master/DragDrop/";

  /**
   * Determine Subversion URL for provided class literal, to be used in HTML anchors.
   *
   * @param clazz a class literal
   * @return the Subversion URL
   */
  public static String getClassAnchorHTML(Class<?> clazz) {
    String className = getClassName(clazz);
    String url = GIT_BROWSE_HEAD;
    if (className.startsWith(DEMO_CLIENT_PACKAGE)) {
      url += "demo/";
    } else {
      url += "src/";
    }
    url += className.replace('.', '/') + ".java";
    String baseName = className.substring(className.lastIndexOf('.') + 1);
    return "<code><a target='_blank' href='" + url + "'>" + baseName + ".java</a></code>";
  }

  /**
   * Determine the java package name for the provided class literal.
   *
   * @param clazz the class literal
   * @return the java package name
   */
  public static String getPackageName(Class<?> clazz) {
    String className = getClassName(clazz);
    return className.substring(0, className.lastIndexOf('.'));
  }

  private static String getClassName(Class<?> clazz) {
    String className = clazz.toString();
    className = className.replaceFirst(".* ", "");
    return className;
  }
}
