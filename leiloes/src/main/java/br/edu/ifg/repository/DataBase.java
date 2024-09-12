package br.edu.ifg.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is capable to open a database connection.
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class DataBase {

    private static Connection connection;

    private static Properties properties;

    public static Connection getConnection() {
        if(connection != null)
            return connection;
        if(properties == null)
            loadProperties();
        try {
             connection = DriverManager.getConnection(properties.getProperty("database.url"), properties.getProperty("database.userName"), properties.getProperty("database.pass"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            InputStream resourceAsStream = DataBase.class.getClassLoader().getResourceAsStream("leiloes.properties");
            properties.load(resourceAsStream);
            properties.put("database.pass", System.getenv("MYSQL_PASS"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
