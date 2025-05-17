/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DaoImpl.companyDaoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Company;
import model.Job;
import model.User;

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
    public List<User> getJobApplicants(int JobId){
        return compDaoImpl.getJobApplicants(JobId);
    }
    public Company getCompanyById(int companyid){
        if(companyid <= 0){
            return null;
        }
        return compDaoImpl.getCompanyById(companyid);
    }
}
