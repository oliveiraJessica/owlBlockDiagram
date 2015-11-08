/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Jéssica
 */
public class ResizablePanel extends JPanel {

    Component resizableComponent;
    int labelWidth;

    public ResizablePanel(Component resizableComponent, int labelWidth) {
        super();
        this.resizableComponent = resizableComponent;
        this.labelWidth = labelWidth;
        
    }
    
    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        int width = (int) this.getParent().getSize().getWidth() - this.labelWidth - 30;
        int height = (int) this.resizableComponent.getPreferredSize().getHeight();
        this.resizableComponent.setPreferredSize(new Dimension(width, height));
        
        this.repaint();
    }

}
