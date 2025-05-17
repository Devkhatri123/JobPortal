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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.IncorrectFileType;


/**
 *
 * @author Dev khatri
 */
@MultipartConfig
@WebServlet(name = "editCompanyProfile", urlPatterns = {"/editCompanyProfile"})
public class editCompanyProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("company") == null || request.getSession(false).getAttribute("user") != null) {
          response.sendRedirect(request.getContextPath() + "/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) != null) {
            try {
                if (new companyController().updateProfile(request)) {
                    response.sendRedirect(request.getContextPath() + "/index");
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("editCompanyProfile.jsp");
                    rd.forward(request, response);
                }
            } catch (IncorrectFileType ex) {
                request.setAttribute("errorMsg", ex.getMessage());
                  RequestDispatcher rd = request.getRequestDispatcher("editCompanyProfile.jsp");
                  rd.forward(request, response);
               // response.sendRedirect(request.getContextPath() + "/editCompanyProfile");

            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
