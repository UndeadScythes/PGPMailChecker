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

package com.undeadscythes.pgpmailchecker.student;

import com.undeadscythes.ceremail.Mail;
import com.undeadscythes.pgpmailchecker.ex.NotEnoughColumnsException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String regNo = "";
    private String surname = "";
    private String forename = "";
    private String email = "";
    private String programme = "";
    private final ArrayList<Mail> mail = new ArrayList<>(0);
    
    public Student(String details) {
        email = details;
    }
    
    public Student(String[] columns) throws NotEnoughColumnsException {
        if (columns.length != 5) {
            throw new NotEnoughColumnsException();
        }
        regNo = columns[0].trim();
        surname = columns[1].trim();
        forename = columns[2].trim();
        email = columns[3].trim();
        programme = columns[4].trim();
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public String getFullName() {
        return forename + " " + surname;
    }
    
    public String getProgramme() {
        return programme;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void addMail(Mail newMail) {
        mail.add(newMail);
    }
    
    public List<Mail> getMail() {
        return mail;
    }
    
    public int mailCount() {
        return mail.size();
    }
    
    public int getScore() {
        return 0;
    }
}
