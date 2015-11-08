/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe criada quando um indivíduo é adicionado a cena
 *
 * @author Jéssica
 */
public class IndividualDTO implements Serializable {

    private String name;
    private ClassDTO classDTO;
    private List<PropValueDTO> propertieValue;
    private List<Input> inputs;
    private List<Output> outputs;

    public IndividualDTO(String name) {
        this.name = name;
    }

    public IndividualDTO(ClassDTO classDTO, String name) {
        this(name);
        this.classDTO = classDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String ontClass) {
        this.name = ontClass;
    }

    public ClassDTO getClassDTO() {
        return classDTO;
    }

    public void setClassDTO(ClassDTO classDTO) {
        this.classDTO = classDTO;
    }

    public List<PropValueDTO> getPropertieValue() {
        if (this.propertieValue == null) {
            this.setPropertieValue(new LinkedList<PropValueDTO>());
        }
        return propertieValue;
    }

    public void setPropertieValue(List<PropValueDTO> propertieValue) {
        this.propertieValue = propertieValue;
    }

    public void addPropertieValue(PropValueDTO propValue) {
        if (this.getPropertieValue() == null) {
            this.setPropertieValue(new ArrayList<PropValueDTO>());
        }
        this.getPropertieValue().add(propValue);
    }

    public void removePropValues(){
        this.propertieValue = null;
    }
    
    public List<Input> getInputs() {
        return this.inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }
    
    public void addInput(Input input){
        if (this.inputs == null) {
            this.setInputs(new LinkedList<Input>());
        }
        this.inputs.add(input);
    }

    public List<Output> getOutputs() {
        return this.outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
    public void addOutput(Output output){
        if (this.outputs == null) {
            this.setOutputs(new LinkedList<Output>());
        }
        this.outputs.add(output);
    }

}
