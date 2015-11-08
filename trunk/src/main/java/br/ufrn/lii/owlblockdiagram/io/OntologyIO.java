package br.ufrn.lii.owlblockdiagram.io;

import java.io.Serializable;

/**
 * To be used in future implementation
 * @author Jéssica
 */
public class OntologyIO implements Serializable {

    private String ONTOLOGY_PATH;
    private String NS;
    private String EXTENSION;
    private String FILE;

    public OntologyIO(String ONTOLOGY_PATH, String NS, String EXTENSION, String FILE) {
        this.ONTOLOGY_PATH = ONTOLOGY_PATH;
        this.NS = NS;
        this.EXTENSION = EXTENSION;
        this.FILE = FILE;
    }

    public String getONTOLOGY_PATH() {
        return ONTOLOGY_PATH;
    }

    public void setONTOLOGY_PATH(String ONTOLOGY_PATH) {
        this.ONTOLOGY_PATH = ONTOLOGY_PATH;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getEXTENSION() {
        return EXTENSION;
    }

    public void setEXTENSION(String EXTENSION) {
        this.EXTENSION = EXTENSION;
    }

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }
}
