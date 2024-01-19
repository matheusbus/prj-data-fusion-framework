/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  br.com.datafusion.log;

import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.manager.INFConnectionManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public final class LogDatabase implements Log {

    private static INFConnectionManager connectionManager;
    private static INFConnection connection;
    
    /*
        Vai possuir conexão com o banco de destino através do Pool de conexões com destino.
        Vai possuir uma query que será aberta e fechada sempre que o executeLog for chamado.
    */

    public LogDatabase() {
        if(connectionManager == null) {
            connectionManager = INFConnectionManager.getInstance();
        }
        
        // O log de banco de dados será salvo no destino sempre.
        if(connection == null) {
            try {
                connection = connectionManager
                        .getConnectionsPoolDestination()
                        .getAvailableConnection();
            } catch (Exception ex) {
                Logger.getLogger(LogDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(connection.existsEntity("conf.tblog")) {
            createLogEntity();
        }
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void createLogEntity() {
        String sqlTable = "";
        
    }
    
}
