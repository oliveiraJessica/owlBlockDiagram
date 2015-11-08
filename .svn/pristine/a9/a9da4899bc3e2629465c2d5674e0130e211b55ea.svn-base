/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.lii.owlblockdiagram.utils;

import br.ufrn.lii.owlblockdiagram.DTO.ClassDTO;
import br.ufrn.lii.owlblockdiagram.DTO.ComponentDTO;
import br.ufrn.lii.owlblockdiagram.DTO.IndividualDTO;
import br.ufrn.lii.owlblockdiagram.DTO.Input;
import br.ufrn.lii.owlblockdiagram.DTO.ModelDTO;
import br.ufrn.lii.owlblockdiagram.DTO.Output;
import br.ufrn.lii.owlblockdiagram.DTO.PropDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropDataDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropObjDTO;
import br.ufrn.lii.owlblockdiagram.DTO.PropValueDTO;
import br.ufrn.lii.owlblockdiagram.model.ImageGraphScene;
import br.ufrn.lii.owlblockdiagram.model.ImageNode;
import br.ufrn.lii.owlblockdiagram.model.Ontology;
import com.hp.hpl.jena.graph.query.regexptrees.OneOrMore;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author JÃƒÂ©ssica
 */
public class OWLUtil {

    public static ArrayList<ComponentDTO> consult(File owlFile, OntModel base) {
        //Cria um model base que vai servir para guardar o modelo lido no arquivo owl passado

        FileManager.get().readModel(base, "./src/main/resources/br/ufrn/lii/owlblockdiagram/docs/" + Ontology.FILE);
        ArrayList<ComponentDTO> componentList = new ArrayList<>();
        ModelDTO model = ModelDTO.getInstance();
        //model.init(null, null, null);
        ComponentDTO component;
        //criando hierarquia
        for (Iterator i = base.listClasses(); i.hasNext();) {
            OntClass c = (OntClass) i.next();
            if (c.getLocalName() != null && (c.getSuperClass() != null || c.getLocalName().equals(Ontology.ROOT) || OWLUtil.isIO(c.getLocalName()))) {
                //Testa se o componente jÃƒÂ¡ exite, no caso de ser criado quando examinada a subclasse de um componente                
                if (model.findClasse(c.getLocalName()) == null) {
                    // verificar se ele nao tem pai e se ele ÃƒÂ© "Component"
                    component = new ComponentDTO(c.getLocalName());
                    model.addClasse(component);
                } else {
                    component = (ComponentDTO) model.findClasse(c.getLocalName());
                }
                if (!c.getLocalName().equals(Ontology.ROOT)) {
                    componentList.add(component);
                }
                if (c.getSuperClass() != null && c.getSuperClass().getLocalName() != null) {
                    //Se o componente nao for achado, criar novo component e adicionar no model
                    if (model.findClasse(c.getSuperClass().getLocalName()) == null) {
                        ComponentDTO parent = new ComponentDTO(c.getSuperClass().getLocalName());
                        model.addClasse(parent);
                        parent.addChildren(component);
                        component.setParent(parent);
                    } else {
                        ComponentDTO parent = (ComponentDTO) model.findClasse(c.getSuperClass().getLocalName());
                        parent.addChildren(component);
                        component.setParent(parent);
                    }
                }
            }
        }

        for (Iterator it = base.listClasses(); it.hasNext();) {
            OntClass oc = (OntClass) it.next();

            if (oc.getLocalName() != null && (oc.getSuperClass() != null || oc.getLocalName().equals(Ontology.ROOT) || OWLUtil.isIO(oc.getLocalName()))) {
                ClassDTO classDTO = (ClassDTO) model.findClasse(oc.getLocalName());
                for (Iterator j = oc.listDeclaredProperties(); j.hasNext();) {
                    OntProperty p = (OntProperty) j.next();
                    // se prop range nao ÃƒÂ© achado no model, entao ÃƒÂ© PropData
//                        System.out.println(p.getLocalName());
                    for (Iterator n = p.listRange(); n.hasNext();) {
                        OntResource range = (OntResource) n.next();
                        // ÃƒÂ© PropObj
                        if (range.getLocalName() != null && !OWLUtil.isRangeData(range)) {
                            //if (range.getLocalName() != null && (model.findClasse(range.getLocalName()) != null || OWLUtil.isIO(range.getLocalName()))) {
                            PropObjDTO prop = new PropObjDTO(p.getLocalName());
                            prop.setFunctionalPropertie(p.isFunctionalProperty());
                            prop.setInverseFunctionalPropertie(p.isInverseFunctionalProperty());
                            if (model.findProperty(prop.getName()) == null) {
                                model.addProperty(prop);
                            }
                            for (Iterator k = p.listDomain(); k.hasNext();) {
                                OntResource d = (OntResource) k.next();
                                if (d.getLocalName() != null) {
                                    prop.addDomain(model.findClasse(d.getLocalName()));
                                }
                            }
                            for (Iterator m = p.listRange(); m.hasNext();) {
                                OntResource r = (OntResource) m.next();
                                if (r.getLocalName() != null) {
                                    prop.addRange(model.findClasse(r.getLocalName()));
                                }
                            }
                            classDTO.addProperty(prop);
                        } else { // ÃƒÂ© PropData
                            //System.out.println("Proriedade: " + p.getLocalName() + " Range: " + range.getLocalName());
                            PropDataDTO prop = new PropDataDTO(p.getLocalName());
                            if (model.findProperty(prop.getName()) == null) {
                                model.addProperty(prop);
                            }
                            for (Iterator k = p.listDomain(); k.hasNext();) {
                                OntResource d = (OntResource) k.next();
                                if (d.getLocalName() != null) {
                                    prop.addDomain(model.findClasse(d.getLocalName()));
                                }
                            }
                            for (Iterator m = p.listRange(); m.hasNext();) {
                                OntResource r = (OntResource) m.next();
                                if (r.getLocalName() != null) {
                                    prop.addRange(r.getLocalName());
                                }
                            }
                            classDTO.addProperty(prop);
                        }
                    }

                }
            }
        }
        return componentList;
    }

    public static void createOntInstance(OntModel inst, OntModel base, ImageGraphScene scene) {

        //Listar individuos        
        Collection<ImageNode> nodesCollection = scene.getNodes();

        for (ImageNode imageNode : nodesCollection) {
            IndividualDTO individualDTO = imageNode.getComponentInstance();
            if (individualDTO != null) {
                //individualDTO.removePropValues();
            }
        }

        for (ImageNode imageNode : nodesCollection) {
            IndividualDTO individualDTO = imageNode.getComponentInstance();
            Individual individual = inst.getIndividual(Ontology.NS + individualDTO.getName());
            if (individual == null) {
                inst.createIndividual(Ontology.NS + individualDTO.getName(), base.getOntClass(Ontology.NS + individualDTO.getClassDTO().getName()));
            }
            //chamar mÃ©todo para preencher as propriedades de cada indivÃ­duo
            OWLUtil.fillPropObjValue(individualDTO);
            OWLUtil.fillIOPropValue(inst, base, individualDTO.getInputs(), individualDTO.getOutputs());
        }

//        PQ DANADO TEM ESSA SEGUNDA PARTE AQUI??? o METODO FILLPORP. NAO ADICIONA AS PROPRIEDADES NA INSTANCIA
        for (ImageNode imageNode : nodesCollection) {
            IndividualDTO individualDTO = imageNode.getComponentInstance();
            Individual individual = inst.getIndividual(Ontology.NS + individualDTO.getName());
            List<PropValueDTO> propValueList = individualDTO.getPropertieValue();
            for (PropValueDTO propValueDTO : propValueList) {
                Property property = base.getProperty(Ontology.NS + propValueDTO.getPropertie().getName());
                if (propValueDTO.getPropertie().isPropData()) {
                    if (propValueDTO.getValue() != null) {
                        Literal literal = inst.createLiteral(propValueDTO.getValue());
//                        individual.setPropertyValue(property, literal);
                        Statement statement = inst.createStatement(individual, property, literal);
                        inst.add(statement);
                    }
                } else {
                    if (propValueDTO.getValue() != null) {
                        Individual individualProperty = inst.getIndividual(Ontology.NS + propValueDTO.getValue());
                        if (individualProperty == null) {
                            individualProperty = OWLUtil.addIndividual(propValueDTO.getValue(), inst, base, nodesCollection);
                        }
                        // TODO analisar se propriedade pode ter mais do que uma valor pode sim!Acho qu eisso jÃ¡ tÃ¡ OK
//                        individual.setPropertyValue(property, individualProperty);
                        Statement statement = inst.createStatement(individual, property, individualProperty);
//                        System.out.println(individualDTO.getName() + "->" + property.getLocalName() + "->" + propValueDTO.getValue());
                        inst.add(statement);
                    }
                }
            }
        }

        inst.setNsPrefix(Ontology.getPrefix(), Ontology.NS);
    }

    private static Individual addIndividual(String individualName, OntModel inst, OntModel base, Collection<ImageNode> nodesCollection) {
        Individual individual = null;
        for (ImageNode imageNode : nodesCollection) {
            IndividualDTO individualDTO = imageNode.getComponentInstance();
            // vai dar problema quando mudar o label dos outputs. rever i metodo isIO
            if (individualDTO.getName().equals(individualName)) { // || OWLUtil.isIO(individualName)) {
                individual = inst.createIndividual(Ontology.NS + individualDTO.getName(), base.getOntClass(Ontology.NS + individualDTO.getClassDTO().getName()));
                if (individual != null) {
                    List<PropValueDTO> propValueList = individualDTO.getPropertieValue();
                    for (PropValueDTO propValueDTO : propValueList) {
                        Property property = base.getProperty(Ontology.NS + propValueDTO.getPropertie().getName());
                        if (property != null) {
                            if (propValueDTO.getPropertie().isPropData()) {
                                if (propValueDTO.getValue() != null) {
                                    Literal literal = inst.createLiteral(propValueDTO.getValue());
//                            individual.setPropertyValue(property, literal);
                                    Statement statement = inst.createStatement(individual, property, literal);
                                    inst.add(statement);
                                }
                            } else {
                                if (propValueDTO.getValue() != null) {
                                    Individual individualProperty = inst.getIndividual(Ontology.NS + propValueDTO.getValue());
                                    if (individualProperty == null) {

                                        individualProperty = OWLUtil.addIndividual(propValueDTO.getValue(), inst, base, nodesCollection);
                                    }
//                            individual.setPropertyValue(property, individualProperty);
                                    Statement statement = inst.createStatement(individual, property, individualProperty);
                                    inst.add(statement);
                                }
                            }
                        }
                    }
                }

            }
        }
        return individual;

    }

    private static boolean isIO(String s) {
        //String s = cDTO.getName();
        return s.equals("Output") || s.equals("Input");
    }

    //Faz com que os outputs e inputs aparecam com suas propriedades na instÃƒÂ¢ncia ontolÃƒÂ³gica
    private static void fillIOPropValue(OntModel inst, OntModel base, List<Input> inputs, List<Output> outputs) {
        if (inputs != null) {
            for (Input input : inputs) {
                List<PropDTO> inputPropList = input.getProperties();
                OntClass oc = base.getOntClass(Ontology.NS + input.getName());
                Individual individual = inst.getIndividual(Ontology.NS + input.getLabel());
                if (individual == null) {
                    individual = inst.createIndividual(Ontology.NS + input.getLabel(), oc);
                }
                // Individual individual = inst.getIndividual(Ontology.NS + "Input");
                if (inputPropList != null) {
                    for (PropDTO prop : inputPropList) {
                        if (!prop.isPropData()) {
                            List<ClassDTO> rangeList = prop.getRange();
                            for (ClassDTO rangeClass : rangeList) {
                                Property property = base.getProperty(Ontology.NS + prop.getName());
                                // checar se o output/input associado ÃƒÂ© igual ao range
                                if (input.getComponent().getClassDTO().equals(rangeClass)) {
//                                    Literal literal = inst.createLiteral(input.getComponent().getName());
                                    Individual individualProperty = inst.getIndividual(Ontology.NS + input.getComponent().getName());
                                    if (individualProperty == null) {
                                        individualProperty = inst.createIndividual(Ontology.NS + input.getComponent().getName(), oc);
                                    }

                                    Statement statement = inst.createStatement(individual, property, individualProperty);
                                    // COMENTANDO AUQI RESOLVE?!
                                    inst.add(statement);
                                    System.out.println(individual.getLocalName() + "->" + property.getLocalName() + "->" + individualProperty.getLocalName());

//                                    PropValueDTO propValueDTO = new PropValueDTO(prop,input.getComponent().getName()); // input.getName());
//                                    individual.setPropertieValue(propValueDTO);
                                } else if (rangeClass != null && rangeClass.getName().equals("Output")) {  //checar se o componente associado tÃƒÂ¡ no ranged
//                                    Literal literal = inst.createLiteral(input.getOutput().getLabel()); // literal e pra data propertie!!!
                                    Individual individualProperty = inst.getIndividual(Ontology.NS + input.getOutput().getLabel());
                                    if (individualProperty == null) {
                                        individualProperty = inst.createIndividual(Ontology.NS + input.getOutput().getLabel(), base.getOntClass(Ontology.NS + input.getOutput().getLabel()));
                                    }

                                    Statement statement = inst.createStatement(individual, property, individualProperty);
                                    inst.add(statement);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (outputs != null) {
            for (Output output : outputs) {
                List<PropDTO> outputPropList = output.getProperties();
                OntClass oc = base.getOntClass(Ontology.NS + output.getName());
                Individual individual = inst.getIndividual(Ontology.NS + output.getLabel());
                if (individual == null) {
                    individual = inst.createIndividual(Ontology.NS + output.getLabel(), oc);
                }
                if (outputPropList != null) {
                    for (PropDTO prop : outputPropList) {
                        if (!prop.isPropData()) {
                            List<ClassDTO> rangeList = prop.getRange();
                            for (ClassDTO rangeClass : rangeList) {
                                Property property = base.getProperty(Ontology.NS + prop.getName());
                                // checar se o output/input associado ÃƒÂ© igual ao range
                                if (output.getComponent().getClassDTO().equals(rangeClass)) {
//                                    Literal literal = inst.createLiteral(output.getComponent().getName());
                                    Individual individualProperty = inst.getIndividual(Ontology.NS + output.getComponent().getName());
                                    if (individualProperty == null) {
                                        individualProperty = inst.createIndividual(Ontology.NS + output.getComponent().getName(), oc);
                                    }
                                    Statement statement = inst.createStatement(individual, property, individualProperty);
                                    //inst.add(statement);
                                } else if (rangeClass != null && rangeClass.getName().equals("Input")) {  //checar se o componente associado tÃƒÂ¡ no ranged
//                                    Literal literal = inst.createLiteral(output.getInput().getLabel());
                                    Individual individualProperty = inst.getIndividual(Ontology.NS + output.getInput().getLabel());
                                    if (individualProperty == null) {
                                        individualProperty = inst.createIndividual(Ontology.NS + output.getInput().getLabel(), base.getOntClass(Ontology.NS + output.getInput().getLabel()));
                                    }

                                    Statement statement = inst.createStatement(individual, property, individualProperty);
                                    inst.add(statement);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //recebe propriedade, obtem o range da propriedade, vÃƒÂª quais individuos conectados a ele se qualificam nesse range
    private static void fillPropObjValue(IndividualDTO individualDTO) {

        List<PropDTO> propList = individualDTO.getClassDTO().getProperties();
        for (PropDTO prop : propList) {
            if (!prop.isPropData()) {
                System.out.println("Prop obj: " + prop.getName());
                List<ClassDTO> rangeList = prop.getRange();
                for (ClassDTO rangeClass : rangeList) {
                    List<Output> outputs = individualDTO.getOutputs();
                    if (outputs != null) {
                        for (Output output : outputs) { // hasComponet InverseFunctionalProperty
                            String propName = prop.getName();

                            if (propName.startsWith("has") || propName.endsWith("Entry") || propName.contains("Output")) {

                                IndividualDTO inputIndividual = output.getInput().getComponent();
                                ComponentDTO component = (ComponentDTO) inputIndividual.getClassDTO();
                                // os objetos podem ver os outputs e inputs como valores de propriedade
                                if (rangeClass.getName().equals("Output")) { // Output nÃƒÂ£o tem parent
                                    PropValueDTO propValueDTO = new PropValueDTO(prop, output.getLabel()); //output.getName());
                                    individualDTO.addPropertieValue(propValueDTO);
                                }

                                while (component != null) {
                                    if (component.equals(rangeClass)) {
                                        PropValueDTO propValueDTO = new PropValueDTO(prop, inputIndividual.getName());
                                        individualDTO.addPropertieValue(propValueDTO);
                                        break;
                                    } else {
                                        component = component.getParent();
                                    }
                                }
                            }
                        }
                    }
                    List<Input> inputs = individualDTO.getInputs();
                    if (inputs != null) {
                        for (Input input : inputs) {
                            String propName = prop.getName();

                            if (propName.startsWith("is") || propName.contains("Input")) {

                                IndividualDTO outputIndividual = input.getOutput().getComponent(); // acha o componente que ÃƒÂ© relacionado ao input do componente investigado
                                ComponentDTO component = (ComponentDTO) outputIndividual.getClassDTO();
                                // os objetos podem ver os inputs e outputs como valores de propriedade
                                if (rangeClass.getName().equals("Input")) { // Input nÃƒÂ£o tem parent
                                    PropValueDTO propValueDTO = new PropValueDTO(prop, input.getLabel()); // input.getName());
                                    individualDTO.addPropertieValue(propValueDTO);
                                }

                                while (component != null) {
                                    if (component.equals(rangeClass)) {
                                        PropValueDTO propValueDTO = new PropValueDTO(prop, outputIndividual.getName());
                                        individualDTO.addPropertieValue(propValueDTO);
                                        break;
                                    } else {
                                        component = component.getParent();
                                    }
                                }
                            }
                        }
                    }

                }
            }else{System.out.println("Prop data: " + prop.getName());}
        }
    }

    private static boolean isRangeData(OntResource range) {
        return range.getLocalName().equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#string") ||
             //   range.getLocalName().equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#boolean") ||
                range.getLocalName().equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#float");
    }
}
