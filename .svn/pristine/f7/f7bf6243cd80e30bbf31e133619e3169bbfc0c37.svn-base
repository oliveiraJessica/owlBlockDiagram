package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.IndividualDTO;
import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import br.ufrn.lii.owlblockdiagram.DTO.Input;
import br.ufrn.lii.owlblockdiagram.DTO.Output;
import br.ufrn.lii.owlblockdiagram.io.NodeIO;
import br.ufrn.lii.owlblockdiagram.utils.ImageUtil;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.ImageWidget;

/**
 *
 * @author jessica
 */
public class ImageNode extends ImageWidget implements Serializable {

    private String label;
    private Image image;
    private Point point;
    private Color color;
    private ArrayList<String> children;
    private IndividualDTO individual;

    public ImageNode(ImageGraphScene scene, Image image, Color color, String label) {
        super(scene, image);
        this.color = color;
        this.label = label;
        this.image = image;

        getActions().addAction(ActionFactory.createMoveAction());
    }

    public ImageNode(NodeIO imageNodeIO, final ImageGraphScene scene) {
        super(scene, ImageUtil.colorIcon("br/ufrn/lii/owlblockdiagram/images/block.png", imageNodeIO.getColor()).getImage());
        this.label = imageNodeIO.getLabel();
        this.image = ImageUtil.colorIcon("br/ufrn/lii/owlblockdiagram/images/block.png", imageNodeIO.getColor()).getImage();
        this.point = imageNodeIO.getPoint();
        this.color = imageNodeIO.getColor();
        this.children = imageNodeIO.getChildrenList();
        this.individual = imageNodeIO.getIndividual();

        getActions().addAction(ActionFactory.createMoveAction());
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
        this.individual.setName(label);
        this.updateIOLabel();
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<String> getChildrenList() {
        return this.children;
    }

    public void setChildrenList(ArrayList<String> children) {
        this.children = children;
    }

    public IndividualDTO getComponentInstance() {
        return individual;
    }

    public void setIndividual(IndividualDTO individual) {
        this.individual = individual;
    }

    public void fillChildList(String label) {
        ArrayList<String> list;
        if (this.getChildrenList() != null) {
            list = this.getChildrenList();
        } else {
            list = new ArrayList<>();
        }
        if (!list.contains(label)) {
            list.add(label);
        }
        this.setChildrenList(list);
    }

    public void createIndividual(ClassDTO component) {
        IndividualDTO instance = new IndividualDTO(component, this.getLabel());
        this.setIndividual(instance);
    }

    private void updateIOLabel() {
        List<Output> outputs = this.individual.getOutputs();
        if (outputs != null) {
            for (Iterator<Output> iterator = outputs.iterator(); iterator.hasNext();) {
                Output output = iterator.next();
                output.constructOutputLabel();
                output.getInput().constructInputLabel();
            }
        }
        List<Input> inputs = this.individual.getInputs();
        if (inputs != null) {
            for (Iterator<Input> iterator = inputs.iterator(); iterator.hasNext();) {
                Input input = iterator.next();
                input.constructInputLabel();
                input.getOutput().constructOutputLabel();
            }
        }
    }
}
