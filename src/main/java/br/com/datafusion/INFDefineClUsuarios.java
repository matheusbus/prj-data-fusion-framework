package br.com.datafusion;

import br.com.datafusion.core.data.INFDataPackage;
import br.com.datafusion.core.entity.INFEntityClass;
import br.com.datafusion.core.entity.INFEntityCollectionDefinition;
import br.com.datafusion.core.field.INFCollectionFieldType;

public class INFDefineClUsuarios extends INFEntityCollectionDefinition {

    public INFDefineClUsuarios(INFDataPackage dataPackage) {
        super(dataPackage);
    }

    @Override
    public INFEntityClass createDefine() {

        this.collectionName = "INFDEFINECLUSUARIOS";

        addCollectionField("ID", INFCollectionFieldType.BIGINTEGER,true,true);
        addCollectionField("NAME", INFCollectionFieldType.STRING,true,true);
        addCollectionField("PASSWORD", INFCollectionFieldType.STRING,true,true);

        return super.createDefine();
    }

}