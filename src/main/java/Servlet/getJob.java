/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.indexController;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Job;
import model.User;

/**
 *
 * @author Dev khatri
 */
@WebServlet(name = "getJob", urlPatterns = {"/getJob"})
public class getJob extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("id") != null){
        indexController.getJobById(request.getParameter("id"), request);
        if(indexController.getJobById(request.getParameter("id"), request)){
       
       
         
        RequestDispatcher rd = request.getRequestDispatcher("job.jsp");
        rd.forward(request, response);
        return;
       }
       else response.sendRedirect(request.getContextPath() + "/index");
        }
       else response.sendRedirect(request.getContextPath() + "/index");
     }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
