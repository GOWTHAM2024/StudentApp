package com.service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.dao.CourseDao;
import com.model.Course;

public class CourseService extends HttpServlet{
    private static final String model = "/user/model";
    public static int duration = 0;
    public static String coursename = null;
    private static CourseService courseService;

    public static CourseService getInstance() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
    }

    public List<Course> viewCourses() {
        try {
            return CourseDao.getInstance().Course();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public int save(Course course) {
        try {
            CourseDao.getInstance().add(course);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    public Course edit(int id) {
        try {
            return CourseDao.getInstance().getDetails(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public int delete(int id) {
        try {
            return CourseDao.getInstance().delete(id);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
}
