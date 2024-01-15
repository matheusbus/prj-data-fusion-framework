/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.field;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFTableFieldFactory {
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType) {
        
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(false);
        field.setIsPrimaryKey(false);
        field.setIsAutoIncrement(false);
        field.setLength(0);
        field.setValue(null);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsPrimaryKey(isPrimaryKey);
        field.setIsMandatory(isPrimaryKey);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isAutoIncrement) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(isPrimaryKey);
        field.setIsPrimaryKey(isPrimaryKey);
        field.setIsAutoIncrement(isAutoIncrement);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(isMandatory);
        field.setIsPrimaryKey(isPrimaryKey);
        field.setLength(length);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, boolean isAutoIncrement) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(isMandatory);
        field.setIsPrimaryKey(isPrimaryKey);
        field.setIsAutoIncrement(isAutoIncrement);
        field.setLength(length);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, Integer decimalPlaces) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(isMandatory);
        field.setIsPrimaryKey(isPrimaryKey);
        field.setLength(length);
        field.setDecimalPlaces(decimalPlaces);
        
        return field;
    }
    
    public static INFTableField createField(String fieldName, INFTableFieldType fieldType, boolean isMandatory, Object defaultValue) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setFieldType(fieldType);
        field.setName(fieldName);
        field.setIsMandatory(isMandatory);
        field.setDefaultValue(defaultValue);
        
        return field;
    }
    
    public static INFTableField assignField(String fieldName, Object value) {
        INFTableFieldImpl field = new INFTableFieldImpl();
        field.setName(fieldName);
        field.setValue(value == null ? "" : value);
        return field;
    }
    
}
