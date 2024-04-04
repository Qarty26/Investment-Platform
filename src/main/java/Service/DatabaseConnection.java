package Service;


import Model.Helpers.AuditEntity;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static final String DB_DRIVER = System.getenv("oracle.jdbc.OracleDriver");
    private static final String DB_URL = System.getenv("jdbc:oracle:thin:@//localhost:1521/orcl");
    private static final String DB_USER = System.getenv("java");
    private static final String DB_PASS = System.getenv("oracle");
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

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static ArrayList<AuditEntity> audit() throws SQLException {
        ArrayList<AuditEntity> result = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    select object_schema,
                           object_name,
                           sql_text
                    from UNIFIED_AUDIT_TRAIL
                    where current_user = 'UTILIZATOR' and UNIFIED_AUDIT_POLICIES = 'AUDIT_ALL_OPERATIONS'""");

            while (rs.next()) {
                AuditEntity entity = new AuditEntity(
                        rs.getString("object_schema"),
                        rs.getString("object_name"),
                        rs.getString("sql_text").replace("\n", " ")
                );

                result.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
