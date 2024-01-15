/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection;

import br.com.datafusion.database.connection.specific.INFConnectionMongoDB;
import br.com.datafusion.database.connection.specific.INFConnectionMySQL;
import br.com.datafusion.database.connection.specific.INFConnectionOracle;
import br.com.datafusion.database.connection.specific.INFConnectionPostgreSQL;
import br.com.datafusion.database.connection.specific.INFConnectionRedisDB;
import br.com.datafusion.database.connection.specific.INFConnectionSQLServer;
import br.com.datafusion.database.manager.INFDatabaseType;
import br.com.datafusion.database.pool.INFConnectionPool;


/**
 *
 * @author matheus.buschermoehl
 */
public class INFConnectionFactory {

    public static INFConnection createConnection(INFDatabaseType databaseType, INFConnectionPool connectionPool) throws Exception {

        switch (databaseType) {
            case MYSQL -> {
                return new INFConnectionMySQL(connectionPool);
            }
            case POSTGRESQL -> {
                return new INFConnectionPostgreSQL(connectionPool);
            }
            case ORACLE -> {
                return new INFConnectionOracle(connectionPool);
            }
            case SQLSERVER -> {
                return new INFConnectionSQLServer(connectionPool);
            }
            case MONGODB -> {
                return new INFConnectionMongoDB(connectionPool);
            }
            case REDIS -> {
                return new INFConnectionRedisDB(connectionPool);
            }
            default ->
                throw new Exception("O tipo de banco de dados passado não existe na definição do migrador.");
        }

    }

}
