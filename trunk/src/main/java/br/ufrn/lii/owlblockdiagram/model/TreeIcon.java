/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jessica
 */
public class TreeIcon extends ImageIcon implements Serializable{
    
    private Color color;
    private JLabel label;
    private ClassDTO components;

    public TreeIcon(Image image, Color color) {
        super(image);
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public void setLabel(String label) {
        this.label = new JLabel(label);
    }

    public ClassDTO getComponents() {
        return components;
    }

    public void setComponents(ClassDTO components) {
        this.components = components;
    }
}