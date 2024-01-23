/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.file;

import br.com.datafusion.generator.field.GRTEntityCollectionField;
import br.com.datafusion.generator.field.GRTEntityField;
import java.nio.file.Path;

/**
 *
 * @author Matheus
 */
public class GRTEntityDefinitionCollectionFile extends GRTEntityDefinitionFile {
    
    private String collectionName;
    
    public GRTEntityDefinitionCollectionFile(Path path, String className) {
        super(path, className);
        this.collectionName = className;
    }

    @Override
    public void createBody() {
        classBody
                .append("package br.com.datafusion;\n")
                .append("\n")
                .append("import br.com.datafusion.core.data.INFDataPackage;\n")
                .append("import br.com.datafusion.core.entity.INFEntityClass;\n")
                .append("import br.com.datafusion.core.entity.INFEntityCollectionDefinition;\n")
                .append("import br.com.datafusion.core.field.INFCollectionFieldType;\n")
                .append("\n")
                .append("public class " + className + " extends INFEntityCollectionDefinition {\n")
                .append("\n")
                .append("    public " + className + "(INFDataPackage dataPackage) {\n")
                .append("        super(dataPackage);\n")
                .append("    }\n")
                .append("\n")
                .append("    @Override\n")
                .append("    public INFEntityClass createDefine() {\n")
                .append("\n")
                .append("        this.collectionName = \"" + collectionName.toUpperCase() + "\";\n")
                .append("\n");

        for (GRTEntityField field : fields) {
            classBody.append("        addCollectionField(\"" + field.getFieldName().toUpperCase() + "\", INFCollectionFieldType." + field.getFieldType().toUpperCase() + "," + field.isIdentifier() + "," + field.isMandatory() + ");\n");
        }

        classBody
                .append("\n")
                .append("        return super.createDefine();\n")
                .append("    }\n")
                .append("\n")
                .append("}\n");
    }
    
    @Override
    public void addEntityName(String... args) {
        this.collectionName = args[0];
    }

    @Override
    public void addField(String fieldName, String fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length) {
        GRTEntityCollectionField field = new GRTEntityCollectionField(fieldName, fieldType, isMandatory, isMandatory);
        this.fields.add(field);
    } 
}
