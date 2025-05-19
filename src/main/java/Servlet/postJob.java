/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.companyController;
import Service.skillService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Skill;
import Exception.CharacterLengthExceeded;

/**
 *
 * @author Dev khatri
 */
@WebServlet("/postJob")
public class postJob extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession(false) != null && request.getSession(false).getAttribute("company") != null){
            ArrayList<Skill> skills_Array = new skillService().getSkills();
            request.setAttribute("skills", skills_Array);
            
            RequestDispatcher rd = request.getRequestDispatcher("postJob.jsp");
            rd.forward(request, response);
            return;
        }
           
        
            response.sendRedirect(request.getContextPath() + "/index");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            companyController companyController = new companyController();
            try{
            if(companyController.postJob(request)){
            response.sendRedirect(request.getContextPath() + "/index");
            }
            }catch(CharacterLengthExceeded ex){
            request.setAttribute("errorMsg", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("postJob.jsp");
            rd.forward(request, response);

            }
    }

}
