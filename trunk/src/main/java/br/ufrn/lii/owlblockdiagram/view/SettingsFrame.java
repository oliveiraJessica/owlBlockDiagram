/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.view;

import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import br.ufrn.lii.owlblockdiagram.DTO.ComponentDTO;
import br.ufrn.lii.owlblockdiagram.DTO.IndividualDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropDataDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropValueDTO;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.ImageNode;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jéssica
 */
public class SettingsFrame extends JDialog {

    JPanel mainPanel;
    JPanel addPropPanel;
    JButton addPropButton;
    JTextField addPropField;
    IndividualDTO individual;
    List<PropDTO> properties;
    ImageGraphScene scene;
    Hashtable<PropDTO, Component> dataTable = new Hashtable<>();
    int height = 100;
    int width = 400;

    public SettingsFrame(IndividualDTO individual, ImageGraphScene scene) throws HeadlessException {
        super();
        this.scene = scene;
        this.individual = individual;
        this.init(individual.getClassDTO().getProperties());
    }

    private void init(List<PropDTO> properties) {
        this.setTitle("Janela de Propriedades");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.addMainPanel(properties);

        this.addPropPanel = new JPanel();
        this.addPropPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.addPropPanel.setBorder(BorderFactory.createTitledBorder("Criar nova Propriedade"));
        this.addPropField = new JTextField(15);
        this.addPropButton = new JButton(" + ");
        this.addPropButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.this.createProperty();
            }
        });
        addPropPanel.add(addPropField);
        addPropPanel.add(addPropButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean foundFlag;
                //salvar as propriedades
                List<PropValueDTO> propValue = SettingsFrame.this.individual.getPropertieValue();
                Enumeration<PropDTO> keys = SettingsFrame.this.dataTable.keys();

                while (keys.hasMoreElements()) {
                    foundFlag = false;
                    PropDTO key = keys.nextElement();
                    PropValueDTO propValueDTO = new PropValueDTO();

                    for (PropValueDTO valueDTO : propValue) {
                        // se a propriedade já tiver um valor setado, setar ele novamente, pois pode ter mudado
                        if (propValueDTO.getPropertie().equals(key)) {
                            foundFlag = true;
                            propValueDTO = valueDTO;
                        }
                    }

                    if (foundFlag) {
                        // se o valor da propriedade for diferente de vazio, criar um novo propValue
                        if (propValueDTO.getPropertie().isPropData()) {
                            JTextField textField = (JTextField) SettingsFrame.this.dataTable.get(key);
                            propValueDTO.setValue(textField.getText());
                            if (textField.getText().trim().length() == 0) {
                                propValue.remove(propValueDTO);
                            }
                        }
                    } else {
                        // se o valor da propriedade for diferente de vazio, criar um novo propValue
                        if (key.isPropData()) {
                            JTextField textField = (JTextField) SettingsFrame.this.dataTable.get(key);
                            if (textField.getText().trim().length() > 0) {
                                propValueDTO.setValue(textField.getText());
                                propValueDTO.setPropertie(key);
                                propValue.add(propValueDTO);
                                //SettingsFrame.this.individual.addPropertieValue(propValueDTO);
                            }
                        }
                    }
                    SettingsFrame.this.individual.setPropertieValue(propValue);
                }

                SettingsFrame.this.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                SettingsFrame.this.dispose();
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, (int) okButton.getPreferredSize().getHeight() + 30)));

        this.setSize(new Dimension(this.width, this.height));
        this.add(addPropPanel, BorderLayout.PAGE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.repaint();
        this.revalidate();
    }

    private void addMainPanel(List<PropDTO> properties) {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        
        for (Iterator it = properties.iterator(); it.hasNext();) {
            PropDTO prop = (PropDTO) it.next();

            if (prop.isPropData()) {
                this.addPropDataLabel(prop);
            }
        }

        this.mainPanel.setPreferredSize(new Dimension(this.width, this.height));
        this.add(this.mainPanel, BorderLayout.CENTER);
        this.mainPanel.repaint();
        this.mainPanel.revalidate();
    }

    private void addPropDataLabel(PropDTO prop) {
        JLabel propLabel = new JLabel(" " + prop.getName() + ": ");

        JTextField textField = fillTextField(prop);
        textField.setEditable(true);
        int textwidth = this.width - (int) propLabel.getPreferredSize().getWidth() - 30;
        textField.setPreferredSize(new Dimension(textwidth, (int) textField.getPreferredSize().getHeight()));

        ResizablePanel panel = new ResizablePanel(textField, (int) propLabel.getPreferredSize().getWidth());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(propLabel);
        panel.add(textField);

        this.dataTable.put(prop, textField);

        this.mainPanel.add(panel);
        this.height += 50;
    }

    private void addPropObjLabel(PropDTO prop) {
        JLabel propLabel = new JLabel(" " + prop.getName() + ": ");

        JComboBox<String> box = fillComboBox(prop);
        int boxwidth = this.width - (int) propLabel.getPreferredSize().getWidth() - 30;
        box.setPreferredSize(new Dimension(boxwidth, (int) box.getPreferredSize().getHeight()));

        ResizablePanel panel = new ResizablePanel(box, (int) propLabel.getPreferredSize().getWidth());
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(propLabel);
        panel.add(box);

        this.dataTable.put(prop, box);

        this.mainPanel.add(panel);
        this.height += 50;
    }

    private JTextField fillTextField(PropDTO prop) {
        JTextField textField = new JTextField();
        List<PropValueDTO> valueList = this.individual.getPropertieValue();
        if (valueList != null) {
            for (PropValueDTO propValueDTO : valueList) {
                if (propValueDTO.getPropertie().equals(prop)) {
                    textField.setText((String) propValueDTO.getValue());
                }
            }
        }
        return textField;
    }

    private JComboBox<String> fillComboBox(PropDTO prop) {
        JComboBox<String> box = new JComboBox<>();
        box.addItem("-");
        List<ClassDTO> rangeList = prop.getRange();
        for (ClassDTO rangeClass : rangeList) {
            Collection<ImageNode> nodesCollection = this.scene.getNodes();
            for (ImageNode imageNode : nodesCollection) {
                IndividualDTO individualDTO = imageNode.getComponentInstance();
                ComponentDTO parent = (ComponentDTO) individualDTO.getClassDTO();
                while (parent != null) {
                    if (parent.equals(rangeClass)) {
                        box.addItem(individualDTO.getName());
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
            }
        }

        List<PropValueDTO> valueList = this.individual.getPropertieValue();
        for (PropValueDTO propValueDTO : valueList) {
            if (propValueDTO.getPropertie().equals(prop)) {
                box.setSelectedItem(propValueDTO.getValue());
            }
        }

        return box;
    }

    private void createProperty() {
//        if (this.addPropField.getText() == null){
//            return;
//        }
        String newPropString = this.addPropField.getText();
        PropDataDTO newProp = new PropDataDTO(newPropString);
        newProp.addDomain(individual.getClassDTO());
        newProp.addRange("http://www.w3.org/2001/XMLSchema#string");
        this.individual.getClassDTO().addProperty(newProp);
        //this.properties.add(newProp);
       //PropValueDTO value = new PropValueDTO(newProp, valueString);
        //this.individual.addPropertieValue(value);

        this.addPropDataLabel(newProp);

//        this.removeAll();
        this.remove(this.mainPanel);
        this.addMainPanel(this.individual.getClassDTO().getProperties());
//        this.init(this.individual.getClassDTO().getProperties());
        this.revalidate();
        this.repaint();
    }

}
