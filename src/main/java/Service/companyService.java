/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Controller.indexController;
import DaoImpl.companyDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Company;
import model.Job;
import model.User;
import java.util.stream.Collectors;

/**
 *
 * @author Dev khatri
 */
public class companyService {
    private companyDaoImpl compDaoImpl;
    public companyService(){
        this.compDaoImpl = new companyDaoImpl();
    }
    public boolean createAccount(Company company){
        return compDaoImpl.createAccount(company);
    }
     public Company signIn(String email,String password){
        return compDaoImpl.signIn(email, password);
    }
     public boolean updateProfile(Company company){
        return compDaoImpl.updateProfile(company);
    }
    public boolean postJob(Job job){
       return compDaoImpl.postJob(job);
    }
    public List<Job> getPostedJobs(int companyId){
        return compDaoImpl.getPostedJobs(companyId);
    }
    public List<User> getJobApplicants(HttpServletRequest request){
        List<User> JobApplicants =  compDaoImpl.getJobApplicants(Integer.parseInt(request.getParameter("jobId")));
        Job currentJob = indexController.getJobById(Integer.parseInt(request.getParameter("jobId")), request);
        Company loggedInCompany = (Company) request.getSession(false).getAttribute("company");
        // if loggedIn company is the owner of posted job and wants to see job applicants then 
        // show the applicants else don't show the applicants
        
        if(loggedInCompany.getC_id() == currentJob.getCompany().getC_id()){
        return JobApplicants;
       }
        return null;
    }
    public Company getCompanyById(int companyid){
        if(companyid <= 0){
            return null;
        }
        return compDaoImpl.getCompanyById(companyid);
    }
    public List<Job> getCompanypostedJobs(int companyId,List<Job> allJobs){
        List<Job> companyJobs = new ArrayList<>();
       companyJobs = allJobs.stream()
                     .filter(job -> job.getCompany().getC_id() == companyId)
                     .collect(Collectors.toList());
       
        return companyJobs;
    }
}
