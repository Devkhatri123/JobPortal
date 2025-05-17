/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.companyController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Dev khatri
 */
@WebServlet(name = "getJobApplicants", urlPatterns = {"/getJobApplicants"})
public class getJobApplicants extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession(false) == null || request.getSession(false).getAttribute("company") == null){
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }
        List<User> applicants = new companyController().getJobApplicants(request);
        if(applicants != null){
            request.setAttribute("applicants", applicants);
            RequestDispatcher rd = request.getRequestDispatcher("JobApplicants.jsp");
            rd.forward(request, response);
        }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
