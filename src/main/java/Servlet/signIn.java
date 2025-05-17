/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

/**
 *
 * @author Dev khatri
 */
@WebServlet("/signIn")

public class signIn extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        if(new authController().signIn(request)){
            request.removeAttribute("errorMsg");
            response.sendRedirect(request.getContextPath() + "/index");
        }else{
            request.setAttribute("errorMsg", "Wrong credentails");
            RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
            rd.forward(request, response);
        }
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
       
        
        if(request.getSession(false) != null){
        if(request.getSession(false).getAttribute("user") != null || request.getSession(false).getAttribute("company") != null){
//             response.sendRedirect(request.getContextPath() + "/index");
        String previousRequestURL = request.getHeader("referer");
        response.sendRedirect(previousRequestURL);
        System.out.println("previousRequestURL : " + previousRequestURL);
        return;
        }
        }
        RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
        rd.forward(request, response);
    }
}
