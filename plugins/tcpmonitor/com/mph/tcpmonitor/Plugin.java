/*
 * Copyright (C) 2002-2003 Stefan Stiller
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.mph.tcpmonitor;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;

import javax.swing.*;
import java.net.URL;

/**
 * @author Mike Hoefer
 */
public class Plugin implements ProjectComponent
{
  private Project _project;
  private ToolWindow _toolWindow;

  public static final String TOOL_WINDOW_ID = "TCPMON";

  public Plugin( Project project )
  {
    _project = project;
  }

  public void projectOpened()
  {
    initToolWindow();
  }

  public void projectClosed()
  {
    unregisterToolWindow();
  }

  public void initComponent()
  {
  }

  public void disposeComponent()
  {
  }

  public String getComponentName()
  {
    return "TCPMON";
  }

  private void initToolWindow()
  {
    ToolWindowManager toolWindowManager = ToolWindowManager.getInstance( _project );
    _toolWindow = toolWindowManager.registerToolWindow(
      TOOL_WINDOW_ID, MainPanel.getInstance( _project ), ToolWindowAnchor.RIGHT );
    URL resource = getClass().getClassLoader().getResource( "images/tcpmonitor.gif" );
    _toolWindow.setIcon( new ImageIcon( resource ) );
  }

  private void unregisterToolWindow()
  {
    ToolWindowManager toolWindowManager = ToolWindowManager.getInstance( _project );
    toolWindowManager.unregisterToolWindow( TOOL_WINDOW_ID );
    MainPanel.removeInstance( _project );
  }
}