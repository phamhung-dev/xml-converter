/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerException;
import services.Converter;
import utils.Config;

/**
 *
 * @author ASUS RG
 */
public class TemplateDialog extends javax.swing.JDialog {
    private static Converter converter = new Converter();
    private Thread convertThread;
    private String templateName;

    /**
     * Creates new form TestJTreeDialog
     */
    public TemplateDialog(java.awt.Frame parent, String title, boolean modal, String templateName) {
        super(parent, modal);
        setTitle(title);
        initComponents();
        loadCBB();
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        jTreeXML.setSelectionRow(0);
        setIconNode();
        this.templateName = templateName;
        if (templateName != null) {
            loadXmlToTree(templateName);
            txtTemplateName.setText(templateName);
            txtTemplateName.setEditable(false);
            txtTemplateName.setFocusable(false);
        } else {
            javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("tag (Application)");
            jTreeXML.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        }
        this.jTreeXML.setSelectionRow(0);
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
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cbbOptionAdd = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTemplateName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create template");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("tag (XML Document)");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("tag (Application)");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("tag (RegNumber)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("tag (PhoneList)");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("attributes (ref='PHONE;')");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("tag (Phone)");
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("attributes (type='number', use='required')");
        treeNode4.add(treeNode5);
        treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("value (84${PHONE})");
        treeNode4.add(treeNode5);
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTreeXML.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeXML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeXMLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeXML);

        btnAdd.setText("Add");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cbbOptionAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbbOptionAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbOptionAddActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Template name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 119, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTemplateName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(cbbOptionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbOptionAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTemplateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadXmlToTree(String templateName) {
        templateName += "Template";
        try {
            Stack<DefaultMutableTreeNode> stack = new Stack<DefaultMutableTreeNode>();
            XMLEventReader template = Config.getTemplate(templateName);
            while (template.hasNext()) {
                XMLEvent event = template.nextEvent();

                if (event.isStartElement()) {
                    StartElement element = event.asStartElement();
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode("tag (" + element.getName().getLocalPart() + ")");
                    stack.push(node);
                    Iterator<Attribute> attributes = element.getAttributes();
                    if (attributes.hasNext()) {
                        String attr = "attributes (";
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            attr += attribute.getName().getLocalPart() + "='" + attribute.getValue() + "', ";
                        }
                        attr = attr.substring(0, attr.length() - 2) + ")";
                        DefaultMutableTreeNode attrNode = new DefaultMutableTreeNode(attr);
                        node.add(attrNode);
                    }
                    continue;
                }

                if (event.isCharacters()) {
                    if (!event.asCharacters().isWhiteSpace()) {
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode("value (" + event.asCharacters().getData() + ")");
                        stack.peek().add(node);
                    }
                    continue;
                }

                if (event.isEndElement()) {
                    DefaultMutableTreeNode root = stack.pop();
                    if (stack.isEmpty()) {
                        this.jTreeXML.setModel(new DefaultTreeModel(root));
                    } else {
                        stack.peek().add(root);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            JOptionPane.showMessageDialog(this, "Can't load template file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
        jTreeXML.expandPath(selectionPath);
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        TreePath selectionPath = jTreeXML.getSelectionPath();
        if (selectionPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to add!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jTreeXML.getSelectionCount() > 1) {
            JOptionPane.showMessageDialog(this, "Please select only one node to add!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        MainForm mainForm = (MainForm) this.getParent();
        String selectedItem = cbbOptionAdd.getSelectedItem().toString();
        DefaultMutableTreeNode currNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
        boolean isSuccess = false;
        if(selectedItem.contains("tag")) {
            TagDialog addTagDialog = new TagDialog(mainForm, "Add tag", rootPaneCheckingEnabled);
            addTagDialog.setVisible(true);
            if (addTagDialog.isOK()) {
                String nodeName = "tag (" + addTagDialog.getTagName() + ")";
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
                currNode.insert(node, currNode.getChildCount());
                isSuccess = true;
            }
            addTagDialog.dispose();
        } else if(selectedItem.contains("attributes")) {
            if (isExisted(currNode, "^attributes \\(.*\\)$")) {
                JOptionPane.showMessageDialog(null, "Attributes already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            AttributesDialog addAttributeDialog = new AttributesDialog(mainForm, "Add attributes", rootPaneCheckingEnabled);
            addAttributeDialog.setVisible(true);
            if (addAttributeDialog.isOK() && !addAttributeDialog.getAttributes().isBlank()) {
                String nodeName = "attributes (" + addAttributeDialog.getAttributes() + ")";
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
                currNode.insert(node, 0);
                isSuccess = true;
            }
            addAttributeDialog.dispose();
        } else {
            if (isExisted(currNode, "^value \\(.*\\)$")) {
                JOptionPane.showMessageDialog(null, "Value already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            ValueDialog addValueDialog = new ValueDialog(mainForm, "Add value", rootPaneCheckingEnabled);
            addValueDialog.setVisible(true);
            if(addValueDialog.isOK() && !addValueDialog.getValue().isBlank()) {
                String nodeName = "value (" + addValueDialog.getValue() + ")";
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
                if (currNode.getChildCount() == 0) {
                    currNode.insert(node, 0);
                } else if (((DefaultMutableTreeNode)currNode.getFirstChild()).getUserObject().toString().contains("attributes")) {
                    currNode.insert(node, 1);
                } else {
                    currNode.insert(node, 0);
                }
                isSuccess = true;
            }
            addValueDialog.dispose();
        }

        if (isSuccess) {
            redrawTree(currNode, selectionPath);
            cbbOptionAdd.setSelectedIndex(0);
            jTreeXML.setSelectionPath(selectionPath);
            JOptionPane.showMessageDialog(null, "Add success", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        TreePath selectionPath = jTreeXML.getSelectionPath();
        if (selectionPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a node to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jTreeXML.getSelectionCount() > 1) {
            JOptionPane.showMessageDialog(this, "Please select only one node to add!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultMutableTreeNode currNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
        String nodeText = currNode.getUserObject().toString();
        MainForm mainForm = (MainForm) this.getParent();
        boolean isSuccess = false;
        if (nodeText.contains("tag")) {
            TagDialog editTagDialog = new TagDialog(mainForm, "Edit tag", rootPaneCheckingEnabled);
            editTagDialog.setVisible(true);
            if (editTagDialog.isOK()) {
                String nodeName = "tag (" + editTagDialog.getTagName() + ")";
                currNode.setUserObject(nodeName);
                isSuccess = true;
            }
            editTagDialog.dispose();            
        } else if (nodeText.contains("attributes")) {
            AttributesDialog editAttributeDialog = new AttributesDialog(mainForm, "Edit attributes", rootPaneCheckingEnabled);
            editAttributeDialog.setVisible(true);
            if (editAttributeDialog.isOK() && !editAttributeDialog.getAttributes().isBlank()) {
                String nodeName = "attributes (" + editAttributeDialog.getAttributes() + ")";
                currNode.setUserObject(nodeName);
                isSuccess = true;
            }
            editAttributeDialog.dispose();
        } else {
            ValueDialog editValueDialog = new ValueDialog(mainForm, "Edit value", rootPaneCheckingEnabled);
            editValueDialog.setVisible(true);
            if (editValueDialog.isOK() && !editValueDialog.getValue().isBlank()) {
                String nodeName = "value (" + editValueDialog.getValue() + ")";
                currNode.setUserObject(nodeName);
                isSuccess = true;
            }
            editValueDialog.dispose();
        }

        if (isSuccess) {
            redrawTree(currNode, selectionPath.getParentPath());
            jTreeXML.setSelectionPath(selectionPath);
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
                jTreeXML.setSelectionRow(0);
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
        int childCount = ((DefaultMutableTreeNode) jTreeXML.getModel().getRoot()).getChildCount();
        if (childCount == 0) {
            JOptionPane.showMessageDialog(this, "Please input XML!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtTemplateName.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Please input template name!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String newTemplateName = txtTemplateName.getText().substring(0, 1).toUpperCase() + txtTemplateName.getText().substring(1).toLowerCase() + "Template";
            if (this.templateName == null && Config.getConfigPath().get(newTemplateName, "PATH") != null) {
                JOptionPane.showMessageDialog(this, "Template name already exist!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Save fail!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
                    Map<String, String> result = converter.convertJTreeToXml(jTreeXML, txtTemplateName.getText(), progressBar);
                    if (templateName == null) {
                        MainForm main = (MainForm) TemplateDialog.this.getParent();
                        main.getCbbTemplate().addItem(result.get("templateName"));
                    }
                    TemplateDialog.this.dispose();
                    JOptionPane.showMessageDialog(null, "Save success!\nFile path: " + result.get("targetFilePath"), "Save Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (XMLStreamException | IOException| TransformerException e) {
                    JOptionPane.showMessageDialog(null, "Save fail!", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    dialog.dispose();
                }
            }
        });
        convertThread.start();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTreeXMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeXMLMouseClicked
        TreeSelectionModel selectionModel = jTreeXML.getSelectionModel();
        if(selectionModel.getSelectionCount() > 0 && selectionModel.getSelectionPath().getPathCount() > 0)
        {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeXML.getSelectionPath().getLastPathComponent();
            String nodeText = selectedNode.getUserObject().toString();
            if (nodeText.contains("tag") && !cbbOptionAdd.getSelectedItem().toString().contains("none")) {
                btnAdd.setEnabled(true);
            } else {
                btnAdd.setEnabled(false);
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
            java.util.logging.Logger.getLogger(TemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TemplateDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TemplateDialog dialog = new TemplateDialog(new javax.swing.JFrame(), "Template dialog", true, null);
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
    private javax.swing.JTextField txtTemplateName;
    // End of variables declaration//GEN-END:variables
}
