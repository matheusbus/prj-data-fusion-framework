/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.field.INFCollectionFieldType;

/**
 *
 * @author Matheus
 */
public class INFEntityCollectionDefinition extends INFEntityDefinitionImpl {

    protected String collectionName;
    
    public INFEntityCollectionDefinition(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    
    public final INFEntityClass createEntity() {
        return getEntityByName(collectionName);
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType) {
        //addField(INFTableFieldFactory.createField(fieldName, fieldType));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isPrimaryKey) {
        //addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isPrimaryKey, boolean isAutoIncrement) {
        //addField(INFCollectionFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isAutoIncrement));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length) {
        //addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, boolean isAutoIncrement) {
       // addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length, isAutoIncrement));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length, Integer decimalPlaces) {
        //addField(INFTableFieldFactory.createField(fieldName, fieldType, isPrimaryKey, isMandatory, length, decimalPlaces));
    }
    
    public final void addCollectionField(String fieldName, INFCollectionFieldType fieldType, boolean isMandatory, Object defaultValue) {
        //addField(INFTableFieldFactory.createField(fieldName, fieldType, isMandatory, defaultValue));
    }
    
    protected final INFEntityClass getEntityByName(String collectionName) {
        INFEntityCollectionClass entityClass = new INFEntityCollectionClass(this);
        entityClass.setCollectionName(collectionName);
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
