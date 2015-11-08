package br.ufrn.lii.owlblockdiagram.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author JÃ©ssica Oliveira
 */
public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenu helpMenu;

    private JMenuItem newProject;

    private JMenuItem openOWL;

    private JMenuItem openProject;
    private JMenuItem saveProject;
    private JMenuItem openInst;
    private JMenuItem saveInst;

    public MenuBar() {
        super();
        this.fileMenu = new JMenu();
        this.helpMenu = new JMenu();
        this.init();
    }

    public JMenuItem getOpenOWLMenuItem() {
        return this.openOWL;
    }

    public JMenuItem getOpenInst() {
        return openInst;
    }

    public JMenuItem getSaveInst() {
        return saveInst;
    }

    public JMenuItem getOpenProjectMenuItem() {
        return openProject;
    }

    public JMenuItem getSaveProjectMenuItem() {
        return this.saveProject;
    }

    public JMenuItem getNewProjectMenuItem() {
        return newProject;
    }

    private void init() {
        //Menu Bar creation:
        this.fileMenu.setText("Arquivo");

        this.newProject = new JMenuItem("Novo Projeto");
        this.newProject.setToolTipText("Criar novo projeto");
        this.fileMenu.add(this.newProject);

//        this.fileMenu.addSeparator();

        this.openOWL = new JMenuItem("Carregar OWL");
//        this.fileMenu.add(this.openOWL);

        this.fileMenu.addSeparator();

        this.openInst = new JMenuItem("Abrir Instância OWL");
        this.openInst.setToolTipText("Abrir Instância Ontológica");
        this.fileMenu.add(this.openInst);

        this.saveInst = new JMenuItem("Salvar Instância OWL");
        this.saveInst.setToolTipText("Salvar Instância Ontológica");
        this.fileMenu.add(this.saveInst);

        this.fileMenu.addSeparator();

        this.openProject = new JMenuItem("Abrir Projeto");
        this.openProject.setToolTipText("Abrir Projeto");
        this.fileMenu.add(this.openProject);

        this.saveProject = new JMenuItem("Salvar Projeto");
        this.saveProject.setToolTipText("Salvar Projeto");
        this.fileMenu.add(this.saveProject);

        this.add(this.fileMenu);

//        this.helpMenu.setText("Ajuda");
//        this.add(this.helpMenu);
    }
}
