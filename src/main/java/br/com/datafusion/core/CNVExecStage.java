package br.com.datafusion.core;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.entity.INFEntityDefinition;
import br.com.datafusion.core.exception.CNVExecStageNotRegistered;
import br.com.datafusion.database.manager.INFConnectionManager;
import br.com.datafusion.database.query.INFQuery;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 *
 * Classe abstrata das etapas de migração
 */
public abstract class CNVExecStage {

    protected INFConnectionManager connectionManager;
    protected INFEntityDefinition define;
    protected INFDataPackage dataPackage;
    protected static CNVExecStage step;

    protected String log;

    //protected Lista ListaCondicoesPesquisa
    
    protected abstract void createIndexesForSearch();
    protected abstract void dropIndexesForSearch();
    protected abstract Boolean executeAfterPersist();

    // This method is triggered as soon as the step is selected to start the migration
    protected static Boolean beforeExecuteConversion(Class<?> clazz, String table) throws NoSuchMethodException {
        if (step != null) {
            step = null;
        }

        if (step == null) {
            if (clazz == null) {
                throw new CNVExecStageNotRegistered("Migration class not registered.");
            }

            var constructor = clazz.getDeclaredConstructor();
            try {
                step = (CNVExecStage) constructor.newInstance();
                // atribuir step ao proxy.
                
                step.executeConversion();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(CNVExecStage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
    
    protected Boolean executeConversion() {
        
        // Marca a etapa como "executando" no front-end
        
        // Limpar log de informações sobre a etapa, pois a etapa será realizada novamente.
        clearLog();
        
        // Validar a estrutura da tabela migrada.
        validateEntityStructure();
        
        // Cria os índices necessários para pesquisas.
        createIndexesForSearch();
        
        return true;
    }
    
    protected Boolean finishConversion() {
        dropIndexesForSearch();
        return true;
    }
    
    protected Boolean processStepConversion() {
        return true;
    }

    protected void validateEntityStructure() {
        if(!dataPackage.validateStructure()) {
            sendError("Error during validateStructure()");
        }
    }

    protected Boolean validatePendingIssues() {
        return true;
    }

    protected Boolean stepIsReady() {
        return true;
    }

    protected void clearLog() {
        // clear log;
    }

    protected void log(String logText) {
        // add text to log
    }

    protected void logSQL(String sql) {
        // add sql to log
    }

    protected void sendError(String error) {
        // emite erro
    }

    protected INFQuery newQueryOrigin() throws Exception {
        return connectionManager
                .getConnectionsPoolOrigin()
                .getAvailableConnection()
                .getQuery();
    }

    protected INFQuery newQueryIntermediary() throws Exception {
        return connectionManager
                .getConnectionsPoolIntermediary()
                .getAvailableConnection()
                .getQuery();
    }

    protected INFQuery newQueryDestination() throws Exception {
        return connectionManager
                .getConnectionsPoolDestination()
                .getAvailableConnection()
                .getQuery();
    }

}
