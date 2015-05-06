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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class StudentListModel extends AbstractListModel<Student> {
    private static final Logger logger = Logger.getLogger(StudentListModel.class.getName());
    private static final long serialVersionUID = 1L;
    private static final File dataFile = new File("student.data");
    private final ArrayList<Student> students = new ArrayList<>(0);
    
    public void addMail(Mail mail) {
        switch (mail.getBox()) {
            case INBOX:
                getStudent(mail.getSender()).addMail(mail);
                break;
            case SENT_ITEMS:
                break;
            default:
                break;
        }
    }
    
    public Student getStudent(String clue) {
        for (Student student : students) {
            if (clue.contains(student.getEmail())) {
                return student;
            }
        }
        Student newStudent = new Student(clue);
        addStudent(newStudent);
        return newStudent;
    }
    
    public void addStudent(Student student) {
        students.add(student);
        students.sort(new StudentSort());
        int index = students.indexOf(student);
        this.fireIntervalAdded(this, index, index);
    }
    
    public boolean save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(dataFile);
            try (ObjectOutputStream output = new ObjectOutputStream(fileOut)) {
                output.writeObject(students);
            }
        } catch (IOException ignore) {
            return false;
        }
        return true;
    }
    
    public boolean load() {
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            try (ObjectInputStream input = new ObjectInputStream(fileIn)) {
                @SuppressWarnings("unchecked")
                List<Student> studentList = (List<Student>) input.readObject();
                students.addAll(studentList);
            }
        } catch (ClassNotFoundException | IOException ignore) {
            return false;
        }
        return true;
    }
    
    public void load(String filename) throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = input.readLine()) != null) {
            try {
                addStudent(new Student(line.split(",")));
            } catch (NotEnoughColumnsException ex) {
                logger.log(Level.SEVERE, "Cannot load students from csv.", ex);
            }
        }
    }

    @Override
    public int getSize() {
        return students.size();
    }

    @Override
    public Student getElementAt(int index) {
        return students.get(index);
    }
}
