/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import services.Configurator;

/**
 *
 * @author sing1
 */
public class DefaultValueDialog extends javax.swing.JDialog {
    private Configurator configurator = new Configurator();
    /**
     * Creates new form DefaultValueDialog
     */
    public DefaultValueDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            loadData();
            reset();
            this.setVisible(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Cannot load data", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void loadData() throws IOException {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) this.tblDefaultValue.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        this.tblDefaultValue.setModel(configurator.getConfigDefaultValue());
        this.tblDefaultValue.setDefaultEditor(Object.class, null);
        this.tblDefaultValue.setFocusable(false);
        this.tblDefaultValue.setAutoCreateRowSorter(true);
        this.tblDefaultValue.getColumnModel().getColumn(0).setMinWidth(120);
        this.tblDefaultValue.getColumnModel().getColumn(1).setMinWidth(180);
        this.tblDefaultValue.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDefaultValue = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtValue = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Config default value");
        setMinimumSize(new java.awt.Dimension(600, 450));

        tblDefaultValue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDefaultValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDefaultValueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDefaultValue);
        if (tblDefaultValue.getColumnModel().getColumnCount() > 0) {
            tblDefaultValue.getColumnModel().getColumn(0).setMinWidth(120);
            tblDefaultValue.getColumnModel().getColumn(1).setMinWidth(180);
            tblDefaultValue.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Value:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtName)
                            .addComponent(txtValue)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void reset() {
        this.txtName.setText("");
        this.txtName.setEditable(true);
        this.txtName.setFocusable(true);
        this.txtValue.setText("");
        this.tblDefaultValue.clearSelection();
        this.btnAdd.setEnabled(true);
        this.btnUpdate.setEnabled(false);
        this.btnDelete.setEnabled(false);
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String name = txtName.getText();
        String value = txtValue.getText();
        try {
            HashMap<String, String> defaultValue = configurator.createDefaultValue(name, value);
            DefaultTableModel model = (DefaultTableModel) this.tblDefaultValue.getModel();
            model.addRow(new Object[]{defaultValue.get("NAME"), defaultValue.get("VALUE")});
            reset();
            JOptionPane.showMessageDialog(this, "Default value added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (this.tblDefaultValue.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Please select only one default value to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.tblDefaultValue.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a default value to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this default value?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String name = txtName.getText();
            String value = txtValue.getText();
            try {
                HashMap<String, String> defaultValue = configurator.updateDefaultValue(name, value);
                DefaultTableModel model = (DefaultTableModel) this.tblDefaultValue.getModel();
                model.setValueAt(defaultValue.get("VALUE"), this.tblDefaultValue.getSelectedRow(), 1);
                reset();
                JOptionPane.showMessageDialog(this, "Default value updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int[] selectedRows = this.tblDefaultValue.getSelectedRows();
        if (selectedRows.length < 1) {
            JOptionPane.showMessageDialog(this, "Please select a default value to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected default values?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            List<String> deleted = new ArrayList<String>();
            List<String> notDeleted = new ArrayList<String>();
            List<String> errors = new ArrayList<String>();
            DefaultTableModel model = (DefaultTableModel) this.tblDefaultValue.getModel();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                String name = (String) this.tblDefaultValue.getValueAt(selectedRows[i], 0);
                try {
                    String result = configurator.deleteDefaultValue(name);
                    deleted.add(result);
                    model.removeRow(selectedRows[i]);
                } catch (Exception e) {
                    notDeleted.add(name);
                    errors.add(e.getMessage());
                }
            }
            reset();
            String error = errors.size() > 0 ? "\nError: " + String.join("\n", errors) : "";
            JOptionPane.showMessageDialog(this, "Deleted: " + String.join(", ", deleted) + "\nNot deleted: " + String.join(", ", notDeleted) + error, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblDefaultValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDefaultValueMouseClicked
        int selectedRow = this.tblDefaultValue.getSelectedRow();
        if (selectedRow < 0) {
            reset();
            return;
        }
        String name = (String) this.tblDefaultValue.getValueAt(selectedRow, 0);
        String value = (String) this.tblDefaultValue.getValueAt(selectedRow, 1);
        this.txtName.setText(name);
        this.txtName.setEditable(false);
        this.txtName.setFocusable(false);
        this.txtValue.setText(value);
        this.btnAdd.setEnabled(false);
        this.btnUpdate.setEnabled(true);
        this.btnDelete.setEnabled(true);
    }//GEN-LAST:event_tblDefaultValueMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        reset();
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DefaultValueDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefaultValueDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefaultValueDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefaultValueDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DefaultValueDialog dialog = new DefaultValueDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDefaultValue;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
