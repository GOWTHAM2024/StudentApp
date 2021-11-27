package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.service.LoginService;

public class LoginController extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

            String mailid = request.getParameter("mailid");
            String password = request.getParameter("password");

            if(mailid.equals("") || password.equals("")){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('mailid or password fields should not be blank');");
                out.println("location='index.html';");
                out.println("</script>");
            }
            else{
                try{
                    User roleId = LoginService.getInstance().loginValidation(mailid, password);

                    if (roleId !=null){
                        
                        int roleid=roleId.getRole().iterator().next().getId();
                        if(roleid == 1) {
                            HttpSession session=request.getSession();
                            session.setAttribute("login" , roleId.getMailid());
                            response.sendRedirect("admin");
                        }else if(roleid == 2){
                            HttpSession session=request.getSession();
                            session.setAttribute("login", roleId.getMailid());
                            response.sendRedirect("user");
                        }
                    }else{
                        out.println("<font color=red>Invalid User or Password</font>");
                    }
                }catch(NullPointerException e){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User or Password is incorrect');");
                    out.println("location='index.html';");
                    out.println("</script>");

                }
            }
        }
}