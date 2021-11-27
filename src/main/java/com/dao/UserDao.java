package com.dao;


import com.model.User;
import com.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDao {
    private static UserDao userDaoImpl;

    //Singleton instance for UserDao
    public static UserDao getInstance(){
        if (userDaoImpl == null){
            userDaoImpl = new UserDao();
        }
        return userDaoImpl;
    }
    //Save user to the Database
    public int save(User user){
        Transaction transaction=null;
        Session session = HibernateUtil.createSession();
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return 1;
        }catch(Exception e){
            System.err.println(e);
            if(transaction !=null)
                transaction.rollback();
            return 0;
            }
        }

    //find user by mailid
    public User findByMailid(String mailid, String password) {
        Session session = HibernateUtil.createSession();
        try {
            Query query = session.createQuery("from User where mailid=:mailid and password=:password");
            query.setParameter("mailid", mailid);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            session.close();
        }
    }
    public int updatePassword(int id,String password,String confirmpassword){
        Session session=HibernateUtil.createSession();
        try{
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("update User set password=:password,confirmpassword=:confirmpassword where id=:id");
            query.setParameter("id",id);
            query.setParameter("password", password);
            query.setParameter("confirmpassword", confirmpassword);
            query.executeUpdate();
            transaction.commit();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e);
            session.beginTransaction().rollback();
            return 0;
        }
        finally
        {
            session.close();
        }
        
    }

    public User findByMailidOnly(String mailid){

        Session session = HibernateUtil.createSession();
        try{
            Query query = session.createQuery("from User where mailid=:mailid");
            query.setParameter("mailid", mailid);
            User user = (User) query.uniqueResult();
            return user;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        finally
        {
            session.close();
        }
    }
}
    //update new password in db
//     public int update(User user1){
//         Session session=HibernateUtil.createSession();
//         try{
//             Transaction transaction=session.beginTransaction();
//             session.update(user1);
//             transaction.commit();
            

//         }catch(Exception e){
//             System.out.println(e);
//             session.beginTransaction().rollback();
//             return 0;
//         }finally{
//             session.close();
//         }
//         return 1;
//     }
// }
