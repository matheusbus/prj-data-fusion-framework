/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.pool;

import br.com.datafusion.database.manager.INFDatabaseType;


/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionPoolNoRelational extends INFConnectionPool {

    public INFConnectionPoolNoRelational(INFDatabaseType databaseType) {
        super(databaseType);
    }

    public INFConnectionPoolNoRelational(String protocol, String dbHost, String dbPort, INFDatabaseType databaseType) {
        super(databaseType);
        this.protocol = protocol;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
    }

    @Override
    protected void createConnections() {
        // logica para criar conexões no mongo
    }
    
}
