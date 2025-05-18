/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.companyService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.Company;
import Exception.IncorrectFileType;
import java.sql.Date;
import java.time.LocalDate;
import model.Job;
import DaoImpl.companyDaoImpl;
import Service.skillService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.Skill;
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

        Company company = (Company) request.getSession(false).getAttribute("company");

        Part part = request.getPart("imgPath");

        if (part != null && part.getSize() > 0) {
            int dotIndex = part.getSubmittedFileName().indexOf('.');
            String fileExtension = part.getSubmittedFileName().substring(dotIndex + 1);
            if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
                try (InputStream is = part.getInputStream()) {
                    byte[] myImgData = company.getC_profileImg();
                    byte[] data = is.readAllBytes();
                    if (myImgData == null || !Arrays.equals(myImgData, data)) {
                        company.setC_profileImg(data);
                    }

                }
            } else {
                throw new IncorrectFileType("Only png, jpeg and jpg images are allowed.");
            }
        }

        company.setC_name(request.getParameter("fullname"));
        company.setC_email(request.getParameter("email"));
        company.setC_phone(request.getParameter("phone"));
        company.setC_about(request.getParameter("aboutcompany"));
        company.setC_address(request.getParameter("Address"));
        company.setC_websiteurl(request.getParameter("websiteurl"));
        company.setC_size(request.getParameter("companySize"));
        company.setC_city(request.getParameter("city"));
        if (companyService.updateProfile(company)) {
            ArrayList<Job> allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
            if(allJobs != null && allJobs.size() > 0){
                List<Job> companyPostedJobs = allJobs.stream()
                        .filter(Job -> Job.getCompany().getC_id() == company.getC_id())
                        .collect(Collectors.toList());
                if(companyPostedJobs != null && companyPostedJobs.size() > 0){
                    for (Job companyPostedJob : companyPostedJobs) {
                        allJobs.remove(companyPostedJob);
                        companyPostedJob.setCompany(company);
                        allJobs.add(companyPostedJob);
                    }
                    request.getSession(false).setAttribute("allJobs", allJobs); 
                }
            }
            request.getSession(false).setAttribute("company", company);
            return true;
        }
        return false;
    }
    public boolean postJob(HttpServletRequest request){
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
    public List<Job> getPostedJobs(int companyId,HttpServletRequest request){
         return companyService.getPostedJobs(companyId);
    }
    public List<User> getJobApplicants(HttpServletRequest request){
      //  int jobId = Integer.parseInt(request);
        return new companyService().getJobApplicants(request);
    }
    public boolean getCompanyById(HttpServletRequest request){
        if(request.getParameter("companyId") == null) return false;
        Company foundCompany = new companyService().getCompanyById(Integer.parseInt(request.getParameter("companyId")));
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
