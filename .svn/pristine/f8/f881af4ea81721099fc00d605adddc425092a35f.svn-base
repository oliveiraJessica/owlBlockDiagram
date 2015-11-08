package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.ComponentDTO;
import br.ufrn.lii.owlblockdiagram.utils.ImageUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author jessica
 */
public class PaletteTree extends JTree implements Serializable {

    TreeIcon selectedNodeIcon = null;
    boolean isLeaf = false;
    Hashtable<String, TreeIcon> hashTable = new Hashtable<String, TreeIcon>();
    DefaultMutableTreeNode root;
    private MyRenderer myRenderer = new MyRenderer();

    public PaletteTree(DefaultMutableTreeNode root) {
        super(root);
        this.root = root;
    }

    public TreeIcon getSelectedNodeIcon() {
        return this.selectedNodeIcon;
    }

    public TreeIcon getSelectedNodeIcon(String node) {
        return this.hashTable.get(node);
    }

    public void setSelectedNodeIcon(TreeIcon node) {
        this.selectedNodeIcon = node;
    }

    public boolean selIsLeaf() {
        return this.isLeaf;
    }

    public void setSelIsLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    public JLabel getSelectedNodeLabel() {
        return this.getSelectedNodeIcon().getLabel();
    }

    public void setSelectedNodeLabel(String label) {
        this.getSelectedNodeIcon().setLabel(label);
    }

    public MyRenderer getMyRenderer() {
        return myRenderer;
    }

    private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, String nodeString, ComponentDTO components) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeString);
        parent.add(node);

        TreeIcon treeIcon = ImageUtil.ramdonColorIcon("br/ufrn/lii/owlblockdiagram/images/block.png");
        treeIcon.setComponents(components);
        this.hashTable.put(nodeString, treeIcon);
        return node;
    }

    public void addComponents(ArrayList<ComponentDTO> c) {
        this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        addNodes(c);

        this.myRenderer.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setCellRenderer(this.myRenderer);
    }

    private void addNodes(List<ComponentDTO> list) {
        ArrayList<ComponentDTO> auxList = new ArrayList<>();
        ArrayList<ComponentDTO> leafs = new ArrayList<>();
        ComponentDTO componentDTO = null;
        for (int i = 0; i < list.size(); i++) {
            componentDTO = list.get(i);
            if (componentDTO.getChildren() != null && componentDTO.getParent().getName().equals(Ontology.ROOT)) {
                this.addNode(this.root, componentDTO.getName(), componentDTO);
                auxList.add(componentDTO);
            } else if (componentDTO.getParent() != null && componentDTO.getParent().getName().equals(Ontology.ROOT) && componentDTO.getChildren() == null) {
                leafs.add(componentDTO);
            }
        }

        for (ComponentDTO auxList1 : auxList) {
            addChildrens(auxList1);
        }

        for (ComponentDTO leafComponent : leafs) {
            this.addNode(this.root, leafComponent.getName(), leafComponent);
        }
    }

    private void addChildrens(ComponentDTO componentDTO) {
        List<ComponentDTO> componentChildren = componentDTO.getChildren();
        List<ComponentDTO> leafs = new ArrayList<>();
        if (componentChildren != null) {
            for (ComponentDTO component : componentChildren) {
                if (component.getChildren() != null) {
                    addNode(this.findNode(this.root, componentDTO.getName()), component.getName(), component);
                    addChildrens(component);
                } else {
                    leafs.add(component);
                }
            }
        }

        for (ComponentDTO leafComponent : leafs) {
            this.addNode(this.findNode(this.root, leafComponent.getParent().getName()), leafComponent.getName(), leafComponent);
        }
    }

    private DefaultMutableTreeNode findNode(DefaultMutableTreeNode root, String s) {
        Enumeration<DefaultMutableTreeNode> enumeration = root.depthFirstEnumeration();
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node = enumeration.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return node;
            }
        }
        return null;
    }

    public class MyRenderer extends DefaultTreeCellRenderer {

        private final Color rollOverRowColor = new Color(220, 240, 255);
        public String searchString;
//        private boolean rollOver;

        public MyRenderer() {
            super();
        }

        @Override
        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            super.getTreeCellRendererComponent(
                    tree, value, sel,
                    expanded, leaf, row,
                    hasFocus);
            this.updateUI();

            JComponent c = (JComponent) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            c.setOpaque(false);
            PaletteTree paletteTree = (PaletteTree) tree;

            if (leaf) {
                setIcon(paletteTree.getSelectedNodeIcon(value.toString()));
            } else {
                setIcon(this.getDefaultClosedIcon());
            }

            if (sel) {
                if (leaf) {
                    paletteTree.setSelIsLeaf(true);
                    TreeIcon icon = (TreeIcon) getIcon();
                    icon.setLabel(value.toString());
                    paletteTree.setSelectedNodeIcon(icon);
                } else {
                    paletteTree.setSelIsLeaf(false);
                }

            } else {
                //rollOver = searchString != null && !searchString.isEmpty() && Objects.toString(value, "").startsWith(searchString);
                //c.setForeground(getTextNonSelectionColor());
                //c.setBackground(getBackgroundNonSelectionColor());
                c.setOpaque(true);
                if (searchString != null && !searchString.isEmpty() && value.toString().startsWith(searchString)) {
                    c.setForeground(getTextNonSelectionColor());
                    c.setBackground(rollOverRowColor);
                } else {
                    c.setForeground(getTextNonSelectionColor());
                    c.setBackground(getBackgroundNonSelectionColor());
                }
            }
            return this;
        }
    }
}
