package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.DTO.ComponentDTO;
import br.ufrn.lii.owlblockdiagram.controller.OntologyController;
import br.ufrn.lii.owlblockdiagram.io.SaveFile;
import br.ufrn.lii.owlblockdiagram.io.Serializer;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import com.hp.hpl.jena.ontology.OntModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.OverlayLayout;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 * A JFrame instance, representing the Main Frame of the program.
 *
 * @author jessica
 */
public class MainFrame extends JFrame implements MainFrameInterface {

    private ImageGraphScene scene;
    private JInternalFrame internalFrame;
    private JSplitPane splitPane;
    private JViewport viewport;
    private JScrollPane paletteScrollPane;
    private PalettePanel palettePanel;
    private JTabbedPane tabbedPane;
    private JLayeredPane layeredPane;
    private JScrollPane sceneScrollPane;
    private JScrollPane sourceCodeScrollPane;
    private RSyntaxTextArea editorPane;
    private MenuBar menuBar;
    private JFileChooser owlFileChooser;
    private JToolBar toolBar;
    private SceneAction createAction;
    private JComponent satelliteView;
    private static Point point = new Point();

    private final String NEW_PROJECT_ACTION = "New Project";
    private final String SAVE_PROJECT_ACTION = "Save Project";
    private final String CLEAN_ACTION = "Clean";
    private final String PLAY_ACTION = "Play";

    public MainFrame() throws HeadlessException {
        this.init();
    }

    @Override
    public ImageGraphScene getScene() {
        return this.scene;
    }

    public void setScene(ImageGraphScene scene) {
        this.scene = scene;
    }

    @Override
    public JScrollPane getSceneScrollPane() {
        return this.sceneScrollPane;
    }

    @Override
    public PalettePanel getPalettePanel() {
        return this.palettePanel;
    }

    protected JFileChooser getOWLFileChooser() {
        if (this.owlFileChooser == null) {
            this.loadOWLFileChooser();
        }
        return this.owlFileChooser;
    }

    private void init() {
        // variables initialization
        this.scene = new ImageGraphScene();
        this.scene.createView();

        this.internalFrame = new JInternalFrame();
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.viewport = new JViewport();
        this.paletteScrollPane = new JScrollPane(this.viewport);
        this.palettePanel = new PalettePanel();
        this.tabbedPane = new JTabbedPane();
        this.layeredPane = new JLayeredPane();
        this.sceneScrollPane = new JScrollPane();
        this.sourceCodeScrollPane = new JScrollPane();
        this.menuBar = new MenuBar();
        this.toolBar = new JToolBar();

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/br/ufrn/lii/owlblockdiagram/images/OWL.png");
        this.setIconImage(icon);
        this.setTitle("OWLBlockDiagram");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrameListener windowListener = new MainFrameListener(this);
        this.addWindowListener(windowListener);
        this.setSize(new Dimension(800, 600));

        javax.swing.plaf.InternalFrameUI ifu = this.internalFrame.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
        this.internalFrame.setVisible(true);

        this.internalFrame.setJMenuBar(this.menuBar);
        MenuBarActionListener menuBarActionListener = new MenuBarActionListener(this);
        JMenuItem openOWLItem = this.menuBar.getOpenOWLMenuItem();
        JMenuItem openProjectItem = this.menuBar.getOpenProjectMenuItem();
        JMenuItem saveProjectItem = this.menuBar.getSaveProjectMenuItem();
        JMenuItem newProjectItem = this.menuBar.getNewProjectMenuItem();
        JMenuItem openInstItem = this.menuBar.getOpenInst();
        JMenuItem saveInstItem = this.menuBar.getSaveInst();

        openOWLItem.addActionListener(menuBarActionListener);
        openProjectItem.addActionListener(menuBarActionListener);
        saveProjectItem.addActionListener(menuBarActionListener);
        newProjectItem.addActionListener(menuBarActionListener);
        openInstItem.addActionListener(menuBarActionListener);
        saveInstItem.addActionListener(menuBarActionListener);

        this.createToolBar();

        this.createAction = new SceneAction(this);
        this.scene.getActions().addAction(this.createAction);

        this.createTabbedPanel();
        this.mainFrameLayout();
    }

    private void mainFrameLayout() {
        this.internalFrame.setLayout(new BorderLayout());
        this.internalFrame.add(this.toolBar, BorderLayout.NORTH);

        this.viewport.add(this.palettePanel);

        this.paletteScrollPane.setMinimumSize(new Dimension(200, 200));
        this.splitPane.setLeftComponent(this.paletteScrollPane);
        this.splitPane.setRightComponent(this.tabbedPane);
        this.internalFrame.add(this.splitPane, BorderLayout.CENTER);

        this.add(this.internalFrame);
    }

    private void createToolBar() {
        this.toolBar.setRollover(true);
        this.toolBar.setVisible(true);
        this.toolBar.setFloatable(false);

        ToolBarActionListener toolBarActionListener = new ToolBarActionListener(this);

        ImageIcon newImageIcon = new ImageIcon(ImageUtilities.loadImage("br/ufrn/lii/owlblockdiagram/images/new.png"));
        JButton newProjectButton = new JButton(newImageIcon);
        newProjectButton.setActionCommand(NEW_PROJECT_ACTION);
        newProjectButton.addActionListener(toolBarActionListener);
        newProjectButton.setToolTipText("Criar novo projeto");
        this.toolBar.add(newProjectButton);

        ImageIcon saveImageIcon = new ImageIcon(ImageUtilities.loadImage("br/ufrn/lii/owlblockdiagram/images/save.jpg"));
        JButton saveButton = new JButton(saveImageIcon);
        saveButton.setActionCommand(SAVE_PROJECT_ACTION);
        saveButton.addActionListener(toolBarActionListener);
        saveButton.setToolTipText("Salvar o projeto atual");
        this.toolBar.add(saveButton);

        this.toolBar.addSeparator();

        ImageIcon cleanImageIcon = new ImageIcon(ImageUtilities.loadImage("br/ufrn/lii/owlblockdiagram/images/clean.png"));
        JButton cleanButton = new JButton(cleanImageIcon);
        cleanButton.setActionCommand(CLEAN_ACTION);
        cleanButton.addActionListener(toolBarActionListener);
        cleanButton.setToolTipText("Limpar a área de trabalho");
        this.toolBar.add(cleanButton);

        this.toolBar.addSeparator();

        ImageIcon playImageIcon = new ImageIcon(ImageUtilities.loadImage("br/ufrn/lii/owlblockdiagram/images/play.png"));
        JButton playButton = new JButton(playImageIcon);
        playButton.setActionCommand(PLAY_ACTION);
        playButton.addActionListener(toolBarActionListener);
        playButton.setToolTipText("Gerar instância Ontológica");
        this.toolBar.add(playButton);
    }

    private void createTabbedPanel() {
        this.sceneScrollPane.setViewportView(this.scene.createView());

        JPanel scrollScenePanel = new JPanel();
        scrollScenePanel.setPreferredSize(new Dimension(300, 300));
        scrollScenePanel.setLayout(new BoxLayout(scrollScenePanel, BoxLayout.PAGE_AXIS));
        scrollScenePanel.add(this.sceneScrollPane);
        JPanel sceneScrollablePanel = new JPanel();
        sceneScrollablePanel.setLayout(new BoxLayout(sceneScrollablePanel, BoxLayout.PAGE_AXIS));
        sceneScrollablePanel.setOpaque(false);
        sceneScrollablePanel.add(Box.createHorizontalGlue());
        sceneScrollablePanel.add(scrollScenePanel);
        sceneScrollablePanel.add(Box.createHorizontalGlue());

        JPanel satelliteViewPanel = new JPanel();
        satelliteViewPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        this.satelliteView = this.scene.createSatelliteView();
        satelliteView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        satelliteView.setLayout(new FlowLayout(FlowLayout.RIGHT));
        satelliteViewPanel.add(this.satelliteView);
        satelliteViewPanel.setOpaque(false);
        
        this.layeredPane.setLayout(new OverlayLayout(this.layeredPane));
        this.layeredPane.add(sceneScrollablePanel, JLayeredPane.DEFAULT_LAYER);
        this.layeredPane.add(satelliteViewPanel, JLayeredPane.PALETTE_LAYER);
        this.layeredPane.setToolTipText("Área de trabalho");

        this.tabbedPane.addTab("Área de trabalho", this.layeredPane);

        this.sourceCodeScrollPane.setBackground(Color.WHITE);
        this.editorPane = new RSyntaxTextArea();
        this.editorPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        this.editorPane.setCodeFoldingEnabled(true);
        this.editorPane.setAntiAliasingEnabled(true);
        this.editorPane.setFont(new Font("Georgia", Font.PLAIN, 20));

        this.sourceCodeScrollPane.setViewportView(this.editorPane);
        this.tabbedPane.addTab("Instância Ontológica", sourceCodeScrollPane);
        this.sourceCodeScrollPane.setToolTipText("Arquivo OWL da instância Ontológica");

    }

    private void loadOWLFileChooser() {
        this.owlFileChooser = new JFileChooser();
        this.owlFileChooser.setMultiSelectionEnabled(false);
    }

    public void importOWLFile() {
            File owlFile = new File("src/main/resources/br/ufrn/lii/owlblockdiagram/docs/automacao.owl");

            OntologyController controller = OntologyController.getInstance();
            if (controller.importOWL(owlFile)) {
                ArrayList<ComponentDTO> componentsList = controller.loadOWL();
                if (componentsList != null) {
                    this.palettePanel.getPaletteTree().addComponents(componentsList);
                }
            }
    }

    public void openProject() {
        int returnVal = this.getOWLFileChooser().showOpenDialog(this);
        File file = this.getOWLFileChooser().getSelectedFile();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Serializer serializer = new Serializer(this);
            serializer.loadWorkBench(file);
        }
    }

    public void saveProject() {
        Serializer serializer = new Serializer(this);
        SaveFile saveFile = new SaveFile(this);

        int returnVal = this.getOWLFileChooser().showSaveDialog(this);
        File file = this.getOWLFileChooser().getSelectedFile();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String myPath = file.getAbsolutePath();
            serializer.save(saveFile, myPath);
        }
    }

    public void openInstOWL() {
        int returnVal = this.getOWLFileChooser().showOpenDialog(this);
        File file = this.getOWLFileChooser().getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.loadInstInSourceCodeTab(file);
            this.changeFocusToSourceCode();
        }
    }

    public void saveInstOWL() {
        OntModel inst = this.generateInstance();
        this.loadInstFile(inst);
        Serializer serializer = new Serializer(this);
        int returnVal = this.getOWLFileChooser().showSaveDialog(this);
        File file = this.getOWLFileChooser().getSelectedFile();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String myPath = file.getAbsolutePath();
            serializer.saveOWL(this.editorPane.getText(), myPath);
        }
    }

    public OntModel generateInstance() {
        return OntologyController.getInstance().createOntInstance(this.scene);
    }

    public void changeFocusToSourceCode() {
        this.tabbedPane.setSelectedIndex(1);
    }

    public void loadInstFile(OntModel inst) {
        File outFile = new File("./src/main/resources/br/ufrn/lii/owlblockdiagram/docs/" + "inst.owl");
        FileOutputStream fop;
        try {
            fop = new FileOutputStream(outFile);
            inst.write(fop, null, "RDF/XML");

            this.loadInstInSourceCodeTab(outFile);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    public void loadInstInSourceCodeTab(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line + "\n");
                line = reader.readLine();
            }
            this.editorPane.setText(sb.toString());
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public void newProjectDialog() {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Deseja salvar o projeto atual antes de criar um novo?",
                "Aviso",
                JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            this.saveProject();
        }
    }

    public void newInstDialog() {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Deseja salvar a instância atual antes de criar uma nova?",
                "Aviso",
                JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            this.saveInstOWL();
        }
    }
}
