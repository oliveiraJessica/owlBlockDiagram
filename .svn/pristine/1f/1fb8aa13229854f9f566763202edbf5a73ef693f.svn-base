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
public class Input extends ClassDTO {

    String label;
    Output output;
    IndividualDTO component;

    public Input(String label) {
        super(label);
    }
    
    public Input(ClassDTO c){
        super(c.getName(), c.getId(), c.getProperties());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public IndividualDTO getComponent() {
        return component;
    }

    public void setComponent(IndividualDTO component) {
        this.component = component;
        this.component.addInput(this);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isEdge() {
        return true;
    }

    // checar esse label! não deveria ser o nome da classe na ontologia descritiva?
    public void constructInputLabel() {
        String inComp = this.getComponent().getName();
        String outComp = this.getOutput().getComponent().getName();
        this.setLabel("Input" + inComp.trim() + outComp.trim());
    }

}
