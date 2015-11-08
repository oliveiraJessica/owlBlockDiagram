package br.ufrn.lii.owlblockdiagram.DTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jessica
 */
public abstract class ClassDTO implements Serializable {

    private String name;
    private String id;
    private String prefix;
    private ArrayList<PropDTO> properties;

    public ClassDTO(String name) {
        this.name = name;
    }

    public ClassDTO(String name, String id) {
        this(name);
        this.id = id;
    }

    public ClassDTO(String name, String id, ArrayList<PropDTO> properties) {
        this(name, id);
        this.properties = properties;
    }

    public abstract boolean isLeaf();

    public abstract boolean isEdge();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ArrayList<PropDTO> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<PropDTO> properties) {
        this.properties = properties;
    }

    public void addProperty(PropDTO prop) {
        if (this.getProperties()== null) {
            this.setProperties(new ArrayList<PropDTO>());
        }
        this.getProperties().add(prop);
    }

}
