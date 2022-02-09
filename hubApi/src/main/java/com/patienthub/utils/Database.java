package com.patienthub.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * class handles creating a datbase connection
 * takes users db variable from projects env
 * only one db connection is established
 */
public class Database {

    private String url;
    private static Database db;
    private Dotenv dotenv;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbName;

    private Database() {
        String driver = null;
        dotenv = Dotenv.load();
        dbUsername = dotenv.get("DB_USERNAME", "username");
        dbPassword = dotenv.get("DB_PASSWORD", "password");
        dbName = dotenv.get("DB_NAME", "patienthub");
        try {
            driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            this.url = String.format("jdbc:mysql://localhost:3306/%s", dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        // handles creation of connection
        if (db == null) {
            db = new Database();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(db.url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection connection) {
        // closes connection
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
