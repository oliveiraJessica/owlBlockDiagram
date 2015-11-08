/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.model.Ontology;
import br.ufrn.lii.owlblockdiagram.model.PaletteTree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author jessica
 */
public class PalettePanel extends JPanel {

    private PaletteTree paletteTree;
    private JPanel searchPanel;
    private JTextField searchTextField;
    private JButton searchButton;

    public PalettePanel() {
        super();
        this.init();
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public PaletteTree getPaletteTree() {
        return this.paletteTree;
    }

    public void setPaletteTree(PaletteTree paletteTree) {
        this.remove(this.paletteTree);
        this.paletteTree = paletteTree;
        this.add(paletteTree);
    }

    private void init() {
        this.setBackground(Color.WHITE);

        if (this.paletteTree == null) {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(Ontology.ROOT);
            this.paletteTree = new PaletteTree(root);
        }

        GridLayout springLayout = new GridLayout(1, 2);

        this.searchPanel = new JPanel(springLayout);
        this.searchPanel.setPreferredSize(new Dimension(50, 45));
        this.searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        this.searchPanel.setBackground(Color.WHITE);

        this.searchButton = new JButton("OK");
        this.searchButton.setBackground(Color.WHITE);
        this.searchTextField = new JTextField("");

        this.searchTextField.setPreferredSize(new Dimension(100, 20));
        this.searchTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                PalettePanel.this.fireDocumentChangeEvent();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.searchPanel);
        this.searchPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchButton)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );

        this.setLayout(new BorderLayout(2,8));
        this.add(this.searchPanel, BorderLayout.NORTH);
        this.add(this.paletteTree, BorderLayout.CENTER);
    }

    private void fireDocumentChangeEvent() {
        String q = searchTextField.getText();
        this.paletteTree.getMyRenderer().searchString = q;

        TreePath root = this.paletteTree.getPathForRow(0);
        collapseAll(this.paletteTree, root);
        if (!q.isEmpty()) {
            searchTree(this.paletteTree, root, q);
        }
        //tree.repaint();
    }

    private static void searchTree(JTree tree, TreePath path, String q) {
        TreeNode node = (TreeNode) path.getLastPathComponent();
        if (node == null) {
            return;
        }
        if (node.toString().startsWith(q)) {
            tree.expandPath(path.getParentPath());
        }
        if (!node.isLeaf() && node.getChildCount() >= 0) {
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                searchTree(tree, path.pathByAddingChild(e.nextElement()), q);
            }
        }
    }

    private static void collapseAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (!node.isLeaf() && node.getChildCount() >= 0) {
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                collapseAll(tree, path);
            }
        }
        tree.collapsePath(parent);
    }
}
