/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.manager;

/**
 *
 * @author matheus.buschermoehl
 * 
 *  Enumeração dos tipos de bancos de dados disponíveis no migrador.
 */
public enum INFDatabaseType {
    
    POSTGRESQL(1),
    ORACLE(2),
    MYSQL(3),
    SQLSERVER(4),
    MONGODB(5),
    REDIS(6);
    
    private int code;
    
    private INFDatabaseType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public INFDatabaseType valueof(int code){
        for(INFDatabaseType connType : INFDatabaseType.values()){
            if(connType.getCode() == code){
                return connType;
            }
        }
        throw new IllegalArgumentException("O tipo de banco de dados não existe. [INFDatabaseType.valueof(int code)]");
    }
    
}
