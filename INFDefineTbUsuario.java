package br.com.datafusion;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.entity.INFEntityClass;
import br.com.datafusion.core.entity.INFEntityTableDefinition;
import br.com.datafusion.core.field.INFTableFieldType;

public class INFDefineTbUsuario extends INFEntityTableDefinition {

    public INFDefineTbUsuario(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    @Override
    public INFEntityClass createDefine() {

        this.tableSchema = "GLOBAL";
        this.tableName = "TBUSUARIO";

        addTableField("USUCODIGO", INFTableFieldType.INTEGER,true,true,0);
        addTableField("USUNOME", INFTableFieldType.VARCHAR,true,true,100);
        addTableField("USUSENHA", INFTableFieldType.VARCHAR,true,true,60);
        addTableField("USUIDADE", INFTableFieldType.INTEGER,false,false,0);
        addTableField("USUDATACADASTRO", INFTableFieldType.DATE,true,true,0);

        return super.createDefine();
    }

}
