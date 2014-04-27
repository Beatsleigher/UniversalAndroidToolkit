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

package eu.m4gkbeatz.androidtoolkit;

import eu.m4gkbeatz.androidtoolkit.logging.Logger;
import eu.m4gkbeatz.androidtoolkit.settings.SettingsManager;
import eu.m4gkbeatz.androidtoolkit.splash.*;

import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Main class - Universal Android Toolkit's entry point.
 * This class handles everything from initial start to initiating settings and windows.
 * @author beatsleigher
 * @since 1.0
 * @version 1.0
 */
public class Main {
    
    //# =============== Possible Args =============== #\\
    private static final String ARG_DEBUG = "--debug";
    private static final String ARG_VERSION = "--version";
    private static final String ARG_NO_SETTINGS = "--no-settings";
    private static final String ARG_OVERRIDE_LAF = "--override-laf";
    private static final String ARG_FORCE_UPDATE = "--force-update";
    private static final String ARG_ANNOY = "--annoy-user";
    private static final String ARG_HELP = "--help";
    //# =============== Other variables =============== #\\
    private static boolean debug = false;
    private static boolean ignorePrefs = false;
    private static String lafToUse = "Nimbus";
    private static boolean forceUpdate = false;
    public static final String VERSION = "Universal Android Toolkit version: 1.0\n"
            + "\tDesigned, Created and Compiled by Beatsleigher.\n"
            + "\thttp://team-m4gkbeatz.eu";
    
    public static void main(String[] args) {
        Logger logger = null;
        Logger.Level level = null;
        try {
            logger = new Logger();
        } catch (IOException ex) {
            System.err.println("ERROR: Error while loading UAT log! Application will terminate! (Error Code 1)");
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        
        /*Initial setup - Determine default look and feel*/
        String osName = System.getProperty("os.name");
        if (osName.equals("Linux"))
            lafToUse = "Joxy";
        else if (osName.contains("Windows"))
            lafToUse = "Windows";
        else if (osName.contains("Mac"))
            lafToUse = "Macintosh";
        else
            lafToUse = "Nimbus";
        
        if (args != null) {
            switch (args[0].toLowerCase()) {
                case ARG_DEBUG:
                    debug = true; break;
                case ARG_VERSION:
                    System.err.println(VERSION); return;
                case ARG_NO_SETTINGS:
                    ignorePrefs = true; break;
                case ARG_OVERRIDE_LAF:
                    lafToUse = args[1]; break;
                case ARG_FORCE_UPDATE:
                    forceUpdate = true; break;
                case ARG_ANNOY: return;
            }
        }
        
        /*Start actual program*/
        logger.log(level.INFO, "Booting Universal Android Toolkit. Starting Settings engine...(VROOM!)");
        SplashScreen splash = new SplashScreen();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        
        SettingsManager settings = null;
        try {
            settings = new SettingsManager(debug, ignorePrefs, logger, lafToUse);
        } catch (IOException ex) {
            logger.log(level.ERROR, "Error while trying to create new instance of SettingsManager. Stack trace will be printed to console...");
            ex.printStackTrace(System.err);
            logger.log(level.INFO, "Application will now terminate. (Error code 2)");
            System.exit(2);
        }
        
        if (debug)
            logger.log(level.DEBUG, "Settings locked and loaded. Ready to commence launch...");
        
        // Load settings that can be applied here
        if (settings.showLog())
            logger.setVisible(true);
        if (settings.checkForUpdatesOnStartup()) {
            boolean updateAvailable = checkForUpdates();
        }
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if (info.getName().equals(settings.getLookAndFeel()))
                    UIManager.setLookAndFeel(info.getName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.log(level.ERROR, "An error occurred while attempting to set the wished look and feel. Universal Android Toolkit's appearence may distort!\n"
                    + "\tPlease make sure you correct your input via the settings menu!");
            ex.printStackTrace(System.err);
        }
        
    }
    
    private static boolean checkForUpdates() {
        boolean returnVal;
        
        return returnVal;
    }
    
}
