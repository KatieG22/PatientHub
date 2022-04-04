package com.patienthub.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

    @Test
    public void testUserPasswordIsHashed() {
        User user = new User();
        user.setPassword("password");
        String hashedPassword = user.hashPassword();
        System.out.println("hashed password is " + hashedPassword);
        assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", hashedPassword);
    }

}
