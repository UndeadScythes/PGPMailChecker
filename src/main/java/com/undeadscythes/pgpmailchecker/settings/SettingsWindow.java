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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class SettingsWindow extends JFrame {
    private static final Logger logger = Logger.getLogger(SettingsWindow.class.getName());
    private static final long serialVersionUID = 1L;

    private final Settings settings;
    
    public SettingsWindow(Settings settings) {
        this.settings = settings;
        initComponents();
        hostText.setText(settings.getSetting(Setting.HOST));
        usernameText.setText(settings.getSetting(Setting.USERNAME));
        passwordText.setText(settings.getSetting(Setting.PASSWORD));
        studentDataText.setText(settings.getSetting(Setting.STUDENT_DATA));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabel hostLabel = new JLabel();
        JButton saveButton = new JButton();
        JButton cancelButton = new JButton();
        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel studentDataLabel = new JLabel();
        hostText = new JTextField();
        usernameText = new JTextField();
        passwordText = new JTextField();
        studentDataText = new JTextField();
        JButton studentDataBrowseButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");
        setResizable(false);

        hostLabel.setText("Host:");

        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        usernameLabel.setText("Username:");

        passwordLabel.setText("Password:");

        studentDataLabel.setText("Student data:");

        studentDataBrowseButton.setText("Browse");
        studentDataBrowseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                studentDataBrowseButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(studentDataLabel)
                            .addComponent(passwordLabel)
                            .addComponent(usernameLabel)
                            .addComponent(hostLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(hostText, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(usernameText)
                            .addComponent(passwordText)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(studentDataText, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentDataBrowseButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(hostLabel)
                    .addComponent(hostText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(studentDataLabel)
                    .addComponent(studentDataText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(studentDataBrowseButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        settings.setSetting(Setting.HOST, hostText.getText());
        settings.setSetting(Setting.USERNAME, usernameText.getText());
        settings.setSetting(Setting.PASSWORD, passwordText.getText());
        settings.setSetting(Setting.STUDENT_DATA, studentDataText.getText());
        try {
            settings.save();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save new settings.", ex);
        }
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void studentDataBrowseButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_studentDataBrowseButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        int response = fileChooser.showDialog(this, "Select");
        if (response == JFileChooser.APPROVE_OPTION) {
            studentDataText.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_studentDataBrowseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField hostText;
    private JTextField passwordText;
    private JTextField studentDataText;
    private JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}