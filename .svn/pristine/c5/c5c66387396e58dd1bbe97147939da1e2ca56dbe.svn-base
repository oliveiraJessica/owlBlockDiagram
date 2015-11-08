package br.ufrn.lii.owlblockdiagram.model;

import java.awt.Point;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 * This class implements a ConnectProvider to the {@link ImageGraphScene}
 * object.
 *
 * @author Jéssica Oliveira
 */
public class SceneConnectProvider implements ConnectProvider {

    private ImageNode source = null;
    private ImageNode target = null;
    private ImageGraphScene scene;
    int counter = 0;

    public SceneConnectProvider(ImageGraphScene scene) {
        this.scene = scene;
    }

    
    
    @Override
    public boolean isSourceWidget(Widget sourceWidget) {
        Object object = this.scene.findObject(sourceWidget);
        this.source = this.scene.isNode(object) ? (ImageNode) object : null;
        return this.source != null;
    }

    @Override
    public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
        Object object = this.scene.findObject(targetWidget);
        this.target = this.scene.isNode(object) ? (ImageNode) object : null;
        if (this.target != null) {
            ConnectorState state = !this.source.equals(this.target) ? ConnectorState.ACCEPT : ConnectorState.REJECT_AND_STOP;
            return state;
        }
        return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
    }

    @Override
    public boolean hasCustomTargetWidgetResolver(Scene scene) {
        return false;
    }

    @Override
    public Widget resolveTargetWidget(Scene scene, Point sceneLocation) {
        return null;
    }

    @Override
    public void createConnection(Widget sourceWidget, Widget targetWidget) {
        String edge = "edge" + this.counter++;
        this.scene.addEdge(edge);
        this.scene.setEdgeSource(edge, this.source);
        this.scene.setEdgeTarget(edge, this.target);
        this.scene.validate();
    }
}
