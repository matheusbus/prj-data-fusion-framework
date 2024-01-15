/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.field;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFCollectionFieldImpl implements INFCollectionField {

    protected INFCollectionFieldType collecionFieldType;
    protected Boolean isPrimaryKey;
    protected Boolean isMandatory;
    protected String name;
    protected Object value;
    protected Object defaultValue;
    protected Integer length;
    protected boolean fieldExists;
    protected boolean structureOk;

    public boolean getFieldExists() {
        return fieldExists;
    }

    @Override
    public void setFieldExists(boolean fieldExists) {
        this.fieldExists = fieldExists;
    }

    @Override
    public INFCollectionFieldType getCollectionFieldType() {
        return collecionFieldType;
    }

    @Override
    public Boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    @Override
    public Boolean isMandatory() {
        return isMandatory;
    }

    @Override
    public String getFieldName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void assignValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Boolean persistField() {
        return true;
    }

    @Override
    public Integer getFieldLength() {
        return length;
    }

    @Override
    public boolean validateField() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean asBoolean() throws INFFieldException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public Integer asInteger() throws INFFieldException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer getDecimalPlaces() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setStructureOk(boolean structureOk) {
        this.structureOk = structureOk;
    }

    public boolean isStructureOk() {
        return structureOk;
    }

    public INFCollectionFieldType getCollecionFieldType() {
        return collecionFieldType;
    }

    public void setCollecionFieldType(INFCollectionFieldType collecionFieldType) {
        this.collecionFieldType = collecionFieldType;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    
    @Override
    public boolean fieldExists() {
        return fieldExists;
    }

    @Override
    public boolean structureOk() {
        return structureOk;
    }
    
}
