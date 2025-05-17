
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.indexController;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "index", urlPatterns = {"/index"})
public class index extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       if(request.getSession(false)==null) indexController.getJobs(request);
        RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
        rd.forward(request, response);
    }
    @Override
    public void destroy() {
        System.out.println("destroyed...");
    }
}