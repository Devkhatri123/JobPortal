/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.companyController;
import Controller.userController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Company;
import model.User;

/**
 *
 * @author Dev khatri
 */
@WebServlet(name = "getProfile", urlPatterns = {"/getProfile"})
public class getProfile extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            if (request.getSession(false) == null) {
               response.sendRedirect(request.getContextPath() + "/index");
               return;
            }
            if(request.getParameter("userId") != null){
                if(new userController().getUserById(request)){
                 RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                 rd.forward(request, response);
                 return;
                }
            }else if(request.getParameter("companyId") != null){
                if(new companyController().getCompanyById(request)){
                    RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                    rd.forward(request, response);
                    return;
                }
            }
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
