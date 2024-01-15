/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection.specific;

import br.com.datafusion.database.connection.INFConnectionNoRelational;
import br.com.datafusion.database.pool.INFConnectionPool;



/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionRedisDB extends INFConnectionNoRelational {
    
    public INFConnectionRedisDB(INFConnectionPool connectionPool) {
        super(connectionPool);
    }
    
    @Override
    protected boolean existsEntity(String entityName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
