
package forumdb.EntitiesFrames.Appointment;

import EntitiesClasses.Appointment;
import EntitiesClasses.User;
import forumdb.EntitiesFrames.User.UserModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

public class NewAppointment extends javax.swing.JDialog {

    private Connection c;
    private Appointment editItem;
    private AppointmentModel model;

    public NewAppointment(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();
        model = new AppointmentModel(c);
        this.c = c;
        combos();
    }

    public NewAppointment(java.awt.Frame parent, boolean modal, Connection c, Appointment u) {
        super(parent, modal);
        initComponents();
        this.c = c;
        editItem = u;        
        model = new AppointmentModel(c);
        fillFields();
    }
    
    private void combos() {
        userBox.setModel(new DefaultComboBoxModel(model.getModelUser().getList().toArray()));
        //userBox.setModel(new DefaultComboBoxModel(modelUser.getList().toArray()));
    }

    private void fillFields() {
        dataField.setText(editItem.getDate());
        combos();

    }

    public boolean check() {
        if ("".equals(dataField.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty");
            return false;
        }
        Pattern p = Pattern.compile("[0-9]{4}-([1-9]|1[012])-([1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher m = p.matcher(dataField.getText());
        if (!m.matches()) {
            JOptionPane.showMessageDialog(new JFrame(), "Date has wrong format");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New appointment");

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Data");

        jLabel2.setText("User");

        userBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(51, 71, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(userBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(dataField)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(userBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!check()) {
            return;
        }
        try {
            if (editItem == null) {
                model.insert(new Appointment(0, ((User) userBox.getSelectedItem()).getId_user(), dataField.getText()));
            } else {
                model.update(new Appointment(editItem.getId_appointment(), ((User) userBox.getSelectedItem()).getId_user(), dataField.getText()));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            return;
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void userBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userBoxActionPerformed

    }//GEN-LAST:event_userBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dataField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> userBox;
    // End of variables declaration//GEN-END:variables
}
