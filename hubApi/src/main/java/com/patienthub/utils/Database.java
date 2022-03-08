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
    private static String dbUsername;
    private static String dbPassword;
    private static String dbName;

    private Database(DbConfig dbConfig) {
        String driver = null;
        dbUsername = dbConfig.getConfig("dbUsername");
        dbPassword = dbConfig.getConfig("dbPassword");
        dbName = dbConfig.getConfig("dbName");
        try {
            driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            url = String.format("jdbc:mysql://localhost:3306/%s", dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(DbConfig config) {
        // handles creation of connection.
        // ensures just on instance of db is instantiated
        if (db == null) {
            db = new Database(config);
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(db.url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
