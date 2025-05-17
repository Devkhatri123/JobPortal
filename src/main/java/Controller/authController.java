/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.EmailisAlreadyInUse;
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
     public boolean signUp(HttpServletRequest request) throws EmailisAlreadyInUse{
        if(request.getParameter("userType").equals("Applicant")){
            
           if(new userService().checkEmailExists(request.getParameter("email"))){
               throw new EmailisAlreadyInUse("Email is already in use");
           }
           
           User user = new User();
           user.setFullName(request.getParameter("fullname"));
           user.setEmail(request.getParameter("email"));
           user.setPassword(authUtil.hashPassword(request.getParameter("password")));
           user.setUserJobDetail(null);
           user.setAppliedJobs(null);
           
           
            userService userService = new userService();
            if(userService.createUser(user)){
            if(request.getSession(false) == null)
            request.getSession().setAttribute("user", user);
            else request.getSession(false).setAttribute("user", user);
            return true;
 }
       }else{
            Company company = new Company();
            company.setC_name(request.getParameter("fullname"));
            company.setC_email(request.getParameter("email"));
            company.setC_password(request.getParameter("password"));
            company.setJobs(null);
            companyService companyService = new companyService();
            if(companyService.createAccount(company)){
            if(request.getSession(false) == null) request.getSession().setAttribute("company", company);
            else request.getSession(false).setAttribute("company", company);
            return true;
            }
        }
        return false;
    }
     public boolean signIn(HttpServletRequest request){
       
     User user = new userService().signIn(request.getParameter("email"), request.getParameter("password"));
     if(user == null){
       Company company = new companyService().signIn(request.getParameter("email"), request.getParameter("password"));
       if(company != null){
           if(request.getSession(false) == null) request.getSession().setAttribute("company", company);
           else request.getSession(false).setAttribute("company", company);
       return true;
    } else return false;
     }else{
       request.getSession().setAttribute("user", user); 
       return true;
     }
    }
}
