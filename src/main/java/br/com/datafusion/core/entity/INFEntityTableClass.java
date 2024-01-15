/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity;

import br.com.datafusion.core.exceptions.INFEntityException;
import br.com.datafusion.core.validator.INFStructureValidator;
import br.com.datafusion.core.validator.INFStructureValidatorFactory;
import br.com.datafusion.database.connection.INFConnectionType;
import br.com.datafusion.database.manager.INFConnectionManager;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author matheus.buschermoehl
 */
public class INFEntityTableClass extends INFEntityClass {

    private String tableName;
    private String schemaName;

    public INFEntityTableClass(INFEntityDefinition entityDefinition) {
        super(entityDefinition);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    @Override
    public boolean validateStructure() {
        boolean result = false;
        if (!entityDefinition.isPKDefined()) {
            throw new INFEntityException("The primary key of the [" + tableName + "] was not defined in the migrator [" + entityDefinition.getClass().getSimpleName() + "]");
        }
        
        try {
            INFStructureValidator validator = INFStructureValidatorFactory.getStructureValidator(INFConnectionType.DESTINATION,
                    INFConnectionManager.getInstance().getConnectionsPoolDestination().getDatabaseType(),
                    this);
            
            result = validator.correctStructure();
        } catch (Exception ex) {
            Logger.getLogger(INFEntityTableClass.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
