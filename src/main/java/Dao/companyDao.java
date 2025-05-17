/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;
import model.Company;
import model.Job;
import model.User;

/**
 *
 * @author Dev khatri
 */
public interface companyDao {
    public boolean createAccount(Company company);
    public Company signIn(String email,String password);
    public boolean updateProfile(Company company);
    public boolean postJob(Job job);
    public List<User> getJobApplicants(int jobid);
    public Company getCompanyById(int companyid);
}
