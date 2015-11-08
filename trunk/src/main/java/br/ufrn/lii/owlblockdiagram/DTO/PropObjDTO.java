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
public class PropObjDTO extends PropDTO<ClassDTO> {

    private List<ClassDTO> domain;
    private List<ClassDTO> range;
    private boolean functionalPropertie;
    private boolean inverseFunctionalPropertie;

    public PropObjDTO() {
        super();
    }

    public PropObjDTO(String name) {
        super(name);
    }

    public PropObjDTO(List<ClassDTO> domain, List<ClassDTO> range, String name) {
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
    public void setDomain(List<ClassDTO> domain) {
        this.domain = domain;
    }

    @Override
    public void addDomain(ClassDTO classe) {
        if (this.getDomain() == null) {
            this.setDomain(new LinkedList<ClassDTO>());
        }
        this.getDomain().add(classe);
    }

    @Override
    public List<ClassDTO> getRange() {
        return range;
    }

    @Override
    public void setRange(List<ClassDTO> range) {
        this.range = range;
    }

    @Override
    public void addRange(ClassDTO range) {
        if (this.getRange() == null) {
            this.setRange(new LinkedList<ClassDTO>());
        }
        this.getRange().add(range);
    }
    
    @Override
    public boolean isPropData(){
        return false;
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
