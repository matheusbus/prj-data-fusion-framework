/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity.observer;

import br.com.datafusion.log.LogToFileCommand;
import br.com.datafusion.log.Logger;
import java.util.logging.Level;

/**
 *
 * @author Matheus
 */
public class INFLogCollectionDefinitionObserver implements INFEntityDefinitionObserver {

    @Override
    public void onCollectionDefinitionCreated(String entityName) {
        Logger logger = Logger.getInstance(new LogToFileCommand());
        
        try {
            logger.logInfo("Definição de entidade do tipo Collection gerada: " + entityName);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(INFLogCollectionDefinitionObserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
