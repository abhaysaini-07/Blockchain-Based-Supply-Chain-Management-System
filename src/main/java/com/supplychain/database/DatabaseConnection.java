package com.supplychain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.supplychain.exception.DatabaseException;

/**
 * Database connection manager
 * Demonstrates: JDBC, Database Connectivity, Singleton Pattern
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/supplychain_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    private DatabaseConnection() {
        // Private constructor for singleton
    }
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     * Get a database connection
     * Demonstrates: JDBC Connection
     */
    public Connection getConnection() throws DatabaseException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to connect to database", e);
        }
    }
    
    /**
     * Close a database connection
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}

