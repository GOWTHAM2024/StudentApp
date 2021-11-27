package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Role;
import com.model.User;
import com.service.UserService;

public class RegistrationController extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String mailid=request.getParameter("mailid");
        String decider=request.getParameter("decider");
        String password=request.getParameter("password");
        String confirmPassword=request.getParameter("confirmPassword");
        String forgetPassword=request.getParameter("forgetPassword") ;

        User user=new User();
        user.setMailid(mailid);
        user.setPassword(password);
        user.setconfirmPassword(confirmPassword);
        user.setForgetPassword(forgetPassword);

        if(decider.equals("1")){
            Role role=new Role();
            role.setId(1);
            role.setRoleName("admin");
            user.getRole().add(role);
        }else{
            Role role=new Role();
            role.setId(2);
            role.setRoleName("user");
            user.getRole().add(role);
        }

        if(user.getMailid().equals("") || user.getPassword().equals("") || user.getconfirmPassword().equals("") || user.getForgetPassword().equals("")){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Fields Should Not Be Blank');");
            out.println("location='signup.html';");
            out.println("</script>");
        }else if(!user.getPassword().equals(user.getconfirmPassword())){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Password and Confirmpassword must be same');");
            out.println("location='signup.html';");
            out.println("</script>");            
        }else{
            try{
                int status = UserService.getInstance().add(user);
                if(status == 1){
                    out.println("<h2>User Registered Successfully</h2>");
                    out.println("<form action='index.html'>");
                    out.println("<input type='submit' value='login'>");
                    out.println("</form>");
                }else{
                    out.println("<h2>User Already present</h2>");
                    out.println("<form action='signup.html'>");
                    out.println("<input type='submit' value='signup'>");
                    out.println("</form>");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
