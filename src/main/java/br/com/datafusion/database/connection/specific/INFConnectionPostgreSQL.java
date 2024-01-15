/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection.specific;

import br.com.datafusion.database.connection.INFConnectionRelational;
import br.com.datafusion.database.pool.INFConnectionPool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionPostgreSQL extends INFConnectionRelational {
    
    public INFConnectionPostgreSQL(INFConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected String getSQLPIDSelect() {
        return "SELECT PG_BACKEND_PID()";
    }

    @Override
    public String getSQLCountSelect() {
        return "SELECT COUNT(*) FROM ({SQL}) AS COUNT";
    }
    
    @Override
    protected boolean existsEntity(String entityName) {
        if(entityName.contains(".")) {
            String entity[] = entityName.split(".");
            return schemaExists(entity[0]) && tableExists(entity[0], entity[1]);
        } else {
            return tableExists("", entityName);
        }
    }

    @Override
    public Boolean schemaExists(String schemaName) {
        Boolean exists = false;
        ResultSet result;
        PreparedStatement preparedStatement;
        if (connection != null && inUse) {
            try {
                preparedStatement = connection.prepareStatement(getSQLSchemaExists().replace("{SCHEMA_NAME}", schemaName));
                result = preparedStatement.executeQuery();
                result.next();
                exists = result.getBoolean(1);
                
                result.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(INFConnectionRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exists;
    }

    @Override
    public Boolean tableExists(String schemaName, String tableName) {
        Boolean exists = false;
        ResultSet result;
        PreparedStatement preparedStatement;
        if (connection != null && inUse) {
            try {
                preparedStatement = connection.prepareStatement(getSQLTableExists().replace("{SCHEMA_NAME}", schemaName).replace("{TABLE_NAME}", tableName));
                result = preparedStatement.executeQuery();
                result.next();
                exists = result.getBoolean(1);
                
                result.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(INFConnectionRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exists;
    }

    @Override
    public String getSQLSchemaExists() {
        return "SELECT EXISTS (SELECT FROM INFORMATION_SCHEMA.SCHEMATA WHERE UPPER(SCHEMA_NAME) = UPPER('{SCHEMA_NAME}'))";
    }

    @Override
    public String getSQLTableExists() {
        return "SELECT EXISTS (SELECT FROM INFORMATION_SCHEMA.TABLES WHERE UPPER(TABLE_NAME) = UPPER('{TABLE_NAME}') AND UPPER(TABLE_SCHEMA) = UPPER('{SCHEMA_NAME}'))";
    }
    
}
