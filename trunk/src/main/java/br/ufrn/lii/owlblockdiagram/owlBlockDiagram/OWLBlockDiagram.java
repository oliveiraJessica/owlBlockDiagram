package br.ufrn.lii.owlblockdiagram.owlBlockDiagram;

import br.ufrn.lii.owlblockdiagram.view.MainFrame;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class is the main class for all the program.
 *
 * @author Jéssica Oliveira
 */
public class OWLBlockDiagram {

    /**
     * The main class. This is the start of the program.
     *
     * @param args No arguments are expected.
     */
    public static void main(String[] args) throws IOException {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        new MainFrame().setVisible(true);
    }
}
