/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.field;

/**
 *
 * @author Matheus
 */
public class GRTEntityTableField extends GRTEntityField {

    private Integer length;
    
    public GRTEntityTableField() {
    }

    public GRTEntityTableField(Integer length, String fieldName, String fieldType, Boolean identifier, Boolean Mandatory) {
        super(fieldName, fieldType, identifier, Mandatory);
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    
    
    
}
