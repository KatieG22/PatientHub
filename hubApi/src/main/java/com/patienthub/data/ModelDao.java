package com.patienthub.data;

import java.lang.reflect.Field;

import com.patienthub.model.User;

public class ModelDao {
    public static void help() {
        Class c = User.class;
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("The Field is: " + fields[i].getType());
        }
    }
}
