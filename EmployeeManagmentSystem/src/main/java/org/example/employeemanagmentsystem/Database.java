package org.example.employeemanagmentsystem;
import java.sql.*;

public class Database {
    public static Connection connectDB(){
        try {
            // Add the MySQL Connector/J library to the project
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
            System.out.println("db connected");
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return null;
    }
}
