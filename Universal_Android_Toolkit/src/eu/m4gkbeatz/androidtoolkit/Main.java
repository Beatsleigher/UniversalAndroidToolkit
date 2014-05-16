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

import JDroidLib.android.controllers.*;
import eu.m4gkbeatz.androidtoolkit.logging.Logger;
import eu.m4gkbeatz.androidtoolkit.settings.SettingsManager;
import eu.m4gkbeatz.androidtoolkit.splash.*;
import eu.m4gkbeatz.androidtoolkit.ui.*;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import net.lingala.zip4j.exception.*;

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
    public static final String VERSION_NO = "1.0";
    public static final String VERSION = "Universal Android Toolkit version: " + VERSION_NO + "\n"
            + "Designed, Created and Compiled by Beatsleigher.\n"
            + "http://team-m4gkbeatz.eu";
    static Logger logger = null;
    static Logger.Level level = null;
    static SettingsManager settings = null;
    static ADBController adbController = null;
    
    public static void main(String[] args) {
        
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
        
        System.out.println("Arguments found: " + args.length);
        for (String str : args)
            System.out.println("Found argument: " + str);
        if (args.length != 0) {
            for (String str : args) {
                switch (str.toLowerCase()) {
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
                    case ARG_ANNOY: 
                        System.out.println(":p NENENENENENE! I don't like you!");
                        return;
                    case ARG_HELP:
                        printHelp();
                        return;
                }
            }
            System.out.println("==================== Application Output Following ====================");
        }
        
        try {
            logger = new Logger(debug);
        } catch (IOException ex) {
            System.err.println("ERROR: Error while loading UAT log! Application will terminate! (Error Code 1)");
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        
        /*Start actual program*/
        logger.log(level.INFO, "Booting Universal Android Toolkit. Starting Settings engine...(VROOM!)");
        SplashScreen splash = new SplashScreen();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        
        boolean updateAvailable = false;
        
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
        logger.log(level.INFO, "Applying settings...");
        
        if (debug)
            logger.log(level.DEBUG, "Attempting to apply look and feel \"" + settings.getLookAndFeel() + "\"");
        
        try {
            if (debug)
                logger.log(level.DEBUG, "Disposing of splash screen...");
            splash.dispose();
            logger.dispose();
            if (debug)
                logger.log(level.DEBUG, "Setting look and feel...");
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if (info.getName().equals(settings.getLookAndFeel()))
                    UIManager.setLookAndFeel(info.getClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.log(level.ERROR, "An error occurred while attempting to set the wished look and feel. Universal Android Toolkit's appearence may distort!\n"
                    + "\tPlease make sure you correct your input via the settings menu!");
            ex.printStackTrace(System.err);
        }
        if (debug)
            logger.log(level.DEBUG, "Setting visibility of splash screen to true again...");
        splash.setVisible(true);
        
        if (settings.showLog())
            logger.setVisible(true);
        /*if (settings.checkForUpdatesOnStartup()) 
            updateAvailable = checkForUpdates(logger);*/
        
        // Launch JDroidLib main class
        if (debug)
            logger.log(level.DEBUG, "Firing up JDroidLib. Prepare for god-damn awesomeness!");
        
        try {
            adbController = new ADBController();
        } catch (IOException | ZipException | InterruptedException ex) {
            logger.log(level.ERROR, "Error while initializing JDroidLib!\n\tLaunch will abort (Error code 3!)");
            ex.printStackTrace(System.err);
            System.exit(3);
        }
        
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {}
                interrupt();
            }
        }.start();
        
        if (debug)
            logger.log(level.DEBUG, "Init complete. Creating new instance of main UI...");
            
        splash.dispose();
        new Thread() {
            @Override
            public void run() {
                new UAT(debug, logger, level, settings, adbController).setVisible(true);
            }
        }.start();
        
    }
    
    private static boolean checkForUpdates(Logger logger) {
        logger.log(Logger.Level.INFO, "Checking for updates for Universal Android Toolkit. Please wait...");
        BufferedReader reader = null;
        boolean returnVal = false;
        try {
            reader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Beatsleigher/UniversalAndroidToolkit/master/version").openStream()));
            String line = reader.readLine();
            returnVal = !(line.startsWith("Version") && line.contains(VERSION_NO));
        } catch (IOException ex) {
            logger.log(Logger.Level.ERROR, "Error occurred while checking for updates!\n\tStack trace was printed to console!");
            ex.printStackTrace(System.err);
            logger.log(Logger.Level.INFO, "Update check was aborted! Please check manually.");
            return false;
        }
        logger.log(Logger.Level.INFO, "A new update has been found! You will be notified when you have reached the control window.");
        return returnVal;
    }
    
    private static void printHelp() {
        String help = VERSION + "\n"
                + "Usage:\n"
                + "\t" + ARG_DEBUG + "\t\t makes UAT enter debug-mode\n"
                + "\t" + ARG_VERSION + "\t prints UAT's version\n"
                + "\t" + ARG_NO_SETTINGS + "\t overrides all UAT's preferences and forces the defaults to be used\n"
                + "\t" + ARG_OVERRIDE_LAF + "\t override the set look and feel and goes to the default look and feel\n"
                + "\t" + ARG_FORCE_UPDATE + "\t overrides any settings preventing automatic updates and forcibly installs any and all patches available\n"
                + "\t" + ARG_HELP + "\t\t prints this message\n"
                + "\t<empty/no args>\t starts Universal Android Toolkit using all user-specified options and settings.";
        System.out.println(help);
    }
    
}
