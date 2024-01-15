/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.query;

import br.com.datafusion.core.field.INFField;
import br.com.datafusion.database.connection.INFConnection;
import br.com.generator.view.prototipoMigracao.infra.database.exceptions.INFQueryException;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFQuery {
    
    protected String statement;
    protected INFConnection connection;

    public INFQuery(INFConnection connection) {
        this.connection = connection;
    }
    
    public abstract void open() throws INFQueryException;
    public abstract void close() throws INFQueryException;
    public abstract boolean bof();
    public abstract boolean eof();
    public abstract INFField fieldByName(String fieldName);
    public abstract INFField fieldByIndex(Integer fieldIndex);
    public abstract Integer getRecordCount() throws INFQueryException;
    
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
    
    
    
    
}
