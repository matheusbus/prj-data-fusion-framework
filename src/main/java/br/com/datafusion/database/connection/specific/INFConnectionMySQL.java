/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection.specific;

import br.com.datafusion.database.connection.INFConnectionRelational;
import br.com.datafusion.database.pool.INFConnectionPool;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionMySQL extends INFConnectionRelational {

    public INFConnectionMySQL(INFConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected String getSQLPIDSelect() {
        return "SELECT CONNECTION_ID()";
    }

    @Override
    public String getSQLCountSelect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsEntity(String entityName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean schemaExists(String schemaName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean tableExists(String schemaName, String tableName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSQLSchemaExists() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSQLTableExists() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
