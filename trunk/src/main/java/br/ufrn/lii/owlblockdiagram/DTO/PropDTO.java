/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufrn.lii.owlblockdiagram.DTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public abstract class PropDTO <T>  implements Serializable{
    
    private String name;

    public PropDTO() {
    }

    public PropDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract List<ClassDTO> getDomain();
    
    public abstract void setDomain(List<ClassDTO> list);
    
    public abstract void addDomain(ClassDTO domain);
    
    public abstract List<T> getRange();
    
    public abstract void setRange(List<T> range);
    
    public abstract void addRange(T range);
    
    public abstract boolean isPropData();
    
    public abstract boolean isFunctionalProperty();
    
    public abstract boolean isInverseFunctionalProperty();
}
