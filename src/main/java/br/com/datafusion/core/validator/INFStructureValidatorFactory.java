/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator;

import br.com.datafusion.core.entity.INFEntityClass;
import br.com.datafusion.core.entity.INFEntityCollectionClass;
import br.com.datafusion.core.entity.INFEntityTableClass;
import br.com.datafusion.core.validator.specific.INFStructureValidatorMongoDB;
import br.com.datafusion.core.validator.specific.INFStructureValidatorMySQL;
import br.com.datafusion.core.validator.specific.INFStructureValidatorPostgreSQL;
import br.com.datafusion.core.validator.specific.INFStructureValidatorSQLServer;
import br.com.datafusion.database.connection.INFConnectionType;
import br.com.datafusion.database.manager.INFDatabaseType;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFStructureValidatorFactory {

    public static INFStructureValidator getStructureValidator(INFConnectionType connectionType, INFDatabaseType dbType, INFEntityClass entityClass) throws Exception {

        switch (dbType) {
            case POSTGRESQL -> {
                INFStructureValidator validator = new INFStructureValidatorPostgreSQL(
                        connectionType, 
                        ((INFEntityTableClass) entityClass).getSchemaName(), 
                        ((INFEntityTableClass) entityClass).getTableName());
                validator.setFields(((INFEntityTableClass) entityClass).getFields());
                return validator;
            }
            case MONGODB -> {
                INFStructureValidator validator = new INFStructureValidatorMongoDB(
                        connectionType, 
                        ((INFEntityCollectionClass) entityClass).getCollectionName());
                validator.setFields(((INFEntityCollectionClass) entityClass).getFields());
                return validator;
            }
            case MYSQL -> {
                INFStructureValidator validator = new INFStructureValidatorMySQL(
                        connectionType, 
                        ((INFEntityTableClass) entityClass).getSchemaName(), 
                        ((INFEntityTableClass) entityClass).getTableName());
                validator.setFields(((INFEntityTableClass) entityClass).getFields());
                return validator;
            }
            case ORACLE -> {
                throw new Exception("A fábrica de validação de estrutura ainda não implementou o banco de dados [" + dbType.toString() + "].");
            }
            case REDIS -> {
                throw new Exception("A fábrica de validação de estrutura ainda não implementou o banco de dados [" + dbType.toString() + "].");
            }
            case SQLSERVER -> {
                INFStructureValidator validator = new INFStructureValidatorSQLServer(
                        connectionType, 
                        ((INFEntityTableClass) entityClass).getSchemaName(), 
                        ((INFEntityTableClass) entityClass).getTableName());
                validator.setFields(((INFEntityTableClass) entityClass).getFields());
                return validator;
            }
            default -> {
                throw new Exception("O tipo de banco de dados passado não existe na definição do migrador.");
            }
        }
    }

}
