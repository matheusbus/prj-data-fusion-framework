/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.infra;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.entity.INFEntityClass;
import br.com.datafusion.core.entity.INFEntityTableDefinition;
import br.com.datafusion.core.field.INFTableFieldType;


/**
 *
 * @author matheus.buschermoehl
 */
public class INFDefineTbUnidadeSaude extends INFEntityTableDefinition {

    public INFDefineTbUnidadeSaude(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    /*
        create schema saude;
        drop table saude.tbunidadesaude
        create table saude.tbunidadesaude (
                unicodigo serial not null,
                unicnpj varchar (14) not null,
                uninomerazao varchar(60) not null,
                unitelefone varchar(11) not null,
                unicnes varchar(7) not null,
                unibairro varchar(100),
                unilogradouro varchar(150),
                uninumero integer,
                unistatus smallint,
                uniorcamento numeric(14,2),
                unifisica boolean default true,
                constraint tbunidadesaude_pkey primary key (unicodigo)
);
    */
    
    @Override
    public INFEntityClass createDefine() {
        tableSchema = "saude";
        tableName = "tbunidadesaude";
        
        addTableField("UNICODIGO", INFTableFieldType.INTEGER, true, true);
        addTableField("UNICNPJ", INFTableFieldType.VARCHAR, false, true, 14);
        addTableField("UNINOMERAZAO", INFTableFieldType.VARCHAR, false, true, 60);
        addTableField("UNITELEFONE", INFTableFieldType.VARCHAR, false, true, 11);
        addTableField("UNICNES", INFTableFieldType.VARCHAR, false, true, 7);
        addTableField("UNIBAIRRO", INFTableFieldType.VARCHAR, false, false, 100);
        addTableField("UNILOGRADOURO", INFTableFieldType.VARCHAR, false, false, 150);
        addTableField("UNINUMERO", INFTableFieldType.INTEGER);
        addTableField("UNISTATUS", INFTableFieldType.SMALLINT);
        addTableField("UNIORCAMENTO", INFTableFieldType.NUMERIC, false, false, 14, 2);
        addTableField("UNIFISICA", INFTableFieldType.BOOLEAN, false, Boolean.TRUE);
        
        return super.createDefine();
    }
    
}
