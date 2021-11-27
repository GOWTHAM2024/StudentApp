package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.logging.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.model.Course;
import com.service.CourseService;

import java.util.List;

public class CourseController extends HttpServlet{
    
    //private static Logger logger= Logger.getLogger("CourseController class");

    //Path ----Declaration------
    private static final String viewCourses = "/user/viewcourses";
    private static final String yes = "/user/yes";
    private static final String no = "/user/no";
    private static final String adminview = "/admin/viewcourses";
    private static final String add = "/admin/add";
    private static final String save = "/admin/save";
    private static final String edit = "/admin/edit";
    private static final String delete = "/admin/delete";
    
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        processRequest(request,response);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String path = request.getServletPath();
            System.out.println(path);

            switch (path){
                //select course from DB
                case viewCourses:
                    List<Course> course = CourseService.getInstance().viewCourses();
                    System.out.println(request.getContextPath());
                    if(course!=null){
                        out.print("<table border='1' width='100%'");
                        out.print("<tr><th>Course Name</th><th>User Choice</th><th>User Choice</th>");
                        for(Course list:course){
                            out.print("<tr><td>"+list.getCoursename()+"</td><td>"+"<a href='yes'>yes</a>"+"</td><td>"+"<a href='no'>no</a>"+"</td></tr>");
                        }
                    }else{
                        out.print("No Courses Available");
                    }
                    break;
                //if User select a course
                case yes:
                    try{
                        out.print("<h1>Thank you for your interest and the course link will be send to your mailid");
                        out.print("<form action='"+request.getContextPath()+"/user'><br>");
                        out.print("<input type='submit' value='go back'>");
                        out.print("</form>");
                    }catch(Exception e){
                        out.print("<p>Unable to process request ");
                    }
                    break;
                //if User select no course
                case no:
                    response.sendRedirect(request.getContextPath()+"/user");
                    break;
                //admin view course
                case adminview:
                    List<Course> courses=CourseService.getInstance().viewCourses();
                    System.out.println(request.getContextPath());
                    if(courses!=null){
                        out.print("<center><a href='" +request.getContextPath()+ "/admin'>home</a><br>");
                        out.print("<a href='add'>Add Course</a><br>");
                        out.print("<table border='1' width='100%'>");
                        out.print(
                            "<tr><th>Course Name</th><th>Update</th><th>Delete</th></tr>");
                         for(Course list: courses){
                             out.print("<tr><td>"+list.getCoursename()+"</td><td>"+"<a href='edit?id="+list.getId()+"'>edit</a>"
                             +"</td><td>"+"<a href='delete?id="+list.getId()+"'>Delete</a>"+"</td></tr></center>"); 

                         }
                    }else{
                        out.print("no course available");
                    }
                    break;
                        
                //admin add courses to DB
                case add:
                    out.print("<center><body>");
                    out.print("<form action='save' method='POST'>");
                    out.print("<label> Course Name</label><br><br>");
                    out.print("<input type='text' name='coursename' placeholder='Enter Course Name' required><br><br>");
                    out.print("<label>Link</label><br><br>");
                    out.print("<input type='text' name='link' placeholder='Enter the link' required><br><br>");
                    out.print("<input type='submit' value='Add Course'><br><br>");
                    out.print("</form>");
                    out.print("</body></center>");
                    break;

                case save:
                    String coursename = request.getParameter("coursename");
                    String link = request.getParameter("link");

                    Course saveCourse=new Course();
                    saveCourse.setCoursename(coursename);
                    saveCourse.setLink(link);

                    int status=CourseService.getInstance().save(saveCourse);
                    if(status>0){
                        out.print("<p>Course Added Successfully</p>");
                        out.print("<br><br>");
                        out.print("<a href='"+request.getContextPath()+"/admin'>Home</a>");
                        request.getRequestDispatcher("/add").include(request,response);
                        out = response.getWriter();
                        response.setContentType("text/html");
                    }else{
                        out.print("<p>Failed to Add Course</p>");
                        request.getRequestDispatcher("/add").include(request,response);
                    }
                    break;
                
                    case edit:
                        out.print("<h1>Update Course Details</h1>");
                        int id = Integer.parseInt(request.getParameter("id"));
                        System.out.println(id);
                        Course courselist=CourseService.getInstance().edit(id);
                        out.print("<form action='update' method='PUT'>");
                        out.print("<table>");
                        out.print("<tr><td></td><td><input type='hidden' name='id' value='" + courselist.getId()
                        + "'/></td></tr>");
                        out.print("<tr><td>Course Name:</td><td><input type='text' name='course' value='"
                        + courselist.getCoursename() + "'/></td></tr>");
                        out.print("<tr><td>Link:</td><td><input type='text' name='link' value='"
                        + courselist.getLink() + "'/></td></tr>");
                        out.print("</td></tr>");
                        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
                        out.print("</table>");
                        out.print("</form>");
                        break;
                    
                    //delete from database
                    case delete:
                        int courseid=Integer.parseInt(request.getParameter("id"));
                        try{
                            int updateStatus=CourseDao.getInstance().delete(courseid);
                            if(updateStatus>0){
                                response.sendRedirect(request.getContextPath()+"/admin/viewcourses");
                                out.print("alert('Deleted successfully');");
                            }else{
                                out.print("<p>Sorry Failed to Delete </p>");
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }

}

