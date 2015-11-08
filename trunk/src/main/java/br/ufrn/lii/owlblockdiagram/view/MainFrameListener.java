package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.io.SaveFile;
import br.ufrn.lii.owlblockdiagram.io.Serializer;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

/**
 *
 * @author Jessica
 */
public class MainFrameListener implements WindowListener {

    private MainFrameInterface mainFrame;

    public MainFrameListener(MainFrameInterface mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Salvando Estado");

        Serializer serializer = new Serializer(this.mainFrame);
        SaveFile saveFile = new SaveFile(this.mainFrame);
        serializer.save(saveFile, "src/main/resources/br/ufrn/lii/owlblockdiagram/docs/doc.owlbd");
    }

    public void windowOpened(WindowEvent e) {
        System.out.println("Abrindo Estado");
        //deserialize 

            if (new File("src/main/resources/br/ufrn/lii/owlblockdiagram/docs/doc.owlbd").exists()) {
                File file = new File("src/main/resources/br/ufrn/lii/owlblockdiagram/docs/doc.owlbd");
                Serializer serializer = new Serializer(this.mainFrame);
                serializer.loadWorkSpace(file);
            } else {
                this.mainFrame.importOWLFile();
            }

    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
