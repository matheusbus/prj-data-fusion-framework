/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.validator.specific;

import br.com.datafusion.core.exceptions.INFEntityException;
import br.com.datafusion.core.field.INFField;
import br.com.datafusion.core.field.INFTableFieldImpl;
import br.com.datafusion.core.validator.INFStructureValidatorRelational;
import br.com.datafusion.database.connection.INFConnectionRelational;
import br.com.datafusion.database.connection.INFConnectionType;
import br.com.datafusion.database.pool.INFConnectionPoolRelational;
import br.com.datafusion.database.query.INFQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFStructureValidatorPostgreSQL extends INFStructureValidatorRelational {

    public INFStructureValidatorPostgreSQL(INFConnectionType connectionType, String schemaName, String tableName) throws Exception {
        super(connectionType);
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    protected boolean entityExists() {
        return ((INFConnectionRelational) connection).tableExists(schemaName, tableName);
    }

    @Override
    public boolean correctStructure() throws INFEntityException {
        returnMessage = "";

        if (entityExists()) {

            try {

                INFQuery query = ((INFConnectionRelational) connection).getQuery();

                query.setStatement(getSQLScanFields());
                query.open();

                while (!query.eof()) {

                    boolean fieldExists = false;
                    boolean structureOk = false;
                    INFField actualField = null;

                    String fieldNameDB = query.fieldByIndex(2).asString().toUpperCase();
                    
                    for (INFField f : fields) {

                        actualField = f;
                        String fieldNameDefine = f.getFieldName().toUpperCase();
                        if (fieldNameDefine.equals(fieldNameDB)) {
                            fieldExists = true;
                            f.setFieldExists(fieldExists);

                            String fieldTypeDB = query.fieldByIndex(5).asString();

                            switch (((INFTableFieldImpl) f).getTableFieldType()) {
                                case VARCHAR -> {
                                    structureOk = ((fieldTypeDB.equals("varchar")
                                            || fieldTypeDB.equals("bpchar"))
                                            && query.fieldByIndex(6).asInteger().equals(f.getFieldLength()));
                                }
                                case INTEGER -> {
                                    structureOk = fieldTypeDB.equals("int4");
                                }
                                case SMALLINT -> {
                                    structureOk = ((fieldTypeDB.equals("int2"))
                                            || (fieldTypeDB.equals("_int2")));
                                }
                                case BIGINT -> {
                                    structureOk = fieldTypeDB.equals("int8");
                                }
                                case NUMERIC -> {
                                    structureOk = (fieldTypeDB.equals("numeric")
                                            && (f.getFieldLength().equals(query.fieldByIndex(7).asInteger()))
                                            && (f.getDecimalPlaces().equals(query.fieldByIndex(8).asInteger())));
                                }
                                case BOOLEAN -> {
                                    structureOk = fieldTypeDB.equals("bool");
                                }
                                case DATE -> {
                                    structureOk = fieldTypeDB.equals("date");
                                }
                                case TIME -> {
                                    structureOk = (fieldTypeDB.equals("time") || fieldTypeDB.equals("timetz") || fieldTypeDB.equals("varchar"));
                                }
                                case TIMESTAMP -> {
                                    structureOk = (fieldTypeDB.equals("timestamp") || fieldTypeDB.equals("timestamptz"));
                                }
                                case INTERVAL -> {
                                    structureOk = fieldTypeDB.equals("interval");
                                }
                                case DOUBLE_PRECISION -> {
                                    structureOk = fieldTypeDB.equals("float8");
                                }
                                case TEXT -> {
                                    structureOk = (fieldTypeDB.equals("text") || fieldTypeDB.equals("tsvector"));
                                }
                                case SERIAL -> {
                                    structureOk = fieldTypeDB.equals("int4");
                                }
                                case BLOB -> {
                                    structureOk = fieldTypeDB.equals("bytea");
                                }
                                case JSON -> {
                                    structureOk = fieldTypeDB.equals("json");
                                }
                                default -> {
                                    returnMessage = "The field type is not implemented.";
                                }
                            }

                            if (structureOk) {
                                structureOk = (f.isMandatory()) == !query.fieldByIndex(4).asBoolean();
                            }

                            if (structureOk) {
                                structureOk = f.isPrimaryKey() == query.fieldByIndex(9).asBoolean();
                            }
                            
                            if(structureOk && !f.isPrimaryKey()) {
                                structureOk = f.getDefaultValue().toString().toUpperCase().equals(query.fieldByIndex(3).asString().toUpperCase());
                            }

                            f.setStructureOk(structureOk);
                            break;
                        }

                    }

                    if (!fieldExists) {
                        returnMessage += ("".equals(returnMessage) ? "" : "\n")
                                + "The field [" + fieldNameDB + "] does not exists in the migrator definition.";
                    }

                    if (fieldExists && !actualField.structureOk()) {
                        returnMessage += ("".equals(returnMessage) ? "" : "\n")
                                + "The field [" + fieldNameDB + "] has a different data structure than expected.";
                    }
                }

                for (INFField f : fields) {
                    if (!f.fieldExists()) {
                        returnMessage += ("".equals(returnMessage) ? "" : "\n")
                                + "The field [" + f.getFieldName() + "] does not physically exists in the database.";
                    }
                }

            } catch (Exception ex) {
                returnMessage = ex.getMessage();
            } finally {
                try {
                    connection.getQuery().close();
                } catch (Exception ex) {
                    Logger.getLogger(INFStructureValidatorPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            returnMessage = "Table [" + schemaName + "." + tableName + "] does not exists in database.";
        }

        if(returnMessage.isEmpty()) {
            return true;
        } else {
            throw new INFEntityException(returnMessage);
        }
    }

    @Override
    protected String getSQLScanFields() {
        String dbName = ((INFConnectionPoolRelational) connection.getConnectionPool()).getDbName();
        return """
               SELECT ORDINAL_POSITION,
                      COLUMN_NAME,
                      COLUMN_DEFAULT,
                      CAST(IS_NULLABLE AS VARCHAR(3)) AS IS_NULLABLE,
                      UDT_NAME AS DATA_TYPE,
                      CHARACTER_MAXIMUM_LENGTH,
                      NUMERIC_PRECISION,
                      NUMERIC_SCALE,
                      CASE WHEN POSITION(','||COLUMNS.ORDINAL_POSITION||',' IN
                                         ','||ARRAY_TO_STRING(ARRAY((SELECT ARRAY_TO_STRING(CONKEY,',')
                                                                       FROM PG_CONSTRAINT
                                                                      WHERE CONTYPE = 'p'
                                                                        AND CONRELID = PG_CLASS.OID)),',')||',') > 0
                           THEN 1
                           ELSE 0
                      END AS PK
                 FROM INFORMATION_SCHEMA.COLUMNS
                 LEFT JOIN PG_NAMESPACE ON
                      NSPNAME = TABLE_SCHEMA
                 LEFT JOIN PG_CLASS ON
                      RELNAME = TABLE_NAME AND
                      RELNAMESPACE = PG_NAMESPACE.OID
                WHERE LOWER(TABLE_CATALOG) = LOWER('""" + dbName + "')\n"
                + "   AND LOWER(TABLE_SCHEMA) = LOWER('" + schemaName + "')\n"
                + "   AND LOWER(TABLE_NAME) = LOWER('" + tableName + "')\n"
                + " ORDER BY ORDINAL_POSITION";
    }

}
