package br.com.datafusion.log;

import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.manager.INFConnectionManager;
import br.com.datafusion.database.query.INFQueryNoRelational;
import br.com.datafusion.database.query.INFQueryRelational;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public final class LogToDatabaseCommand implements LogCommand {

    private static INFConnectionManager connectionManager;
    private static INFConnection connection;
    private static String TABLE_NAME = "conf.tblog";
    private static String COLLECTION_NAME = "cllog";
    private AtomicBoolean entityExists;
    private LogData logData;

    /*
        Vai possuir conexão com o banco de destino através do Pool de conexões com destino.
        Vai possuir uma query que será aberta e fechada sempre que o executeLog for chamado.
     */
    public LogToDatabaseCommand() {
        if (connectionManager == null) {
            connectionManager = INFConnectionManager.getInstance();
        }

        // O log de banco de dados será salvo no destino sempre.
        if (connection == null) {
            try {
                connection = connectionManager
                        .getConnectionsPoolDestination()
                        .getAvailableConnection();

                entityExists = new AtomicBoolean();
                if (connection.isRelational()) {
                    entityExists.set(connection.existsEntity(TABLE_NAME));
                } else {
                    entityExists.set(connection.existsEntity(COLLECTION_NAME));
                }

            } catch (Exception ex) {
                Logger.getLogger(LogToDatabaseCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void execute(LogData logData) {
        this.logData = Objects.requireNonNull(logData, "LogData should be not null");

        if (!connection.isRelational()) {
            saveToCollection();
        } else {
            saveToTable();
        }
    }

    public boolean saveToCollection() {
        if (!entityExists.get()) {
            createCollection();
        }

        return true;
    }

    public boolean saveToTable() {
        if (!entityExists.get()) {
            createTable();
        }
        
        String statement = """
                           INSERT INTO CONF.TBLOG 
                               VALUES ( 
                                   (SELECT COALESCE(MAX(LOGCODIGO)+1, 1) FROM CONF.TBLOG),  
                                   CAST(TO_CHAR(CAST('"""+logData.getFormattedDateTime()+"' AS TIMESTAMP), 'YYYY-MM-DD HH:MM:SS') AS TIMESTAMP),  \n" +
                           "         '"+logData.getCriticality().toUpperCase()+"',  \n" +
                           "         '"+logData.getMessage()+"',  \n" +
                           "         "+logData.getUderId()+");";

        try {
            ((INFQueryRelational) connection.getQuery()).execInsert(statement);
        } catch (Exception ex) {
            Logger.getLogger(LogToDatabaseCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    public void createCollection() {
        try {
            ((INFQueryNoRelational) connection.getQuery()).createCollection(COLLECTION_NAME);
        } catch (Exception ex) {
            Logger.getLogger(LogToDatabaseCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable() {
        String statementSchema = "create schema if not exists conf;";
        String statementTable = """
                                create table if not exists conf.tblog (
                                    logcodigo serial not null,
                                    logdatahora timestamp not null,
                                    logcriticidade text not null,
                                    logmensagem text not null,
                                    logusuario integer,
                                    primary key (logcodigo)
                                );""";
        
        try {
            ((INFQueryRelational) connection.getQuery()).execDDL(statementSchema);
            ((INFQueryRelational) connection.getQuery()).execDDL(statementTable);
        } catch (Exception ex) {
            Logger.getLogger(LogToDatabaseCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
