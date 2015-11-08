/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import br.ufrn.lii.owlblockdiagram.DTO.IndividualDTO;
import br.ufrn.lii.owlblockdiagram.DTO.Input;
import br.ufrn.lii.owlblockdiagram.DTO.ModelDTO;
import br.ufrn.lii.owlblockdiagram.DTO.Output;
import java.util.Iterator;
import java.util.List;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author Jéssica
 */
public class Connection extends ConnectionWidget {

    private Input input;
    private Output output;
    private ImageNode source;
    private ImageNode target;

    public Connection(Scene scene) {
        super(scene);
    }

    //TODO rever esse construtor, criar um metodo para, a partir do target criar um input, e a partir do source criar um output
//    public Connection(Scene scene, ImageNode source, ImageNode target) {
//        this(scene);
//        this.source = source;
//        this.target = target;
//
//        this.input = (Input) ModelDTO.getInstance().findClasse("Input");
//        this.output = (Output) ModelDTO.getInstance().findClasse("Output");
////        this.input = new Input(Input.contructInputLabel());
////        this.output = new Output(Output.constructOutputLabel());
//
//        this.input.setOutput(output);
//        this.output.setInput(input);
//
//        this.input.setComponent(this.target.getComponentInstance());
//        this.output.setComponent(this.source.getComponentInstance());
//    }

    public void attachTarget(ImageNode target) {
        this.target = target;
//        this.input = new Input(Input.contructInputLabel());
        ClassDTO c = ModelDTO.getInstance().findClasse("Input");
        this.input = new Input(c);
        //this.getScene().addChild(this.input);
        this.input.setComponent(this.target.getComponentInstance());
    }

    public void attachSource(ImageNode source) {
        this.source = source;
        ClassDTO c = ModelDTO.getInstance().findClasse("Output");
        this.output = new Output(c);
//        this.output = new Output(Output.constructOutputLabel());
        this.output.setComponent(this.source.getComponentInstance());
    }

    public void connect(ImageNode source, ImageNode target) {
        if (!alreadyConnected(source, target)) {
            this.attachTarget(target);
            this.attachSource(source);
            this.input.setOutput(this.output);
            this.input.constructInputLabel();
            this.output.setInput(this.input);
            this.output.constructOutputLabel();
        }
    }

    private boolean alreadyConnected(ImageNode source, ImageNode target) {
        IndividualDTO newComponent = target.getComponentInstance();
        List<Output> outputs = source.getComponentInstance().getOutputs();
        if (outputs != null) {
            for (Iterator<Output> iterator = outputs.iterator(); iterator.hasNext();) {
                Output sourceOutput = iterator.next();
                IndividualDTO component = sourceOutput.getInput().getComponent();
                if (newComponent.equals(component)) {
                    return true;
                }
            }
        }
        return false;
    }
}
