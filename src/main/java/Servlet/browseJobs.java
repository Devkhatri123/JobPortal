/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.indexController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Job;

/**
 *
 * @author Dev khatri
 */
@WebServlet(name = "browseJobs", urlPatterns = {"/browseJobs"})
public class browseJobs extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession(false) == null){
             response.sendRedirect(request.getContextPath() + "/index");
             return;
        }
        ArrayList<Job> allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
        new indexController().filteredJobs(request, allJobs);
        
        RequestDispatcher rd = request.getRequestDispatcher("browseJobs.jsp");
        rd.forward(request, response);
    }


}
