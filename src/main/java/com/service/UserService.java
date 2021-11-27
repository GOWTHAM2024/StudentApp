package com.service;

import com.dao.UserDao;
import com.model.User;

public class UserService {
    
    private static UserService userService;

    public static UserService getInstance(){
        if (userService==null){
            userService = new UserService(); 
        }
        
        return userService;
    }

    public int add(User user) throws Exception{
        return UserDao.getInstance().save(user);
    }


}
