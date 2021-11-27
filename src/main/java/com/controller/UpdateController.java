package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.model.Course;

public class UpdateController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPut(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPut(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String CourseName = request.getParameter("course");
            String Link = request.getParameter("link");
            Course course =new Course();
            course.setId(id);
            course.setCoursename(CourseName);
            course.setLink(Link);
            int status = CourseDao.getInstance().update(course);
            if (status > 0) {
                    response.sendRedirect(request.getContextPath() + "/admin/viewcourses");
            } else {
                    out.println("Failed to Update course");
                }
        }catch(Exception e) {
            e.printStackTrace();
        }  
    }

}
