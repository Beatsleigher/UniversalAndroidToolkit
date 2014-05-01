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


/**
 *
 * @author beatsleigher
 */
public enum Language {
    
    /**
     * English (United States)
     */
    en_us,
    
    /**
     * English (Great Britain), default
     */
    en_gb,
    
    /**
     * German (Germany)
     */
    de_de;
    
    public static Language getLanguage(String lang) {
        return Language.valueOf(lang);
    }
    
    public static String toString(Language lang) {
        return String.valueOf(lang);
    }
    
}
