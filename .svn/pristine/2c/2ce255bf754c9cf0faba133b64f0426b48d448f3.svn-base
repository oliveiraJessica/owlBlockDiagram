package br.ufrn.lii.owlblockdiagram.model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class EdgeMenu implements PopupMenuProvider, ActionListener, Serializable {

    private static final String ADD_REMOVE_CP_ACTION = "addRemoveCPAction";
//    private static final String DELETE_ALL_CP_ACTION = "deleteAllCPAction"; 
    private static final String DELETE_TRANSITION = "deleteTransition";
    private ImageGraphScene scene;
    private JPopupMenu menu;
    private ConnectionWidget edge;
    private Point point;

    public EdgeMenu(ImageGraphScene scene) {
        this.scene = scene;
        this.menu = new JPopupMenu("Menu de Transição");
        JMenuItem item;

        item = new JMenuItem("Adicionar/Deletar Ponto de Controle");
        item.setActionCommand(ADD_REMOVE_CP_ACTION);
        item.addActionListener(this);
        this.menu.add(item);

        this.menu.addSeparator();

//        item = new JMenuItem("Delete All Control Points");
//        item.setActionCommand(DELETE_ALL_CP_ACTION);
//        item.addActionListener(this);
//        item.setEnabled(false);
//        menu.add(item);

        item = new JMenuItem("Deletar Transição");
        item.setActionCommand(DELETE_TRANSITION);
        item.addActionListener(this);
        this.menu.add(item);

    }

    public JPopupMenu getPopupMenu(Widget widget, Point point) {
        if (widget instanceof ConnectionWidget) {
            this.edge = (ConnectionWidget) widget;
            this.point = point;
            return this.menu;
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ADD_REMOVE_CP_ACTION)) {
            addRemoveControlPoint(this.point);
        } else if (e.getActionCommand().equals(DELETE_TRANSITION)) {
            this.scene.removeEdge((String) this.scene.findObject(this.edge));
        }
    }

    private void addRemoveControlPoint(Point localLocation) {
        ArrayList<Point> list = new ArrayList<Point>(this.edge.getControlPoints());
        double createSensitivity = 1.00, deleteSensitivity = 5.00;
        if (!removeControlPoint(localLocation, list, deleteSensitivity)) {
            Point exPoint = null;
            int index = 0;
            for (Point elem : list) {
                if (exPoint != null) {
                    Line2D l2d = new Line2D.Double(exPoint, elem);
                    if (l2d.ptLineDist(localLocation) < createSensitivity) {
                        list.add(index, localLocation);
                        break;
                    }
                }
                exPoint = elem;
                index++;
            }
        }
        this.edge.setControlPoints(list, false);
    }

    private boolean removeControlPoint(Point point, ArrayList<Point> list, double deleteSensitivity) {
        for (Point elem : list) {
            if (elem.distance(point) < deleteSensitivity) {
                list.remove(elem);
                return true;
            }
        }
        return false;
    }
}
