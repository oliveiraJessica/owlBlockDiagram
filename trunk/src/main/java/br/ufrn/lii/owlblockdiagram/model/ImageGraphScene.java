package br.ufrn.lii.owlblockdiagram.model;

import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author jessica
 */
public class ImageGraphScene extends GraphScene<ImageNode, String> implements Serializable {

    private LayerWidget mainLayer;
    private LayerWidget connectionLayer;
    private LayerWidget interactionLayer = new LayerWidget(this);
    private Connection connection;
    private WidgetAction editorAction = ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor(this));
    private WidgetAction connectAction = ActionFactory.createExtendedConnectAction(this.interactionLayer, new SceneConnectProvider(this));
    private WidgetAction reconnectAction = ActionFactory.createReconnectAction(new SceneReconnectProvider(this));
    private WidgetAction rectangularSelectAction = ActionFactory.createRectangularSelectAction(new MyRectangularSelectDecorator(this), interactionLayer, ActionFactory.createObjectSceneRectangularSelectProvider(this));
    private NodeMenu nodeMenu = new NodeMenu(this);
    private EdgeMenu edgeMenu = new EdgeMenu(this);
    private ArrayList<ClassDTO> ioArray = new ArrayList<ClassDTO>();
//    private Hashtable<String, Integer> labelTable = new Hashtable<String, Integer>();
    private ArrayList<String> labelList = new ArrayList<String>();

    public ImageGraphScene() {
        super();

        this.mainLayer = new LayerWidget(this);
        this.addChild(this.mainLayer);

        this.connectionLayer = new LayerWidget(this);
        this.addChild(this.connectionLayer);

        this.getActions().addAction(ActionFactory.createZoomAction());
        this.getActions().addAction(ActionFactory.createPanAction());
        this.getActions().addAction(ActionFactory.createRectangularSelectAction(new MyRectangularSelectDecorator(this), mainLayer, ActionFactory.createObjectSceneRectangularSelectProvider(this)));

        this.validate();
    }

    public ArrayList<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(ArrayList<String> labelList) {
        this.labelList = labelList;
    }

    @Override
    protected Widget attachNodeWidget(ImageNode n) {
        IconNodeWidget node = new IconNodeWidget(this);
        if (n.getImage() != null) {
            node.setImage(n.getImage().getScaledInstance(60, 40, 0));
        }
        String label = n.getLabel();
        node.setLabel(label);

        node.getActions().addAction(this.connectAction);
        node.getActions().addAction(this.reconnectAction);
//        node.getActions().addAction(createSelectAction());
        node.getActions().addAction(ActionFactory.createSelectAction(new MySelectProvider()));
        node.getActions().addAction(1, ActionFactory.createMoveAction(null, new MultipleMovementAction(this)));
        node.getLabelWidget().getActions().addAction(this.editorAction);
        node.getActions().addAction(ActionFactory.createPopupMenuAction(this.nodeMenu));

        this.mainLayer.addChild(node);
        return node;
    }

    @Override
    protected Widget attachEdgeWidget(String e) {
        this.connection = new Connection(this);
        this.connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
        this.connection.setEndPointShape(PointShape.SQUARE_FILLED_BIG);
        this.connection.setControlPointShape(PointShape.SQUARE_FILLED_BIG);

        this.connection.getActions().addAction(createSelectAction());
        this.connection.getActions().addAction(ActionFactory.createAddRemoveControlPointAction());
        this.connection.getActions().addAction(ActionFactory.createFreeMoveControlPointAction());
        this.connection.getActions().addAction(ActionFactory.createPopupMenuAction(edgeMenu));

        this.connectionLayer.addChild(this.connection);
        return this.connection;
    }

    @Override
    protected void attachEdgeSourceAnchor(String edge, ImageNode oldSourceNode, ImageNode sourceNode) {
        Widget w = sourceNode != null ? findWidget(sourceNode) : null;
        ((Connection) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));
    }

    @Override
    protected void attachEdgeTargetAnchor(String edge, ImageNode oldTargetNode, ImageNode targetNode) {
        Widget w = targetNode != null ? findWidget(targetNode) : null;
        ((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
        if (targetNode != null) {
            Connection edgeWidget = new Connection(this);
            edgeWidget = (Connection) findWidget(edge);
            Widget sourceWidget = edgeWidget.getSourceAnchor().getRelatedWidget();
            String sourceString = ((LabelWidget) sourceWidget.getChildren().get(1)).getLabel();
            ImageNode sourceImageNode = findImageNode(sourceString);

            Widget targetWidget = edgeWidget.getTargetAnchor().getRelatedWidget();
            String targetString = ((LabelWidget) targetWidget.getChildren().get(1)).getLabel();

            sourceImageNode.fillChildList(targetString);

            edgeWidget.connect(sourceImageNode, targetNode);

        }
    }

    public ImageNode createImageNode(PaletteTree tree) {
        JLabel label = tree.getSelectedNodeLabel();
        String labelString = label.getText();

        int labelCount = labelString.length();
        String blockLabel = "";
        boolean labelExists = false;
        if (this.labelList.isEmpty()) {
            blockLabel = labelString + " " + '1';
        } else {
            for (String string : this.labelList) {
                if (string.startsWith(labelString)) {
                    String numStr = string.substring(labelCount);
                    int num = Integer.parseInt(numStr.trim());
                    blockLabel = labelString + " " + (num + 1);
                    labelExists = true;
                }
            }
            if (!labelExists) {
                blockLabel = labelString + " " + '1';
            }

        }
        label.setText(blockLabel);
        this.labelList.add(blockLabel);

        ImageNode image = new ImageNode(this, tree.getSelectedNodeIcon().getImage(), tree.getSelectedNodeIcon().getColor(), blockLabel);
        TreeIcon treeIcon = tree.getSelectedNodeIcon();
        image.createIndividual(treeIcon.getComponents());
        return image;
    }

    private class MySelectProvider implements SelectProvider {

        @Override
        public boolean isAimingAllowed(Widget widget, Point point, boolean bln) {
            return false;
        }

        @Override
        public boolean isSelectionAllowed(Widget widget, Point point, boolean bln) {
            return findObject(widget) != null;
        }

        @Override
        public void select(Widget widget, Point point, boolean bln) {
            Object object = findObject(widget);

            setFocusedObject(object);
            if (object != null) {
                if (!bln && getSelectedObjects().contains(object)) {
                    return;
                }
                userSelectionSuggested(Collections.singleton(object), bln);
            } else {
                userSelectionSuggested(Collections.emptySet(), bln);
            }
        }

    }

    private class MyRectangularSelectDecorator implements RectangularSelectDecorator {

        private final GraphScene scene;

        private MyRectangularSelectDecorator(GraphScene scene) {
            this.scene = scene;
        }

        @Override
        public Widget createSelectionWidget() {
            Widget widget = new Widget(this.scene);
            widget.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 3, 3));
            return widget;
        }
    }

    private class LabelTextFieldEditor implements TextFieldInplaceEditor {

        ImageGraphScene scene;

        public LabelTextFieldEditor(ImageGraphScene scene) {
            this.scene = scene;
        }

        private void updateChildList(String newString, String oldString) {
            Collection<ImageNode> nodes = this.scene.getNodes();
            if (nodes == null) {
                return;
            }
            for (Iterator<ImageNode> iterator = nodes.iterator(); iterator.hasNext();) {
                ImageNode node = iterator.next();
                if (node.getChildrenList() == null) {
                    return;
                }
                ArrayList<String> childrens = (ArrayList<String>) node.getChildrenList().clone();
                for (Iterator<String> iterator1 = childrens.iterator(); iterator1.hasNext();) {
                    String children = iterator1.next();
                    if (children.equals(oldString)) {
                        node.getChildrenList().remove(children);
                        node.getChildrenList().add(newString);
                    }
                }
            }
        }

        @Override
        public boolean isEnabled(Widget widget) {
            return true;
        }

        @Override
        public String getText(Widget widget) {
            return ((LabelWidget) widget).getLabel();
        }

        @Override
        public void setText(Widget widget, String text) {
            if (text.trim().length() > 0) {
                Collection<ImageNode> nodesCollection = this.scene.getNodes();
                for (ImageNode node : nodesCollection) {
                    String nodeLabel = node.getLabel();
                    if (nodeLabel.equals(text)) {
                        text = this.getText(widget);
                        break;
                    }
                }
                ImageNode node = scene.findImageNode(this.getText(widget));
                this.updateChildList(this.getText(widget), text);
                node.setLabel(text);
                scene.labelList.remove(this.getText(widget));
                scene.labelList.add(text);
                ((LabelWidget) widget).setLabel(text);
            }
        }
    }

    private class MultipleMovementAction implements MoveProvider {

        HashMap<Widget, Point> originals = new HashMap<Widget, Point>();
        private Point original;
        private GraphScene scene;

        public MultipleMovementAction(GraphScene scene) {
            this.scene = scene;
        }

        @Override
        public void movementStarted(Widget widget) {
            Object object = this.scene.findObject(widget);
            if (this.scene.isObject(object)) {
                for (Object o : this.scene.getSelectedObjects()) {
                    if (this.scene.isObject(o)) {
                        Widget w = this.scene.findWidget(o);
                        if (w != null) {
                            if (w.getPreferredLocation() != null) {
                                this.originals.put(w, w.getPreferredLocation());
                            } else {
                                this.originals.put(w, w.getPreferredBounds().getLocation());
                            }
                        }
                    }
                }
            } else {
                this.originals.put(widget, widget.getPreferredLocation());
            }
        }

        @Override
        public void movementFinished(Widget widget) {
            this.originals.clear();
            this.original = null;
        }

        @Override
        public Point getOriginalLocation(Widget widget) {
            this.original = widget.getPreferredLocation();
            return this.original;
        }

        @Override
        public void setNewLocation(Widget widget, Point point) {
            int dx = point.x - this.original.x;
            int dy = point.y - this.original.y;
            for (Map.Entry<Widget, Point> entry : this.originals.entrySet()) {
                Point location = entry.getValue();
                entry.getKey().setPreferredLocation(new Point(location.x + dx, location.y + dy));

            }
        }
    }

    public void cleanScene() {
        Collection<ImageNode> imageNodeCollection = this.getNodes();
        Object[] imageNodeArray = imageNodeCollection.toArray();
        int size = imageNodeCollection.size();
        this.labelList.clear();
        for (int i = 0; i < size; i++) {
            ImageNode imageNode = (ImageNode) imageNodeArray[i];
            this.removeNodeWithEdges(imageNode);
        }

        this.revalidate();
        this.repaint();
    }

    public ImageNode findImageNode(String label) {
        Collection<ImageNode> nodesCollection = this.getNodes();
        for (ImageNode imageNode : nodesCollection) {
            if (imageNode.getLabel().equals(label)) {
                return imageNode;
            }
        }
        return null;
    }
}
