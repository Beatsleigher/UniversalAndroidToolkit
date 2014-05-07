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


package eu.m4gkbeatz.androidtoolkit.language;


import eu.m4gkbeatz.androidtoolkit.logging.*;
import java.io.*;
import java.net.*;


/**
 *
 * @author beatsleigher
 */
public class LangFileParser {
        
        //# =============== Variables =============== #\\
        String translationFile = null;
        
        public void parse(Language lang, Logger logger, boolean debug) throws IOException {
            URL languageFile = this.getClass().getResource("/eu/m4gkbeatz/androidtoolkit/resources/langs/" + lang + ".lang");
            if (debug)
                logger.log(Logger.Level.DEBUG, "Attempting translation from file: " + languageFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(languageFile.openStream()));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                strBuilder.append(line + "\n");
            reader.close();
            translationFile = strBuilder.toString();
        }
        
        public String parse(String item) throws IOException {
            BufferedReader reader = new BufferedReader(new StringReader(translationFile));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("#")) continue;
                if (line.contains(item)) {
                    String[] arr = line.split("=");
                    return arr[1];
                }
            }
            return "???";
        }
        
    }
