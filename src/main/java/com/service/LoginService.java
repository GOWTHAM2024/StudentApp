package com.service;

import com.dao.UserDao;
import com.model.User;

public class LoginService {
    
    private static LoginService loginservice;
    
    public static LoginService getInstance(){
        if (loginservice == null){
            loginservice =new LoginService();
        }
        return loginservice;
    }


    public User loginValidation(String mailid, String password){
        User user = UserDao.getInstance().findByMailid(mailid, password);
        if(user.getMailid().equals(mailid) && user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }

}
