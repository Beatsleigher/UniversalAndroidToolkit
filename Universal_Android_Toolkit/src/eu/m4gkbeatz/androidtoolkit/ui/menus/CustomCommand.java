/*
 * Copyright (C) 2014 Beatsleigher
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

import JDroidLib.android.controllers.ADBController;

import eu.m4gkbeatz.androidtoolkit.language.LangFileParser;
import eu.m4gkbeatz.androidtoolkit.logging.Logger;
import static eu.m4gkbeatz.androidtoolkit.logging.Logger.Level;
import eu.m4gkbeatz.androidtoolkit.ui.Devices;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Beatsleigher
 */
public class CustomCommand extends javax.swing.JFrame {
    
    private final LangFileParser parser;
    private final boolean debug;
    private final ADBController adbController;
    private final Logger logger;
    private final List<String> adbCommandList;
    private final List<String> fbCommandList;
    private final Devices deviceManager;
    private final DefaultListModel adbCmdModel;
    private final DefaultListModel fbCmdModel;
    
    private boolean fbMode = false;

    public CustomCommand(LangFileParser parser, boolean debug, ADBController adbController, Logger logger, Devices deviceManager) {
        this.setLocationRelativeTo(null);
        this.parser = parser;
        this.debug = debug;
        this.adbController = adbController;
        this.logger = logger;
        this.deviceManager = deviceManager;
        this.setIconImage(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/UniversalAndroidToolkit_logo.png")).getImage());
        
        adbCommandList = new ArrayList<>();
        fbCommandList = new ArrayList<>();
        adbCmdModel = new DefaultListModel();
        fbCmdModel = new DefaultListModel();
        
        initComponents();
        loadPastCmds();
        
        jTextField1.setForeground(Color.GREEN);
    }
    
    private void loadPastCmds() {
        if (debug)
            logger.log(Level.DEBUG, "Loading user's custom past-executed commands...");
        File pastCmds = new File(System.getProperty("user.home") + "/.androidtoolkit/pcmd/p.cmd");
        if (!pastCmds.exists()) 
            try {
                pastCmds.getParentFile().mkdirs();
                pastCmds.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(pastCmds));
                writer.write("# THIS FILE IS NOT AN EXECUTABLE!\n# This file is used to save PAST USED commands in Universal Android Toolkit!");
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                logger.log(Level.ERROR, "An error occurred creating a file: " + ex.toString() + "\n"
                        + "The error stack trace will be printed to the console...");
                ex.printStackTrace(System.err);
            }
        adbCommandList.clear();
        fbCommandList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pastCmds));
            String line = "";
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                if (line.endsWith(";->adb"))
                    adbCommandList.add((line.split(";->"))[0]); // Well, that looks like a Smiley. Fuuu...
                else if (line.endsWith(";->fb"))
                    fbCommandList.add((line.split(";->"))[0]);
            }
            reader.close();
        } catch (IOException ex) {
            logger.log(Level.ERROR, "An error occurred while loading the last-used commands: " + ex.toString() + "\n"
                    + "The error stack trace will be printed to the console...");
            ex.printStackTrace(System.err);
        } finally {
            adbCmdModel.clear();
            fbCmdModel.clear();
            
            // Load ADB commands into ListModel
            for (String cmd : adbCommandList)
                adbCmdModel.addElement(cmd);
            
            // Load fastboot commands into ListModel
            for (String cmd : fbCommandList)
                fbCmdModel.addElement(cmd);
            
            if (fbMode)
                jList1.setModel(fbCmdModel);
            else
                jList1.setModel(adbCmdModel);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(parser.parse("customCmd:title") + " [ " + parser.parse("customCmd:adbMode") + " ]");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jLabel1.setText(parser.parse("customCmd:enterCmdLabel"));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jList1.setCellRenderer(new CommandListCellRenderer());
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText(parser.parse("customCmd:executeButton"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText(parser.parse("customCmd:adbMode"));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(parser.parse("customCmd:fbMode"));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTextField1)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String cmd = jTextField1.getText();
        
        if (cmd == null || cmd.equals("")) {
            logger.log(Level.ERROR, "ERROR: Invalid command! Please enter a valid command or select one from the list (if applicable)!");
            return;
        }
        
        if (cmd.contains("adb"))
            cmd = cmd.replace("adb", "");
        else if (cmd.contains("fastboot"))
            cmd = cmd.replace("fastboot", "");
        
        String[] splitArgs = cmd.split("(\\s)");
        
        String beginOut = "=============== Begin Command Output ==============="; String endOut = "=============== End Command Output ===============";
        String[] adbOutput = null;
        String rawOut = null;
        
        try {
            if (fbMode) {
                if (deviceManager.getSelectedFastbootDevice() == null || deviceManager.getSelectedFastbootDevice().equals("")) {
                    logger.log(Level.ERROR, "An error occurred while attempting to execute a fastboot command! No devices found!\n"
                            + "Please attach a device connected via USB in fastboot-mode to your computer and make sure UAT can see the device!");
                    return;
                }
                rawOut = adbController.getFastbootController().executeFastbootCommand(deviceManager.getSelectedFastbootDevice(), splitArgs);
            } else {
                if (deviceManager.getSelectedAndroidDevice() == null) {
                    logger.log(Level.ERROR, "An error occurred while attempting to execute an ADB command! No devices found!\n"
                            + "Please attack an Android device connected via USB either in the OS or recovery mode and make sure that the Developer Options and USB Debugging is enabled!");
                    return;
                }
                rawOut = adbController.executeADBCommand(false, false, deviceManager.getSelectedAndroidDevice(), splitArgs);
            }
        } catch (IOException ex) {
            logger.log(Level.ERROR, "An error occurred while executing a command: " + ex.toString() + "\n"
                    + "The error stack trace will be printed to the console...");
            ex.printStackTrace(System.err);
        }
        
        adbOutput = rawOut.split("\n");
        
        logger.log(Level.DEBUG, "Oh, noes. THAR SHE BLOWS!");
        logger.log(Level.INFO, beginOut);
        for (String out : adbOutput)
            logger.log(Level.INFO, out);
        logger.log(Level.INFO, endOut);
        
        logger.log(Level.DEBUG, "Adding last command (" + cmd + ") to last-used list...");
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.home") + "/.androidtoolkit/pcmd/p.cmd")));
            String line = "";
            rawOut = "";
            while ((line = reader.readLine()) != null)
                rawOut += line + "\n";
            reader.close();
            rawOut += "\n";
            rawOut += cmd;
            
            if (fbMode) rawOut += (";->fb");
            else        rawOut += (";->adb");
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home") + "/.androidtoolkit/pcmd/p.cmd")));
            writer.write(rawOut);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.ERROR, "An error occurred while adding last command to list: " + ex.toString() + "\n"
                    + "The error stack trace will be printed to the console...");
            ex.printStackTrace(System.err);
        } finally {
            loadPastCmds();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        fbMode = false;
        setTitle(parser.parse("customCmd:title") + " [ " + parser.parse("customCmd:adbMode") + " ]");
        jTextField1.setForeground(Color.GREEN); 
        loadPastCmds();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        fbMode = true;
        setTitle(parser.parse("customCmd:title") + " [ " + parser.parse("customCmd:fbMode") + " ]");
        jTextField1.setForeground(Color.BLUE);
        loadPastCmds();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        jTextField1.setText((String) jList1.getSelectedValue());
    }//GEN-LAST:event_jList1ValueChanged

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButton1.doClick();
    }//GEN-LAST:event_jTextField1KeyPressed

    
    /**
     * Gives the list of previously used commands a decent look, that would appeal to most users.
     */
    public class CommandListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/commands/Console-32.png")));
            if (fbMode) 
                label.setForeground(Color.BLUE);
            else
                label.setForeground(Color.GREEN);
            label.setHorizontalTextPosition(JLabel.RIGHT);
            return label;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
