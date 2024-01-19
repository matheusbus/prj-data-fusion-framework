/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;


/**
 *
 * @author matheus.buschermoehl
 */
public final class Logger {

    private Log logger;
    private static Logger instance;
    
    public static Logger getInstance() {
        if(instance == null) {
            instance = new Logger();
        }
        
        return instance;
    }
    
    public static Logger getInstance(Log logger) {
        if(instance == null) {
            instance = new Logger(logger);
        } 
        
        if(instance.getLogger().getClass() != logger.getClass()) {
            throw new IllegalArgumentException("The Logger class has already been initialized with another log type: "
                    + instance.getLogger().getClass().getName()
                    + ". The class passed for log was: " + logger.getClass().getName()
            );
        }
        
        return instance;
    }
    
    private Logger() {
        
    }
    
    private Logger(Log logger) {
        setLogger(logger);
    }
    
    public void withLoggerType(Log logger) {
        setLogger(logger);
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public Log getLogger() {
        return logger;
    }

    public void log(String message) throws Exception {
        if (logger != null) {
            logger.execute();
        } else {
            throw new Exception("The logger type is null.");
        }
    }

}
