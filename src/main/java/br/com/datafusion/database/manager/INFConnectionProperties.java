/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.database.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author matheus.buschermoehl
 */
public final class INFConnectionProperties {

    private static final Properties properties = new Properties();
    private static final String FILE_PATH = "config.properties";
    private static INFConnectionProperties instance;

    public static INFConnectionProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new INFConnectionProperties();
        }
        return instance;
    }

    private INFConnectionProperties() throws IOException {
        saveProperties();
        loadProperties();
    }

    public Set<Map.Entry<Object, Object>> loadProperties() throws FileNotFoundException, IOException {
        InputStream input = new FileInputStream(FILE_PATH);
        // Load user preferences
        properties.load(input);

        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
        return entrySet;
    }

    public void saveProperties() throws FileNotFoundException, IOException {
        OutputStream output = new FileOutputStream(FILE_PATH);

        // Save user preferences
        properties.store(output, "DataFusion Framework - User Configuration");

        saveMethodProperties("origin", "jdbc:mysql:", "localhost", "joao", "3666", "joao", "12345", INFDatabaseType.MYSQL.toString());
        properties.store(output, "Origin Configuration");

        saveMethodProperties("intermediary", "mongo:mongo", "127.0.0.1", "mymongodb", "27017", "root", "root", INFDatabaseType.MONGODB.toString());
        properties.store(output, "Intermediary Configuration");

        saveMethodProperties("destination", "jdbc:postgresql:", "localhost", "matheus", "5432", "postgres", "postgres", INFDatabaseType.POSTGRESQL.toString());
        properties.store(output, "Destination Configuration");
    }

    public void saveMethodProperties(String method, String dbProtocol, String dbHost, String dbName, String dbPort, String dbUsername, String dbPassword, String dbType) {
        properties.clear();
        properties.setProperty(method + "DBProtocol", dbProtocol);
        properties.setProperty(method + "DBHost", dbHost);
        properties.setProperty(method + "DBName", dbName);
        properties.setProperty(method + "DBPort", dbPort);
        properties.setProperty(method + "DBUsername", dbUsername);
        properties.setProperty(method + "DBPassword", dbPassword);
        properties.setProperty(method + "DBType", dbType);
    }

    public Properties getMethodProperties(String method) {
        Properties p = new Properties();
        p.setProperty(method + "DBProtocol", properties.getProperty(method + "DBProtocol"));
        p.setProperty(method + "DBHost", properties.getProperty(method + "DBHost"));
        p.setProperty(method + "DBName", properties.getProperty(method + "DBName"));
        p.setProperty(method + "DBPort", properties.getProperty(method + "DBPort"));
        p.setProperty(method + "DBUsername", properties.getProperty(method + "DBUsername"));
        p.setProperty(method + "DBPassword", properties.getProperty(method + "DBPassword"));
        p.setProperty(method + "DBType", properties.getProperty(method + "DBType"));
        return p;
    }

}
