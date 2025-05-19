/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.companyService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.Company;
import Exception.IncorrectFileType;
import model.Job;
import java.util.List;
import Exception.CharacterLengthExceeded;
import model.User;
/**
 *
 * @author Dev khatri
 */
public class companyController {

    private companyService companyService;

    public companyController() {
        this.companyService = new companyService();
    }

    public boolean updateProfile(HttpServletRequest request) throws IOException, ServletException, IncorrectFileType {

        if (companyService.updateProfile(request)) {
           return true;
        }
        return false;
    }
    public boolean postJob(HttpServletRequest request) throws CharacterLengthExceeded{
        if(companyService.postJob(request)){
            return true;
        }
        return false;
    }
    public List<Job> getPostedJobs(int companyId,HttpServletRequest request){
         return companyService.getPostedJobs(companyId);
    }
    public List<User> getJobApplicants(HttpServletRequest request){
        return companyService.getJobApplicants(request);
    }
    public boolean getCompanyById(HttpServletRequest request){
        if(request.getParameter("companyId") == null) return false;
        Company foundCompany = companyService.getCompanyById(Integer.parseInt(request.getParameter("companyId")));
        if(foundCompany != null){
            request.setAttribute("foundCompany", foundCompany);
            return true;
        }
        return false;
    }
    public boolean getCompanypostedJobs(HttpServletRequest request){
        
       int companyId = Integer.parseInt(request.getParameter("companyId"));
       List<Job> companyPostedJobs = new companyService().getCompanypostedJobs(companyId, (List<Job>)request.getSession(false).getAttribute("allJobs"));
        if(companyPostedJobs != null){
            request.setAttribute("companyPostedJobs", companyPostedJobs);
            return true;
        }
        return false;
    }
}
