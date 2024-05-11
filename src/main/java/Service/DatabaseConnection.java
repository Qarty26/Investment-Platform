package Service;


import Model.Helpers.AuditEntity;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnection {

    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String DB_USER = "java";
    private static final String DB_PASS = "oracle";
    public static Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection(){

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getInstance(){
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

}
