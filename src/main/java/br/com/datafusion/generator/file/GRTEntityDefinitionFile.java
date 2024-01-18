/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.generator.file;

import br.com.datafusion.generator.field.GRTEntityField;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public abstract class GRTEntityDefinitionFile {

    protected Path path;
    protected String className;
    protected final StringBuilder classBody;
    protected List<GRTEntityField> fields = new ArrayList<>();

    public abstract void createBody();

    public abstract void addEntityName(String... args);

    public abstract void addField(String fieldName, String fieldType, boolean isPrimaryKey, boolean isMandatory, Integer length);

    public GRTEntityDefinitionFile(Path path, String className) {
        this.path = path;
        this.className = className;
        classBody = new StringBuilder();
    }

    public StringBuilder getClassBody() {
        return classBody;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void saveToFile() {
        createBody();

        String arqName = className+".java";
        
        Path fullPath = path.resolve("src").resolve("main").resolve("java").resolve("br").resolve("com").resolve("datafusion").resolve(arqName);

        try {
            // Cria o arquivo se não existir e escreve o conteúdo
            Files.write(fullPath, classBody.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            System.out.println("Arquivo criado e conteúdo gravado com sucesso em: " + fullPath);
        } catch (IOException e) {
            System.err.println("Erro ao criar ou gravar no arquivo: " + e.getMessage());
        }
    }

}
