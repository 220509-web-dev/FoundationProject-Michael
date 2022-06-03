package com.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static final Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.err.println("Could not load DB credentials from properties file.");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find and load DB driver");
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){

        try {
            Connection connection = DriverManager.getConnection(props.getProperty("db-url"),
                    props.getProperty("db-username"),
                    props.getProperty("db-password"));
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not establish a connection to the database");
        }
    }
}