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


package eu.m4gkbeatz.androidtoolkit.gc;


import eu.m4gkbeatz.androidtoolkit.logging.*;
import static eu.m4gkbeatz.androidtoolkit.logging.Logger.Level;
import eu.m4gkbeatz.androidtoolkit.settings.*;


/**
 *
 * @author beatsleigher
 */
public class GarbageCollector {
    
    private static SettingsManager settings = null;
    private static boolean debug = false;
    private static Logger logger = null;
    private static boolean runGC = false;
    
    private static Thread gc = new Thread() {
        @Override
        public void run() {
            while (runGC) {
                if (debug) {
                    logger.log(Level.DEBUG, "========== JVM Monitor ==========");
                    logger.log(Level.DEBUG, "Available CPUs: " + Runtime.getRuntime().availableProcessors());
                    logger.log(Level.DEBUG, "Max Memory: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
                    logger.log(Level.DEBUG, "Free Memory: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "MB");
                    logger.log(Level.DEBUG, "Total Memory: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "MB");
                    logger.log(Level.DEBUG, "Used Memory: " + ((Runtime.getRuntime().totalMemory() / 1024 / 1024) - (Runtime.getRuntime().freeMemory() / 1024 / 1024)) + "MB");
                    logger.log(Level.DEBUG, "==========/JVM Monitor ==========");
                    logger.log(Level.DEBUG, "Running GC...");
                }
                System.gc();
                try {
                    Thread.sleep(settings.getGCInterval());
                } catch (InterruptedException ex) {}
            }
        }
    };
    
    public static void init(SettingsManager settings, boolean debug, Logger logger) {
        GarbageCollector.settings = settings;
        GarbageCollector.debug = debug;
        GarbageCollector.logger = logger;
        runGC = true;
        gc.setName("GC-Thread");
        gc.setPriority(Thread.MIN_PRIORITY);
        gc.start();
    }
    
    public static void kill() {
        runGC = false;
        for (int i = 0; i < 200; i++) {} // Just wait a bit
        gc.interrupt();
    }
    
}
