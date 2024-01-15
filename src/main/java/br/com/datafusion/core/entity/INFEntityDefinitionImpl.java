/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.exceptions.INFEntityException;
import br.com.datafusion.core.field.INFField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFEntityDefinitionImpl implements INFEntityDefinition {

    List<INFField> fields;
    private INFEntityClass entity;
    protected static INFDataPackage dataPackage;
    
    protected INFEntityDefinitionImpl(INFDataPackage dataPackage) {
        this.dataPackage = dataPackage;
        this.fields = new ArrayList<>();
    }

    public List<INFField> getFields() {
        return fields;
    }

    public void setFields(List<INFField> fields) {
        this.fields = fields;
    }
    
    @Override
    public final INFEntityClass addEntity(INFEntityClass entity) {
        this.entity = entity;
        dataPackage.setMigrateEntity(entity);
        return this.entity;
    }
    
    @Override
    public final void addField(INFField field) {
        fields.add(field);
    }

    @Override
    public boolean clearRecordsBefore() {
        return false;
    }

    @Override
    public final boolean isPKDefined() {
        for (INFField f : fields) {
            if (f.isPrimaryKey()) {
                return true;
            }
        }
        throw new INFEntityException("PK is not Defind. [INFEntityDefinitionImpl.isPKDefined()]");
    }

    @Override
    public final boolean isPKComposite() {
        List<INFField> pkFields = fields.stream().filter((f) -> f.isPrimaryKey()).toList();
        
        if (isPKDefined()) {
            return pkFields.size() > 1;
        } else {
            throw new INFEntityException("PK is not Defind. [INFEntityDefinitionImpl.isPKComposite()]");
        }
    }
    
    @Override
    public boolean usesSwitching() {
        return false;
    }
    
    public void setEntity(INFEntityClass entity) {
        this.entity = entity;
    }

    @Override
    public INFEntityClass getEntity() {
        return entity;
    }
    
}
