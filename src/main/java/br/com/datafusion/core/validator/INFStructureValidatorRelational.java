/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator;

import br.com.datafusion.database.connection.INFConnectionType;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFStructureValidatorRelational extends INFStructureValidator {

    protected String schemaName;
    protected String tableName;
    
    public INFStructureValidatorRelational(INFConnectionType connectionType) throws Exception {
        super(connectionType);
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    protected abstract String getSQLScanFields();
    
    
    
}
