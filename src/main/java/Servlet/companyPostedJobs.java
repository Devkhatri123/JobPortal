/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.companyController;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "companyPostedJobs", urlPatterns = {"/CompanyPostedJobs"})
public class companyPostedJobs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession(false) == null|| request.getParameter("companyId") == null ){
             response.sendRedirect(request.getContextPath()+ "/index");
        } 
           
        else if(new companyController().getCompanypostedJobs(request)){
            RequestDispatcher rd = request.getRequestDispatcher("CompanyPostedJobs.jsp");
            rd.forward(request, response);
        }
        
    }

     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
