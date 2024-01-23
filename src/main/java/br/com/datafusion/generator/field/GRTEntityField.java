/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.field;

/**
 *
 * @author Matheus
 */
public abstract class GRTEntityField implements Cloneable {
    
    private String fieldName;
    private String fieldType;
    private Boolean identifier;
    private Boolean Mandatory;

    public GRTEntityField() {
    }

    public GRTEntityField(String fieldName, String fieldType, Boolean identifier, Boolean Mandatory) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.identifier = identifier;
        this.Mandatory = Mandatory;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean isIdentifier() {
        return identifier;
    }

    public void setIdentifier(Boolean identifier) {
        this.identifier = identifier;
    }

    public Boolean isMandatory() {
        return Mandatory;
    }

    public void setMandatory(Boolean Mandatory) {
        this.Mandatory = Mandatory;
    }
    
    
    
    
    
}
