package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("login");
        PrintWriter out=response.getWriter();
        out.print("<html><body>");
        out.print(("<h1>Welcome "+name+"</h1><br></br>"));
        out.print("<a href='user/viewcourses'>View Courses</a><br>");
        out.print("<a href='logout'>Logout</a>");
        out.print("</body></html>");
    }
    
}
