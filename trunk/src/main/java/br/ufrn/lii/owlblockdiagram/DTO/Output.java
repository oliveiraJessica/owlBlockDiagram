/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.DTO;

/**
 *
 * @author Jéssica
 */
public class Output extends ClassDTO {

    String label;
    Input input;
    IndividualDTO component;

    public Output(String label) {
        super(label);
    }

    public Output(ClassDTO c) {
        super(c.getName(), c.getId(), c.getProperties());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public IndividualDTO getComponent() {
        return component;
    }

    public void setComponent(IndividualDTO component) {
        this.component = component;
        this.component.addOutput(this);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isEdge() {
        return true;
    }

    public void constructOutputLabel() {
        String outComp = this.getComponent().getName();
        String inComp = this.getInput().getComponent().getName();
        this.setLabel("Output" + outComp.trim() + inComp.trim());
    }
}
