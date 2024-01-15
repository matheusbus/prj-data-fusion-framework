/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.field;

/**
 *
 * @author matheus.buschermoehl
 */
public interface INFTableField extends INFField {
    
    public INFTableFieldType getTableFieldType();
    public Boolean isAutoIncrement();
    
}
