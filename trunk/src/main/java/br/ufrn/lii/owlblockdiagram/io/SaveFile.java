package br.ufrn.lii.owlblockdiagram.io;

import br.ufrn.lii.owlblockdiagram.DTO.ModelDTO;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.ImageNode;
import br.ufrn.lii.owlblockdiagram.model.Ontology;
import br.ufrn.lii.owlblockdiagram.view.MainFrameInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JTree;
import org.netbeans.api.visual.widget.Widget;

/**
 * Method called to save the workspace or auto save the section when the program is closed.
 *
 * @author Jessica
 */
public class SaveFile implements Serializable {

    private List<NodeIO> nodes;
    private ArrayList<String> labelList;
    private JTree jtree;
    private OntologyIO ontologyIO;
    private ModelDTO modelDTO;

    public SaveFile(MainFrameInterface mainFrameInterface) {
        // inicializar o JTree com o PalleteTree do MainFrame
        this.modelDTO = ModelDTO.getInstance();
        this.jtree = mainFrameInterface.getPalettePanel().getPaletteTree();
        this.nodes = new ArrayList();
        this.labelList = mainFrameInterface.getScene().getLabelList();
        this.fillList(mainFrameInterface.getScene());
        if (Ontology.ONTOLOGY_PATH != null && Ontology.NS != null && Ontology.EXTENSION != null && Ontology.FILE != null) {
            this.ontologyIO = new OntologyIO(Ontology.ONTOLOGY_PATH, Ontology.NS, Ontology.EXTENSION, Ontology.FILE);
        }
    }

    public ModelDTO getModel(){
        return this.modelDTO;
    }
    
    public List<NodeIO> getNodes() {
        return this.nodes;
    }

    public ArrayList<String> getLabelList() {
        return labelList;
    }

    public JTree getTree() {
        return this.jtree;
    }

    public OntologyIO getOntologyIO() {
        return ontologyIO;
    }

    private void fillList(ImageGraphScene scene) {
        //pegar nodes do scene 
        Collection<ImageNode> nodesCollection = scene.getNodes();
        for (ImageNode imageNode : nodesCollection) {
            Widget findWidget = scene.findWidget(imageNode);
            imageNode.getComponentInstance().removePropValues();
            ImageNodeIO imageNodeIO = new ImageNodeIO(imageNode, findWidget.getPreferredLocation());

            this.nodes.add(imageNodeIO);
        }
    }
}
