/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator;

import br.com.datafusion.database.connection.INFConnectionType;

/**
 *
 * @author Matheus
 */
public abstract class INFStructureValidatorNoRelational extends INFStructureValidator {

    protected String collectionName;
    
    public INFStructureValidatorNoRelational(INFConnectionType connectionType) throws Exception {
        super(connectionType);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    protected boolean entityExists() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean correctStructure() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected abstract String getStatementScanFields();
    
}
