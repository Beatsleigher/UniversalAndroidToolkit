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


package eu.m4gkbeatz.androidtoolkit.ui.menus;


import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.Component;

import eu.m4gkbeatz.androidtoolkit.logging.Logger;
import static eu.m4gkbeatz.androidtoolkit.logging.Logger.Level;
import eu.m4gkbeatz.androidtoolkit.settings.SettingsManager;
import eu.m4gkbeatz.androidtoolkit.language.LangFileParser;

import JDroidLib.android.controllers.ADBController;
import JDroidLib.android.device.Device;

/**
 *
 * @author beatsleigher
 */
public class BackupManager extends JFrame {

    private Logger logger = null;
    private ADBController adbController = null;
    private SettingsManager settings = null;
    private Device device = null;
    private boolean debug = false;
    private LangFileParser parser = null;
    private String listLabel1 = "", listLabel2 = "";
    private String workingLabel1 = "", workingLabel2 = "";
    private String backupDir = new File(System.getProperty("user.home") + "/.androidtoolkit/backups").getAbsolutePath();
    private boolean backingUp = false;

    public BackupManager(Logger logger, ADBController adbController, SettingsManager settings, Device device, boolean debug, LangFileParser parser) {
        initComponents();
        this.setIconImage(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/UniversalAndroidToolkit_logo.png")).getImage());
        this.logger = logger;
        this.adbController = adbController;
        this.settings = settings;
        this.device = device;
        this.debug = debug;
        this.parser = parser;
        try {
            loadTranslations();
        } catch (IOException ex) {
            logger.log(Level.ERROR, "An error occurred while loading the translations for the Backup Manager: " + ex.toString() + "\n"
                       + "The error stack trace will be printed to the console...");
        }
        jList1.setCellRenderer(new BackupCellRenderer());
        backupTitleLabel1.setText("backup-" + device.getSerial() + "-");
        int amount = new File(System.getProperty("user.home") + "/.androidtoolkit/backups").listFiles().length;
        backupTitleText.setText("myBackup" + (amount + 1));
        loadBackups();
    }

    private IOException exception = null;

    private void loadTranslations() throws IOException {
        new Thread() {
            @Override
            public void run() {
                try {
                    setTitle("Universal Android Toolkit | " + parser.parse("backupManager:title"));
                    listLabel.setText(parser.parse("backupManager:listLabel1"));
                    listLabel1 = listLabel.getText();
                    listLabel2 = parser.parse("backupManager:listLabel2");
                    backupNameLabel.setText(parser.parse("backupManager:backupNameLabel"));
                    workingLabel.setText(parser.parse("backupManager:workingLabel1"));
                    workingLabel1 = workingLabel.getText();
                    workingLabel2 = parser.parse("backupManager:workingLabel2");
                    optionsLabel.setText(parser.parse("backupManager:optionsLabel"));
                    addSystemLabel.setText(parser.parse("backupManager:addSystemLabel"));
                    addSystemLabel.setToolTipText(parser.parse("backupManager:addSystemLabelToolTip"));
                    addStorageLabel.setText(parser.parse("backupManager:addStorageLabel"));
                    addStorageLabel.setToolTipText(parser.parse("backupManager:addStorageLabelToolTip"));
                    addApplicationsLabel.setText(parser.parse("backupManager:addApplicationsLabel"));
                    addApplicationsLabel.setToolTipText(parser.parse("backupManager:addApplicationsLabelToolTip"));
                    addEFSLabel.setText(parser.parse("backupManager:addEFSLabel"));
                    addEFSLabel.setToolTipText(parser.parse("backupManager:addEFSLabelToolTip"));
                    backupButton.setText(parser.parse("backupManager:backupButton"));
                } catch (IOException ex) {
                    exception = ex;
                }
                interrupt();
            }
        }.start();
        if (exception != null)
            throw exception;
    }

    private void loadBackups() {
        new Thread() {
            @Override
            public void run() {
                DefaultListModel model = new DefaultListModel();
                File backupDir = new File(System.getProperty("user.home") + "/.androidtoolkit/backups");
                if (debug)
                    logger.log(Level.DEBUG, "Loading backups from: " + backupDir.getAbsolutePath() + " for device: " + device.toString());
                if (!backupDir.exists()) {
                    backupDir.mkdirs();
                    listLabel.setText(listLabel2);
                    interrupt();
                }

                // List backups
                for (File f : backupDir.listFiles(new ABFilter())) {
                    model.addElement(f.getName());
                }
                jList1.setModel(model);
                listLabel.setText(listLabel2);
                interrupt();
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        listLabel = new javax.swing.JLabel();
        backupNameLabel = new javax.swing.JLabel();
        backupTitleLabel1 = new javax.swing.JLabel();
        backupTitleText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        workingLabel = new javax.swing.JLabel();
        addSystemLabel = new javax.swing.JCheckBox();
        optionsLabel = new javax.swing.JLabel();
        addStorageLabel = new javax.swing.JCheckBox();
        addApplicationsLabel = new javax.swing.JCheckBox();
        addEFSLabel = new javax.swing.JCheckBox();
        backupButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        listLabel.setText("List of backups...");

        backupNameLabel.setText("Backup name:");

        backupTitleLabel1.setText("backup-serial-");

        backupTitleText.setText("myBackup1");

        jLabel2.setText(".ab");

        workingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        workingLabel.setText("Working");

        addSystemLabel.setText(" + System");

        optionsLabel.setText("Options:");

        addStorageLabel.setText(" + Storage");

        addApplicationsLabel.setText(" + Apps");

        addEFSLabel.setText(" + EFS");

        backupButton.setText("Backup...");
        backupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backupNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backupTitleLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backupTitleText, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(optionsLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addSystemLabel)
                        .addGap(18, 18, 18)
                        .addComponent(addStorageLabel)
                        .addGap(18, 18, 18)
                        .addComponent(addApplicationsLabel)
                        .addGap(18, 18, 18)
                        .addComponent(addEFSLabel)))
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(backupButton)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(listLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(workingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backupNameLabel)
                    .addComponent(backupTitleLabel1)
                    .addComponent(backupTitleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(optionsLabel)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSystemLabel)
                    .addComponent(addStorageLabel)
                    .addComponent(addApplicationsLabel)
                    .addComponent(addEFSLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(workingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonActionPerformed
        new Thread() {
            @Override
            public void run() {
                logger.log(Level.INFO, "=============== Backup device " + device.toString() + " ===============");
                jProgressBar1.setIndeterminate(true);
                workingLabel.setText(workingLabel2);

                ProcessBuilder process = null;
                Process pr = null;
                BufferedReader reader = null;
                List<String> args = null;
                String line = "";
                IOException[] exceptionArray = new IOException[5];
                int arrSize = 0;
                boolean bool = false, bool1 = false, bool2 = false, bool3 = false;

                if (addSystemLabel.isSelected())
                    bool = backupSystem();

                if (addStorageLabel.isSelected())
                    bool1 = backupStorage();

                if (addApplicationsLabel.isSelected())
                    bool2 = backupApps();

                if (addEFSLabel.isSelected())
                    bool3 = backupEFS();
                
                while (!(bool == addSystemLabel.isSelected()) && !(bool1 == addStorageLabel.isSelected()) && !(bool2 == addApplicationsLabel.isSelected()) && !(bool3 == addEFSLabel.isSelected())) {
                    // Wait.
                }

                jProgressBar1.setIndeterminate(false);
                logger.log(Level.INFO, "===============/Backup device " + device.toString() + "\\===============");
                workingLabel.setText(workingLabel1);
            }
        }.start();
    }//GEN-LAST:event_backupButtonActionPerformed

    private boolean backupSystem() {
        new Thread() {
            @Override
            public void run() {
                ProcessBuilder process = null;
                Process pr = null;
                BufferedReader reader = null;
                String line = null;
                List<String> args = null;
                try {
                    workingLabel.setText(workingLabel2);
                    File f = new File(backupDir + "/" + backupTitleLabel1.getText() + backupTitleText.getText() + "/system");
                    f.mkdirs();
                    process = new ProcessBuilder();
                    args = new ArrayList<>();
                    args.add(adbController.getADB().getAbsolutePath());
                    args.add("-s");
                    args.add(device.toString());
                    args.add("pull");
                    args.add("/system");
                    process.command(args);
                    process.directory(f);
                    process.redirectErrorStream(true);
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (debug)
                            logger.log(Level.DEBUG, "ADB Output: " + line);
                        if (line.contains("files pulled.")) {
                            workingLabel.setText(line);
                            break;
                        }
                    }
                    pr.destroy();
                    reader.close();
                    args.clear();
                    for (int i = 0; i < 2000; i++) {
                    } // Display result to user before continuing work.
                } catch (IOException ex) {
                    logger.log(Level.ERROR, "An error occurred while backing up /system: " + ex.toString() + "\nThe error stack trace will be printed to the console...");
                    ex.printStackTrace(System.err);
                }
                interrupt();
            }
        }.start();
        return true;
    }

    private boolean backupStorage() {
        new Thread() {
            @Override
            public void run() {
                ProcessBuilder process = null;
                Process pr = null;
                BufferedReader reader = null;
                List<String> args = null;
                String line = null;
                try {
                    workingLabel.setText(workingLabel2);
                    File f = new File(backupDir + "/" + backupTitleLabel1.getText() + backupTitleText.getText() + "/storage/internal");
                    f.mkdirs();
                    process = new ProcessBuilder();
                    args = new ArrayList<>();
                    args.add(adbController.getADB().getAbsolutePath());
                    args.add("-s");
                    args.add(device.toString());
                    args.add("pull");
                    args.add("/mnt/sdcard");
                    process.command(args);
                    process.directory(f);
                    process.redirectErrorStream(true);
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (debug)
                            logger.log(Level.DEBUG, "ADB Output: " + line);
                        if (line.contains("files pulled.")) {
                            workingLabel.setText(line);
                            break;
                        }
                    }
                    pr.destroy();
                    reader.close();
                    args.clear();
                    for (int i = 0; i < 2000; i++) {
                    } // Display result to user before continuing work.

                    workingLabel.setText(workingLabel2);
                    f = new File(backupDir + "/" + backupTitleLabel1.getText() + backupTitleText.getText() + "/storage/sd_card");
                    f.mkdirs();
                    process = new ProcessBuilder();
                    args = new ArrayList<>();
                    args.add(adbController.getADB().getAbsolutePath());
                    args.add("-s");
                    args.add(device.toString());
                    args.add("pull");
                    args.add("/mnt/extsdcard");
                    process.command(args);
                    process.directory(f);
                    process.redirectErrorStream(true);
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (debug)
                            logger.log(Level.DEBUG, "ADB Output: " + line);
                        if (line.contains("files pulled.")) {
                            workingLabel.setText(line);
                            break;
                        }
                    }
                    pr.destroy();
                    reader.close();
                    args.clear();
                    for (int i = 0; i < 2000; i++) {
                    } // Display result to user before continuing work.
                } catch (IOException ex) {
                    logger.log(Level.ERROR, "An error occurred while backing up storage devices: " + ex.toString() + "\nThe error stack trace will be printed to the console...");
                    ex.printStackTrace(System.err);
                }
                interrupt();
            }
        }.start();
        return true;
    }

    private boolean backupApps() {
        new Thread() {
            @Override
            public void run() {
                ProcessBuilder process = null;
                Process pr = null;
                List<String> args = null;
                String line = null;
                BufferedReader reader = null;
                try {
                    workingLabel.setText(workingLabel2);
                    process = new ProcessBuilder(adbController.getADB().getAbsolutePath(), "-s", device.toString(), "root");
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                    } // Wait
                    pr.destroy();
                    reader.close();

                    File f = new File(backupDir + "/" + backupTitleLabel1.getText() + backupTitleText.getText() + "/data");
                    f.mkdirs();
                    process = new ProcessBuilder();
                    args = new ArrayList<>();
                    args.add(adbController.getADB().getAbsolutePath());
                    args.add("-s");
                    args.add(device.toString());
                    args.add("pull");
                    args.add("/data");
                    process.command(args);
                    process.directory(f);
                    process.redirectErrorStream(true);
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (debug)
                            logger.log(Level.DEBUG, "ADB Output: " + line);
                        if (line.contains("files pulled.")) {
                            workingLabel.setText(line);
                            break;
                        }
                    }
                    pr.destroy();
                    reader.close();
                    args.clear();
                    for (int i = 0; i < 2000; i++) {
                    } // Display result to user before continuing work.
                } catch (IOException ex) {
                    logger.log(Level.ERROR, "An error occurred while backing up applications: " + ex.toString() + "\nThe error stack trace will be printed to the console...");
                    ex.printStackTrace(System.err);
                }
                interrupt();
            }
        }.start();
        return true;
    }

    private boolean backupEFS() {
        new Thread() {
            @Override
            public void run() {
                BufferedReader reader = null;
                List<String> args = null;
                ProcessBuilder process = null;
                Process pr = null;
                String line = null;
                try {
                    workingLabel.setText(workingLabel2);
                    File f = new File(backupDir + "/" + backupTitleLabel1.getText() + backupTitleText.getText() + "/efs");
                    f.mkdirs();
                    process = new ProcessBuilder();
                    args = new ArrayList<>();
                    args.add(adbController.getADB().getAbsolutePath());
                    args.add("-s");
                    args.add(device.toString());
                    args.add("pull");
                    args.add("/efs");
                    process.command(args);
                    process.directory(f);
                    process.redirectErrorStream(true);
                    pr = process.start();
                    reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (debug)
                            logger.log(Level.DEBUG, "ADB Output: " + line);
                        if (line.contains("files pulled.")) {
                            workingLabel.setText(line);
                            break;
                        }
                    }
                    pr.destroy();
                    reader.close();
                    args.clear();
                    for (int i = 0; i < 2000; i++) {
                    } // Display result to user before continuing work.
                } catch (IOException ex) {
                    logger.log(Level.ERROR, "An error occurred while backing up applications: " + ex.toString() + "\nThe error stack trace will be printed to the console...");
                    ex.printStackTrace(System.err);
                }
                interrupt();
            }
        }.start();
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addApplicationsLabel;
    private javax.swing.JCheckBox addEFSLabel;
    private javax.swing.JCheckBox addStorageLabel;
    private javax.swing.JCheckBox addSystemLabel;
    private javax.swing.JButton backupButton;
    private javax.swing.JLabel backupNameLabel;
    private javax.swing.JLabel backupTitleLabel1;
    private javax.swing.JTextField backupTitleText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listLabel;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JLabel workingLabel;
    // End of variables declaration//GEN-END:variables

    private class BackupCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/backups/backup.png")));
            return label;
        }

    }

    private class ABFilter implements FileFilter {

        @Override
        public boolean accept(File f) {
            return (f.isDirectory()) || (f.getAbsolutePath().toLowerCase().endsWith(".ab") && f.getAbsolutePath().toLowerCase().contains(device.getSerial().toLowerCase()));
        }

    }

}
