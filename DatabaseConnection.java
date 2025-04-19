package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String URL = "jdbc:mysql://localhost:3306/booking";
    private static final String USER = "root";        // ou autre user MySQL
    private static final String PASSWORD = "";        // si vous avez un mot de passe

    public static Connection getConnection() throws SQLException {
        try {
            // Charge le driver MySQL (peut être facultatif selon la version JDBC).
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
