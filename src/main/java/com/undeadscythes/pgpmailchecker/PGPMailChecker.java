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

package com.undeadscythes.pgpmailchecker;

import com.undeadscythes.ceremail.MailBox;
import com.undeadscythes.ceremail.MailHandler;
import com.undeadscythes.ceremail.MailTerm;
import com.undeadscythes.jobrunner.JobDirector;
import com.undeadscythes.jobrunner.JobMarker;
import com.undeadscythes.jobrunner.JobRunner;
import com.undeadscythes.pgpmailchecker.settings.Setting;
import com.undeadscythes.pgpmailchecker.settings.Settings;
import com.undeadscythes.pgpmailchecker.settings.SettingsWindow;
import com.undeadscythes.pgpmailchecker.student.Student;
import com.undeadscythes.pgpmailchecker.student.StudentCellRenderer;
import com.undeadscythes.pgpmailchecker.student.StudentData;
import com.undeadscythes.pgpmailchecker.student.StudentListModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A frame which forms the foundation of the MailChecker application. Contains
 * access to all student data and email transactions as well as score editing
 * and general note taking.
 * 
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class PGPMailChecker extends JFrame implements JobDirector {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PGPMailChecker.class.getName());
    
    private final StudentListModel studentListModel = new StudentListModel();
    private MailHandler mailHandler;
    private final Settings settings = new Settings();

    public PGPMailChecker() {
        initComponents();
    }
    
    private void output(String message, Exception ex) {
        if (debugToggle.isSelected()) {
            logger.log(Level.WARNING, message, ex);
        }
        outputText.append(message + System.lineSeparator());
    }
    
    private void load() {
        try {
            settings.load();
        } catch (IOException ex) {
            output("Could not load settings file.", ex);
        }
        mailHandler = new MailHandler(
                settings.getSetting(Setting.HOST),
                settings.getSetting(Setting.USERNAME),
                settings.getSetting(Setting.PASSWORD));
        if (!studentListModel.load()) {
            try {
                studentListModel.load(settings.getSetting(Setting.STUDENT_DATA));
            } catch (IOException ex) {
                output("Could not load data file.", ex);
            }
        }
    }
    
    public void checkMail(MailTerm term) throws IOException, MessagingException {
            mailHandler.openBox(MailBox.INBOX);
            mailHandler.searchBox(term);
            while (mailHandler.hasNext()) {
                try {
                    studentListModel.addMail(mailHandler.getNextMail());
                } catch (MessagingException ex) {
                    output("Cannot read mail from mail box.", ex);
                    mailHandler.nextMail();
                }
            }
            mailHandler.closeBox();
    }
    
    @Override
    public void run(JobMarker job) {
        switch ((Job) job) {
            case ALL_MAIL:
                try {
                    checkMail(null);
                } catch (MessagingException | IOException ex) {
                    output("Could not check mail box.", ex);
                }
                break;
            case NEW_MAIL:
                try {
                    checkMail(MailTerm.NEW);
                } catch (MessagingException | IOException ex) {
                    output("Could not check mail box.", ex);
                }
                break;
            default:
                break;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        JSplitPane mainSplit = new JSplitPane();
        studentData = new StudentData();
        JScrollPane studentScroll = new JScrollPane();
        studentList = new JList<>();
        checkMailButton = new JButton();
        settingsButton = new JButton();
        statusText = new JTextField();
        outputScroll = new JScrollPane();
        outputText = new JTextArea();
        debugToggle = new JToggleButton();
        jProgressBar1 = new JProgressBar();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        mainSplit.setDividerLocation(200);
        mainSplit.setRightComponent(studentData);

        studentList.setModel(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setCellRenderer(new StudentCellRenderer());
        studentList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                studentListValueChanged(evt);
            }
        });
        studentScroll.setViewportView(studentList);

        mainSplit.setLeftComponent(studentScroll);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(mainSplit, gridBagConstraints);

        checkMailButton.setText("Check Mail");
        checkMailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                checkMailButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(checkMailButton, gridBagConstraints);

        settingsButton.setText("Settings");
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(settingsButton, gridBagConstraints);

        statusText.setEditable(false);
        statusText.setText("Ready.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(statusText, gridBagConstraints);

        outputText.setColumns(20);
        outputText.setRows(5);
        outputScroll.setViewportView(outputText);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(outputScroll, gridBagConstraints);

        debugToggle.setText("Debug");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        getContentPane().add(debugToggle, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        getContentPane().add(jProgressBar1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkMailButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_checkMailButtonActionPerformed
        if ((evt.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            JobRunner.run(Job.ALL_MAIL, this);
        } else {
            JobRunner.run(Job.NEW_MAIL, this);
        }
    }//GEN-LAST:event_checkMailButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        SettingsWindow settingsWindow = new SettingsWindow(settings);
        settingsWindow.setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void studentListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_studentListValueChanged
        studentData.load(studentList.getSelectedValue());
    }//GEN-LAST:event_studentListValueChanged

    public static void main(String args[]) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PGPMailChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PGPMailChecker mailChecker = new PGPMailChecker();
                mailChecker.setVisible(true);
                mailChecker.load();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton checkMailButton;
    private JToggleButton debugToggle;
    private JProgressBar jProgressBar1;
    private JScrollPane outputScroll;
    private JTextArea outputText;
    private JButton settingsButton;
    private JTextField statusText;
    private StudentData studentData;
    private JList<Student> studentList;
    // End of variables declaration//GEN-END:variables
}
