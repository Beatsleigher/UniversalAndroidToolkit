/*
 * Copyright (C) 2014 beatsleigher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package eu.m4gkbeatz.androidtoolkit.ui.context;

import JDroidLib.android.controllers.ADBController;
import JDroidLib.android.device.Device;

import eu.m4gkbeatz.androidtoolkit.language.LangFileParser;
import eu.m4gkbeatz.androidtoolkit.logging.Logger;
import static eu.m4gkbeatz.androidtoolkit.logging.Logger.Level;

import javax.swing.*;
import javax.swing.table.TableModel;


/**
 *
 * @author beatsleigher
 */
public class TaskManagerContext extends JPopupMenu {
    
    private final Device device;
    private final ADBController adbController;
    private final boolean debug;
    private final LangFileParser parser;
    private final Logger logger;
    private final TableModel table;
    
    public TaskManagerContext(Device device, ADBController adbController, boolean debug, LangFileParser parser, Logger logger, TableModel table) {
        this.device = device;
        this.adbController = adbController;
        this.debug =  debug;
        this.parser = parser;
        this.logger = logger;
        this.table = table;
        // Add items
        add(parser.parse("moreUI:context:killProcess"));
        add(parser.parse("moreUI:context:killAllProcesses"));
    }
    
}
