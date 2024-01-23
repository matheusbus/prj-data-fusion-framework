/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.view;

import br.com.datafusion.core.field.INFCollectionFieldType;
import br.com.datafusion.generator.field.GRTEntityCollectionField;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Matheus
 */
public class CPTFieldCollectionStructure extends JPanel implements Cloneable {
    private JTextField txtFieldName;
    private JCheckBox chxPrimaryKey;
    private JCheckBox chxMandatory;
    private JComboBox<INFCollectionFieldType> cbxDataType;

    public CPTFieldCollectionStructure() {
        // Inicialização e configuração dos componentes
        txtFieldName = new JTextField(10);
        chxPrimaryKey = new JCheckBox("Chave Primária");
        chxMandatory = new JCheckBox("Obrigatório");
        cbxDataType = new JComboBox<>(INFCollectionFieldType.values());

        // Layout
        setLayout(new FlowLayout());
        add(new JLabel("Nome do Campo:"));
        add(txtFieldName);
        add(chxPrimaryKey);
        add(chxMandatory);
        add(new JLabel("Tipo de Dado:"));
        add(cbxDataType);
    }

    public GRTEntityCollectionField getField() {
        GRTEntityCollectionField field = new GRTEntityCollectionField();
        field.setFieldName(txtFieldName.getText());
        field.setIdentifier(chxPrimaryKey.isSelected());
        field.setMandatory(chxMandatory.isSelected());
        field.setFieldType(cbxDataType.getSelectedItem().toString());
        return field;
    }

    @Override
    public CPTFieldCollectionStructure clone() {
        CPTFieldCollectionStructure clone = new CPTFieldCollectionStructure();
        clone.txtFieldName.setText(this.txtFieldName.getText());
        clone.chxPrimaryKey.setSelected(this.chxPrimaryKey.isSelected());
        clone.chxMandatory.setSelected(this.chxMandatory.isSelected());
        clone.cbxDataType.setSelectedItem(this.cbxDataType.getSelectedItem());
        return clone;
    }
}