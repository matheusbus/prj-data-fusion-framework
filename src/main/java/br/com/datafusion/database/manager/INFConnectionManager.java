/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.manager;

import br.com.datafusion.database.pool.INFConnectionPool;
import br.com.datafusion.database.pool.INFConnectionPoolRelational;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionManager {

    private static INFConnectionPool connectionsPoolOrigin;
    private static INFConnectionPool connectionsPoolIntermediary;
    private static INFConnectionPool connectionsPoolDestination;

    private static INFConnectionManager instance;

    public static INFConnectionManager getInstance() {
        if (instance == null) {
            instance = new INFConnectionManager();
        }
        return instance;
    }

    private INFConnectionManager() {
        try {
            if (connectionsPoolOrigin == null) {
                connectionsPoolOrigin = createOriginConnectionPool();
            }

            if (connectionsPoolIntermediary == null) {
                connectionsPoolIntermediary = createIntermediaryConnectionPool();
            }

            if (connectionsPoolDestination == null) {
                connectionsPoolDestination = createDestinationConnectionPool();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    private INFConnectionPool createOriginConnectionPool() throws IOException {
        return createMethodConnectionPool("origin");
    }

    private INFConnectionPool createIntermediaryConnectionPool() {
        return null;
    }

    private INFConnectionPool createDestinationConnectionPool() throws IOException {
        return createMethodConnectionPool("destination");
    }
    
    private INFConnectionPool createMethodConnectionPool(String method) throws IOException {
        Properties p = INFConnectionProperties.getInstance().getMethodProperties(method);
        return new INFConnectionPoolRelational(p.getProperty(method + "DBProtocol"),
                p.getProperty(method + "DBHost"),
                p.getProperty(method + "DBPort"),
                p.getProperty(method + "DBName"),
                p.getProperty(method + "DBUsername"),
                p.getProperty(method + "DBPassword"),
                INFDatabaseType.valueOf(p.getProperty(method + "DBType")));
    }

    public INFConnectionPool getConnectionsPoolOrigin() {
        return connectionsPoolOrigin;
    }

    public INFConnectionPool getConnectionsPoolIntermediary() {
        return connectionsPoolIntermediary;
    }

    public INFConnectionPool getConnectionsPoolDestination() {
        return connectionsPoolDestination;
    }

}
