/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.query;

import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.manager.INFDatabaseType;


/**
 *
 * @author matheus.buschermoehl
 */
public class INFQueryFactory {

    public static INFQuery createQuery(INFDatabaseType databaseType, INFConnection connection) throws Exception {

        switch (databaseType) {
            case POSTGRESQL -> {
                return new INFQueryRelational(connection);
            }
            case MYSQL -> {
                return new INFQueryRelational(connection);
            }
            case ORACLE -> {
                return new INFQueryRelational(connection);
            }
            case SQLSERVER -> {
                return new INFQueryRelational(connection);
            }
            case MONGODB -> {
                return new INFQueryNoRelational(connection);
            }
            case REDIS -> {
                return new INFQueryNoRelational(connection);
            }
            default -> {
                throw new Exception("O tipo de banco de dados passado não existe na definição do migrador.");
            }
        }

    }

}
