/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.datafusion.view;

import br.com.datafusion.controller.CTLGeneratorDefineCollectionController;
import br.com.datafusion.generator.field.GRTEntityCollectionField;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class VIWGeneratorDefineEntityCollectionFrame extends javax.swing.JFrame {

    private CTLGeneratorDefineCollectionController controller;

    private JTextField txtCollectionName;
    private List<CPTFieldCollectionStructure> fieldCollectionStructures;
    private JPanel pnlField;
    
    private JButton btnAddField;
    private JButton btnGenerateEntity;

    public VIWGeneratorDefineEntityCollectionFrame(CTLGeneratorDefineCollectionController controller) {
        FlatArcDarkIJTheme.setup();
        this.controller = controller;

        txtCollectionName = new JTextField(20);
        fieldCollectionStructures = new ArrayList<>();
        pnlField = new JPanel();
        pnlField.setLayout(new BoxLayout(pnlField, BoxLayout.Y_AXIS));

        btnAddField = new JButton("Adicionar Campo");
        btnGenerateEntity = new JButton("Gerar Entidade");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(btnAddField);
        buttonsPanel.add(btnGenerateEntity);

        // Layout
        setLayout(new BorderLayout());
        JPanel superiorPanel = new JPanel(new BorderLayout());
        superiorPanel.add(new JLabel("Nome da Collection:"), BorderLayout.NORTH);
        superiorPanel.add(txtCollectionName, BorderLayout.CENTER);
        add(superiorPanel, BorderLayout.NORTH);
        add(new JScrollPane(pnlField), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerador de entidades [collection]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addFieldStructure() {
        CPTFieldCollectionStructure fieldCollectionStructure = new CPTFieldCollectionStructure();
        fieldCollectionStructures.add(fieldCollectionStructure);
        rebuildPnlFields();
    }

    public void setFields() {
        for (CPTFieldCollectionStructure fieldCollectionStructure : fieldCollectionStructures) {
            controller.addField(
                    fieldCollectionStructure.getField().getFieldName(),
                    fieldCollectionStructure.getField().getFieldType(),
                    fieldCollectionStructure.getField().isIdentifier(),
                    fieldCollectionStructure.getField().isMandatory());
        }
    }

    private void rebuildPnlFields() {
        pnlField.removeAll();
        for (CPTFieldCollectionStructure fieldCollectionStructure : fieldCollectionStructures) {
            pnlField.add(fieldCollectionStructure);
        }
        pnlField.revalidate();
        pnlField.repaint();
    }

    public List<GRTEntityCollectionField> getFields() throws CloneNotSupportedException {
        List<GRTEntityCollectionField> fields = new ArrayList<>();
        for (CPTFieldCollectionStructure fieldCollectionStructure : fieldCollectionStructures) {
            fields.add(fieldCollectionStructure.getField().clone());
        }
        return fields;
    }

    public String getCollectionName() {
        return txtCollectionName.getText();
    }

    public JButton getBtnAddField() {
        return this.btnAddField;
    }

    public JButton getBtnGenerateEntity() {
        return this.btnGenerateEntity;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
