package com.patienthub.data;

import static org.junit.Assert.assertNotEquals;

import com.patienthub.model.User;

import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testUserPasswordIsHashedInDB() {
        User user = new User("ragnar", "lothbrok", "+35399000", "ragnar@gmail.com", "000009988", "male");
        user.setPassword("password");
        UserDao userDao = new UserDao();
        userDao.save(user);
        User dbUser = userDao.getByPpsNumber(user.getPps());
        userDao.delete(user);
        assertNotEquals(user.getPassword(), dbUser.getPassword());

    }

}
