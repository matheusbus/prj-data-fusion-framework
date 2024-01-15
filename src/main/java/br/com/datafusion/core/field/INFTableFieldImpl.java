/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.field;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFTableFieldImpl implements INFTableField {

    protected INFTableFieldType fieldType;
    protected Boolean isPrimaryKey;
    protected Boolean isMandatory;
    protected String name;
    protected Object value;
    protected Object defaultValue;
    protected Integer length;
    protected Integer decimalPlaces;
    protected Boolean isAutoIncrement;
    protected boolean fieldExists;
    protected boolean structureOk;

    public INFTableFieldImpl() {
        this.fieldType = null;
        this.isPrimaryKey = false;
        this.isMandatory = false;
        this.name = "";
        this.value = "";
        this.length = 0;
        this.decimalPlaces = 0;
        this.isAutoIncrement = false;
        this.defaultValue = "";
    }

    public INFTableFieldImpl(INFTableFieldType fieldType, Boolean isPrimaryKey, Boolean isMandatory, String name, Object value, Integer length, Integer decimalPlaces, Boolean isAutoIncrement, Object defaultValue) {
        this.fieldType = fieldType;
        this.isPrimaryKey = isPrimaryKey;
        this.isMandatory = isMandatory;
        this.name = name;
        this.value = value;
        this.length = length;
        this.isAutoIncrement = isAutoIncrement;
        this.decimalPlaces = decimalPlaces;
        this.defaultValue = defaultValue;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
    
    public boolean getFieldExists() {
        return fieldExists;
    }

    @Override
    public void setFieldExists(boolean fieldExists) {
        this.fieldExists = fieldExists;
    }
    
    public void setFieldType(INFTableFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setIsAutoIncrement(Boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    @Override
    public INFTableFieldType getTableFieldType() {
        return fieldType;
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
    public boolean validateField() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void assignValue(Object value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value.toString();
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
    public Boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    @Override
    public boolean asBoolean() throws INFFieldException {
        if(value.equals("f") || value.equals("false") || value.equals("0") || value.equals("NO")) {
            return false;
        } else if(value.equals("t") || value.equals("true") || value.equals("1") || value.equals("YES")) {
            return true;
        } else {
            throw new INFFieldException("Could not parse ["+value+"] as boolean.");
        }
    }

    @Override
    public Integer asInteger() throws INFFieldException {
        try {
            Integer v = Integer.valueOf(value.toString());
            return v;
        } catch (NumberFormatException ex) {
            throw new INFFieldException("Could not parse ["+value+"] as integer. " + ex.getMessage());
        }
    }

    @Override
    public void setStructureOk(boolean structureOk) {
        this.structureOk = structureOk;
    }

    public boolean isStructureOk() {
        return structureOk;
    }

    public INFTableFieldType getFieldType() {
        return fieldType;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public Boolean getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public boolean isFieldExists() {
        return fieldExists;
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
