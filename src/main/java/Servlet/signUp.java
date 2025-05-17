/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.authController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.EmailisAlreadyInUse;

/**
 *
 * @author Dev khatri
 */
@WebServlet("/signUp")
public class signUp extends HttpServlet {

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try{
        if(new authController().signUp(request)){
           response.sendRedirect(request.getContextPath() + "/index");
       }
        }catch(EmailisAlreadyInUse ex){
            request.setAttribute("errorMsg", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("signUp.jsp");
            rd.forward(request, response);
        }

}

       
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
   if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null || request.getSession(false) != null && request.getSession(false).getAttribute("company") != null){
        response.sendRedirect(request.getContextPath() + "/index");
     
 }else{
       RequestDispatcher rd = request.getRequestDispatcher("/signUp.jsp");
       rd.forward(request, response);
   }
}

}
