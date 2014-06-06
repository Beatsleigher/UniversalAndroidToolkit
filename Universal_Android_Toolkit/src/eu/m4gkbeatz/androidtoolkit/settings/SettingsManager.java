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

package eu.m4gkbeatz.androidtoolkit.settings;

import eu.m4gkbeatz.androidtoolkit.*;
import eu.m4gkbeatz.androidtoolkit.language.*;
import eu.m4gkbeatz.androidtoolkit.logging.Logger;

import java.io.*;

/**
 * SettingsManager - Manages and maintains all preferences used in and by Universal Android Toolkit.
 * Provides getter- and setter-methods to get and set all the information needed and used by UAT.
 * All preferences start off with default values, and can be changed by the settings UI.
 * @author beatsleigher
 * @since 1.0
 * @version 1.0
 */
public final class SettingsManager {
    
    //# =============== Overridden Methods =============== #\\
    @Override
    public String toString() {
        return 
                  "### Universal Android Toolkit Preferences ###\n"
                + "###                                         #\n"
                + "### These are the user-configurable settings#\n"
                + "### used by Universal Android Toolkit.      #\n"
                + "### You may alter these settings any way you#\n"
                + "### want, however, you will receive no      #\n"
                + "### support.                                #\n"
                + "###                                         #\n"
                + "### ========== Index ==========             #\n"
                + "### [#] → Comment. When at the beginning of #\n"
                + "###       a line, the line will be ignored. #\n"
                + "###                                         #\n"
                + "### [pref::(data=type){] → Preference       #\n"
                + "###       block. Contains all preferences   #\n"
                + "###       a specific data-type.             #\n"
                + "###                                         #\n"
                + "### [info::{] → Info-block. Contains        #\n"
                + "###       information about the program and/#\n"
                + "###       or the file(s).                   #\n"
                + "###                                         #\n"
                + "### [sys::{] → System-block. Contains       #\n"
                + "###       information about the system.     #\n"
                + "###       This information is anonymous and #\n"
                + "###       will stay that way. It will in no #\n"
                + "###       way be used to identify you.      #\n"
                + "###                                         #\n"
                + "### [}] → Block-end. Ends a block.          #\n"
                + "###                                         #\n"
                + "### [MD5] → MD5 hash.                       #\n"
                + "### ==========/Index ==========             #\n"
                + "###                                         #\n"
                + "### ========== Disclaimer ==========        #\n"
                + "### By using this program, you agree to the #\n"
                + "### terms of the GNU GPL version 3 or later.#\n"
                + "### The use of this program may cause damage#\n"
                + "### to devices used with it.                #\n"
                + "### Neither I, Beatsleigher, or any other   #\n"
                + "### entity can be held liable for any       #\n"
                + "### damage caused by the use of this program#\n"
                + "### or the libraries included with it.      #\n"
                + "### If you do not agree to these terms, you #\n"
                + "### must delete any and all copies of it.   #\n"
                + "### Should you fail to do so, Beatsleigher  #\n"
                + "### has the ultimate right to remove it from#\n"
                + "### your computer personally, physically or #\n"
                + "### virtually.                              #\n"
                + "### ==========/Disclaimer ==========        #\n"
                + "###                                         #\n"
                + "### Universal Android Toolkit Preferences ###\n\n"
                //
                + "#####################################################################################################\n"
                + "# Current file location:                                                                            #\n"
                + "# " + prefsFile.getAbsolutePath() + "\t\t#\n"
                + "#####################################################################################################\n\n"
                //
                + "# Preference block 01.\n"
                + "# Datatype: boolean\n"
                + "pref::(data=boolean){\n"
                + "\tpref::[name=refreshDevices, value=" + refreshDevices + "];\n"
                + "\tpref::[name=checkForUpdatesOnStartup, value=" + checkForUpdatesOnStartup + "];\n"
                + "\tpref::[name=autoUpdate, value=" + autoUpdate + "];\n"
                + "\tpref::[name=sendLogs, value=" + sendLogs + "];\n"
                + "\tpref::[name=showLog, value=" + showLog + "];\n"
                + "}\n\n"
                //
                + "# Preference block 02.\n"
                + "# Datatype: int\n"
                + "pref::(data=int){\n"
                + "\tpref::[name=deviceRefreshInterval, value=" + deviceRefreshInterval + "];\n"
                + "\t# ↓DON'T TOUCH THIS UNLESS YOU KNOW WHAT YOU'RE DOING!↓\n"
                + "\tpref::[name=gcInterval, value=" + gcInterval + "];\n"
                + "\tpref::[name=infoRefreshInterval, value=" + infoRefreshInterval + "];\n"
                + "}\n\n"
                //
                + "# Preference block 03.\n"
                + "# Datatype: String\n"
                + "pref::(data=String){\n"
                + "\tpref::[name=lookAndFeel, value=" + lookAndFeel + "];\n"
                + "\tpref::[name=language, value=" + language + "];\n"
                + "}\n\n"
                //
                + "# Information block.\n"
                + "info::{\n"
                + "\tUAT-Ver:\n"
                + "\t" + Main.VERSION + "\n"
                + "}\n\n"
                //
                + "# System information block.\n"
                + "sys::{\n"
                + "\tOperating System: " + System.getProperty("os.name") + "\n"
                + "\tOperating System Arch: " + System.getProperty("os.arch") + "\n"
                + "\tOperating System Version: " + System.getProperty("os.version") + "\n"
                + "}\n\n"
                //
                + "";
    }
    
    //# =============== Variables =============== #\\
    private static boolean refreshDevices = true;
    private static int deviceRefreshInterval = 30000; // Seconds between device refreshes (default: 30)
    private static boolean checkForUpdatesOnStartup = true;
    private static boolean autoUpdate = false;
    private static String lookAndFeel = "Nimbus";
    private static boolean sendLogs = true;
    private static Language language = Language.en_gb;
    private static boolean showLog = true;
    private static int gcInterval = 60000;
    private static int infoRefreshInterval = 800;
    /*Misc. Vars*/
    private boolean debug = false;
    private File prefsFile = null;
    private Logger logger = null;
    private Logger.Level level;
    
    //# =============== Constructor =============== #\\
    public SettingsManager(boolean debug, boolean ignore, Logger logger, String laf) throws IOException {
        this.debug = debug;
        prefsFile = new File(System.getProperty("user.home") + "/.androidtoolkit/prefs/settings.cfg");
        this.logger = logger;
        if (!ignore)
            loadSettings();
    }
    
    //# =============== Loading Method =============== #\\
    public void loadSettings() throws IOException {
        BufferedReader reader = null;
        String line = "";
        
        if (debug)
            logger.log(level.DEBUG, "Loading preferences. Checking if settings file exists...");
        if (!prefsFile.exists()) {
            if (debug)
                logger.log(level.DEBUG, "Settings file does not exist. Saving default values...");
            saveSettings();
        }
        if (debug)
            logger.log(level.DEBUG, "Loading preferences. Please be patient.");
        
        reader = new BufferedReader(new FileReader(prefsFile));
        
        while ((line = reader.readLine()) != null) {
            // Continue on comment, break on end of file operator
            if (line.startsWith("#")) continue;
            if (line.equals("### EOF ###")) { if (debug) logger.log(level.DEBUG, "Found EOF operator. Breaking load-loop!"); break; }
            // Load preferences
            // Load booleans first
            if (line.equals("pref::(data=boolean){")) {
                //<editor-fold defaultstate="collapsed" desc="">
                if (debug)
                    logger.log(level.DEBUG, "Loading boolean-value preferences...");
                do {
                    if (line.contains("name=refreshDevices") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        refreshDevices = Boolean.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: refreshDevices == " + arr[0]);
                    }
                    if (line.contains("name=checkForUpdatesOnStartup") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        checkForUpdatesOnStartup = Boolean.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: checkForUpdateOnStartup == " + arr[0]);
                    }
                    if (line.contains("name=autoUpdate") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        autoUpdate = Boolean.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: autoUpdate == " + arr[0]);
                    }
                    if (line.contains("name=sendLogs") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        sendLogs = Boolean.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: sendLogs == " + arr[0]);
                    }
                    if (line.contains("name=showLog") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        showLog = Boolean.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: showLog == " + arr[0]);
                    }
                    line = reader.readLine();
                    
                } while (!line.equals("}"));
                //</editor-fold>
            }
            if (line.equals("pref::(data=int){")) {
                //<editor-fold defaultstate="collapsed" desc="">
                if (debug)
                    logger.log(level.DEBUG, "Loading integer-value preferences...");
                do {
                    if (line.contains("name=deviceRefreshInterval") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        deviceRefreshInterval = Integer.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: deviceRefreshInterval == " + arr[0]);
                    }
                    if (line.contains("name=gcInterval") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        gcInterval = Integer.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: gcInterval == " + arr[0]);
                    }
                    line = reader.readLine();
                    
                } while (!line.equals("}"));
                //</editor-fold>
            }
            
            if (line.equals("pref::(data=String){")) {
                //<editor-fold defaultstate="collapsed" desc="">
                if (debug)
                    logger.log(level.DEBUG, "Loading String-value preferences...");
                do {
                    if (line.contains("name=lookAndFeel") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        lookAndFeel = arr[0];
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: lookAndFeel == " + arr[0]);
                    }
                    if (line.contains("name=language") && line.endsWith(";")) {
                        String[] arr = line.split("value="); arr = arr[1].split("]");
                        language = Language.valueOf(arr[0]);
                        if (debug)
                            logger.log(level.DEBUG, "Found pref: language == " + arr[0]);
                    }
                    line = reader.readLine();
                    
                } while (!line.equals("}"));
                //</editor-fold>
            }
            
        }
    }
    
    //# =============== Saving Method =============== #\\
    public void saveSettings() throws IOException {
        BufferedWriter writer = null;
        if (!prefsFile.exists()) {
            prefsFile.getParentFile().mkdirs();
            prefsFile.createNewFile();
            for (int i = 0; i < 200; i++) {} // Occupy fs for a short amount of time (Windows)
        }
        
        writer = new BufferedWriter(new FileWriter(prefsFile));
        writer.write(toString());
        writer.flush();
        writer.close();
        
    }
    
    //# =============== Getter Methods =============== #\\
    public boolean refreshDevices() { return refreshDevices; }
    
    public boolean checkForUpdatesOnStartup() { return checkForUpdatesOnStartup; }
    
    public boolean autoUpdate() { return autoUpdate; }
    
    public boolean sendLogs() { return sendLogs; }
    
    public boolean showLog() { return showLog; }
    
    public int getDeviceRefreshInterval() { return deviceRefreshInterval; }
    
    public String getLookAndFeel() { return lookAndFeel; }    
    
    public Language getLanguage() { return language; }
    
    public int getGCInterval() { return gcInterval; }
    
    public int getInfoRefreshInterval() { return infoRefreshInterval; }
    
    //# =============== Setter Methods =============== #\\
    public void setRefreshDevices(boolean val) { refreshDevices = val; }
    
    public void setCheckForUpdatesOnStartup(boolean val) { checkForUpdatesOnStartup = val; }
    
    public void setAutoUpdate(boolean val) { autoUpdate = val; }
    
    public void setSendLogs(boolean val) { sendLogs = val; }
    
    public void setShowLog(boolean val) { showLog = val; }
    
    public void setDeviceRefreshInterval(int val) { deviceRefreshInterval = val; }
    
    public void setLookAndFeel(String val) { lookAndFeel = val; }
    
    public void setLanguage(String lang) { language = Language.valueOf(lang); }
    
    public void setGCInterval(int int_) { gcInterval = int_; }
    
    public void setInfoRefreshInterval(int val) { infoRefreshInterval = val; }
    
}
