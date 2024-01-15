/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.field;

/**
 *
 * @author matheus.buschermoehl
 */
public interface INFField {
    
    public Boolean isPrimaryKey();
    public Boolean isMandatory();
    public String getFieldName();
    public Integer getDecimalPlaces();
    public Object getValue();
    public boolean validateField();
    public void assignValue(Object value);
    public Object getDefaultValue();
    public Boolean persistField();
    public String asString();
    public Integer asInteger() throws INFFieldException;
    public boolean asBoolean() throws INFFieldException;
    public Integer getFieldLength();
    public void setFieldExists(boolean fieldExists);
    public boolean fieldExists();
    public void setStructureOk(boolean structureOk);
    public boolean structureOk();
    
}
