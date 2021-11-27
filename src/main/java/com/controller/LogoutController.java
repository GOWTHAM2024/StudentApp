package com.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.mysql.cj.Session;

public class LogoutController extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServerException,IOException{
        response.setContentType("text/html");
        HttpSession session=request.getSession();
        session.removeAttribute("login");
        session.invalidate();
        response.sendRedirect("index.html");
    }
    
}
