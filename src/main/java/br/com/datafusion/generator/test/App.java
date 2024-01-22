/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.test;

import br.com.datafusion.generator.factory.GRTEntityDefinitionCollectionFileFactory;
import br.com.datafusion.generator.factory.GRTEntityDefinitionFileAbstractFactory;
import br.com.datafusion.generator.factory.GRTEntityDefinitionTableFileFactory;
import br.com.datafusion.generator.file.GRTEntityDefinitionFile;
/**
 *
 * @author Matheus
 */
public class App {
    
    public static void main(String[] args) {
        
        GRTEntityDefinitionFileAbstractFactory factoryTableDefinition = new GRTEntityDefinitionTableFileFactory();
        
        GRTEntityDefinitionFile defineTable = factoryTableDefinition.getEntityDefinitionFile("Usuario");
        defineTable.addEntityName("global", "tbusuario");
        defineTable.addField("usucodigo", "INTEGER", true, true, 0);
        defineTable.addField("usunome", "VARCHAR", false, true, 100);
        defineTable.addField("ususenha", "VARCHAR", false, true, 60);
        defineTable.addField("usuidade", "INTEGER", false, false, 0);
        defineTable.addField("usudatacadastro", "DATE", false, true, 0);
        
        defineTable.saveToFile();
        
        GRTEntityDefinitionFileAbstractFactory factoryCollectionDefinition = new GRTEntityDefinitionCollectionFileFactory();
        
        GRTEntityDefinitionFile defineCollection = factoryCollectionDefinition.getEntityDefinitionFile("GrupoUsuario");
        defineCollection.addEntityName("tbgrupousuario");
        defineCollection.addField("grucodigo", "INTEGER", true, true, 0);
        defineCollection.addField("grudescri", "VARCHAR", false, true, 100);
        
        defineCollection.saveToFile();
        
    } 
}
