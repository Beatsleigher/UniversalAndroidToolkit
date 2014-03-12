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

import java.io.*;
import javax.swing.*;
import java.util.*;

import JDroidLib.android.controllers.*;
import JDroidLib.android.device.*;
import JDroidLib.enums.RebootTo;

import eu.m4gkbeatz.androidtoolkit.enums.*;
import eu.m4gkbeatz.androidtoolkit.io.*;
import eu.m4gkbeatz.androidtoolkit.backup.*;
import eu.m4gkbeatz.androidtoolkit.logging.*;
import eu.m4gkbeatz.androidtoolkit.settings.*;

/**
 * Please remember this, before reading this code:
 * I am terribly sorry for anyone, reading this - I'm a very messy guy, 
 * I don't like tidying up, and I've been too lazy to do it now -- I might clean up
 * after the first Release Candidate, but I'm not too sure;
 * The code might stay this messy until I die (which will take another few years.
 * Before you ask anything about my humble self, please read the information provided below:
 * @author beatsleigher ← That's me!
 * I am 17 years old at the time of writing this, I'm shit in school, because I'm dyscalculic, 
 * I'm a good developer, but can't find a job. I want to learn more, but still can't find a job.
 * I got this program done in under a week, including completely re-writing JDroidLib.
 * All I ask, is for a job so I can do what I love.
 * 
 * To anyone reading this:
 * Sorry if it didn't interest you. I am getting quite bored, and after
 * about 20 job applications, I'm going to go any way to get a job as a professional developer.
 */
public class AdvancedUI extends JFrame {

    ////////////////////////////////////
    ////////// TO-DO //////////////////
    // Add Backup             ////////
    // Add Restore           ////////
    // Add ADB Sideload     ////////
    // Add EFS-Backup      ////////
    // Add Kitchen        ////////
    // Finish German     ////////
    // translation      ////////
    ///////////////////////////
    
    ADBController adbController = null;
    FastbootController fbController = null;
    Logger log = null;
    SettingsManager settings = null;
    Device device = null;
    Battery battery = null;
    CPU cpu = null;
    SU su = null;
    BusyBox busyBox = null;
    BuildProp buildProp = null;
    AdvancedDevices devices = null;
    BackupEngine backupEngine = null;
    int usedPushes = 0;

    //<editor-fold defaultstate="collapsed" desc="Construction">
    /**
     * Creates new form AdvancedUI
     *
     * @param settings
     * @param log
     * @param adbController
     * @param language The language of the components.
     * @param backupEngine The engine to handle all the backup IO for Android devices.
     */
    public AdvancedUI(SettingsManager settings, Logger log, ADBController adbController, Language language, BackupEngine backupEngine) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/icon.png")).getImage());
        this.log = log;
        this.settings = settings;
        this.adbController = adbController;
        this.fbController = adbController.getFastbootController();
        devices = new AdvancedDevices(adbController, log);
        devices.setVisible(true);
        this.setTitle("Advanced UI | Welcome, " + System.getProperty("user.name") + ". | Universal Android Toolkit");
        log.log(LogLevel.INFO, "Starting ADB server...");
        try {
            adbController.startServer();
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error starting ADB server! Please manually start the ADB server!\n" + ex.toString());
        }
        setLang(language);
        this.backupEngine = backupEngine;
    }
    
    public void setLang(Language lang) {
        log.log(LogLevel.INFO, "Settings component translation to " + lang + ". Please be patient...");
        switch (lang) {
            case GERMAN:
                //<editor-fold defaultstate="collapsed" desc="Set language to English">
                jButton1.setText("Auswählen");
                jButton2.setText("Installieren");
                jButton3.setText("Auswählen");
                jButton4.setText("Deinstallieren");
                jButton5.setText("Auswählen");
                jButton6.setText("Kopieren");
                jButton7.setText("Auswählen");
                jButton8.setText("Ziehen");
                jButton9.setText("Auswählen");
                jButton10.setText("Installieren");
                jButton11.setText("Auswählen");
                jButton12.setText("Deinstallieren");
                jButton13.setText("Server Starten");
                jButton14.setText("Server Stoppen");
                jButton15.setText("Server Neustarten");
                jButton16.setText("Befehl Ausführen");
                jButton17.setText("Via TCP Verbinden");
                jButton18.setText("Geräte Laden");
                jLabel1.setText("App Installieren");
                jLabel2.setText("App Deinstallieren");
                jLabel3.setText("Datei Kopieren");
                jLabel4.setText("Datei Ziehen");
                jLabel5.setText("App Installieren (Root)");
                jLabel6.setText("App Deinstallieren (root)");
                //</editor-fold>
                break;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code. Don't even try. You'll fail miserably...">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        batteryLevel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        busybox = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        root = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        cpuLoad = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        refreshStats = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton45 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        batPoweredByAC = new javax.swing.JLabel();
        batPoweredByUSB = new javax.swing.JLabel();
        batPoweredWirelessly = new javax.swing.JLabel();
        batStatus = new javax.swing.JLabel();
        batHealth = new javax.swing.JLabel();
        batIsPresent = new javax.swing.JLabel();
        batScale = new javax.swing.JLabel();
        batVoltage = new javax.swing.JLabel();
        batCurrent = new javax.swing.JLabel();
        batTemp = new javax.swing.JLabel();
        batTechnology = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        batLevel = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        suInstalled = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        suVersion = new javax.swing.JLabel();
        busyboxInstalled = new javax.swing.JLabel();
        busyboxVersion = new javax.swing.JLabel();
        jButton36 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        cpu_Load = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton37 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator20 = new javax.swing.JSeparator();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel16.setText("jLabel16");

        jTextField14.setText("jTextField14");

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Advanced UI | Welcome, <user>. | Universal Android Toolkit");
        setMinimumSize(new java.awt.Dimension(800, 530));
        setPreferredSize(new java.awt.Dimension(800, 530));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setPreferredSize(new java.awt.Dimension(560, 35));

        batteryLevel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png"))); // NOI18N
        batteryLevel.setText("n/a");
        batteryLevel.setToolTipText("");
        jToolBar1.add(batteryLevel);
        jToolBar1.add(jSeparator1);

        busybox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/busybox-32.png"))); // NOI18N
        busybox.setText("BusyBox: n/a");
        jToolBar1.add(busybox);
        jToolBar1.add(jSeparator2);

        root.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/su-32.png"))); // NOI18N
        root.setText("Root: n/a");
        jToolBar1.add(root);
        jToolBar1.add(jSeparator3);

        cpuLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/cpu-32.png"))); // NOI18N
        cpuLoad.setText("CPU Load: n/a");
        jToolBar1.add(cpuLoad);
        jToolBar1.add(jSeparator4);

        refreshStats.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/data_controls/refresh-32.png"))); // NOI18N
        refreshStats.setFocusable(false);
        refreshStats.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshStats.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshStatsActionPerformed(evt);
            }
        });
        jToolBar1.add(refreshStats);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Install App");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 15));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Uninstall App");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 15));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Push File");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 15));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pull File");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 15));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Install App (Root)");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 15));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Uninstall App (Root)");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 15));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextField1.setPreferredSize(new java.awt.Dimension(120, 21));

        jTextField2.setPreferredSize(new java.awt.Dimension(120, 21));

        jTextField3.setPreferredSize(new java.awt.Dimension(120, 21));

        jTextField4.setPreferredSize(new java.awt.Dimension(120, 21));

        jTextField5.setPreferredSize(new java.awt.Dimension(120, 21));

        jTextField6.setPreferredSize(new java.awt.Dimension(120, 21));

        jButton1.setText("Select...");
        jButton1.setToolTipText("");
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

        jButton3.setText("Select...");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Select...");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Push");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Select...");
        jButton7.setToolTipText("");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Pull");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Select...");
        jButton9.setToolTipText("");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Install");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Select...");
        jButton11.setToolTipText("");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Remove");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Start Server");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Stop Server");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Restart Server");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Execute CMD");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Connect via TCP");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Load Devices");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18))
                            .addComponent(jSeparator11))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(496, 496, 496)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jButton3)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton4))
                                        .addGap(20, 20, 20)
                                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jButton10)
                                            .addComponent(jButton9)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jButton12)
                                            .addComponent(jButton11)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                    .addComponent(jButton6)
                                                    .addComponent(jButton5)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7)
                                    .addComponent(jButton8))))
                        .addContainerGap(79, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12))))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("Android", jPanel1);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Coming Soon!");

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.setPreferredSize(new java.awt.Dimension(174, 104));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Backup");

        jButton42.setText("Backup");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton42)
                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton42)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setPreferredSize(new java.awt.Dimension(174, 104));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Restore");

        jButton43.setText("Select");

        jButton44.setText("Restore");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton44))
                    .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton43)
                    .addComponent(jButton44))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("EFS-Backup (Sammy Only)");

        jButton45.setText("Backup");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton45)
                    .addComponent(jTextField18))
                .addContainerGap())
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel23.setPreferredSize(new java.awt.Dimension(174, 104));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("EFS-Restore (Sammy Only)");

        jButton46.setText("Select");

        jButton47.setText("Restore");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jButton46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton47))
                    .addComponent(jTextField19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton46)
                    .addComponent(jButton47))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel24.setPreferredSize(new java.awt.Dimension(350, 104));

        jScrollPane3.setViewportView(jList2);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel25.setPreferredSize(new java.awt.Dimension(350, 104));

        jScrollPane4.setViewportView(jList3);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel25.setText("List of System Backups");

        jLabel26.setText("List of EFS-Backups");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel26)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Advanced Android", jPanel2);

        jButton20.setText("Flash");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton19.setText("Select Image");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Flash Image");
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 15));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jButton20)
                        .addComponent(jButton19)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton21.setText("Format");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Format Partition");
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 15));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "userdata", "recovery", "system", "boot" }));
        jComboBox1.setSelectedIndex(1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton21)
                    .addComponent(jComboBox1, 0, 138, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addGap(49, 49, 49))
        );

        jButton22.setText("Update");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Select Update");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Update Device");
        jLabel10.setPreferredSize(new java.awt.Dimension(120, 15));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton23)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton25.setText("Get Var!");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Get Variable");
        jLabel11.setPreferredSize(new java.awt.Dimension(120, 15));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton25)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Flash All");

        jLabel13.setText("Boot Image: ");

        jLabel14.setText("Recovery Image: ");

        jLabel15.setText("System Image:");

        jButton24.setText("...");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton26.setText("...");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText("...");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("Flash Device");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton28)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(38, 38, 38)
                                .addComponent(jTextField11))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(27, 27, 27)
                                .addComponent(jTextField13))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton24, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton27, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton29.setText("Execute Custom Command");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("Load Devices");
        jButton30.setPreferredSize(new java.awt.Dimension(160, 25));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 25, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator16, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator12)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator15))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fastboot", jPanel3);

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Flash Bootloader");

        jButton31.setText("Select Image");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setText("Flash Image");
        jButton32.setPreferredSize(new java.awt.Dimension(82, 25));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton31)
                    .addComponent(jTextField15)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Flash Modem");

        jButton33.setText("Select Image");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("Flash Image");
        jButton34.setPreferredSize(new java.awt.Dimension(82, 25));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton33)
                    .addComponent(jTextField16)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("WARNING: The use of these features may potentially BRICK your device, rendering it useless!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator17)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(221, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Advanced Fastboot", jPanel4);

        batPoweredByAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/plug_yellow-32.png"))); // NOI18N
        batPoweredByAC.setText("Powered by AC:  n/a");

        batPoweredByUSB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/usb_power-32.png"))); // NOI18N
        batPoweredByUSB.setText("Powered by USB: n/a");

        batPoweredWirelessly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_wireless_charge-32.png"))); // NOI18N
        batPoweredWirelessly.setText("Powered Wirelessly: n/a");

        batStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png"))); // NOI18N
        batStatus.setText("Battery Status: n/a");

        batHealth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_healthy-32.png"))); // NOI18N
        batHealth.setText("Battery Health: n/a");

        batIsPresent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png"))); // NOI18N
        batIsPresent.setText("Battery is Present: n/a");

        batScale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_scale-32.png"))); // NOI18N
        batScale.setText("Battery Scale: n/a");

        batVoltage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_voltage-32.png"))); // NOI18N
        batVoltage.setText("Battery Voltage: n/a");

        batCurrent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_current-32.png"))); // NOI18N
        batCurrent.setText("(Current) Current: n/a");

        batTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_temp_cold-32.png"))); // NOI18N
        batTemp.setText("Battery Temperature: n/a");

        batTechnology.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_technology-32.png"))); // NOI18N
        batTechnology.setText("Technology: n/a");

        jButton35.setText("Reload");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Reload Automatically");

        batLevel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png"))); // NOI18N
        batLevel.setText("Battery Level: n/a");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batPoweredByAC)
                    .addComponent(batPoweredByUSB)
                    .addComponent(batPoweredWirelessly)
                    .addComponent(batScale)
                    .addComponent(batVoltage)
                    .addComponent(batCurrent))
                .addGap(83, 83, 83)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batLevel)
                    .addComponent(batTechnology)
                    .addComponent(batTemp)
                    .addComponent(batIsPresent)
                    .addComponent(batHealth)
                    .addComponent(batStatus))
                .addContainerGap(259, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton35)
                    .addComponent(jToggleButton1))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batPoweredByAC)
                    .addComponent(batStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batPoweredByUSB)
                    .addComponent(batHealth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batIsPresent)
                    .addComponent(batPoweredWirelessly))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batScale)
                    .addComponent(batTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batVoltage)
                    .addComponent(batTechnology))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batCurrent)
                    .addComponent(batLevel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Battery Information", jPanel16);

        suInstalled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/su-32.png"))); // NOI18N
        suInstalled.setText("SU Installed: n/a");

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator19.setPreferredSize(new java.awt.Dimension(10, 50));

        suVersion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/su-32.png"))); // NOI18N
        suVersion.setText("SU Version: n/a");

        busyboxInstalled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/busybox-32.png"))); // NOI18N
        busyboxInstalled.setText("BusyBox Installed: n/a");

        busyboxVersion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/system/busybox-32.png"))); // NOI18N
        busyboxVersion.setText("BusyBox Version: n/a");

        jButton36.setText("Reload");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(suInstalled)
                            .addComponent(suVersion))
                        .addGap(215, 215, 215)
                        .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busyboxInstalled)
                            .addComponent(busyboxVersion))
                        .addGap(0, 164, Short.MAX_VALUE))
                    .addComponent(jSeparator18)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton36)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(suInstalled)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suVersion))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(busyboxInstalled)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busyboxVersion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton36)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Root Information", jPanel17);

        cpu_Load.setText("CPU Load: n/a");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "n/a" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton37.setText("Reload");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel20.setText("Past CPU Load:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton37))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpu_Load)
                            .addComponent(jLabel20))
                        .addGap(0, 604, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cpu_Load)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jButton37)
                .addContainerGap())
        );

        jTabbedPane2.addTab("CPU Information", jPanel18);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Coming Soon!");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("FileSystem", jPanel19);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Device Info", jPanel5);

        jTextArea1.setBackground(new java.awt.Color(0, 0, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton38.setText("Reload From Device");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setText("Reload From File");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setText("Save to Device");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setText("Save to Hard Drive");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton39)
                    .addComponent(jButton38)
                    .addComponent(jButton40)
                    .addComponent(jButton41))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jButton38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton41)
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator20)
                    .addComponent(jScrollPane2))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Build Properties", jPanel6);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Coming Soon!");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addContainerGap(357, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Device Tweaker", jPanel7);

        jMenu1.setText("ADB");

        jMenuItem1.setText("Start Server");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Stop Server");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Restart Server");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Execute Command");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Connect via TCP");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Load Devices");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenu4.setText("Reboot To...");

        jMenuItem7.setText("Android");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Recovery");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Bootloader");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenu1.add(jMenu4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Fastboot");

        jMenuItem10.setText("Execute Command");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Load Devices");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenu5.setText("Reboot To...");

        jMenuItem13.setText("Android");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setText("Fastboot");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("UAT");

        jMenuItem12.setText("Open Settings");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Window Events">
    /**
     * Closes and saves logs.
     *
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int dialogRes = JOptionPane.showConfirmDialog(null, "WARNING: By exiting this program, you will terminate all instances of ADB,\n"
                + "thus potentially leading to data loss if one or more operations haven't completed!\n"
                + "Are you sure you wish to exit?", "Are you sure?", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if (dialogRes == JOptionPane.CANCEL_OPTION) 
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        else
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jMenuItem2.doClick();
        log.close();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Once this window is open, it will check for any settings and act
     * accordingly.
     *
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (settings.checkForDevicesOnStartup()) {
            getDevices();
        }
    }//GEN-LAST:event_formWindowOpened
    //</editor-fold>  

    //<editor-fold defaultstate="collapsed" desc="Android Tab">
    /**
     * Allows user to select an APK to install to device.
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        apkChooser.setDialogTitle("Select an APK File to Install to your Device.");
        apkChooser.setApproveButtonText("Install this Application.");
        apkChooser.setFileFilter(new APKFilter());
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField1.setText(apkChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Installs selected APK to user device.
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        log.log(LogLevel.INFO, "Preparing to install app " + jTextField1.getText() + " to device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.installApplication(false, devices.getSelectedADBDevice().toString(), jTextField1.getText()));
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error installing application to device!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * (Will soon) allow users to browse device and select an APK to uninstall.
     * This feature is not yet available.
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "INFORMATION: Sorry, this feature is not yet available in this version\n"
                + "of Universal Android Toolkit. Please be patient. You may still enter the application manually to uninstall.", "Feature not Implemented.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Uninstalls a selected application from device.
     *
     * @param evt
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        log.log(LogLevel.FINE, "Preparing to uninstall application " + jTextField2.getText() + " from device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.FINE, "ADB Output: " + adbController.uninstallApplication(false, devices.getSelectedADBDevice().toString(), jTextField2.getText()));
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while uninstalling application from device!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Allows user to select a file or folder from hard drive and then push it
     * to the selected device.
     *
     * @param evt
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a File or Folder to Push to Device...");
        fileChooser.setApproveButtonText("Push this File.");
        int dialogRes = fileChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField3.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * Attempts to push selected file to selected device.
     *
     * @param evt
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        log.log(LogLevel.INFO, "Preparing to push file " + jTextField3.getText() + " to device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    boolean remount = false;
                    log.log(LogLevel.FINE, "Prompting user to select destination...");
                    String dest = JOptionPane.showInputDialog(null, "Please enter the location on the device, to push the file to, below.", "Where Should I Push the File to?", JOptionPane.QUESTION_MESSAGE);
                    if (dest.contains("/system") || dest.contains("/data") || dest.contains("/dev") || dest.contains("/etc") || dest.contains("/proc") || dest.contains("/efs") || dest.contains("/root") || dest.contains("/sys")) {
                        remount = true;
                    }
                    log.log(LogLevel.INFO, "The selected file will be pushed to: " + dest + ".\n"
                            + "The filesystem will be remounted: " + remount + ".\n"
                            + "Pushing file...");
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, remount, devices.getSelectedADBDevice().toString(), new String[]{"push", jTextField3.getText(), dest}));
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while pushing file to device!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * Will soon allow users to select a file from the device, and pull said
     * file to the hard drive.
     *
     * @param evt
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JOptionPane.showMessageDialog(null, "INFORMATION: This feature is not yet available.\n"
                + "However, you can still type the location manually and pull the file that way.", "Feature not yet Implemented.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Pushes selected file to selected device.
     *
     * @param evt
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        log.log(LogLevel.INFO, "Preparing to pull file " + jTextField4.getText() + " from device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                String dest = JOptionPane.showInputDialog(null, "Please enter the file destination (on your hard drive) below:", "Where Should I Pull the File?", JOptionPane.QUESTION_MESSAGE);
                try {
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, false, devices.getSelectedADBDevice().toString(), new String[]{"pull", jTextField4.getText(), dest}));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while pulling file from device!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * Allows user to select an application file on the hard drive to install
     * (as root app) to device.
     *
     * @param evt
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        apkChooser.setDialogTitle("Select an APK File to Install to your Device.");
        apkChooser.setApproveButtonText("Install this Application.");
        apkChooser.setFileFilter(new APKFilter());
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField1.setText(apkChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * Installs selected application to system partition on device.
     *
     * @param evt
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        log.log(LogLevel.INFO, "Preparing to install app " + jTextField1.getText() + " to device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.installApplication(true, devices.getSelectedADBDevice().toString(), jTextField5.getText()));
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error installing application to device!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * Will soon allow user to select an application from device and delete it.
     *
     * @param evt
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        JOptionPane.showMessageDialog(null, "INFORMATION: Sorry, this feature is not yet available in this version\n"
                + "of Universal Android Toolkit. Please be patient. You may still enter the application manually to uninstall.", "Feature not Implemented.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Uninstalls selected application from device.
     *
     * @param evt
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        log.log(LogLevel.FINE, "Preparing to uninstall application " + jTextField2.getText() + " from device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.FINE, "ADB Output: " + adbController.uninstallApplication(true, devices.getSelectedADBDevice().toString(), jTextField6.getText()));
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while uninstalling application from device!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * Starts ADB Server
     *
     * @param evt
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        log.log(LogLevel.INFO, "Starting ADB server...");
        new Thread() {
            @Override
            public void run() {
                try {
                    adbController.startServer();
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error starting ADB server!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * Stops ADB server.
     *
     * @param evt
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        log.log(LogLevel.INFO, "Stopping ADB server...");
        new Thread() {
            @Override
            public void run() {
                try {
                    adbController.stopServer();
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while stopping ADB server!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * Restarts ADB server.
     *
     * @param evt
     */
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        log.log(LogLevel.INFO, "Restarting ADB server...");
        new Thread() {
            @Override
            public void run() {
                try {
                    adbController.restartServer();
                    interrupt();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while restarting ADB server!\n" + ex.toString());
                    interrupt();
                }
            }
        }.start();
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * Opens dialog, which allows user to execute custom ADB commands.
     *
     * @param evt
     */
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        new ExecuteCMD(adbController, log).setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * Opens dialog which allows user to connect wirelessly to a device of
     * choice.
     *
     * @param evt
     */
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        new ConnectDeviceTCP(adbController, log).setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
     * Loads new list of connected devices.
     *
     * @param evt
     */
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        getDevices();
    }//GEN-LAST:event_jButton18ActionPerformed
    //</editor-fold>

    private void refreshStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshStatsActionPerformed
        loadDeviceInfo(devices.getSelectedADBDevice().toString());
    }//GEN-LAST:event_refreshStatsActionPerformed

    //<editor-fold defaultstate="collapsed" desc="Fastboot Tab">
    /**
     * Initiates and opens a file chooser, allowing the user to select an image
     * file, to flash to the device.
     *
     * @param evt
     */
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select an Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField7.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * Attempts to determine type of selected image and if image type could not
     * be determined, an input dialog will be thrown, prompting the user to
     * specify the image type.
     *
     * @param evt
     */
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        new Thread() {
            @Override
            public void run() {
                String img = jTextField7.getText().toLowerCase();
                String imgType = "";
                String deviceSerial = devices.getSelectedFastbootDevice().toString();
                /*Log*/ log.log(LogLevel.INFO, "Flashing image " + img + " to device " + deviceSerial + ". Detecting image type.");
                String[] cmds = null;
                // Detect image type.
                if ((img.contains("recovery")) || (img.contains("cwm")) || (img.contains("twrp"))) {
                    imgType = "recovery";
                } else if (img.contains("system")) {
                    imgType = "system";
                } else if ((img.contains("boot")) || img.contains("kernel")) {
                    imgType = "boot";
                } else if (img.contains("userdata")) {
                    imgType = "userdata";
                } else if ((img.contains("radio")) || (img.contains("modem"))) {
                    imgType = "radio";
                } else {
                    imgType = JOptionPane.showInputDialog(null, "WARNING: Image type could not be identified! Please enter the image type below:\n"
                            + "You have following choices:\n"
                            + "recovery\n"
                            + "boot\n"
                            + "userdata\n"
                            + "system\n"
                            + "radio\n"
                            + "bootloader", "Could not Identify Image!", JOptionPane.WARNING_MESSAGE);
                }
                /*Log*/ log.log(LogLevel.INFO, "Detected image type: " + imgType);
                // Prepare commands and flash image.
                try {
                    cmds = new String[]{"flash", imgType};
                    log.log(LogLevel.INFO, "Fastboot output: " + fbController.executeFastbootCommand(deviceSerial, cmds));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error flashing image to device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton20ActionPerformed

    /**
     * Formats selected partition on device.
     *
     * @param evt
     */
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        log.log(LogLevel.WARNING, "Formatting fastboot partition " + jComboBox1.getSelectedItem() + " on device " + devices.getSelectedFastbootDevice() + "!");
        try {
            log.log(LogLevel.INFO, "Fastboot output: " + fbController.executeFastbootCommand(devices.getSelectedFastbootDevice().toString(), new String[]{"format", jComboBox1.getSelectedItem().toString()}));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while formatting partition!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    /**
     * Opens a file chooser, allowing the user to select a .ZIP file to update
     * the device from.
     *
     * @param evt
     */
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        JFileChooser zipChooser = new JFileChooser();
        zipChooser.setDialogTitle("Select an Update Package to Update your Device from.");
        zipChooser.setApproveButtonText("Use this File to Update my Device");
        zipChooser.setFileFilter(new ZipFilter());
        int dialogRes = zipChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            jTextField8.setText(zipChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    /**
     * Updates connected device via selected update package.
     *
     * @param evt
     */
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        new Thread() {
            @Override
            public void run() {
                log.log(LogLevel.FINE, "Updating device " + devices.getSelectedFastbootDevice() + " via update package " + jTextField8.getText());
                try {
                    log.log(LogLevel.INFO, "Fastboot output: " + fbController.executeFastbootCommand(devices.getSelectedFastbootDevice().toString(), new String[]{"update", jTextField8.getText()}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error updating device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton22ActionPerformed

    /**
     * Gets a device variable from connected device.
     *
     * @param evt
     */
    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Fastboot variable: " + fbController.executeFastbootCommand(devices.getSelectedFastbootDevice().toString(), new String[]{"getvar", jTextField9.getText()}));
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error retrieving variable from device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton25ActionPerformed

    /**
     * Allows user to select boot image to flash to device.
     *
     * @param evt
     */
    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select a Boot Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField11.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    /**
     * Allows user to select a recovery image to flash to device.
     *
     * @param evt
     */
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select a Recovery Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField12.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    /**
     * Allows user to select a system image to flash to device.
     *
     * @param evt
     */
    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select a System Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField13.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    /**
     * Flashes selected images to device.
     *
     * @param evt
     */
    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        new Thread() {
            @Override
            @SuppressWarnings("UseSpecificCatch")
            public void run() {
                try {
                    String img1 = jTextField11.getText();
                    String img2 = jTextField12.getText();
                    String img3 = jTextField13.getText();
                    String device = devices.getSelectedFastbootDevice().toString();
                    log.log(LogLevel.INFO, "Preparing to flash images " + img1 + ", " + img2 + " and " + img3 + " to device " /*+ device*/);
                    if (!(img1.equals("") && img2.equals("") && img3.equals(""))) {
                        log.log(LogLevel.INFO, "All files were found and are present. Flashing device...");
                        log.log(LogLevel.INFO, "Fastboot output: " + fbController.executeFastbootCommand(device, new String[]{"flashall"}));
                        log.log(LogLevel.FINE, "Flashing was completed. Rebooting device...");
                        log.log(LogLevel.INFO, "Fastboot output: " + fbController.rebootDeviceFastboot(device, RebootTo.ANDROID));
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: Some or all of the files could not be found!\n"
                                + "Please make sure you select all the files needed, before attempting to flash your device!", "File Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {/*Your IDE will say this is too broad, but trust me. You'll love me for this.*/
                    log.log(LogLevel.SEVERE, "ERROR: Error flashing images to device!\n" + ex.toString() + "\nDid you connect your device to the computer?");
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton28ActionPerformed

    /**
     * Allows user to execute custom fastboot command via command dialog.
     * @param evt 
     */
    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        new ExecuteFastbootCMD(adbController, log).setVisible(true);
    }//GEN-LAST:event_jButton29ActionPerformed

    /**
     * Loads all connected device and displays them in the list window.
     * @param evt 
     */
    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        getDevices();
    }//GEN-LAST:event_jButton30ActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Advanced Fastboot Tab">
    /**
     * Allows user to select an image file (bootloader) to flash to device.
     * @param evt 
     */
    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select a Bootloader Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField15.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        log.log(LogLevel.WARNING, "WARNING: Flashing device (" + devices.getSelectedFastbootDevice() + ") bootloader with image " + jTextField15.getText() + "!");
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Fastboot Output: " + fbController.executeFastbootCommand(devices.getSelectedFastbootDevice().toString(), new String[]{"flash", "bootloader", jTextField15.getText()}));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while flashing device bootloader!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton32ActionPerformed

    /**
     * Allows user to select an image (modem/radio) to flash to device.
     * @param evt 
     */
    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select a Bootloader Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img"))) {
            jTextField16.setText(imageChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    /**
     * Flashes selected (radio/modem) to selected device.
     * @param evt 
     */
    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        log.log(LogLevel.WARNING, "WARNING: Flashing radio (" + jTextField16.getText() + ") to device " + devices.getSelectedFastbootDevice());
        new Thread() {
            @Override
            @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Fastboot Output: " + fbController.executeFastbootCommand(devices.getSelectedFastbootDevice().toString(), new String[]{"flash", "radio", jTextField16.getText()}));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error flashing new modem to device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jButton34ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Device Information Tab">
    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        loadAdvancedBatteryInfo();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        loadRootInfo();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        loadCPUInfo();
    }//GEN-LAST:event_jButton37ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="BuildProp Tab">
    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Pulling build properties from device " + devices.getSelectedADBDevice());
                    String buildProp = adbController.getDevice(devices.getSelectedADBDevice().toString()).getBuildProp().getProp();
                    jTextArea1.setText(buildProp);
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error getting build properties from device " + devices.getSelectedADBDevice() + "!\n" + ex.toString());
                }
            }
        }.start();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        JFileChooser propChooser = new JFileChooser();
        propChooser.setDialogTitle("Select an Android Build Properties File...");
        propChooser.setApproveButtonText("Open this File...");
        propChooser.setFileFilter(new PropFilter());
        int dialogRes = propChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(propChooser.getSelectedFile()));
                String line = "";
                while ((line  = reader.readLine()) != null) {
                    jTextArea1.append(line + "\n");
                }
                reader.close();
            } catch (IOException ex) {
                log.log(LogLevel.SEVERE, "ERROR: Error while reading file " + propChooser.getSelectedFile().toString() + "!\n" + ex.toString());
            }
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        log.log(LogLevel.INFO, "Preparing to push new build.prop file to device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                File f = new File(System.getProperty("user.home") + "/AndroidToolkit/build.prop");
                try {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                    writer.write(jTextArea1.getText());
                    writer.close();
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, true, devices.getSelectedADBDevice().toString(), new String[]{"push", f.toString(), "/system/build.prop"}));
                    f.delete();
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while saving build prop to device " + devices.getSelectedADBDevice().toString() + "!\n" + ex.toString());
                }
        }
        }.start();
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        File f = new File(System.getProperty("user.home") + "/AndroidToolkit/build.prop");
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(jTextArea1.getText());
            writer.close();
            JOptionPane.showMessageDialog(null, "INFORMATION: Your build.prop file has been saved to: " + f.toString(), "File has Been Saved!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while saving build prop to file!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton41ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ADB Menu">
    /**
     * Starts the ADB server.
     * @param evt 
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jButton13.doClick();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Stops the ADB server.
     * @param evt 
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jButton14.doClick();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * Restarts the ADB server
     * @param evt 
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jButton15.doClick();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Opens dialog, allowing user to execute custom command.
     * @param evt 
     */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jButton16.doClick();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * Opens a dialog, allowing user to connect to device via TCP.
     * @param evt 
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jButton17.doClick();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * Loads connected devices.
     * @param evt 
     */
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jButton18.doClick();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Reboots device to Android.
     * @param evt 
     */
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Rebooting device"  + devices.getSelectedADBDevice() + " to Android..");  
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.rebootDevice(devices.getSelectedADBDevice().toString(), RebootTo.ANDROID));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * Reboots device to Recovery.
     * @param evt 
     */
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
                
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Rebooting device"  + devices.getSelectedADBDevice() + " to Recovery..");
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.rebootDevice(devices.getSelectedADBDevice().toString(), RebootTo.RECOVERY));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * Reboots device to Fastboot.
     * @param evt 
     */
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Rebooting device"  + devices.getSelectedADBDevice() + " to Bootloader..");
                    log.log(LogLevel.INFO, "ADB Output: " + adbController.rebootDevice(devices.getSelectedADBDevice().toString(), RebootTo.BOOTLOADER));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fastboot Menu">
    /**
     * Opens dialog, allowing user to execute custom command.
     * @param evt 
     */
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        jButton29.doClick();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * Loads devices.
     * @param evt 
     */
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        jButton30.doClick();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * Reboots device to Android.
     * @param evt 
     */
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Rebooting device " + devices.getSelectedFastbootDevice() + " to Android...");
                    log.log(LogLevel.INFO, "Fastboot output: " + fbController.rebootDeviceFastboot(devices.getSelectedFastbootDevice().toString(), RebootTo.ANDROID));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting the device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    /**
     * Reboots device to fastboot.
     * @param evt 
     */
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        new Thread() {
            @Override
            public void run() {
                try {
                    log.log(LogLevel.INFO, "Rebooting device " + devices.getSelectedFastbootDevice() + " to fastboot...");
                    log.log(LogLevel.INFO, "Fastboot output: " + fbController.rebootDeviceFastboot(devices.getSelectedFastbootDevice().toString(), RebootTo.BOOTLOADER));
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while rebooting the device!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem14ActionPerformed
    //</editor-fold>
    
    /**
     * Opens settings menu.
     * @param evt 
     */
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new SettingsUI(settings, log).setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    
    
    ///////////////////
    // Device Methods/
    //////////////////
    private void getDevices() {
        new Thread() {
            @Override
            public void run() {
                devices.loadDevices();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while sleeping thread!\n" + ex.toString());
                }
                if (settings.autoLoadDeviceInfo()) {
                    loadDeviceInfo(devices.getSelectedADBDevice().toString());
                    loadAdvancedBatteryInfo();
                    loadRootInfo();
                    loadCPUInfo();
                }
            }
        }.start();
    }

    ////////////////////
    // Information ////
    // Methods ///////
    /////////////////
    /**
     * Loads all device information that can be found in the status strip at the
     * bottom of the window. Touch this, and my friend, ().() (*.*) (")(") Mr.
     * Bunny will pay you a visit you won't like :) Just sayin'.
     *
     * @param deviceSerial
     */
    private void loadDeviceInfo(final String deviceSerial) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Device device = adbController.getDevice(deviceSerial);
                    int batLevel = device.getBattery().getLevel();
                    if (batLevel <= 25) {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_empty-32.png")));
                    } else if (batLevel <= 50) {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_quarter-32.png")));
                    } else if (batLevel <= 75) {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_half-32.png")));
                    } else if (batLevel < 100) {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_third-32.png")));
                    } else if (batLevel == 100) {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_full-32.png")));
                    } else {
                        batteryLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png")));
                    }
                    batteryLevel.setText(String.valueOf(batLevel) + "%");
                    busybox.setText("Busybox installed: " + device.getBusybox().isInstalled());
                    root.setText("SU is installed: " + device.getSU().isInstalled());
                    String _cpuLoad = "";
                    String[] load = device.getCPU().getCPULoad();
                    for (int i = 0; i < load.length; i++) {
                        _cpuLoad += load[i] + " / ";
                    }
                    cpuLoad.setText("CPU Load: " + _cpuLoad);
                } catch (IOException ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error while fetching device data!\n" + ex.toString());
                }
            }
        }.start();
    }
    
    private void loadAdvancedBatteryInfo() {
        try {
            final Battery battery = adbController.getDevice(devices.getSelectedADBDevice().toString()).getBattery();
            new Thread() {
            @Override
                public void run() {
                    try {
                        log.log(LogLevel.INFO, "Loading advanced batery info from device " + devices.getSelectedADBDevice());
                        do {
                            /*Booleans*/
                            boolean isPoweredByAC = battery.isACPowered();
                            boolean isPoweredByUSB = battery.isUSBPowered();
                            boolean isPoweredWirelessly = battery.isWirelessPowered();
                            boolean isPresent = battery.isPresent();
                            /*Integers*/
                            int current = battery.getCurrent();
                            int health = battery.getHealth();
                            int level = battery.getLevel();
                            int scale = battery.getScale();
                            int status = battery.getStatus();
                            int temp = battery.getTemp();
                            int voltage = battery.getVoltage();
                            /*Strings*/
                            String technology = battery.getTechnology();
                            //====================================================\\
                            /*Output Strings*/
                            String _batStatus = "";
                            String _batHealth = "";
                            //<editor-fold defaultstate="collapsed" desc="Switches">
                            switch (status) {
                                case 1:
                                    _batStatus = "UNKNOWN";
                                    break;
                                case 2:
                                    _batStatus = "CHARGING";
                                    break;
                                case 3:
                                    _batStatus = "DISCHARGING";
                                    break;
                                case 4:
                                    _batStatus = "NOT CHARGING";
                                    break;
                                case 5:
                                    _batStatus = "FULL";
                                    break;
                                default:
                                    _batStatus = "UNKNOWN";
                            }
                            switch (health) {
                                case 1:
                                    _batHealth = "UNKNOWN";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                case 2:
                                    _batHealth = "GOOD";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_healthy-32.png")));
                                    break;
                                case 3:
                                    _batHealth = "OVERHEAT";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                case 4:
                                    _batHealth = "DEAD";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                case 5:
                                    _batHealth = "OVER VOLTAGE";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                case 6:
                                    _batHealth = "UNSPECIFIED FAILURE";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                case 7:
                                    _batHealth = "God damn, I'M COLD!";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                                    break;
                                default:
                                    _batHealth = "Sorry, this is the default. #Whoopsy";
                                    batHealth.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_unhealthy-32.png")));
                            }
                            //</editor-fold>
                            //<editor-fold defaultstate="collapsed" desc="Magic.">
                            batPoweredByAC.setText("Powered by AC: " + isPoweredByAC);
                            batPoweredByUSB.setText("Powered by USB: " + isPoweredByUSB);
                            batPoweredWirelessly.setText("Powered Wirelessly: " + isPoweredWirelessly);
                            batIsPresent.setText("Present: " + isPresent);
                            batStatus.setText("Status: " + _batStatus);
                            batHealth.setText("Health: " + _batHealth);
                            batScale.setText("Scale: " + scale);
                            batVoltage.setText("Voltage: " + voltage);
                            batCurrent.setText("Current: " + current);
                            batTemp.setText("Temperature: " + temp + "°C");
                            //<editor-fold defaultstate="collapsed" desc="Set Icon">
                            if (temp <= 15)
                                batTemp.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_temp_cold-32.png")));
                            else if (temp <= 20)
                                batTemp.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_temp_mild-32.png")));
                            else if (temp <= 35)
                                batTemp.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_temp_warm-32.png")));
                            else
                                batTemp.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_temp_hot-32.png")));
                            //</editor-fold>
                            batTechnology.setText("Technology: " + technology);
                            batLevel.setText("Level: " + level);
                            //<editor-fold defaultstate="collapsed" desc="Set Icon">
                            if (level <= 25) {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_empty-32.png")));
                            } else if (level <= 50) {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_quarter-32.png")));
                            } else if (level <= 75) {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_half-32.png")));
                            } else if (level < 100) {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_third-32.png")));
                            } else if (level == 100) {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery_full-32.png")));
                            } else {
                                batLevel.setIcon(new ImageIcon(this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/battery/battery-32.png")));
                            }
                            //</editor-fold>
                            //</editor-fold>
                            Thread.sleep(5000);
                        } while (jToggleButton1.isSelected());
                    } catch (IOException | InterruptedException ex) {
                        log.log(LogLevel.FINE, "ERROR: Error fetching device battery information!\n " + ex.toString());
                    }
                }
            }.start();
        } catch (Exception ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while fetching device information (battery)!\n" + ex.toString());
        }
    }
    
    private void loadRootInfo() {
        log.log(LogLevel.INFO, "Retrieving root information from device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    SU su = adbController.getDevice(devices.getSelectedADBDevice().toString()).getSU();
                    BusyBox bb = adbController.getDevice(devices.getSelectedADBDevice().toString()).getBusybox();
                    boolean suIsInstalled = su.isInstalled();
                    boolean bbIsInstalled = bb.isInstalled();
                    String suVer = su.getSUVersion();
                    String bbVer = bb.getVersion();
                    suInstalled.setText("SU Installed: " + suIsInstalled);
                    suVersion.setText("SU Version: " + suVer);
                    busyboxInstalled.setText("BusyBox Installed: " + bbIsInstalled);
                    busyboxVersion.setText("BusyBox Version: " + bbVer);
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error loading device root information!\n" + ex.toString());
                }
                interrupt();
            }
        }.start();
        
    }
    
    private void loadCPUInfo() {
        log.log(LogLevel.INFO, "Retrieving CPU information from device " + devices.getSelectedADBDevice());
        new Thread() {
            @Override
            public void run() {
                try {
                    CPU cpu = adbController.getDevice(devices.getSelectedADBDevice().toString()).getCPU();
                    String[] load = cpu.getCPULoad();
                    String processedLoad = "";
                    for (int i = 0; i < load.length; i++) {
                        if (i == 0)
                            processedLoad += load[i];
                        else
                            processedLoad += "," + load[i];
                    }
                    String _processedLoad = processedLoad.replace(',', '/');
                    cpu_Load.setText("CPU Load: " + _processedLoad);
                    List<String> use = cpu.getCPUUsage();
                    DefaultListModel model = new DefaultListModel();
                    for (int i = 0; i < use.size(); i++) {
                        if (use.get(i).contains("Load: ")) continue;
                        model.addElement(use.get(i));
                    }
                    jList1.setModel(model);
                } catch (Exception ex) {
                    log.log(LogLevel.SEVERE, "ERROR: Error retrieving CPU information!\n" + ex.toString());
                }
            }
        }.start();
    }

    //<editor-fold defaultstate="collapsed" desc="variables (Generated Code)">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel batCurrent;
    private javax.swing.JLabel batHealth;
    private javax.swing.JLabel batIsPresent;
    private javax.swing.JLabel batLevel;
    private javax.swing.JLabel batPoweredByAC;
    private javax.swing.JLabel batPoweredByUSB;
    private javax.swing.JLabel batPoweredWirelessly;
    private javax.swing.JLabel batScale;
    private javax.swing.JLabel batStatus;
    private javax.swing.JLabel batTechnology;
    private javax.swing.JLabel batTemp;
    private javax.swing.JLabel batVoltage;
    private javax.swing.JLabel batteryLevel;
    private javax.swing.JLabel busybox;
    private javax.swing.JLabel busyboxInstalled;
    private javax.swing.JLabel busyboxVersion;
    private javax.swing.JLabel cpuLoad;
    private javax.swing.JLabel cpu_Load;
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
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
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
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton refreshStats;
    private javax.swing.JLabel root;
    private javax.swing.JLabel suInstalled;
    private javax.swing.JLabel suVersion;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
