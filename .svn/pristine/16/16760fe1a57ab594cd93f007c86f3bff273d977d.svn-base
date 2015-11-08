/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.model;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * Singleton class.
 *
 * @author jessica
 */
public class Ontology {

    public static String ONTOLOGY_PATH;
    public static String NS;
    public static String EXTENSION;
    public static String FILE;
    public static String ROOT = "Component"; //Essa propriedade é o componente pai de todos os componentes, o root da árvore
    
    private static Ontology singleton = new Ontology();
    private OntModel ontology;
    private OntModel ontInstance;

    private Ontology() {
        this.ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
    }

    public static Ontology getInstance() {
        return singleton;
    }

    public static Ontology getInstance(boolean newInstance) {

        if (newInstance) {
            singleton = new Ontology();
        }
        return singleton;
    }

    public OntModel getOntologia() {
        return this.ontology;
    }

    public void readModel(String path) {
        Ontology.ONTOLOGY_PATH = path;
        FileManager.get().readModel(this.ontology, Ontology.ONTOLOGY_PATH);
        Ontology.NS = this.ontology.getNsPrefixURI("");
    }

    public OntModel createOntInstance() {
        this.ontInstance = null;
        this.ontInstance = ModelFactory.createOntologyModel(OWL_MEM);
        return this.ontInstance;
    }

    public static String getPrefix() {
        String file = Ontology.FILE;
        String ext = Ontology.EXTENSION;
        int length = file.length() - ext.length() - 1;
        char[] prefix = new char[length];
        file.getChars(0, length, prefix, 0);
        return new String(prefix);
    }
}
