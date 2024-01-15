/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.data;

import br.com.datafusion.core.entity.INFEntityClass;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFDataPackage {
    
    private INFEntityClass migrateEntity;
    
    // Aqui vai ter o lista redis <Record> que vai salvar e memória todos os registros processados.

    public INFDataPackage() {
        this.migrateEntity = null;
    }

    public INFEntityClass getMigrateEntity() {
        return migrateEntity;
    }

    public void setMigrateEntity(INFEntityClass migrateEntity) {
        this.migrateEntity = migrateEntity;
    }
    
    public boolean validateStructure() {
        return false;
    }
    
    
    
}
