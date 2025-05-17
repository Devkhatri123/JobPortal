
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.userController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Service.skillService;
import model.Skill;
import java.util.ArrayList;
import Service.categoryService;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.category;
import Exception.IncorrectFileType;

/**
 *
 * @author Dev khatri
 */
@MultipartConfig
@WebServlet(name = "editUserProfile", urlPatterns = {"/editUserProfile"})
public class editUserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession(false) == null) {
             response.sendRedirect(request.getContextPath() + "/index");
            return;
        }
        ArrayList<Skill> skills_Array = new skillService().getSkills();
        ArrayList<category> categories_ArrayList = new categoryService().getCategories();

        request.setAttribute("skills", skills_Array);

        request.setAttribute("catogries", categories_ArrayList);

        RequestDispatcher rd = request.getRequestDispatcher("editUserProfile.jsp");
        rd.forward(request, response);
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
         if (new userController().updateProfile(request)) {
             System.out.println("User updated...");
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }else  System.out.println("User not updated...");
        }catch(IncorrectFileType ex){
             request.getSession(false).setAttribute("errorMsg", ex.getMessage());
             System.out.println("errorMsg :" + request.getSession(false).getAttribute("errorMsg"));
             response.sendRedirect(request.getContextPath() + "/editUserProfile");
        }
    }

}
