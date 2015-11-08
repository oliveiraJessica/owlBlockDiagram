/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrn.lii.owlblockdiagram.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public class ComponentDTO extends ClassDTO{

    private List<Input> inputs;
    private List<Output> outputs;
    private ComponentDTO parent = null;
    private List<ComponentDTO> children = null;
    
    
    public ComponentDTO(String name) {
        super(name);
    }

    public ComponentDTO(List<Input> inputs, List<Output> outputs, String name) {
        this(name);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<Input> getInput() {
        return inputs;
    }

    public void setInput(List<Input> input) {
        this.inputs = input;
    }

    public List<Output> getOutput() {
        return outputs;
    }

    public void setOutput(List<Output> output) {
        this.outputs = output;
    }

    public ComponentDTO getParent() {
        return parent;
    }

    public void setParent(ComponentDTO parent) {
        this.parent = parent;
    }

    public boolean hasParent(){
        return this.parent != null;
    }
    
    public List<ComponentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ComponentDTO> children) {
        this.children = children;
    }

    public void addChildren(ComponentDTO children){
        if (this.getChildren() == null) {
            this.setChildren(new ArrayList<ComponentDTO>());
        }
        this.getChildren().add(children);
    }
    
    @Override
    public boolean isLeaf() {
        return children == null;
    }

    @Override
    public boolean isEdge() {
        return false;
    }
    
}
