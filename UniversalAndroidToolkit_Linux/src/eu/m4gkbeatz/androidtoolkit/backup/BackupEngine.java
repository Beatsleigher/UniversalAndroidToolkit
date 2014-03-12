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

package eu.m4gkbeatz.androidtoolkit.backup;

import java.io.*;
import java.util.*;

import JDroidLib.android.controllers.ADBController;

import eu.m4gkbeatz.androidtoolkit.logging.*;
import eu.m4gkbeatz.androidtoolkit.settings.SettingsManager;
import eu.m4gkbeatz.androidtoolkit.enums.*;

/**
 *
 * @author beatsleigher
 */
public class BackupEngine {
    
    //Engine constants
    public final int RETURN_SUCCESS = 0;
    public final int RETURN_FAILURE = 1;
    
    private SettingsManager settings = null;
    private Logger log = null;
    private ADBController adbController = null;
    File backupDir = null;
    File backupEfsDir = null;
    
    /**
     * Default constructor.
     * @param adbController
     * @param settings
     * @param log 
     */
    public BackupEngine(ADBController adbController, SettingsManager settings, Logger log) {
        this.adbController = adbController;
        this.settings = settings;
        this.log = log;
        loadDirs();
    }
    
    /**
     * Loads variables and created directories (will not be modified if already exist).
     */
    private void loadDirs() {
        try {
            log.log(LogLevel.INFO, "Creating directories for Backup Ngine");
            backupDir = new File(System.getProperty("user.home") + "/.androidtoolkit/backups");
            backupEfsDir = new File(backupDir.getAbsolutePath() + "/efs");
            backupDir.mkdirs();
            log.log(LogLevel.FINE, "Created dir " + backupDir.getAbsolutePath());
            backupEfsDir.mkdir();
            log.log(LogLevel.FINE, "Created dir " + backupEfsDir.getAbsolutePath());
        } catch (Exception ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error creating directories for Backup Ngine!\n" + ex.toString());
        }
    }
    
    public int backupDevice(BackupType type, String deviceSerial) {
        return backup(type, deviceSerial);
    }
    
    private synchronized int backup(BackupType type, String deviceSerial) {
        try {
            Date date = new Date();
            String fileName = backupDir.getAbsolutePath() + "/backup$_" + date.getYear() + date.getMonth() + date.getDay() + "-" + new Random().nextInt(1000) + "-UniversalAndroidToolkit.ab";
            
            return RETURN_SUCCESS;
        } catch (Exception ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while backing up device " + deviceSerial + "!\n" + ex.toString()); 
            return RETURN_FAILURE;
        }
    }
    
    public int backupDeviceEfs(String deviceSerial) {
        return backupEfs(deviceSerial);
    }
    
    private synchronized int backupEfs(String deviceSerial) {
        try {
            
            return RETURN_SUCCESS;
        } catch (Exception ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while backing up /efs on device " + deviceSerial + "!\n" + ex.toString());
            return RETURN_FAILURE;
        }
    }
}
