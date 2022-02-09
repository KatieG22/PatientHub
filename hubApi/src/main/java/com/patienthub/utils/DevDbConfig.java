package com.patienthub.utils;

import org.jvnet.hk2.annotations.Service;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class DevDbConfig extends DbConfig {
    public DevDbConfig() {
        super();
        Dotenv dotenv = Dotenv.load();
        super.addConfig("dbUsername", dotenv.get("DB_USERNAME"));
        super.addConfig("dbPassword", dotenv.get("DB_PASSWORD"));
        super.addConfig("dbName", dotenv.get("DB_NAME") + "_test");
    }

    public String toString() {
        return "development";
    }

}
