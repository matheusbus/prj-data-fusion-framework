/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.pool;

import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.manager.INFDatabaseType;
import java.util.List;

/**
 *
 * @author matheus.buschermoehl
 * 
 * Classe de pool de conexões.
 */
public abstract class INFConnectionPool {
    
    protected static Integer DEFAULT_POOL_SIZE = 3;
    protected INFDatabaseType databaseType;
    protected List<INFConnection> connections;
    
    protected String protocol;
    protected String dbHost;
    protected String dbPort;
    
    public INFConnectionPool(INFDatabaseType databaseType) {
        this.databaseType = databaseType;
    }
    
    protected abstract void createConnections();
    
    public static Integer getDEFAULT_POOL_SIZE() {
        return DEFAULT_POOL_SIZE;
    }

    public INFDatabaseType getDatabaseType() {
        return databaseType;
    }

    public List<INFConnection> getConnections() {
        return connections;
    }
    
    public INFConnection getAvailableConnection() throws Exception {
        INFConnection availableConn = null;
        for(INFConnection conn : this.connections) {
            if(!conn.isInUse()) {
                conn.openConnetion();
                availableConn = conn;
                break;
            }
        }
        
        if(availableConn == null) {
            throw new Exception("No connections available. [INFConnectionPool.getAvailableConnection()]");
        }
        return availableConn;
    }
    
    public void leaveConnection(INFConnection connection) {
        if(connections.indexOf(connection) != -1) {
            connections.get(connections.indexOf(connection)).closeConnection();
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }
    
    
}
