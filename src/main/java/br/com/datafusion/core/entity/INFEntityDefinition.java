package br.com.datafusion.core.entity;

import br.com.datafusion.core.field.INFField;

/**
 * @author matheus.buschermoehl
**/
public interface INFEntityDefinition {
    
    public void addField(INFField field);
    public INFEntityClass createDefine();
    public boolean clearRecordsBefore();
    public boolean isPKDefined();
    public boolean isPKComposite();
    public INFEntityClass addEntity(INFEntityClass entity);
    public INFEntityClass getEntity();
    public boolean usesSwitching();
    
    
}
