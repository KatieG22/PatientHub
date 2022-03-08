package com.patienthub.utils;

import java.util.AbstractMap;
import java.util.HashMap;

import org.jvnet.hk2.annotations.Contract;

@Contract
public abstract class DbConfig {
    private static HashMap<String, String> config = new HashMap<>();
    private boolean isConfigured = false;

    public boolean hasConfiguration() {
        return isConfigured;
    }

    public AbstractMap<String, String> addConfig(String key, String value) {
        config.put(key, value);
        isConfigured = true;
        return config;
    }

    public String getConfig(String key) {
        return config.get(key);
    }
}