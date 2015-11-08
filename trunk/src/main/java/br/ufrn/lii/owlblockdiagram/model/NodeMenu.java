/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.PropDTO;
import br.ufrn.lii.owlblockdiagram.view.SettingsFrame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

public class NodeMenu implements PopupMenuProvider, ActionListener, Serializable {

    private static final String DELETE_NODE_ACTION = "deleteNodeAction";
    private static final String BLACK_BOX_ACTION = "BlackBoxAction";
    private static final String PROPERTIES_ACTION = "PropertiesAction";
    private JPopupMenu menu;
    private IconNodeWidget node;
    private Point point;
    private ImageGraphScene scene;

    public NodeMenu(ImageGraphScene scene) {
        this.scene = scene;
        this.menu = new JPopupMenu("Menu do Bloco");
        JMenuItem item;

        item = new JMenuItem("Deletar Bloco");
        item.setActionCommand(DELETE_NODE_ACTION);
        item.addActionListener(this);
        this.menu.add(item);

        item = new JMenuItem("Caixa Preta");
        item.setActionCommand(BLACK_BOX_ACTION);
        item.addActionListener(this);
        //this.menu.add(item);

        item = new JMenuItem("Propriedades");
        item.setActionCommand(PROPERTIES_ACTION);
        item.addActionListener(this);
        this.menu.add(item);
    }

    @Override
    public JPopupMenu getPopupMenu(Widget widget, Point point) {
        this.point = point;
        this.node = (IconNodeWidget) widget;
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DELETE_NODE_ACTION)) {
            this.scene.removeNodeWithEdges((ImageNode) this.scene.findObject(this.node));
            this.scene.validate();
        }

        if (e.getActionCommand().equals(BLACK_BOX_ACTION)) {
            this.scene.validate();
        }

        if (e.getActionCommand().equals(PROPERTIES_ACTION)) {
            ImageNode source;
            source = this.scene.findImageNode(this.node.getLabelWidget().getLabel());
            ArrayList<PropDTO> properties = source.getComponentInstance().getClassDTO().getProperties();
            if (properties != null) {
                SettingsFrame frame = new SettingsFrame(source.getComponentInstance(), this.scene);
                frame.setVisible(true);
            }
        }
    }
}
