/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.connection;

import br.com.datafusion.database.pool.INFConnectionPool;
import br.com.datafusion.database.query.INFQuery;
import br.com.datafusion.database.query.INFQueryFactory;


/**
 *
 * @author matheus.buschermoehl
 *
 */
public abstract class INFConnection {

    protected INFConnectionPool connectionPool;
    protected INFQuery query;
    protected Boolean inUse;

    protected abstract void disconnect();
    protected abstract void connect();
    public abstract boolean existsEntity(String entityName);
    public abstract boolean isRelational();
    public abstract String getPID();

    public INFConnection(INFConnectionPool connectionPool) {
        this.inUse = false;
        this.connectionPool = connectionPool;
    }

    public Boolean isInUse() {
        return inUse;
    }

    public void openConnetion() {
        if (!this.inUse) {
            this.inUse = true;
            connect();
        }
    }

    public void closeConnection() {
        if (this.inUse) {
            this.inUse = false;
            disconnect();
        }
    }

    public INFConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public INFQuery getQuery() throws Exception {
        if(query == null) {
            query = INFQueryFactory.createQuery(connectionPool.getDatabaseType(), this);
        }
        return query;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }
}
