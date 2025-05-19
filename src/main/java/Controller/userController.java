/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.IncorrectFileType;
import Service.userService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.Job;
import model.User;

/**
 *
 * @author Dev khatri
 */
import java.util.List;
import java.util.stream.Collectors;
public class userController {

    private userService userService;

    public userController() {
        this.userService = new userService();
    }

    public boolean updateProfile(HttpServletRequest request) throws IOException, ServletException,IncorrectFileType {
        if (userService.createUserJobDetail(request)) {
            return true;
        }
        return false;
    }
    public boolean applyJob(HttpServletRequest request,int jobId){
        
        if(userService.applyJob(request,jobId)) return true;
        return false;
    }
    public boolean saveJob(HttpServletRequest request){
       
        if(userService.saveJob(request)) return true;
        return false;
    }
    public boolean getUserById(HttpServletRequest request){
        User exsistingUser = userService.getUserById(Integer.parseInt(request.getParameter("userId")));
        if(exsistingUser != null){
            request.setAttribute("applicantProfile", exsistingUser);
            return true;
        } else request.setAttribute("applicantProfile", null);
        return false;
    }
}
