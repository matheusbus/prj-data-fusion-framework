/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection;

import br.com.datafusion.database.pool.INFConnectionPool;
import br.com.datafusion.database.pool.INFConnectionPoolRelational;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFConnectionRelational extends INFConnection {

    protected Connection connection;
    protected Boolean autoCommit;

    public INFConnectionRelational(INFConnectionPool connectionPool) {
        super(connectionPool);
    }
    
    protected abstract String getSQLPIDSelect();
    public abstract Boolean schemaExists(String schemaName);
    public abstract Boolean tableExists(String schemaName, String tableName);
    public abstract String getSQLCountSelect();
    public abstract String getSQLSchemaExists();
    public abstract String getSQLTableExists();

    public Connection getDBConnection() throws SQLException {
        if (this.connection == null) {
            connect();
        }
        return connection;
    }

    @Override
    protected void disconnect() {
        try {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(INFConnectionRelational.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    /* Método candidato a ser sobrescrito em cada tipo de banco de dados especificamente. (vira abstrato aqui daí) */
    @Override
    protected void connect() {
        if (this.connection == null) {
            String url = connectionPool.getProtocol() + "//"
                    + connectionPool.getDbHost() + ":"
                    + connectionPool.getDbPort() + "/"
                    + ((INFConnectionPoolRelational) connectionPool).getDbName();
            try {
                this.connection = DriverManager.getConnection(url,
                        ((INFConnectionPoolRelational) connectionPool).getDbUsername(),
                        ((INFConnectionPoolRelational) connectionPool).getDbPassword());
            } catch (SQLException ex) {
                Logger.getLogger(INFConnectionRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public String getPID() {
        ResultSet result = null;
        PreparedStatement preparedStatement = null;        
        if (connection != null && inUse) {
            try {
                preparedStatement = connection.prepareStatement(getSQLPIDSelect());
                result = preparedStatement.executeQuery();
                if (result.next()) {
                    return String.valueOf(result.getInt(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(INFConnectionRelational.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                result = null;
                preparedStatement = null;
            }
        }
        return "Connection is not active!";
    }
    
    public Boolean getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(Boolean autoCommit) throws SQLException {
        connection.setAutoCommit(this.autoCommit);
        this.autoCommit = autoCommit;
    }

}
