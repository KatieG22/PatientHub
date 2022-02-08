package com.patienthub.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class handles creating a datbase connection
 * takes users db variable from projects env
 * only one db connection is established
 */
public class Database {

    private String url;
    private static Database db;

    private Database() {
        String driver = null;
        try {
            driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            this.url = String.format("jdbc:mysql://localhost:3306/%s", "dbName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // handles creation of connection
        if (db == null) {
            db = new Database();
        }
        try {
            return DriverManager.getConnection(db.url, "username", "password");
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void closeConnection(Connection connection) {
        // closes connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
