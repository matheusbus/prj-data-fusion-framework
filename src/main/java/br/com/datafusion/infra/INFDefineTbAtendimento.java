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
public class INFDefineTbAtendimento extends INFEntityTableDefinition {

    public INFDefineTbAtendimento(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    @Override
    public INFEntityClass createDefine() {
        
        this.tableSchema = "SAUDE";
        this.tableName = "TBATENDIMENTO";
        
        addTableField("ATECODIGO", INFTableFieldType.SERIAL, true);
        addTableField("ATEDESCRICAO", INFTableFieldType.TEXT, false, true, 0);
        addTableField("ATEDATA", INFTableFieldType.DATE, true, "now()");
        addTableField("ATEHORA", INFTableFieldType.TIME, true, "");
        addTableField("PESCODIGO", INFTableFieldType.INTEGER, false, true, 0);
        addTableField("PRFCODIGO", INFTableFieldType.INTEGER, false, true, 0);
        addTableField("ATESTATUS", INFTableFieldType.SMALLINT, false, true, 0);
        addTableField("ATEJSON", INFTableFieldType.JSON);
        
        return super.createDefine();
    }
    
    
    
}
