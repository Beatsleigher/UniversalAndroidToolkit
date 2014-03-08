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
package eu.m4gkbeatz.androidtoolkit.ui;

import eu.m4gkbeatz.androidtoolkit.logging.*;
import eu.m4gkbeatz.androidtoolkit.settings.*;
import eu.m4gkbeatz.androidtoolkit.io.*;

import JDroidLib.android.controllers.ADBController;
import JDroidLib.enums.RebootTo;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author beatsleigher
 */
public class SimpleUI extends JFrame {

    ADBController adbController = null;
    SettingsManager settings = null;
    Logger log = null;

    List<String> connectedADBDevices = new ArrayList();
    List<String> connectedFastbootDevices = new ArrayList();

    /**
     * Creates new form SimpleUI
     *
     * @param settings
     * @param log
     * @param adbController
     */
    public SimpleUI(SettingsManager settings, Logger log, ADBController adbController) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.settings = settings;
        this.log = log;
        this.setTitle("Universal Android Toolkit | Welcome, " + System.getProperty("user.name") + ". | Simple UI");
        this.setIconImage(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/icon.png")).getImage());
        this.adbController = adbController;
        try {
            adbController.startServer();
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error creating instance of ADBController!\n" + ex.toString());
        }
        useSettings();
    }

    private void useSettings() {
        if (settings.checkForDevicesOnStartup()) {
            int i = getADBDevs();
            int j = getFastbootDevs();
        }
        try {
            Thread.sleep(500); // Sleep half a sec, so the program doesn't encounter a stupid NullPointerException. Again...
        } catch (InterruptedException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while sleeping!\n" + ex.toString());
        }
        jList1.setSelectedIndex(0);
        String devSerial = jList1.getSelectedValue().toString();
        if (!devSerial.equals("") && settings.autoLoadDeviceInfo()) {
            if (settings.autoLoadDeviceInfo()) {
                log.log(LogLevel.INFO, "Loading battery level from device " + devSerial);
                getBattery(devSerial);
            }
        }
    }

    /**
     * Get and display any devices connected to computer via ADB.
     */
    private int getADBDevs() {
        new Thread() { /*Search for ADB devices...*/

            @Override
            public void run() {
                log.log(LogLevel.INFO, "Searching for ADB devices...");
                try {
                    connectedADBDevices = adbController.getConnectedDevices();
                    DefaultListModel adbList = new DefaultListModel();
                    for (int i = 0; i < connectedADBDevices.size(); i++) {
                        String[] arr;
                        adbList.addElement((arr = connectedADBDevices.get(i).split("\t"))[0]);
                        log.log(LogLevel.INFO, "Found Device: " + connectedADBDevices.get(i));
                    }
                    jList1.setModel(adbList);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error getting connected devices!\n" + ex.toString());
                }
                log.log(LogLevel.INFO, "Finished retrieving devices...");
                interrupt();
            }

        }.start();
        return 0;
    }

    /**
     * Get and sisplay list of devices connected to computer via fastboot.
     */
    private int getFastbootDevs() {
        new Thread() { /*Search for fastboot devices...*/

            @Override
            public void run() {
                log.log(LogLevel.INFO, "Searching for fastboot devices...");
                try {
                    connectedFastbootDevices = adbController.getConnectedFastbootDevices();
                    DefaultListModel adbList = new DefaultListModel();
                    for (int i = 0; i < connectedFastbootDevices.size(); i++) {
                        String[] arr;
                        adbList.addElement((arr = connectedFastbootDevices.get(i).split("\t"))[0]);
                        log.log(LogLevel.INFO, "Found Device: " + connectedFastbootDevices.get(i));
                    }
                    jList2.setModel(adbList);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error getting connected devices!\n" + ex.toString());
                }
                log.log(LogLevel.INFO, "Finished retrieving devices...");
                interrupt();
            }
        }.start();
        return 0;
    }

    /**
     * Gets battery of device selected in jList1.
     *
     * @param serial of the device, from which to get the info.
     */
    private void getBattery(final String serial) {
        new Thread() {
            @Override
            public void run() {
                try {
                    int batteryLevel = adbController.getDevice(serial).getBattery().getLevel();
                    if (batteryLevel <= 25) {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_empty-32.png")));
                    } else if (batteryLevel <= 50) {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_quarter-32.png")));
                    } else if (batteryLevel <= 75) {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_half-32.png")));
                    } else if (batteryLevel < 100) {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_third-32.png")));
                    } else if (batteryLevel == 100) {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_full-32.png")));
                    } else {
                        jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png")));
                    }
                    jLabel1.setText(batteryLevel + "%");
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error getting battery level from device!");
                    log.log(LogLevel.SEVERE, "Error message: " + ex.toString());
                    log.log(LogLevel.INFO, "Printing stack trace to standard error stream.");
                    ex.printStackTrace(System.err);
                }
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(100, 14));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png"))); // NOI18N
        jLabel1.setText("n/a");
        jToolBar1.add(jLabel1);

        jScrollPane2.setViewportView(jList2);

        jScrollPane1.setViewportView(jList1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Install App");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setText("Select APK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Install");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Push");

        jButton3.setText("Select...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Push");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pull");

        jButton5.setText("Select...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Pull");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Controls");

        jButton7.setText("Start Server");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Stop Server");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Restart Server");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton10.setText("Execute Command");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Connect Wirelessly");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Load Devices");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton13.setText("Reboot to Android");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Reboot to Recovery");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Reboot to Bootloader");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1))
                                .addGap(30, 30, 30)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton6)
                            .addComponent(jButton5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(236, 236, 236))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(95, 95, 95)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton12)
                    .addComponent(jButton11)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton15)
                    .addComponent(jButton14)
                    .addComponent(jButton13))
                .addGap(88, 88, 88))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addGap(7, 7, 7)
                                .addComponent(jButton9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Android", jPanel1);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Flash Recovery");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 15));

        jButton16.setText("Select IMG");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Flash");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Controls");

        jButton18.setText("Load Devices");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Execute Command");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton20.setText("Reboot to Android");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Reboot to Recovery");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Reboot to Fastboot");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jButton17)
                                    .addComponent(jButton16))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton19)
                    .addComponent(jButton18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton22)
                    .addComponent(jButton21)
                    .addComponent(jButton20))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton19))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fastboot", jPanel3);

        jMenu1.setText("ADB");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Start Server");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Stop Server");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Execute Command");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Connect Wirelessly");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Load Devices");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenu3.setText("Reboot to...");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Android");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Recovery");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Bootloader");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Fastboot");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Execute Command");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Load Devices");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenu4.setText("Reboot To...");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Android");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Fastboot");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("UAT");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Settings");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        log.close();
    }//GEN-LAST:event_formWindowClosing

    //<editor-fold defaultstate="collapsed" desc="Android Tab">
    /**
     * Starts ADB server.
     *
     * @param evt
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Starting ADB server... ");
                    adbController.startServer();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while trying to start the server!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Stops ADB server.
     *
     * @param evt
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Stopping ADB server... ");
                    adbController.stopServer();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while trying to stop server!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * Restarts ADB server.
     *
     * @param evt
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Restarting ADB server... ");
                    adbController.restartServer();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while trying to restart ADB server!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * Allows users to execute custom ADB commands. Can be quite useful.
     *
     * @param evt
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        log.log(LogLevel.INFO, "Loading custom ADB command execution window...");
        ExecuteCMD execute = new ExecuteCMD(adbController, log);
        execute.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * Allows users to connect wirelessly to their device. This feature is
     * pretty experimental, as I don't know if all the information will be
     * processed. We'll see.
     *
     * @param evt
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        log.log(LogLevel.FINE, "Loading TCP connection utility...");
        ConnectDeviceTCP connect = new ConnectDeviceTCP(adbController, log);
        connect.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Loads all connected devices. The first device, will also have battery
     * stats loaded.
     *
     * @param evt
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        log.log(LogLevel.FINE, "Loading devices and battery level of device[0]...");
        getADBDevs();
        getFastbootDevs();
        getBattery(jList1.getSelectedValue().toString());
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * Allows user to easily reboot device to Android OS.
     *
     * @param evt
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Rebooting device " + jList1.getSelectedValue().toString() + " to Android OS.");
                try {
                    log.log(LogLevel.INFO, "ADB Output" + adbController.rebootDevice(jList1.getSelectedValue().toString(), RebootTo.ANDROID));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device to Android OS!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * Allows user to easily reboot device to recovery mode. (CWM/TWRP)
     *
     * @param evt
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Rebooting device " + jList1.getSelectedValue().toString() + " to Recovery Mode.");
                try {
                    log.log(LogLevel.INFO, "ADB Output" + adbController.rebootDevice(jList1.getSelectedValue().toString(), RebootTo.RECOVERY));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device to Recovery Mode!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * Allows user to easily reboot device to bootloader.
     *
     * @param evt
     */
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Rebooting device " + jList1.getSelectedValue().toString() + " to Android OS.");
                try {
                    log.log(LogLevel.INFO, "ADB Output" + adbController.rebootDevice(jList1.getSelectedValue().toString(), RebootTo.BOOTLOADER));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device to Android OS!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * Allows user to select an APK to install to device.
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        apkChooser.setFileFilter(new APKFilter());
        apkChooser.setApproveButtonText("Install this APK.");
        apkChooser.setDialogTitle("Select an Android Application Package (.apk) to install to device.");
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField1.setText(apkChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Installs selected APK to device.
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.FINE, "Installing application " + jTextField1.getText() + " to device " + jList1.getSelectedValue().toString());
                try {
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, false, jList1.getSelectedValue().toString(),
                            new String[]{"install", jTextField1.getText()}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while installing application!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Allows user to select any file/folder to push to the device and index[0]
     * on JList1.
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setApproveButtonText("Push this File...");
        fileChooser.setDialogTitle("Select a File/Folder to Push to Device...");
        int dialogRes = fileChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField2.setText(fileChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Pushes file/folder to device.
     *
     * @param evt
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Pushing File " + jTextField2.getText() + " to device. Prompting user for destination...");
                String destination = JOptionPane.showInputDialog(null, "Please enter the location on your device, to which you'd\nlike to push the file to.", "Enter File Destination", JOptionPane.INFORMATION_MESSAGE);
                try {
                    log.log(LogLevel.INFO, "ADB Output:\n" + adbController.executeADBCommand(false, false, jList1.getSelectedValue().toString(),
                            new String[]{"push", jTextField2.getText(), destination}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while pushing file to device " + jList1.getSelectedValue().toString() + "!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Allows user to select a destination on the computer, for the file to pull
     * from device.
     *
     * @param evt
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setApproveButtonText("Pull the File Here...");
        fileChooser.setDialogTitle("Select a File/Folder to Push Pulled File to...");
        int dialogRes = fileChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField2.setText(fileChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * Pulls selected file from device.
     *
     * @param evt
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Pushing File " + jTextField3.getText() + " to device. Prompting user for file to pull...");
                String file = JOptionPane.showInputDialog(null, "Please enter the file on your device, to which you'd\nlike to pull to your computer.", "Enter File Source", JOptionPane.INFORMATION_MESSAGE);
                try {
                    log.log(LogLevel.INFO, "ADB Output:\n" + adbController.executeADBCommand(false, false, jList1.getSelectedValue().toString(),
                            new String[]{"pull", file, jTextField3.getText()}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while pushing file to device " + jList1.getSelectedValue().toString() + "!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton6ActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fastboot Tab">
    /**
     * Allows user to select a recovery image (.img) to flash to device via
     * fastboot.
     *
     * @param evt
     */
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        JFileChooser imgChooser = new JFileChooser();
        imgChooser.setApproveButtonText("Flash this recovery image.");
        imgChooser.setDialogTitle("Select a Recovery Image...");
        imgChooser.setFileFilter(new IMGFilter());
        int dialogRes = imgChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField4.setText(imgChooser.getAcceptAllFileFilter().toString());
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * Flashes selected recovery image to device.
     *
     * @param evt
     */
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.INFO, "Flashing image " + jTextField4.getText() + " to device (fastboot) " + jList2.getSelectedValue().toString());
                String serial = jList2.getSelectedValue().toString();
                try {
                    log.log(LogLevel.INFO, "Fastboot Output: " + adbController.executeFastbootCommand(serial,
                            new String[]{"flash", "recovery", jTextField4.getText()}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while flashing recovery image to device " + jList2.getSelectedValue().toString() + "!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
     * Loads all devices connected via fastboot. Does not load battery
     * information.
     *
     * @param evt
     */
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        getFastbootDevs();
    }//GEN-LAST:event_jButton18ActionPerformed

    /**
     * Allows user to execute custom fastboot commands.
     *
     * @param evt
     */
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        ExecuteFastbootCMD execute = new ExecuteFastbootCMD(adbController, log);
        execute.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * Reboots device from fastboot to Android.
     *
     * @param evt
     */
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        new Thread() {
            @Override
            public void run() {
                String serial = jList2.getSelectedValue().toString();
                try {
                    log.log(LogLevel.INFO, "Rebooting device" + serial + " from fastboot to Android.");
                    adbController.rebootDeviceFastboot(serial, RebootTo.ANDROID);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device from fastboot to Android!");
                }
            }
        }.start();
    }//GEN-LAST:event_jButton20ActionPerformed

    /**
     * Reboots device from fastboot to Recovery (I think...).
     *
     * @param evt
     */
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        new Thread() {
            @Override
            public void run() {
                String serial = jList2.getSelectedValue().toString();
                try {
                    log.log(LogLevel.INFO, "Rebooting device" + serial + " from fastboot to Recovery.");
                    adbController.rebootDeviceFastboot(serial, RebootTo.RECOVERY);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device from fastboot to Recovery!");
                }
            }
        }.start();
    }//GEN-LAST:event_jButton21ActionPerformed

    /**
     * Reboots device's bootloader.
     *
     * @param evt
     */
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        new Thread() {
            @Override
            public void run() {
                String serial = jList2.getSelectedValue().toString();
                try {
                    log.log(LogLevel.INFO, "Rebooting device" + serial + " from fastboot to Android.");
                    adbController.rebootDeviceFastboot(serial, RebootTo.BOOTLOADER);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device from fastboot to bootloader!");
                }
            }
        }.start();
    }//GEN-LAST:event_jButton22ActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ADB Menu">
    /**
     * Starts the ADB server.
     *
     * @param evt
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jButton7.doClick();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Stops the ADB server.
     *
     * @param evt
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jButton8.doClick();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * Allows user to execute custom commands.
     *
     * @param evt
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jButton10.doClick();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Allows user to connect to device via TCP.
     *
     * @param evt
     */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jButton11.doClick();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * Loads all connected devices.
     *
     * @param evt
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jButton12.doClick();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * Reboots device to Android.
     *
     * @param evt
     */
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jButton13.doClick();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Reboots device to recovery.
     *
     * @param evt
     */
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        jButton14.doClick();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * Reboots device to bootloader.
     *
     * @param evt
     */
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jButton15.doClick();
    }//GEN-LAST:event_jMenuItem8ActionPerformed
     //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fastboot Menu">
    /**
     * Allows user to execute command.
     *
     * @param evt
     */
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        jButton18.doClick();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * Loads all connected fastboot devices.
     *
     * @param evt
     */
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        jButton19.doClick();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * Reboots device to Android.
     *
     * @param evt
     */
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        jButton20.doClick();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * Reboots device's bootloader.
     *
     * @param evt
     */
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        jButton22.doClick();
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    //</editor-fold>

    /**
     * Opens up Settings menu.
     *
     * @param evt
     */
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new SettingsUI(settings, log).setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
