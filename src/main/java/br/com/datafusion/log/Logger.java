/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

import java.time.LocalDateTime;


/**
 *
 * @author matheus.buschermoehl
 */
public final class Logger {

    private LogCommand command;
    private static Logger instance;
    
    public static Logger getInstance() {
        if(instance == null) {instance = new Logger();}
        return instance;
    }
    
    public static Logger getInstance(LogCommand command) {
        if(instance == null) {instance = new Logger(command);}
        
        if(instance.getLogCommand().getClass() != command.getClass()) {
            throw new IllegalArgumentException("The Logger(Invoker) class has already been initialized with another log command: "
                    + instance.getLogCommand().getClass().getName()
                    + ". The class passed for log was: " + command.getClass().getName()
            );
        }
        return instance;
    }
    
    private Logger() {}
    
    private Logger(LogCommand logger) {setLogCommand(logger);}
    
    public void setLogCommand(LogCommand command) {this.command = command;}

    public LogCommand getLogCommand() {return command;}

    public void logInfo(String message) throws Exception {
        LogData logData = new LogData();
        logData.setMessage(message);
        logData.setCriticality("INFO");
        logData.setDateTime(LocalDateTime.now());
        //logData.setUderId(userManager.getCurrentUser().getId());
        
        if (command != null) {
            command.execute(logData);
        } else {
            throw new Exception("The log command is null.");
        }
    }
    
    public void logWarning(String message) throws Exception {
        LogData logData = new LogData();
        logData.setMessage(message);
        logData.setCriticality("WARNING");
        logData.setDateTime(LocalDateTime.now());
        //logData.setUderId(userManager.getCurrentUser().getId());
        
        if (command != null) {
            command.execute(logData);
        } else {
            throw new Exception("The log command is null.");
        }
    }
    
    public void logCritical(String message) throws Exception {
        LogData logData = new LogData();
        logData.setMessage(message);
        logData.setCriticality("CRITICAL");
        logData.setDateTime(LocalDateTime.now());
        //logData.setUderId(userManager.getCurrentUser().getId());
        
        if (command != null) {
            command.execute(logData);
        } else {
            throw new Exception("The log command is null.");
        }
    }

    public void withLogCommand(LogCommand command) {
        setLogCommand(command);
    }
    
}
