/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.userController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dev khatri
 */
@WebServlet(name = "applyJob", urlPatterns = {"/applyJob"})
public class applyJob extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession(false) == null){
            response.sendRedirect(request.getContextPath() + "/index");
        }
        int job_id = Integer.parseInt(request.getParameter("id"));
        
        if(new userController().applyJob(request,job_id)){
            System.out.println("Applied to job successfully!!!");
            response.sendRedirect(request.getContextPath() + "/getJob?id="+job_id);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
