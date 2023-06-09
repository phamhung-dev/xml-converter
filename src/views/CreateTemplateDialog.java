/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import services.Converter;
import utils.Validator;

/**
 *
 * @author ASUS RG
 */
public class CreateTemplateDialog extends javax.swing.JDialog {
    private static Converter converter = new Converter();
    private Thread convertThread;
    /**
     * Creates new form TestJTreeDialog
     */
    public CreateTemplateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadCBB();
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        setIconNode();
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
        jTreeXML = new javax.swing.JTree();
        txtInput = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cbbOptionAdd = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Template");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("tag (XML Document)");
        jTreeXML.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeXML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeXMLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeXML);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cbbOptionAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbOptionAddActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("File Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtFileName)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbOptionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(cbbOptionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setIconNode() {
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                    boolean expanded, boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                if (value instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                    
                     if (node.getUserObject().toString().contains("tag")) {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/img/tag.png"));
                        label.setIcon(icon);
                    }
                    else if (node.getUserObject().toString().contains("attributes"))
                    {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/img/attributes.png"));
                        label.setIcon(icon);
                    }
                    else
                    {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/img/value.png"));
                        label.setIcon(icon);
                    }
                }

                return label;
            }
        };
        
        jTreeXML.setCellRenderer(renderer);
    }

    private void loadCBB()
    {
        String[] options = {"none", "tag", "attributes", "value"};
        for(String option : options)
        {
            cbbOptionAdd.addItem(option);
        }
    }

    private void expandAllNodes(JTree tree, boolean expand) {
        TreeNode rootNode = (TreeNode) tree.getModel().getRoot();
        expandAllNodes(tree, new TreePath(rootNode), expand);
    }

    private void expandAllNodes(JTree tree, TreePath path, boolean expand) {
        TreeNode node = (TreeNode) path.getLastPathComponent();

        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNode child = node.getChildAt(i);
            TreePath childPath = path.pathByAddingChild(child);

            expandAllNodes(tree, childPath, expand);
        }

        if (expand) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }
    
    private boolean isExisted(DefaultMutableTreeNode currNode, String regex) {
        int countChildNode = currNode.getChildCount();
        for (int i=0; i<countChildNode; i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) currNode.getChildAt(i);
            if (childNode.getUserObject().toString().matches(regex)) {
                return true;
            }
        }
        return false;
    }
    
    private void redrawTree(DefaultMutableTreeNode currNode, TreePath selectionPath) {
        DefaultTreeModel model = (DefaultTreeModel) jTreeXML.getModel();
        model.nodeStructureChanged(currNode);
        TreePath parentPath = selectionPath.getParentPath();
        jTreeXML.expandPath(parentPath);
        expandAllNodes(jTreeXML, true);
    }
    
    private boolean addTag(DefaultMutableTreeNode currNode, String tagName) {
        tagName = tagName.trim();
        if (tagName.isBlank()) {
            JOptionPane.showMessageDialog(null, "Tag name is not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tagName.matches("^\\w+$")) {
            String nodeName = "tag (" + tagName + ")";
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
            currNode.insert(node, currNode.getChildCount());
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Tag name not contain special character", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
    }
    
    private boolean addAttributes(DefaultMutableTreeNode currNode, String attributes) {
        attributes = attributes.trim();
        if (attributes.isBlank()) {
            JOptionPane.showMessageDialog(null, "Attributes is not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] arrAttributes = attributes.split(",");
        Validator validator = new Validator();
        try {
            for (String attribute : arrAttributes) {
                String[] keyValue = attribute.trim().split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().toUpperCase();
                    String value = keyValue[1].trim().replace("'", "").toUpperCase();
                    if (validator.validateAttribute(key, value) && isExisted(currNode, "^attributes \\(.*\\)$")) {
                        JOptionPane.showMessageDialog(null, "Attributes already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Attributes format invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            String nodeName = "attributes (" + attributes + ")";
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
            currNode.insert(node, 0);
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean addValue(DefaultMutableTreeNode currNode, String value) {
        value = value.trim();
        if (isExisted(currNode, "^value \\(.*\\)$")) {
            JOptionPane.showMessageDialog(null, "Value already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
           String nodeName = "value (" + value + ")";
           DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
           if (currNode.getChildCount() == 0) {
               currNode.insert(node, 0);
           } else {
               currNode.insert(node, 1);
           }
           return true;
        }
    }
    
    private boolean editTag(DefaultMutableTreeNode currNode, String tagName) {
        tagName = tagName.trim();
        if (tagName.isBlank()) {
            JOptionPane.showMessageDialog(null, "Tag name is not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tagName.matches("^\\w+$")) {
            String nodeName = "tag (" + tagName + ")";
            currNode.setUserObject(nodeName);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Tag name not contain special character", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }         
    }
    
    private boolean editAttributes(DefaultMutableTreeNode currNode, String attributes) {
        attributes = attributes.trim();
        if (attributes.isBlank()) {
            JOptionPane.showMessageDialog(null, "Attributes is not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] arrAttributes = attributes.split(",");
        Validator validator = new Validator();
        try {
            for (String attribute : arrAttributes) {
                String[] keyValue = attribute.trim().split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().toUpperCase();
                    String value = keyValue[1].trim().replace("'", "").toUpperCase();
                    if (validator.validateAttribute(key, value) && isExisted(currNode, "^attributes \\(.*\\)$")) {
                        JOptionPane.showMessageDialog(null, "Attributes already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Attributes format invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            String nodeName = "attributes (" + attributes + ")";
            currNode.setUserObject(nodeName);
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean editValue(DefaultMutableTreeNode currNode, String value) {
        value = value.trim();
        String nodeName = "value (" + value + ")";
        currNode.setUserObject(nodeName);
        return true;
    } 

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        TreePath selectionPath = jTreeXML.getSelectionPath();
        if (selectionPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to add!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultMutableTreeNode currNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
        String input = txtInput.getText();
        String option = cbbOptionAdd.getSelectedItem().toString();
        
        boolean isSuccess = true;

        switch (option) {
            case "tag":
                isSuccess = addTag(currNode, input);
                break;
            case "attributes":
                isSuccess = addAttributes(currNode, input);
                break;
            case "value":
                isSuccess = addValue(currNode, input);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Can not add!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (isSuccess) {                    
            redrawTree(currNode, selectionPath);
            cbbOptionAdd.setSelectedIndex(0);
            txtInput.setText("");
            JOptionPane.showMessageDialog(this, "Add success!", "Add Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    
        TreePath selectionPath = jTreeXML.getSelectionPath();
        if (selectionPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultMutableTreeNode currNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
        String nodeText = currNode.getUserObject().toString();
        boolean result = false;
        if (nodeText.contains("tag")) {
            result = editTag(currNode, txtInput.getText());
        } else if (nodeText.contains("attributes")) {
            result = editAttributes(currNode, txtInput.getText());
        } else if (nodeText.contains("value")) {
            result = editValue(currNode, txtInput.getText());
        }
        if (result) {
            redrawTree(currNode, selectionPath);
            JOptionPane.showMessageDialog(this, "Update success!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        TreePath[] selectionPaths = jTreeXML.getSelectionPaths();
        if (selectionPaths == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try 
        {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                for (TreePath selectionPath : selectionPaths) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                    if (parentNode != null) {
                        parentNode.remove(selectedNode);
                        redrawTree(parentNode, selectionPath.getParentPath());
                    }
                }
                JOptionPane.showMessageDialog(this, "Delete success!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                return;
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Delete fail!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("Saving...");
        progressBar.setValue(0);
        JOptionPane pane = new JOptionPane(progressBar);
        pane.setOptions(new Object[]{});
        JDialog dialog = pane.createDialog(this, "Save template");
        dialog.setModal(true);
        dialog.setResizable(false);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                convertThread.interrupt();
                dialog.dispose();
            }
        });
        Thread dialogThread = new Thread(new Runnable() {
            @Override
            public void run() {
                dialog.setVisible(true);
            }
        });
        dialogThread.start(); 
        convertThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String targetFilePath = converter.convertJTreeToXml(jTreeXML, txtFileName.getText(), progressBar);
                    JOptionPane.showMessageDialog(null, "Save success!\nFile path: " + targetFilePath, "Save Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (XMLStreamException | IOException| TransformerException e) {
                    JOptionPane.showMessageDialog(null, "Save fail!", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    dialog.dispose();
                }
            }
        });
        convertThread.start();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTreeXMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeXMLMouseClicked
        TreeSelectionModel selectionModel = jTreeXML.getSelectionModel();
        if(selectionModel.getSelectionCount() > 0 && selectionModel.getSelectionPath().getPathCount() > 1)
        {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeXML.getSelectionPath().getLastPathComponent();
            String nodeText = selectedNode.getUserObject().toString();
            if (nodeText.contains("tag") && !cbbOptionAdd.getSelectedItem().toString().contains("none")) {
                btnAdd.setEnabled(true);
            } else {
                btnAdd.setEnabled(false);
            }
            Pattern pattern = Pattern.compile("^\\w+ \\((.+?)\\)$");
            Matcher matcher = pattern.matcher(nodeText);
            if (matcher.find()) {
                txtInput.setText(matcher.group(1));
            }
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        } else {
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }
    }//GEN-LAST:event_jTreeXMLMouseClicked

    private void cbbOptionAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbOptionAddActionPerformed
        if (cbbOptionAdd.getSelectedItem().toString().contains("none")) 
        {
            btnAdd.setEnabled(false);
        } else {
            TreePath currNode = jTreeXML.getSelectionPath();
            if (currNode != null) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) currNode.getLastPathComponent();
                String nodeText = selectedNode.getUserObject().toString();
                if (nodeText.contains("tag")) {
                    btnAdd.setEnabled(true);
                } else {
                    btnAdd.setEnabled(false);
                }
            } else {
                btnAdd.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cbbOptionAddActionPerformed

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
            java.util.logging.Logger.getLogger(CreateTemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateTemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateTemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateTemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateTemplateDialog dialog = new CreateTemplateDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbbOptionAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTreeXML;
    private javax.swing.JTextField txtFileName;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}