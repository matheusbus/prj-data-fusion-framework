/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.controller;

import br.com.datafusion.core.entity.observer.INFEntityDefinitionSubject;
import br.com.datafusion.core.entity.observer.INFLogCollectionDefinitionObserver;
import br.com.datafusion.generator.factory.GRTEntityDefinitionCollectionFileFactory;
import br.com.datafusion.generator.factory.GRTEntityDefinitionFileAbstractFactory;
import br.com.datafusion.generator.field.GRTEntityCollectionField;
import br.com.datafusion.generator.field.GRTEntityField;
import br.com.datafusion.generator.file.GRTEntityDefinitionFile;
import br.com.datafusion.view.VIWGeneratorDefineEntityCollectionFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public final class CTLGeneratorDefineCollectionController extends INFEntityDefinitionSubject {

    private final GRTEntityDefinitionFileAbstractFactory factory;
    private GRTEntityDefinitionFile entity;
    private final List<GRTEntityField> fields;

    private final VIWGeneratorDefineEntityCollectionFrame frame;

    public CTLGeneratorDefineCollectionController() {
        this.factory = new GRTEntityDefinitionCollectionFileFactory();
        this.fields = new ArrayList<>();
        this.frame = new VIWGeneratorDefineEntityCollectionFrame(this);
        initButtons();
        showFrame();
        
        //Inicializa observadores
        INFLogCollectionDefinitionObserver obs1 = new INFLogCollectionDefinitionObserver();
        this.addObserver(obs1);
    }

    private void initButtons() {
        frame.getBtnAddField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.addFieldStructure();
            }
        });

        frame.getBtnGenerateEntity().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generate();
            }
        });
    }

    public void generate() {
        entity = factory.getEntityDefinitionFile(frame.getCollectionName());
        frame.setFields();
        for (GRTEntityField field : this.fields) {
            entity.addField(field.getFieldName(), field.getFieldType(), field.isIdentifier(), field.isMandatory(), 0);
        }
        entity.saveToFile();
        notifyObservers(entity.getClassName());
    }

    public void addField(String fieldName, String fieldType, boolean isPrimaryKey, boolean isMandatory) {
        GRTEntityCollectionField field = new GRTEntityCollectionField();
        field.setFieldName(fieldName);
        field.setFieldType(fieldType);
        field.setIdentifier(isMandatory);
        field.setMandatory(isMandatory);
        this.fields.add(field);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

}
