/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author matheus.buschermoehl
 */
public class LogData {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private LocalDateTime dateTime;
    private Long uderId;
    private String message;
    private String criticality;

    public LogData() {
    }

    public LogData(LocalDateTime dateTime, Long uderId, String message, String criticality) {
        this.dateTime = dateTime;
        this.uderId = uderId;
        this.message = message;
        this.criticality = criticality;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getUderId() {
        return uderId;
    }

    public void setUderId(Long uderId) {
        this.uderId = uderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }
    
    public String getFormattedDateTime() {
        return FORMATTER.format(getDateTime());
    }

    @Override
    public String toString() {
        return "LOG: " + FORMATTER.format(getDateTime()) + ". [" + getCriticality()+ "] MESSAGE: " + getMessage() + ".";
    }
    
}
