/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.factory;

import br.com.datafusion.generator.file.GRTEntityDefinitionFile;

/**
 *
 * @author Matheus
 */
public abstract class GRTEntityDefinitionFileAbstractFactory {

    public abstract GRTEntityDefinitionFile getEntityDefinitionFile(String entityName);
}
