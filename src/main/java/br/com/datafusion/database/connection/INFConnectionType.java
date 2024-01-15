/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.datafusion.database.connection;

import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public enum INFConnectionType {
    
    ORIGIN,
    INTERMEDIARY,
    DESTINATION;

    public static INFConnectionType getORIGIN() {
        return ORIGIN;
    }

    public static INFConnectionType getINTERMEDIARY() {
        return INTERMEDIARY;
    }

    public static INFConnectionType getDESTINATION() {
        return DESTINATION;
    }
    
    
}
