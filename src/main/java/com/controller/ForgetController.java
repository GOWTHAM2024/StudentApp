package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.service.ForgetService;



public class ForgetController extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String mailid = request.getParameter("email");
        String forget=request.getParameter("answer");
        
        
        User user = ForgetService.getInstance().answerValidation(mailid);
         
        if(user!=null)
        {
            if(user.getForgetPassword().equals(forget))
            {
                //out.print("<body>");
                //out.print("<form>");
                //out.println("<input type='hidden' name="id" value="+request.getParameter("value") +">");
                // out.println("<input type='text' name='mailid' value='"+user.getMailid()+"'>");
                // out.println("<input type='text' name='forgetpassword' value='"+user.getForgetPassword()+"'>");
                // out.print("</form>");
                // out.print("</body>");

                request.setAttribute("user", user);
                
                RequestDispatcher rd=request.getRequestDispatcher("/alert.jsp");

                rd.forward(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Your answer is wrong!!!');");
                //out.println("location='index.html';");
                out.println("</script>");out.print("alert('Deleted successfully');");
                RequestDispatcher rd = request.getRequestDispatcher("/check.html");
                rd.include(request, response);
            }
        }
        else{
            out.println("No user is available");
            RequestDispatcher rd = request.getRequestDispatcher("/check.html");
            rd.include(request, response);
        }

      
    }
}
