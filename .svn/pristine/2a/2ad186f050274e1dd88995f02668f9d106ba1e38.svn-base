package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.ImageNode;
import br.ufrn.lii.owlblockdiagram.model.PaletteTree;
import java.awt.Point;
import java.awt.event.MouseEvent;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author jessica
 */
public class SceneAction extends WidgetAction.Adapter {

    private MainFrameInterface mainFrame;
    private ImageGraphScene scene;
    private ImageNode image;
    private Point point;
    private PaletteTree tree;

    public SceneAction(MainFrameInterface mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.scene = this.mainFrame.getScene();
    }

    @Override
    public WidgetAction.State mousePressed(Widget widget, WidgetAction.WidgetMouseEvent event) {
        this.point = event.getPoint();
        
        this.tree = this.mainFrame.getPalettePanel().getPaletteTree();
        this.tree.revalidate();
        this.tree.repaint();

        if (event.getClickCount() == 2) {
            if ((event.getButton() == MouseEvent.BUTTON1 || event.getButton() == MouseEvent.BUTTON2) && !event.isControlDown()) {
                if (this.tree.selIsLeaf()) {
                    this.image = this.scene.createImageNode(this.tree);
                    this.scene.addNode(this.image).setPreferredLocation(widget.convertLocalToScene(this.point));
                    this.mainFrame.getSceneScrollPane().updateUI();
                } else {
                    return WidgetAction.State.REJECTED;
                }
                return WidgetAction.State.CONSUMED;
            }
        }
        return WidgetAction.State.REJECTED;
    }

}
