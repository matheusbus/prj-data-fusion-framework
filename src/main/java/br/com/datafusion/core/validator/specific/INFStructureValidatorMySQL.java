/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator.specific;

import br.com.datafusion.core.validator.INFStructureValidatorRelational;
import br.com.datafusion.database.connection.INFConnectionType;

/**
 *
 * @author Matheus
 */
public class INFStructureValidatorMySQL extends INFStructureValidatorRelational {

    public INFStructureValidatorMySQL(INFConnectionType connectionType, String schemaName, String tableName) throws Exception {
        super(connectionType);
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    protected String getSQLScanFields() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected boolean entityExists() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean correctStructure() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
