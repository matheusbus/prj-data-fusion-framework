package br.com.datafusion;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.entity.INFEntityClass;
import br.com.datafusion.core.entity.INFEntityTCollectionDefinition;
import br.com.datafusion.core.field.INFCollectionFieldType;

public class INFDefineClGrupoUsuario extends INFEntityTCollectionDefinition {

    public INFDefineClGrupoUsuario(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    @Override
    public INFEntityClass createDefine() {

        this.collectionName = "TBGRUPOUSUARIO";

        addCollectionField("GRUCODIGO", INFCollectionFieldType.INTEGER,true,true);
        addCollectionField("GRUDESCRI", INFCollectionFieldType.VARCHAR,true,true);

        return super.createDefine();
    }

}
