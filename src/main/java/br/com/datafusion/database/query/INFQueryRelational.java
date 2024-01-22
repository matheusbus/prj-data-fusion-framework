/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.query;

import br.com.datafusion.core.field.INFField;
import br.com.datafusion.core.field.INFTableFieldFactory;
import br.com.datafusion.database.connection.INFConnection;
import br.com.datafusion.database.connection.INFConnectionRelational;
import br.com.generator.view.prototipoMigracao.infra.database.exceptions.INFQueryException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFQueryRelational extends INFQuery {

    protected Statement st;
    protected ResultSet result;

    public INFQueryRelational(INFConnection connection) {
        super(connection);
    }

    @Override
    public void open() throws INFQueryException {
        if (statement != null && !statement.isBlank()) {

            try {
                st = ((INFConnectionRelational) connection).getDBConnection().createStatement();
                result = st.executeQuery(statement);
            } catch (SQLException ex) {
                Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            throw new INFQueryException("It wasn't possible to execute the query because SQL is blank. [INFQueryRelational.open()]");
        }
    }

    public void execSQL(String sql) throws INFQueryException {
        setStatement(sql);
        open();
    }

    @Override
    public boolean bof() {
        if (result != null) {
            try {
                return result.isFirst();
            } catch (SQLException ex) {
                Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean eof() {
        if (result != null) {
            try {
                return !result.next();
            } catch (SQLException ex) {
                Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public INFField fieldByName(String fieldName) {
        try {
            if (result.findColumn(fieldName) != -1) {
                return INFTableFieldFactory.assignField(fieldName, result.getString(fieldName));
            } else {
                throw new INFQueryException("Field '" + fieldName + "' not found. [INFQueryRelational.fieldByName(String fieldName)]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public INFField fieldByIndex(Integer fieldIndex) {
        try {
            return INFTableFieldFactory.assignField(null, result.getString(fieldIndex));
        } catch (SQLException ex) {
            Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer getRecordCount() throws INFQueryException {
        String sqlCount = ((INFConnectionRelational) connection).getSQLCountSelect();
        sqlCount = sqlCount.replace("{SQL}", statement);
        ResultSet resultCount = null;

        if (statement != null && !statement.isBlank()) {

            try {
                st = ((INFConnectionRelational) connection).getDBConnection().createStatement();
                resultCount = st.executeQuery(sqlCount);
                resultCount.next();
                return resultCount.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (resultCount != null) {
                    try {
                        resultCount.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        } else {
            throw new INFQueryException("It wasn't possible to execute the query because SQL is blank. [INFQueryRelational.getRecordCount()]");
        }

        return 0;
    }

    public void execDDL(String ddl) throws INFQueryException, SQLException {
        try {
            st = ((INFConnectionRelational) connection).getDBConnection().createStatement();
            st.execute(ddl);
        } catch (SQLException ex) {
            Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void execInsert(String insert) throws INFQueryException, SQLException {
        try {
            st = ((INFConnectionRelational) connection).getDBConnection().createStatement();
            st.execute(insert);
        } catch (SQLException ex) {
            Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws INFQueryException {
        try {
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(INFQueryRelational.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
