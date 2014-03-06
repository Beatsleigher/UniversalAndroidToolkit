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
package eu.m4gkbeatz.androidtoolkit.main;

import eu.m4gkbeatz.androidtoolkit.logging.*;
import eu.m4gkbeatz.androidtoolkit.settings.SettingsManager;
import eu.m4gkbeatz.androidtoolkit.splash.SplashScreen;
import eu.m4gkbeatz.androidtoolkit.ui.*;

import javax.swing.*;
import java.io.*;

/**
 *
 * @author beatsleigher
 */
@SuppressWarnings({"UnusedAssignment", "CallToPrintStackTrace"})
public class Main {

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Error setting default Look and Feel.\n" + ex.toString());
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        Logger log = null;
                        SettingsManager settings = null;

                        SplashScreen splash = new SplashScreen();
                        splash.setVisible(true);
                        splash.setLocationRelativeTo(null);
                        splash.setStatus("Loading Settings and Logger...");

                        try {
                            settings = new SettingsManager();
                            log = new Logger(settings);

                            log.setVisible(true);
                            log.log(LogLevel.FINE, "Universal Android Toolkit has started.");
                            log.log(LogLevel.FINE, "Settings and logger have been loaded.");
                            Thread.sleep(200);
                            splash.setStatus("Loading UI...");
                            log.log(LogLevel.INFO, "Loading UI...");
                            Thread.sleep(200);
                            splash.setStatus("Welcome, " + System.getProperty("user.name") + ".");
                            splash.setImg(new ImageIcon(Main.class.getResource("/eu/m4gkbeatz/androidtoolkit/resources/icon.png")));
                            Thread.sleep(500);
                            if (settings.useAdvancedUI()) {
                                AdvancedUI ui = new AdvancedUI(settings, log);
                                ui.setVisible(true);
                            } else {
                                SimpleUI ui = new SimpleUI(settings, log);
                                ui.setVisible(true);
                            }
                            log.log(LogLevel.INFO, "Destroying splash...");
                            splash.dispose();
                        } catch (IOException | InterruptedException ex) {
                            System.err.println("Error while starting Universal Android Toolkit for Linux.");
                            ex.printStackTrace();
                        }

                    }
                }.start();
            }
        });
    }

}
