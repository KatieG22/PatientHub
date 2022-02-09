package com.patienthub.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ProdDbConfig extends DbConfig {

    public ProdDbConfig() {
        super();
        Dotenv dotenv = Dotenv.load();
        super.addConfig("dbUsername", dotenv.get("DB_USERNAME"));
        super.addConfig("dbPassword", dotenv.get("DB_PASSWORD"));
        super.addConfig("dbName", dotenv.get("DB_NAME"));
    }

    public String toString() {
        return "production";
    }

}