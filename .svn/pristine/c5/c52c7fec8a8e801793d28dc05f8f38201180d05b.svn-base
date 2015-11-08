/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.view;

import com.hp.hpl.jena.ontology.OntModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jéssica
 */
public class ToolBarActionListener implements ActionListener {

    private MainFrame mainFrame;

    public ToolBarActionListener(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New Project")) {
            this.mainFrame.newProjectDialog();
            this.mainFrame.getScene().cleanScene();
        }else if(ae.getActionCommand().equals("Save Project")){
            this.mainFrame.saveProject();
        }else if(ae.getActionCommand().equals("Clean")){
            this.mainFrame.getScene().cleanScene();
        }else if(ae.getActionCommand().equals("Play")){
            this.mainFrame.changeFocusToSourceCode();
            //this.mainFrame.newInstDialog();
            OntModel inst = this.mainFrame.generateInstance();
            this.mainFrame.loadInstFile(inst);
        }
    }
}
