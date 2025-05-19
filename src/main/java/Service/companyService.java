/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Controller.indexController;
import DaoImpl.companyDaoImpl;
import Exception.IncorrectFileType;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Company;
import model.Job;
import model.User;
import java.util.stream.Collectors;
import javax.servlet.http.Part;
import Exception.IncorrectFileType;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import model.Skill;
import Exception.CharacterLengthExceeded;
import util.authUtil;
/**
 *
 * @author Dev khatri
 */
public class companyService {
    private companyDaoImpl compDaoImpl;
    public companyService(){
        this.compDaoImpl = new companyDaoImpl();
    }
    public boolean createAccount(HttpServletRequest request){
        Company company = new Company();
        company.setC_name(request.getParameter("fullname"));
        company.setC_email(request.getParameter("email"));
        company.setC_password(authUtil.hashPassword(request.getParameter("password")));
        company.setJobs(null);
        if(compDaoImpl.createAccount(company)) {
            if(request.getSession(false) == null) request.getSession().setAttribute("company", company);
            else request.getSession(false).setAttribute("company", company);
            return true;
        }
        return false;
    }
     public Company signIn(String email,String password){
        return compDaoImpl.signIn(email, password);
    }
     public boolean updateProfile(HttpServletRequest request) throws IOException, ServletException, IncorrectFileType{
         Company company = (Company) request.getSession(false).getAttribute("company");
         Company tempCompany = new Company();
         tempCompany.setC_profileImg(company.getC_profileImg());
         tempCompany.setC_password(company.getC_password());
         Part part = request.getPart("imgPath");

        if (part != null && part.getSize() > 0) {
            int dotIndex = part.getSubmittedFileName().indexOf('.');
            String fileExtension = part.getSubmittedFileName().substring(dotIndex + 1);
            if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
                try (InputStream is = part.getInputStream()) {
                    byte[] myImgData = tempCompany.getC_profileImg();
                    byte[] data = is.readAllBytes();
                    if (myImgData == null || !Arrays.equals(myImgData, data)) {
                        tempCompany.setC_profileImg(data);
                    }

                }
            } else {
                throw new IncorrectFileType("Only png, jpeg and jpg images are allowed.");
            }
        }
        
        tempCompany.setC_id(company.getC_id());
        tempCompany.setC_name(request.getParameter("fullname"));
        tempCompany.setC_email(request.getParameter("email"));
        tempCompany.setC_phone(request.getParameter("phone"));
        tempCompany.setC_about(request.getParameter("aboutcompany"));
        tempCompany.setC_address(request.getParameter("Address"));
        tempCompany.setC_websiteurl(request.getParameter("websiteurl"));
        tempCompany.setC_size(request.getParameter("companySize"));
        tempCompany.setC_city(request.getParameter("city"));
        tempCompany.setJobs(company.getJobs());
        if (new companyDaoImpl().updateProfile(tempCompany)) {
            ArrayList<Job> allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
            if(allJobs != null && allJobs.size() > 0){
                List<Job> companyPostedJobs = allJobs.stream()
                        .filter(Job -> Job.getCompany().getC_id() == tempCompany.getC_id())
                        .collect(Collectors.toList());
                if(companyPostedJobs != null && companyPostedJobs.size() > 0){
                    for (Job companyPostedJob : companyPostedJobs) {
                        allJobs.remove(companyPostedJob);
                        companyPostedJob.setCompany(tempCompany);
                        allJobs.add(companyPostedJob);
                    }
                    request.getSession(false).setAttribute("allJobs", allJobs); 
                }
            }
            request.getSession(false).setAttribute("company", tempCompany);
            return true;
        }
        return false;
         
         
     }
    public boolean postJob(HttpServletRequest request) throws CharacterLengthExceeded{
        if(request.getParameter("minidescription").length() > 255){
            throw new CharacterLengthExceeded("Mini description can't be greater than 255 characters.");
        }
        
        
        
        Company loggedInCompany = (Company) request.getSession(false).getAttribute("company");
        List<Job> allJobs = (List<Job>) request.getSession(false).getAttribute("allJobs");
        Job job = new Job();
        
        String[] skills = request.getParameterValues("skill");
        Set<Skill> skillSet = new HashSet<>();
        
         if (skills != null) {
            skillService skillService = new skillService();
            for (String id : skills) {
                int skillId = Integer.parseInt(id);
                Skill skill = skillService.getSkillById(skillId);
                if (skill != null) {
                    skillSet.add(skill);
                }
            }
        }
        
        job.setTitle(request.getParameter("jobtitle"));
        job.setAddress(request.getParameter("location"));
        job.setCategory(request.getParameter("category"));
        job.setType(request.getParameter("jobtype"));
        job.setSalary(request.getParameter("salary"));
        job.setExperience(request.getParameter("experience"));
        job.setDescription(request.getParameter("description"));
        job.setWorkLocation(request.getParameter("workLocation"));
        job.setDeadline(java.sql.Date.valueOf(request.getParameter("deadline")));
        job.setPostedOn(Date.valueOf(LocalDate.now()));
        job.setMinidescription(request.getParameter("minidescription"));
        job.setCompany(loggedInCompany);
        job.setIsOpened(true);
        job.setJobSkills(skillSet);
        boolean result = new companyDaoImpl().postJob(job);
        if(result){
         request.getSession(false).setAttribute("company", loggedInCompany);
         if(allJobs == null) allJobs = new ArrayList<>();
         allJobs.add(job);
         request.getSession(false).setAttribute("allJobs", allJobs);
         return true;
        }
        return false;
    }
    public List<Job> getPostedJobs(int companyId){
        return compDaoImpl.getPostedJobs(companyId);
    }
    public List<User> getJobApplicants(HttpServletRequest request){
        List<User> JobApplicants =  compDaoImpl.getJobApplicants(Integer.parseInt(request.getParameter("jobId")));
        Job currentJob = indexController.getJobById(Integer.parseInt(request.getParameter("jobId")), request);
        Company loggedInCompany = (Company) request.getSession(false).getAttribute("company");
       
        
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
