package br.ufrn.lii.owlblockdiagram.controller;

import br.ufrn.lii.owlblockdiagram.DTO.ComponentDTO;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.Ontology;
import br.ufrn.lii.owlblockdiagram.utils.OWLUtil;
import com.hp.hpl.jena.ontology.OntModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author Jéssica Oliveira
 */
public class OntologyController {

    private static OntologyController controller;

    public static OntologyController getInstance() {
        if (OntologyController.controller == null) {
            OntologyController.controller = new OntologyController();
        }

        return OntologyController.controller;
    }

    public boolean importOWL(File selectedFile) {
        String prefix = selectedFile.getName();
        Ontology.FILE = prefix;
        String ext = FilenameUtils.getExtension(selectedFile.getAbsolutePath());
        Ontology.EXTENSION = ext;

        if (selectedFile.canRead()) {
            File owlFile = new File("./src/main/resources/br/ufrn/lii/owlblockdiagram/docs/" + Ontology.FILE);
            try {
                Files.copy(selectedFile.toPath(), owlFile.toPath(), REPLACE_EXISTING);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                System.out.println("Erro ao copiar arquivo");
            }
        }
        return true;
    }

    public ArrayList<ComponentDTO> loadOWL() {
        String path = "./src/main/resources/br/ufrn/lii/owlblockdiagram/docs/";
        File owlFile = new File(path + Ontology.FILE);
        if (!owlFile.canRead()) {
            JOptionPane.showMessageDialog(null, "O arquivo não pode ser lido", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Ontology.ONTOLOGY_PATH = path + Ontology.FILE;
        OntModel base = Ontology.getInstance().getOntologia();
        Ontology.getInstance().readModel(path + Ontology.FILE);
        ArrayList<ComponentDTO> componentsList = OWLUtil.consult(owlFile, base);
        return componentsList;
    }

    public OntModel loadOntology() {
        String path = "./src/main/resources/br/ufrn/lii/owlblockdiagram/docs/";
        OntModel base = Ontology.getInstance().getOntologia();
        Ontology.getInstance().readModel(path + Ontology.FILE);
        return base;
    }

    public OntModel createOntInstance(ImageGraphScene scene) {
        OntModel base = this.loadOntology();
        OntModel inst = Ontology.getInstance().createOntInstance();
        OWLUtil.createOntInstance(inst, base, scene);
        return inst;
    }
}