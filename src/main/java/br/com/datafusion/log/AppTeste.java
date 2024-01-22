package br.com.datafusion.log;

import java.util.logging.Level;

/**
 *
 * @author matheus.buschermoehl
 */
public class AppTeste {
    
    public static void main(String[] args) {
        
        Logger logger = Logger.getInstance(new LogToDatabaseCommand());
        try {
            logger.logWarning("O log está funcionando 1");
            logger.logInfo("O log está funcionando 2");
            logger.logInfo("O log está funcionando 3");
            logger.logWarning("Mensagem de log aqui.");
            logger.logCritical("Mensagem de log aqui.");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AppTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
