/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator;


import br.com.datafusion.core.field.INFField;
import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.connection.INFConnectionType;
import br.com.datafusion.database.manager.INFConnectionManager;
import java.util.List;

/**
 *
 * @author matheus.buschermoehl
 */
public abstract class INFStructureValidator {

    protected INFConnectionType connectionType;
    protected INFConnection connection;
    protected List<INFField> fields;

    protected String returnMessage;

    public INFStructureValidator(INFConnectionType connectionType) throws Exception {
        this.connectionType = connectionType;
        switch (connectionType) {
            case DESTINATION:
                this.connection = INFConnectionManager.getInstance().getConnectionsPoolDestination().getAvailableConnection();
                break;
            case INTERMEDIARY:
                this.connection = INFConnectionManager.getInstance().getConnectionsPoolIntermediary().getAvailableConnection();
                break;
            default:
                this.connection = INFConnectionManager.getInstance().getConnectionsPoolOrigin().getAvailableConnection();
                break;
        }
        
    }

    protected abstract boolean entityExists();

    public abstract boolean correctStructure();

    public INFConnection getConnection() {
        return connection;
    }

    protected void setConnection() throws Exception {
        this.connection = switch (connectionType) {
            case ORIGIN ->
                INFConnectionManager.getInstance().getConnectionsPoolOrigin().getAvailableConnection();
            case INTERMEDIARY ->
                INFConnectionManager.getInstance().getConnectionsPoolIntermediary().getAvailableConnection();
            default ->
                INFConnectionManager.getInstance().getConnectionsPoolDestination().getAvailableConnection();
        };
    }

    public INFConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(INFConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public List<INFField> getFields() {
        return fields;
    }

    public void setFields(List<INFField> fields) {
        this.fields = fields;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
