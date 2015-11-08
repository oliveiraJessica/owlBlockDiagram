package br.ufrn.lii.owlblockdiagram.io;

import br.ufrn.lii.owlblockdiagram.DTO.ModelDTO;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.ImageNode;
import br.ufrn.lii.owlblockdiagram.model.Ontology;
import br.ufrn.lii.owlblockdiagram.model.PaletteTree;
import br.ufrn.lii.owlblockdiagram.view.MainFrameInterface;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author Jessica
 */
public class Serializer {

    private ImageGraphScene scene;
    private MainFrameInterface mainFrameInterface;

    public Serializer(MainFrameInterface mainFrameInterface) {
        this.mainFrameInterface = mainFrameInterface;
        this.scene = this.mainFrameInterface.getScene();
    }

    public void save(Object toSave, String path) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(toSave);
            oos.close();

            System.out.println("Save Sucessfull");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void saveOWL(String toSave, String path) {
        try {
            
            FileWriter file = new FileWriter(path);
            PrintWriter printFile = new PrintWriter(file);
            printFile.printf(toSave);
            printFile.close();

            System.out.println("Save Sucessfull");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    public void loadWorkSpace(File file) {

        try {
            InputStream inputFile = new FileInputStream(file);
            InputStream buffer = new BufferedInputStream(inputFile);
            ObjectInput input = new ObjectInputStream(buffer);
            SaveFile saveFile = (SaveFile) input.readObject();
            ModelDTO.setInstance(saveFile.getModel());
            this.loadWorkBench(saveFile);
            this.loadTree(saveFile);
            this.loadOntology(saveFile);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void loadTree(SaveFile saveFile) {
        this.mainFrameInterface.getPalettePanel().setPaletteTree((PaletteTree) saveFile.getTree());
    }

    public void loadWorkBench(SaveFile saveFile) {
        this.loadNodes(saveFile);
        this.addEdges(saveFile);
    }

    public void loadWorkBench(File file) {
        try {
            InputStream inputFile = new FileInputStream(file);
            InputStream buffer = new BufferedInputStream(inputFile);
            ObjectInput input = new ObjectInputStream(buffer);
            SaveFile saveFile = (SaveFile) input.readObject();
            this.loadNodes(saveFile);
            this.addEdges(saveFile);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void loadNodes(SaveFile saveFile) {
        this.scene.cleanScene();
        this.scene.setLabelList(saveFile.getLabelList());
        List<NodeIO> nodesList = saveFile.getNodes();
        for (NodeIO imageNodeIO : nodesList) {
            ImageNode imageNode = new ImageNode(imageNodeIO, this.scene);
            this.scene.addNode(imageNode).setPreferredLocation(imageNodeIO.getPoint());
        }

        this.scene.validate();
        this.scene.repaint();
    }

    public void addEdges(SaveFile saveFile) {
        List<NodeIO> nodesList = saveFile.getNodes();
        int count = 0;
        for (NodeIO imageNodeIO : nodesList) {
            ImageNode imageNode = this.scene.findImageNode(imageNodeIO.getLabel());

            if (imageNodeIO.getChildrenList() != null) {
                ArrayList<String> childrens = (ArrayList<String>) imageNodeIO.getChildrenList().clone();
                if (childrens != null && imageNode != null) {
                    for (int i = 0; i < childrens.size(); i++) {
                        ImageNode childrenNode = this.scene.findImageNode(childrens.get(i));
                        count++;
                        String edge = "edge" + count;
                        this.scene.addEdge(edge);
                        this.scene.setEdgeSource(edge, imageNode);
                        this.scene.setEdgeTarget(edge, childrenNode);
                    }
                }
                this.scene.validate();
            }
        }
    }

    public void loadOntology(SaveFile saveFile) {
        if (saveFile.getOntologyIO() != null) {
            Ontology.EXTENSION = saveFile.getOntologyIO().getEXTENSION();
            Ontology.FILE = saveFile.getOntologyIO().getFILE();
            Ontology.NS = saveFile.getOntologyIO().getNS();
            Ontology.ONTOLOGY_PATH = saveFile.getOntologyIO().getONTOLOGY_PATH();
        }
    }
}
