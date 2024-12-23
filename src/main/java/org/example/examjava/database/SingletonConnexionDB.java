package org.example.examjava.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {

    private static final Connection connection;

    static {
        String URL = "jdbc:mysql://localhost:3306/exam_java";
        String USER = "root";
        String PASSWORD = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
