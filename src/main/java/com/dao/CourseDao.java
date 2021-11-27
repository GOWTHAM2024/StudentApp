package com.dao;


import java.util.List;

import com.model.Course;
import com.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CourseDao {
    private static CourseDao CourseDaoImpl;

    //create singleton for CourseDaoImpl
    public static CourseDao getInstance(){
        if(CourseDaoImpl==null){
            CourseDaoImpl =new CourseDao();
        }
        return CourseDaoImpl;
    }

    //Save course to Database
    public int add(Course course) throws Exception{
        Session session=HibernateUtil.createSession();
        try{
            Transaction transaction=session.beginTransaction();
            session.save(course);
            transaction.commit();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            session.beginTransaction().rollback();
            return 0;
        }finally{
            session.close();
        }
    }

    //List Courses
    public List<Course> Course(){
        Session session = HibernateUtil.createSession();
        try{
            Query query = session.createQuery("from Course");
            query.setCacheable(true);
            List<Course> courses=query.list();
            return courses;
        }catch(Exception e){
            System.out.println(e);
            session.close();
            return null;
        }
    }

    //remove course from the Database using id
    public int delete(int id) throws Exception{
        Session session=HibernateUtil.createSession();
        try{
            Course course=session.find(Course.class,id);
            Transaction transaction=session.beginTransaction();
            session.delete(course);
            transaction.commit();
            return 1;
        }catch(Exception e){
            System.out.println(e);
            session.beginTransaction().rollback();
            return 0;
        }finally{
            session.close();
        }
    }
    //get details from Database using id
    public Course getDetails(int id) throws Exception{
        Session session=HibernateUtil.createSession();
        try{
            Course course = session.get(Course.class,id);
            return course;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            session.close();
        }
    }
    //update Course
    public int update (Course course){
        Session session = HibernateUtil.createSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e);
            session.beginTransaction().rollback();
            return 0;
        }finally {
            session.close();
        }
        return 1;
    }
}
