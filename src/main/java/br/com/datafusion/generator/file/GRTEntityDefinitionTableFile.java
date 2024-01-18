/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.file;

import br.com.datafusion.core.field.INFTableFieldType;
import br.com.datafusion.generator.field.GRTEntityField;
import br.com.datafusion.generator.field.GRTEntityTableField;
import java.nio.file.Path;

/**
 *
 * @author Matheus
 */
public class GRTEntityDefinitionTableFile extends GRTEntityDefinitionFile {

    private String schemaName;
    private String tableName;

    public GRTEntityDefinitionTableFile(Path path, String className) {
        super(path, className);
    }

    @Override
    public void createBody() {
        classBody
                .append("package br.com.datafusion;\n")
                .append("\n")
                .append("import br.com.datafusion.core.data.INFDataPackage;\n")
                .append("import br.com.datafusion.core.entity.INFEntityClass;\n")
                .append("import br.com.datafusion.core.entity.INFEntityTableDefinition;\n")
                .append("import br.com.datafusion.core.field.INFTableFieldType;\n")
                .append("\n")
                .append("public class " + className + " extends INFEntityTableDefinition {\n")
                .append("\n")
                .append("    public " + className + "(INFDataPackage dataPackage) {\n")
                .append("        super(dataPackage);\n")
                .append("    }\n")
                .append("\n")
                .append("    @Override\n")
                .append("    public INFEntityClass createDefine() {\n")
                .append("\n")
                .append("        this.tableSchema = \"" + schemaName.toUpperCase() + "\";\n")
                .append("        this.tableName = \"" + tableName.toUpperCase() + "\";\n")
                .append("\n");

        for (GRTEntityField field : fields) {
            classBody.append("        addTableField(\"" + field.getFieldName().toUpperCase() + "\", INFTableFieldType." + field.getFieldType().toUpperCase() + "," + field.isIdentifier() + "," + field.isMandatory() + "," + ((GRTEntityTableField) field).getLength() + ");\n");
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
        schemaName = args[0];
        tableName = args[1];
    }

    @Override
    public void addField(String fieldName, String fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length) {
        GRTEntityTableField field = new GRTEntityTableField(length, fieldName, fieldType, isMandatory, isMandatory);
        this.fields.add(field);
    }

}
