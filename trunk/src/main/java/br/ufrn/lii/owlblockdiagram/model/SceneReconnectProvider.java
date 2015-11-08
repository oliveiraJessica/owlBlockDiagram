/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.model;

import java.awt.Point;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.ReconnectProvider;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author jessica
 */
public class SceneReconnectProvider implements ReconnectProvider{

    private ImageWidget edge = null;
    private ImageWidget originalNode = null;
    private ImageWidget replacementNode = null;
    private ImageGraphScene scene;

    public SceneReconnectProvider(ImageGraphScene scene) {
        this.scene = scene;
    }

    public void reconnectingStarted(ConnectionWidget connectionWidget, boolean reconnectingSource) {
    }

    public void reconnectingFinished(ConnectionWidget connectionWidget, boolean reconnectingSource) {
    }

    public boolean isSourceReconnectable(ConnectionWidget connectionWidget) {
//        Object object = this.scene.findObject(connectionWidget);
//        edge = this.scene.isEdge(object) ? (ImageWidget) object : null;
//        originalNode = edge != null ? (ImageWidget) this.scene.getEdgeSource(edge) : null;
//        return originalNode != null;
        return true;
    }

    public boolean isTargetReconnectable(ConnectionWidget connectionWidget) {
//        Object object = this.scene.findObject(connectionWidget);
//        edge = this.scene.isEdge(object) ? (ImageWidget) object : null;
//        originalNode = edge != null ? (ImageWidget) this.scene.getEdgeTarget(edge) : null;
//        return originalNode != null;
        return true;
    }

    public ConnectorState isReplacementWidget(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
        Object object = this.scene.findObject(replacementWidget);
        replacementNode = this.scene.isNode(object) ? (ImageWidget) object : null;
        if (replacementNode != null) {
            return ConnectorState.ACCEPT;
        }
        return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
    }

    public boolean hasCustomReplacementWidgetResolver(Scene scene) {
        return false;
    }

    public Widget resolveReplacementWidget(Scene scene, Point sceneLocation) {
        return null;
    }

    public void reconnect(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
        if (replacementWidget == null) {
//            this.scene.removeEdge(edge);
//        } else if (reconnectingSource) {
//            this.scene.setEdgeSource(edge, replacementNode);
//        } else {
//            this.scene.setEdgeTarget(edge, replacementNode);
        }
//        this.scene.validate();
    }
}
