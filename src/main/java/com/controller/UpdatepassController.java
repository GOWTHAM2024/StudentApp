package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.model.User;

public class UpdatepassController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        User user = (User) request.getSession().getAttribute("USER");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("newpassword");
        String confirmpassword = request.getParameter("confirmnewpassword");

        try {
            if(password.equals(confirmpassword)){
                int status = UserDao.getInstance().updatePassword(id,password,confirmpassword);
                if (status == 1) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password Updated SuccessFully!!!...');");
                    out.println("location='index.html';");
                    out.println("</script>");
                    //response.sendRedirect(request.getContextPath() + "/login");
                } else {
                    out.println("Failed to update password");
                    response.sendRedirect(request.getContextPath() + "/login");
                }

            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('password and confirmpassword should be same');");
                out.println("location='forget.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
