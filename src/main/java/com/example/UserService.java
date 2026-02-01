package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // VULNERABILITY: SQL Injection
    public void findUser(String username) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
             Statement st = conn.createStatement()) {

            String query = "SELECT * FROM users WHERE name = '" + username + "'";

            st.executeQuery(query);
        }
    }

    // SMELL: Unused method
    public void notUsed() {
        logger.info("I am never called");
    }

    // EVEN WORSE: another SQL injection
    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", password);
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, username);
            st.execute();
        }
    }
}
