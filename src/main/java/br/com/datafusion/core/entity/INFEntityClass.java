/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity;

import br.com.datafusion.core.field.INFField;
import java.util.List;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFEntityClass {
    
    protected INFEntityDefinition entityDefinition;
    private List<INFField> fields;

    public INFEntityClass(INFEntityDefinition entityDefinition) {
        this.entityDefinition = entityDefinition;
    }
    
    public void addField(INFField field) {
        this.fields.add(field);
    }

    public INFEntityDefinition getEntityDefinition() {
        return entityDefinition;
    }

    public void setEntityDefinition(INFEntityDefinition entityDefinition) {
        this.entityDefinition = entityDefinition;
    }

    public List<INFField> getFields() {
        return fields;
    }

    public void setFields(List<INFField> fields) {
        this.fields = fields;
    }
    
    public abstract boolean validateStructure();
}
