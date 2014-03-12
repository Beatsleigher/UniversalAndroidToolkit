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

import eu.m4gkbeatz.androidtoolkit.enums.*;

import java.io.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * SettingsManager. The Universal Android Toolkit settings manager manages all
 * the IO of any and all preferences used by UAT. The values are saved to the
 * class via the getter- and setter methods and the actual save is then
 * triggered by the save method.
 *
 * @author beatsleigher
 */
@SuppressWarnings({"FieldMayBeFinal", "UnnecessaryContinue"})
public class SettingsManager {

    /*Look and Feel Constants*/
    private final String nimbus = "Nimbus";
    private final String metal = "Metal";
    private final String system = "System";
    private final String windoze = "Windows";
    private final String gtk_plus = "GTK+";

    // Variables used in class.
    private File file = null;
    private BufferedReader fileReader = null;
    private BufferedWriter fileWriter = null;

    // Actual settings variables.
    private boolean getUpdates = true;
    private boolean checkForDevicesOnStartup = true;
    private boolean autoLoadDeviceInfo = true;
    private boolean saveLogs = true;
    private boolean useAdvancedUI = false;
    private String lookAndFeel = "Nimbus";
    private Language language = Language.ENGLISH_UK;
    private boolean zipEfs = false;
    
    private void save() throws IOException {
        fileWriter = new BufferedWriter(new FileWriter(file));
        String settings
                = //<editor-fold defaultstate="collapsed" desc="Da String">
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
                + "###################################\n"
                + "# Preference \"lookAndFeel\"      #\n"
                + "# This controls the way Universal #\n"
                + "# Android Toolkit looks and       #\n"
                + "# behaves. Please use the provided#\n"
                + "# tools to change this setting.   #\n"
                + "###################################\n"
                + "pref::[name=lookAndFeel, value=" + lookAndFeel + "]\n\n"
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
                + "pref::[name=saveLogs, value=" + saveLogs + "]\n\n"
                //
                + "##############################\n"
                + "# Preference \"language\"    #\n"
                + "# This setting will control  #\n"
                + "# the language used in the UI#\n"
                + "# of Universal Android       #\n"
                + "# Toolkit. This preference   #\n"
                + "# is disabled until RC.      #\n"
                + "##############################\n"
                + "pref::[name=language, value=" + language + "]\n\n"
                //
                + "##############################\n"
                + "# Preference \"zipEfs\"      #\n"
                + "# If true, UAT will compress #\n"
                + "# all created EFS-backups.   #\n"
                + "# These images will NOT be   #\n"
                + "# CWM- or TWRP-flashable!    #\n"
                + "##############################\n"
                + "pref::[name=zipEfs, value=" + zipEfs + "]\n\n"
                //
                + "### EOF ###";
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
            } catch (InterruptedException ex) {
            }
        }
        fileReader = new BufferedReader(new FileReader(file));
        @SuppressWarnings("UnusedAssignment")
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("### EOF ###")) {
                break;
            }
            if (line.startsWith("#")) {
                continue;
            }
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
                if (line.contains("name=lookAndFeel")) {
                    System.out.print("Found pref: lookAndFeel");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    lookAndFeel = arr0[0];
                    System.out.println(" = " + lookAndFeel);
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (lookAndFeel.equals(info.getName())) {
                            try {
                                UIManager.setLookAndFeel(info.getClassName());
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                                Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, null, "ERROR: Error setting requested LAF!\n" + ex);
                            }
                            break;
                        }
                    }
                    continue;
                }
                if (line.contains("name=language")) {
                    System.out.print("Found pref: language");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    language = Language.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }
                if (line.contains("name=zipEfs")) {
                    System.out.print("Found pref: zipEfs");
                    String[] arr = line.split("value="), arr0 = arr[1].split("]");
                    zipEfs = Boolean.valueOf(arr0[0]);
                    System.out.println(" = " + arr0[0]);
                    continue;
                }

            }
        }
    }

    /**
     * Loads settings and reads values into respective variables. Makes use of
     * @see #code load()
     *
     * @throws IOException
     */
    public void loadSettings() throws IOException { load(); }

    /**
     * Saves settings read from variables and writes these into file. Makes use
     * of @see #code save()
     *
     * @throws IOException
     */
    public void saveSettings() throws IOException { save(); }

    /**
     * Determines whether the program should download updates automatically.
     *
     * @return true if yes, false if not.
     */
    public boolean getUpdates() { return getUpdates; }

    /**
     * Determines whether the program should check for devices when UI has
     * loaded.
     *
     * @return true if yes, false if not.
     */
    public boolean checkForDevicesOnStartup() { return checkForDevicesOnStartup; }

    /**
     * Determines whether the program should automatically download device
     * information form device.
     *
     * @return true if yes, false if not.
     */
    public boolean autoLoadDeviceInfo() { return autoLoadDeviceInfo; }

    /**
     * Determines whether the program should automatically save logs to a log
     * file or not.
     *
     * @return true if yes, false if not,.
     */
    public boolean saveLogs() { return saveLogs; }

    /**
     * Determine whether the program should load the advanced UI over the simple
     * UI.
     *
     * @return true if yes, false if not.
     */
    public boolean useAdvancedUI() { return useAdvancedUI; }
    
    /**
     * Gets the look and feel used by Universal Android Toolkit and
     * @return the LAF as string.
     */
    public String getLookAndFeel() { return lookAndFeel; }
    
    /**
     * The language used in the UI-portion of Universal Android Toolkit.
     * @return the language chosen.
     */
    public Language getLang() { return language; }
    
    /**
     * This setting controls the behavior when a Samsung EFS-partition is 
     * backed up. 
     * @return true, if EFS backups should be zipped. False if not.
     */
    public boolean zipEfs() { return zipEfs; }

    /**
     * Sets the getUpdates variable. If set to <i>true</i>, then the program
     * will check for updates, before loading the UI. The updates will then be
     * shown by a JOptionPane, which will then trigger the downloader method to
     * download the file.
     *
     * @param bool
     */
    public void setGetUpdates(boolean bool) { this.getUpdates = bool; }

    /**
     * Sets the variable checkForDevicesOnStartup. If set to <i>true</i>, then
     * the loaded UI will check for any and all connected devices and will
     * display them in a JList.
     *
     * @param bool
     */
    public void setCheckForDevicesOnStartup(boolean bool) { this.checkForDevicesOnStartup = bool; }

    /**
     * Sets the variable autoLoadDeviceInfo. If set to <i>true</i>, then the
     * loaded UI will load the first device's information immediately..
     *
     * @param bool
     */
    public void setAutoLoadDeviceInfo(boolean bool) { this.autoLoadDeviceInfo = bool; }

    /**
     * Sets the variable saveLogs. If set to <i>true</i>, then the program will
     * automatically save any and all created logs to a log file. This
     * preference is locked until the RC state.
     *
     * @param bool
     */
    public void setSaveLogs(boolean bool) { this.saveLogs = bool; }

    /**
     * Sets the variable advancedUI. If set to <i>true</i>, the program will
     * load the advanced UI on startup, instead of the simple UI. Using the
     * advanced UI is only recommended, when using advanced commands, and
     * getting/modifying sensitive device information, such as build props.
     *
     * @param bool
     */
    public void setUseAdvancedUI(boolean bool) { this.useAdvancedUI = bool; }
    
    /**
     * Sets the look and feel used by Universal Android Toolkit.
     * @param laf new look and feel.
     */
    public void setLookAndFeel(String laf) { this.lookAndFeel = laf; }
    
    /**
     * Applies the chosen language to the properties.
     * @param lang 
     */
    public void setLang(Language lang) { this.language = lang; }
    
    /**
     * Sets the setting zipEfs to user preference.
     * @param bool the new setting.
     */
    public void setZipEfs(boolean bool) { this.zipEfs = bool; }

}
