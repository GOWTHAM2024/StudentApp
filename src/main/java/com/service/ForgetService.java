package com.service;

import com.dao.UserDao;
import com.model.User;

public class ForgetService {
    private static ForgetService forgetService;

    public static ForgetService getInstance(){
        if(forgetService==null){
            forgetService=new ForgetService(); 
        }
        return forgetService;
    }
    public User answerValidation(String mailid){
        User user=UserDao.getInstance().findByMailidOnly(mailid);
        if(user.getMailid().equals(mailid))
        {  return user; }
        else{   return null;  }
        
    }
}
