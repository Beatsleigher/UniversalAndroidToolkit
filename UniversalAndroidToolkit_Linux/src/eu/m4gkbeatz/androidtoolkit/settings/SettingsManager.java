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

import java.io.*;

/**
 * SettingsManager.
 * The Universal Android Toolkit settings manager manages all the IO of any and all preferences used by UAT.
 * The values are saved to the class via the getter- and setter methods and the actual save is then triggered by the save method.
 * @author beatsleigher
 */
@SuppressWarnings({"FieldMayBeFinal"})
public class SettingsManager {
    
    private File file = null;
    private BufferedReader fileReader = null, stringReader = null;
    private BufferedWriter fileWriter = null;
    
    private boolean getUpdates = true;
    private boolean checkForDevicesOnStartup = true;
    private boolean autoLoadDeviceInfo = true;
    private boolean saveLogs = true;
    private boolean useAdvancedUI = false;
    
    private void save() throws IOException {
        fileWriter = new BufferedWriter(new FileWriter(file));
        String settings = 
        //<editor-fold defaultstate="collapsed" desc="Da String">
                  "### Universal Android Toolkit Settings File ###\n"
                + "# Index:\n"
                + "# # => Comment: Used for disabling settings, allowing them to stay at the defaults.\n"
                + "# \t Will also be used to mark settings, their use and development stage.\n"
                + "# pref:: => Preference marker: Marks settings. Any line starting with this, will be attempted to read.\n"
                + "# ### EOF ### => End of file: Used for breaking any loops and exiting the settings file.\n"
                + "# \t Should you see any lines behind this line, then these may be development settings, and may be experimental.\n"
                + "# \t Furthermore, by enabling these settings, you will NOT receive any support. Even when enabled inside the program.\n"
                + "### Universal Android Toolkit Settings File ###\n\n"
                // This is not the line you're looking for...
                + "########################################################################################\n"
                + "# Current file location:" + file + "#\n"
                + "# This will not impact any settings and will be reset after every new save.            #\n"
                + "########################################################################################\n\n"
                // This isn't either...
                + "#################################\n"
                + "# Preference \"getUpdates\"       #\n"
                + "# If true, the program will     #\n"
                + "# check for updates, regardless #\n"
                + "# of what the program's doing   #\n"
                + "# at runtime. This will be done #\n"
                + "# in a background thread.       #\n"
                + "#################################\n"
                + "pref::[name=getUpdates, value=" + getUpdates + "]\n\n"
                // Nope. Still isn't...
                + "##################################################\n"
                + "# Preference \"checkForDevicesOnStartup\"          #\n"
                + "# If true, the program will not wait for the     #\n"
                + "# to give instruction to search for devices.     #\n"
                + "# This will run in a background thread, so the   #\n"
                + "# user and his work will not be disturbed.       #\n"
                + "##################################################\n"
                + "pref::[name=checkForDevicesOnStartup, value=" + checkForDevicesOnStartup + "]\n\n"
                // I give up. You just won't learn...
                + "########################################\n"
                + "# Preference \"autoLoadDeviceInfo\"      #\n"
                + "# If true, the program will            #\n"
                + "# automatically download the device's  #\n"
                + "# information and process it           #\n"
                + "# accordingly.                         #\n"
                + "########################################\n"
                + "pref::[name=autoLoadDeviceInfo, value=" + autoLoadDeviceInfo + "]\n\n"
                //
                + "###################################\n"
                + "# Preference \"useAdvancedUI\"      #\n"
                + "# If true, the program will load  #\n"
                + "# the advanced view/UI.           #\n"
                + "# This view/UI allows for         #\n"
                + "# advanced information and        #\n"
                + "# tools to be used.               #\n"
                + "###################################\n"
                + "pref::[name=useAdvancedUI, value=" + useAdvancedUI + "]\n\n"
                //
                + "### EOF ###\n\n"
                //
                + "##############################\n"
                + "# Preference \"saveLogs\"      #\n"
                + "# If true, all logs created  #\n"
                + "# by the program, will be    #\n"
                + "# saved.                     #\n"
                + "# This setting is disabled,  #\n"
                + "# and set to its default,    #\n"
                + "# true, and will be          #\n"
                + "# re-enabled when this       #\n"
                + "# program reaches rc.        #\n"
                + "# Your data will not be      #\n"
                + "# saved.                     #\n"
                + "##############################\n"
                + "pref::[name=saveLogs, value=" + saveLogs + "]\n\n";
        //</editor-fold>
        fileWriter.write(settings);
        fileWriter.close();
    }
    
    public SettingsManager() throws IOException {
        file = new File(System.getProperty("user.home") + "/.androidtoolkit/prefs/settings.bin");
        load();
    }
    
    private void load() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            save();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
        }
        fileReader = new BufferedReader(new FileReader(file));
        @SuppressWarnings("UnusedAssignment")
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("### EOF ###")) break;
            if (line.startsWith("#")) continue;
            if (line.startsWith("pref::")) {
                if (line.contains("name=getUpdates")) {
                    System.out.print("Found pref: getUpdates");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    getUpdates = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }
                if (line.contains("name=checkForDevicesOnStartup")) {
                    System.out.print("Found pref: checkForDevicesOnStartup");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    checkForDevicesOnStartup = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }
                if (line.contains("name=autoLoadDeviceInfo")) {
                    System.out.print("Found pref: autoLoadDeviceInfo");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    autoLoadDeviceInfo = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + autoLoadDeviceInfo);
                    continue;
                }
                if (line.contains("name=savelogs")) {
                    System.out.print("Found pref: saveLogs");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    autoLoadDeviceInfo = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }
                if (line.contains("name=useAdvancedUI")) {
                    System.out.print("Found pref: useAdvancedUI");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    useAdvancedUI = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }
                
            }
        }
    }
    
    /**
     * Loads settings and reads values into respective variables.
     * Makes use of @see #code load()
     * @throws IOException 
     */
    public void loadSettings() throws IOException {
        load();
    }
    
    /**
     * Saves settings read from variables and writes these into file.
     * Makes use of @see #code save()
     * @throws IOException 
     */
    public void saveSettings() throws IOException {
        save();
    }
    
    /**
     * Determines whether the program should download updates automatically.
     * @return  true if yes, false if not.
     */
    public boolean getUpdates() { return getUpdates; }
    
    /**
     * Determines whether the program should check for devices when UI has loaded.
     * @return true if yes, false if not.
     */
    public boolean checkForDevicesOnStartup() { return checkForDevicesOnStartup; }
    
    /**
     * Determines whether the program should automatically download device information form device.
     * @return true if yes, false if not.
     */
    public boolean autoLoadDeviceInfo() { return autoLoadDeviceInfo; }
    
    /**
     * Determines whether the program should automatically save logs to a log file or not.
     * @return true if yes, false if not,.
     */
    public boolean saveLogs() { return saveLogs; }
    
    /**
     * Determine whether the program should load the advanced UI over the simple UI.
     * @return true if yes, false if not.
     */
    public boolean useAdvancedUI() { return useAdvancedUI; }
    
    /**
     * Sets the getUpdates variable.
     * If set to <i>true</i>, then the program will check for updates, before loading the UI.
     * The updates will then be shown by a JOptionPane, which will then trigger the downloader method to download the file.
     * @param bool 
     */
    public void setGetUpdates(boolean bool) { this.getUpdates = bool; }
    
    /**
     * Sets the variable checkForDevicesOnStartup.
     * If set to <i>true</i>, then the loaded UI will check for any and all connected devices and will display them in a JList.
     * @param bool 
     */
    public void setCheckForDevicesOnStartup(boolean bool) { this.checkForDevicesOnStartup = bool; }
    
    /**
     * Sets the variable autoLoadDeviceInfo.
     * If set to <i>true</i>, then the loaded UI will load the first device's information immediately..
     * @param bool 
     */
    public void setAutoLoadDeviceInfo(boolean bool) { this.autoLoadDeviceInfo = bool; }
    
    /**
     * Sets the variable saveLogs.
     * If set to <i>true</i>, then the program will automatically save any and all created logs to a log file.
     * This preference is locked until the RC state.
     * @param bool 
     */
    public void setSaveLogs(boolean bool) { this.saveLogs = bool; }
    
    /**
     * Sets the variable advancedUI.
     * If set to <i>true</i>, the program will load the advanced UI on startup, instead of the simple UI.
     * Using the advanced UI is only recommended, when using advanced commands, and getting/modifying sensitive device information, such as build props.
     * @param bool 
     */
    public void setUseAdvancedUI(boolean bool) { this.useAdvancedUI = bool; }
    
}
