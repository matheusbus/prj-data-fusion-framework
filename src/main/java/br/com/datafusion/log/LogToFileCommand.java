/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public final class LogToFileCommand implements LogCommand {

    private static Path FILE_PATH;
    private static String FILE_NAME;

    public LogToFileCommand() {
        if (FILE_PATH == null) {
            FILE_PATH = Paths.get("").toAbsolutePath();
        }

        if (FILE_NAME == null) {
            FILE_NAME = "logarchive.log";
        }

        FILE_PATH = FILE_PATH.resolve(FILE_NAME);
        try {
            if (!Files.exists(FILE_PATH)) {
                // Se o arquivo não existir, cria um novo
                Files.createFile(FILE_PATH);
            }
        } catch (IOException ex) {
            Logger.getLogger(LogToFileCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void execute(LogData logData) {
        Objects.requireNonNull(logData, "LogData should be not null.");
        
        try (FileWriter fileWriter = new FileWriter(FILE_PATH.toString(), true)) {
            fileWriter.write(logData.toString() + System.lineSeparator());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(LogToFileCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
