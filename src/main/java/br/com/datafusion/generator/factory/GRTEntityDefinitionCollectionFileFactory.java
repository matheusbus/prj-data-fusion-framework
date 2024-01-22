/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.factory;

import br.com.datafusion.generator.file.GRTEntityDefinitionCollectionFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Matheus
 */
public class GRTEntityDefinitionCollectionFileFactory extends GRTEntityDefinitionFileAbstractFactory {
    
    @Override
    public GRTEntityDefinitionCollectionFile getEntityDefinitionFile(String entityName) {
        Path classPath = Paths.get("").toAbsolutePath();
        String className = "INFDefineCl" + entityName;
        
        return new GRTEntityDefinitionCollectionFile(classPath, className);
    }
    
}
