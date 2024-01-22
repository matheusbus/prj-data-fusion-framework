/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator.specific;

import br.com.datafusion.core.validator.INFStructureValidatorNoRelational;
import br.com.datafusion.database.connection.INFConnectionType;

/**
 *
 * @author Matheus
 */
public class INFStructureValidatorMongoDB extends INFStructureValidatorNoRelational {
    
    public INFStructureValidatorMongoDB(INFConnectionType connectionType, String collectionName) throws Exception {
        super(connectionType);
    }

    @Override
    protected String getStatementScanFields() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
