/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.pool;

import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.manager.INFDatabaseType;
import br.com.datafusion.database.connection.INFConnectionFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public final class INFConnectionPoolRelational extends INFConnectionPool {

    protected String dbName;
    protected String dbUsername;
    protected String dbPassword;
    
    public INFConnectionPoolRelational(INFDatabaseType databaseType) {
        super(databaseType);
    }

    public INFConnectionPoolRelational(String protocol, String dbHost, String dbPort, String dbName, String dbUsername, String dbPassword, INFDatabaseType databaseType) {
        super(databaseType);
        this.protocol = protocol;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        
        createConnections();
    }

    @Override
    protected void createConnections() {
        connections = new ArrayList<>();
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                INFConnection connection = INFConnectionFactory.createConnection(databaseType, this);
                connections.add(connection);
            } catch (Exception ex) {
                Logger.getLogger(INFConnectionPoolRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
    
    
    
}
