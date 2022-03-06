package com.patienthub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.patienthub.data.UserDao;
import com.patienthub.model.Credentials;
import com.patienthub.model.Gender;
import com.patienthub.model.User;

import org.junit.Before;
import org.junit.Test;

public class AuthServiceTest {

    private User user;
    private UserDao userDao = new UserDao();;

    @Before
    public void setUp() {
        user = new User("virgina", "wolf", "000000000", "v@gmail.com", "99999999999",
                Gender.MALE);
        user.setPassword("password");
        userDao.save(user);

    }

    @Test
    public void testUserCanGetTokenWithValidDetails() {
        AuthService authService = new AuthService();
        String email = user.getEmail();
        String password = user.getPassword();
        String token = authService.authenticate(new Credentials(email, password));
        assertEquals(token.getClass(), String.class);

    }

}
