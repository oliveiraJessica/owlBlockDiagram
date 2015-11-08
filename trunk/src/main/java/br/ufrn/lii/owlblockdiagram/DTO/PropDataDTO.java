/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.DTO;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public class PropDataDTO extends PropDTO<String> {

    private List<ClassDTO> domain;
    private List<String> range;
    private boolean functionalPropertie;
    private boolean inverseFunctionalPropertie;

    public PropDataDTO() {
        super();
    }

    public PropDataDTO(String name) {
        super(name);
    }

    public PropDataDTO(List<ClassDTO> domain, List<String> range, String name) {
        super(name);
        this.domain = domain;
        this.range = range;
    }

    public void setFunctionalPropertie(boolean b){
        this.functionalPropertie = b;
    }
    
    public void setInverseFunctionalPropertie(boolean b){
        this.inverseFunctionalPropertie = b;
    }
    
    @Override
    public List<ClassDTO> getDomain() {
        return domain;
    }

    @Override
    public void setDomain(List<ClassDTO> list) {
        this.domain = list;
    }

    @Override
    public void addDomain(ClassDTO classe) {
        if (this.getDomain() == null) {
            this.setDomain(new LinkedList<ClassDTO>());
        }
        this.getDomain().add(classe);
    }

    @Override
    public List<String> getRange() {
        return range;
    }

    @Override
    public void setRange(List<String> range) {
        this.range = range;
    }

    public void addRange(String range) {
        if (this.getRange() == null) {
            this.setRange(new LinkedList<String>());
        }
        this.getRange().add(range);
    }

    @Override
    public boolean isPropData() {
        return true;
    }
    
    @Override
    public boolean isFunctionalProperty() {
        return this.functionalPropertie;
    }
    
    @Override
    public boolean isInverseFunctionalProperty() {
        return this.inverseFunctionalPropertie;
    }
}
