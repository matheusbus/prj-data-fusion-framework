/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.field.INFTableFieldFactory;
import br.com.datafusion.core.field.INFTableFieldType;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFEntityTableDefinition extends INFEntityDefinitionImpl {

    protected String tableSchema;
    protected String tableName;
    
    protected INFEntityTableDefinition(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public final INFEntityClass createEntity() {
        return getEntityByName(tableSchema, tableName);
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isAutoIncrement) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isAutoIncrement));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, boolean isAutoIncrement) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length, isAutoIncrement));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, Integer decimalPlaces) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length, decimalPlaces));
    }
    
    public final void addTableField(String fieldName, INFTableFieldType fieldType, boolean isMandatory, Object defaultValue) {
        addField(INFTableFieldFactory.createField(fieldName, fieldType, isMandatory, defaultValue));
    }
    
    protected final INFEntityClass getEntityByName(String schemaName, String tableName) {
        INFEntityTableClass entityClass = new INFEntityTableClass(this);
        entityClass.setSchemaName(schemaName);
        entityClass.setTableName(tableName);
        entityClass.setFields(fields);
        return entityClass;
    }

    @Override
    public INFEntityClass createDefine() {
        INFEntityClass entity = createEntity();
        dataPackage.setMigrateEntity(entity);
        setEntity(entity);
        return entity;
    }
}