package br.ufrn.lii.owlblockdiagram.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jéssica Oliveira
 */
public class MenuBarActionListener implements ActionListener {

    private MainFrame mainFrame;
    private final String OPEN_PROJECT = "Abrir Projeto";
    private final String SAVE_PROJECT = "Salvar Projeto";
    private final String NEW_PROJECT = "Novo Projeto";
    private final String OPEN_INST = "Abrir Instância OWL";
    private final String SAVE_INST = "Salvar Instância OWL";

    public MenuBarActionListener(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(OPEN_PROJECT)) {
            this.mainFrame.openProject();
        } else if (e.getActionCommand().equals(SAVE_PROJECT)) {
            this.mainFrame.saveProject();
        } else if (e.getActionCommand().equals(NEW_PROJECT)) {
            this.mainFrame.newProjectDialog();
            this.mainFrame.getScene().cleanScene();
            // TODO apagar .owlbd?
        } else if (e.getActionCommand().equals(OPEN_INST)) {
            this.mainFrame.newInstDialog();
            this.mainFrame.openInstOWL();
        } else if (e.getActionCommand().equals(SAVE_INST)) {
            this.mainFrame.saveInstOWL();
        }
    }

}
