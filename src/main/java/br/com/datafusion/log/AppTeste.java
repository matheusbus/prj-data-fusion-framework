/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

import java.util.logging.Level;

/**
 *
 * @author matheus.buschermoehl
 */
public class AppTeste {
    
    public static void main(String[] args) {
        
        Logger logger = Logger.getInstance(new LogArchive());
        try {
            //logger.logInfo("O log está funcionando 1");
            //logger.logInfo("O log está funcionando 2");
            //logger.logInfo("O log está funcionando 3");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AppTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
