/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.field;

/**
 *
 * @author Matheus
 */
public class GRTEntityCollectionField extends GRTEntityField implements Cloneable {

    public GRTEntityCollectionField() {
    }

    public GRTEntityCollectionField(String fieldName, String fieldType, Boolean identifier, Boolean Mandatory) {
        super(fieldName, fieldType, identifier, Mandatory);
    }

    @Override
    public GRTEntityCollectionField clone() throws CloneNotSupportedException {
        GRTEntityCollectionField cloneField = new GRTEntityCollectionField();
        cloneField.setFieldName(getFieldName());
        cloneField.setFieldType(getFieldType());
        cloneField.setIdentifier(isIdentifier());
        cloneField.setMandatory(isMandatory());
        return (GRTEntityCollectionField) super.clone();
    }
    
}
