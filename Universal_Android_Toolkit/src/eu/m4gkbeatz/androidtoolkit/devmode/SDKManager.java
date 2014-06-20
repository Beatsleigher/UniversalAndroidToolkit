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


package eu.m4gkbeatz.androidtoolkit.devmode;


import JDroidLib.android.controllers.*;
import eu.m4gkbeatz.androidtoolkit.language.*;

import eu.m4gkbeatz.androidtoolkit.logging.*;
import static eu.m4gkbeatz.androidtoolkit.logging.Logger.Level;
import eu.m4gkbeatz.androidtoolkit.settings.*;

import java.io.*;
import java.net.*;
import javax.swing.*;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;


/**
 *
 * @author beatsleigher
 */
public class SDKManager {
    
    private final File toolkitDir;
    private final String osName = System.getProperty("os.name");
    private final Logger logger;
    private final SettingsManager settings;
    private final ADBController adbController;
    private final boolean debug;
    private final LangFileParser parser;
    
    private boolean isInstalled = false;
    private boolean isOpen = false;
    
    
    public SDKManager(File toolkitDir, Logger logger, SettingsManager settings, ADBController adbController, boolean debug, LangFileParser parser) {
        this.toolkitDir = toolkitDir;
        this.logger = logger;
        this.settings = settings;
        this.adbController = adbController;
        this.debug = debug;
        this.parser = parser;
    }
    
    public final void init() throws IOException, ZipException {
        
        boolean downloadSDK = false;
        
        logger.log(Level.INFO, "Initializing SDK Manager. Checking for SDK directories...");
        logger.log(Level.INFO, "Checking downloads dir...");
        
        if (!new File(toolkitDir.getAbsolutePath() + "/download").exists()) {
            logger.log(Level.INFO, "Downloads dir does not exist. Creating...");
            new File(toolkitDir.getAbsolutePath() + "/download").mkdirs();
        } else logger.log(Level.INFO, "Downloads dir exists. Ignoring...");
        
        logger.log(Level.INFO, "Checking SDK dir...");
        
        if (!new File(toolkitDir.getAbsolutePath() + "/sdk").exists()) {
            logger.log(Level.INFO, "SDK dir does not exist. Creating...");
            new File(toolkitDir.getAbsolutePath() + "/sdk").mkdirs();
        } else logger.log(Level.INFO, "SDK dir exists. Ignoring...");
        
        logger.log(Level.INFO, "Operating system: " + osName + ".");
        
        if (downloadSDK) {
            logger.log(Level.INFO, "Downloading Android SDK...");
            SDKDownload download = new SDKDownload(parser);
            download.setVisible(true);
            logger.log(Level.INFO, "Computing file size...");
            download.setProgressMax(computeRemoteFileSize() / 1024 / 1024);
            logger.log(Level.INFO, "Creating necessary files...");
                        
            File output = null;
            URL input = null;
            BufferedReader inputReader = null;
            BufferedWriter outputWriter = null;
            int data = 0;
            long dataTotal = 0;
            
            if (!"Linux".equals(osName))
                output = new File(toolkitDir.getAbsolutePath() + "/sdk/sdkPackage_uat.tgz");
            else
                output = new File(toolkitDir.getAbsolutePath() + "/sdk/sdkPackage_uat.zip");
            
            if ("Linux".equals(osName))
                input = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-linux.tgz");
            else if (osName.contains("Windows"))
                input = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-windows.zip");
            else // Assume system is Mac OS
                input = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-macosx.zip");
            
            try {
                inputReader = new BufferedReader(new InputStreamReader(input.openStream()));
                outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output.getAbsolutePath())));
                
                while ((data = inputReader.read()) != -1) {
                    outputWriter.write(data);
                    outputWriter.flush();
                    dataTotal += data;
                    download.setProgress(dataTotal);
                }
                
            } finally {
                inputReader.close();
                outputWriter.close();
            }
            
            logger.log(Level.INFO, "SDK was downloaded successfully.\n"
                    + "\t\tTotal data downloaded: " + (dataTotal / 1024 / 1024) + "MB\n"
                    + "\t\tFile size: " + output.length());
            logger.log(Level.INFO, "Installing SDK...");
            
            ZipFile zip;
            try {
                zip = new ZipFile(output.getAbsolutePath());
                zip.extractAll(output.getParentFile().getAbsolutePath());
            } finally {
                zip = null;
            }
            
            JOptionPane.showMessageDialog(download, parser.parse("sdkManager:successMsg"), parser.parse("sdkManager:successMsgTitle"), JOptionPane.INFORMATION_MESSAGE);
            download.dispose();
            download = null;
            
            
        }
        
    }
    
    private long computeRemoteFileSize() {
        URL url = null;
        HttpURLConnection connection = null;
        long result = 0;
        
        try {
            if ("Linux".equals(osName))
                url = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-linux.tgz");
            else if (osName.contains("Windows"))
                url = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-windows.zip");
            else // Assume system is Mac OS
                url = new URL("http://team-m4gkbeatz.eu/UniversalAndroidToolkit/sdkDownload/android-sdk_r22.6.2-macosx.zip");
            
            connection = (HttpURLConnection) url.openConnection();
            result = connection.getContentLengthLong();
            
        } catch (IOException ex) {
            logger.log(Level.ERROR, "An error occurred while computing the file size for the SDK (for OS: " + osName + ": " + ex.toString() + "\n"
                    + "The error stack trace will be printed to the console...");
            ex.printStackTrace(System.err);
        } finally {
            connection.disconnect();
        }
        
        return result;
    }
    
}
