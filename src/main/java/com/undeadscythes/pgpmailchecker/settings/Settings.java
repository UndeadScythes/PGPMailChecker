/*
 * Copyright (C) 2014 UndeadScythes <udscythes@gmail.com>
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

package com.undeadscythes.pgpmailchecker.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class Settings extends Properties {
    private static final long serialVersionUID = 1L;
    private static final String settingsFileName = "settings.properties";

    public Settings() {
        super();
        defaults = new Properties();
        defaults.setProperty("host", "host.com");
        defaults.setProperty("username", "username");
        defaults.setProperty("password", "password");
        defaults.setProperty("studentdata", "studentfile.csv");
    }
    
    public Object setSetting(Setting setting, String value) {
        return super.setProperty(setting.toString(), value);
    }
    
    public String getSetting(Setting setting) {
        return super.getProperty(setting.toString());
    }
    
    public void load() throws IOException {
        try (FileInputStream input = new FileInputStream(new File(settingsFileName))) {
            load(input);
        }
    }
    
    public void save() throws IOException {
        try (FileOutputStream output = new FileOutputStream(new File(settingsFileName))) {
            store(output, "PGPMailChecker settings, modify as you wish.");
        }
    }
}
