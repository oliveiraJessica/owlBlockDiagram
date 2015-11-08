/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.IndividualDTO;

/**
 *
 * @author Jéssica
 */
public class Properties {
    
    private String hasTag;
    private String hasDescription;
    private IndividualDTO industrialProcess;
    private IndividualDTO isComponent;
    private IndividualDTO hasInstrument;

    public String getHasTag() {
        return hasTag;
    }

    public void setHasTag(String hasTag) {
        this.hasTag = hasTag;
    }

    public String getHasDescription() {
        return hasDescription;
    }

    public void setHasDescription(String hasDescription) {
        this.hasDescription = hasDescription;
    }

    public IndividualDTO getIndustrialProcess() {
        return industrialProcess;
    }

    public void setIndustrialProcess(IndividualDTO industrialProcess) {
        this.industrialProcess = industrialProcess;
    }

    public IndividualDTO getIsComponent() {
        return isComponent;
    }

    public void setIsComponent(IndividualDTO isComponent) {
        this.isComponent = isComponent;
    }

    public IndividualDTO getHasInstrument() {
        return hasInstrument;
    }

    public void setHasInstrument(IndividualDTO hasInstrument) {
        this.hasInstrument = hasInstrument;
    }
    
    
}
