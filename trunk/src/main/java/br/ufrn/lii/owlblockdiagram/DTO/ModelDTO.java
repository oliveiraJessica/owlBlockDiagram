/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.DTO;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public class ModelDTO implements Serializable {

    private String NAMESPACE;
    private List<PropDTO> properties;
    private List<ClassDTO> classes;
    private static ModelDTO instance;

    private ModelDTO() { // nao pode fazer isso pq vai deixar nullo tudo que foi calculado no consult!!!

    }

    private ModelDTO(String namespace, List<PropDTO> properties, List<ClassDTO> classes) {
        this.NAMESPACE = namespace;
        this.properties = properties;
        this.classes = classes;
    }

    public static ModelDTO getInstance() {
        if (instance == null) {
            instance = new ModelDTO();
        }
        return instance;
    }

    public static void setInstance(ModelDTO mdto){
        instance = mdto;
    }
    
    public void init(String namespace, List<PropDTO> properties, List<ClassDTO> classes) {
        this.NAMESPACE = namespace;
        this.properties = properties;
        this.classes = classes;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public void setNamespace(String namespace) {
        this.NAMESPACE = namespace;
    }

    public List<PropDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropDTO> properties) {
        this.properties = properties;
    }

    public void addProperty(PropDTO propertie) {
        if (this.getClasses() == null) {
            this.setClasses(new LinkedList<ClassDTO>());
        }
        this.getProperties().add(propertie);
    }

    public List<ClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassDTO> classes) {
        this.classes = classes;
    }

    public void addClasse(ClassDTO classe) {
        if (this.getClasses() == null) {
            this.setClasses(new LinkedList<ClassDTO>());
        }
        this.getClasses().add(classe);
    }

    public ClassDTO findClasse(String className) {
        if (this.getClasses() == null) {
            this.setClasses(new LinkedList<ClassDTO>());
            return null;
        } else {
            for (Iterator<ClassDTO> it = this.getClasses().iterator(); it.hasNext();) {
                ClassDTO classDTO = it.next();
                if (classDTO.getName().equals(className)) {
                    return classDTO;
                }
            }
        }
        return null;
    }

    public PropDTO findProperty(String propName) {
        if (this.getProperties() == null) {
            this.setProperties(new LinkedList<PropDTO>());
        }
        for (Iterator<PropDTO> it = this.getProperties().iterator(); it.hasNext();) {
            PropDTO propDTO = it.next();
            if (propDTO.getName().equals(propName)) {
                return propDTO;
            }
        }
        return null;
    }
}
