/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.EmailAlreadyInUse;
import Service.companyService;
import Service.userService;
import javax.servlet.http.HttpServletRequest;
import model.Company;
import model.User;
import util.authUtil;

/**
 *
 * @author Dev khatri
 */
public class authController {

    public boolean signUp(HttpServletRequest request) throws EmailAlreadyInUse {
        if (request.getParameter("userType").equals("Applicant")) {
            if (new userService().checkEmailExists(request.getParameter("email"))) {
                throw new EmailAlreadyInUse("Email is already in use");
            }

            if (new userService().createUser(request)) {
                return true;
            }
        } else {
            companyService companyService = new companyService();
            if (companyService.createAccount(request)) {
                return true;
            }
        }
        return false;
    }

    public boolean signIn(HttpServletRequest request) {

        User user = new userService().signIn(request.getParameter("email"), request.getParameter("password"));
        if (user == null) {
            Company company = new companyService().signIn(request.getParameter("email"), request.getParameter("password"));
            if (company != null) {
                if (request.getSession(false) == null) {
                    request.getSession().setAttribute("company", company);
                } else {
                    request.getSession(false).setAttribute("company", company);
                }
                return true;
            } else {
                return false;
            }
        } else {
            request.getSession().setAttribute("user", user);
            return true;
        }
    }
}
