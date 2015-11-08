/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import javax.swing.JScrollPane;

/**
 *
 * @author jessica
 */
public interface MainFrameInterface {

    public ImageGraphScene getScene();
    
    public void setScene(ImageGraphScene scene);

    public JScrollPane getSceneScrollPane();

    public PalettePanel getPalettePanel();
    
    public void importOWLFile();
}
