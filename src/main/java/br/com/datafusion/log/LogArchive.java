/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.log;

import br.com.datafusion.infra.user.UserManager;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 */
public final class LogArchive implements Log {

    private static Path FILE_PATH;
    private static String FILE_NAME;
    private static FileWriter fileWriter;
    private static UserManager userManager;

    public LogArchive() {
        if (FILE_PATH == null) {
            FILE_PATH = Paths.get("").toAbsolutePath();
        }

        if (FILE_NAME == null) {
            FILE_NAME = "logarchive.log";
        }

        if (userManager == null) {
            userManager = UserManager.getInstance(null);
        }

        FILE_PATH = FILE_PATH.resolve(FILE_NAME);
        try {
            if (!Files.exists(FILE_PATH)) {
                // Se o arquivo não existir, cria um novo
                Files.createFile(FILE_PATH);
            }
            if (fileWriter == null) {
                fileWriter = new FileWriter(FILE_NAME, true);
            }
        } catch (IOException ex) {
            Logger.getLogger(LogArchive.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void execute() {
        LogData logData = new LogData();
        //logData.setMessage(message);
        logData.setCriticality("INFO");
        logData.setDateTime(LocalDateTime.now());
        //logData.setUderId(userManager.getCurrentUser().getId());

        try (FileWriter fileWriter = new FileWriter(FILE_PATH.toString(), true)) {
            fileWriter.write(logData.toString() + System.lineSeparator());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(LogArchive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
