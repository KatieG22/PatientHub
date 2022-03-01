package com.patienthub.service;

import com.patienthub.data.UserDao;
import com.patienthub.model.User;
import com.patienthub.webexceptions.PasswordMismatch;
import com.patienthub.webexceptions.UserDoesNotExist;

public class UserService {

    public String authenticate(Credentials credential){
        String email = credential.getEmail(); 
        String password = credential.getPassword();

        UserDao userDao = new UserDao();
        User User =userDao.getUserbyEmail(email);
        if(User == null){
            throw new UserDoesNotExist();
        }
      

        if (User.passwordValid(password) == false){
            throw new PasswordMismatch();
        }
        return "";
    }
    
}
