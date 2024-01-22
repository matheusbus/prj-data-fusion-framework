/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

/**
 *
 * @author matheus.buschermoehl
 */
public interface LogCommand {
    
    public void execute(LogData logData);
    
}
