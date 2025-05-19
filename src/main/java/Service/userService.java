/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.User;
import DaoImpl.userDaoImpl;
import java.util.List;
import Exception.EmailAlreadyInUse;
import Exception.IncorrectFileType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.Job;
import model.Skill;
import model.userJobDetail;
import util.authUtil;
/**
 *
 * @author Dev khatri
 */
public class userService{
    
    private userDaoImpl userDaoImpl;
    public userService(){
        this.userDaoImpl = new userDaoImpl();
    }
    
    public boolean createUser(HttpServletRequest request){
        User user = new User();
        user.setFullName(request.getParameter("fullname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(authUtil.hashPassword(request.getParameter("password")));
        user.setUserJobDetail(null);
        user.setAppliedJobs(null);
        if(userDaoImpl.createUser(user)){
        if(request.getSession(false) == null)
        request.getSession().setAttribute("user", user);
        else request.getSession(false).setAttribute("user", user);
        return true;
        }
        return false;
    }
    public boolean createUserJobDetail(HttpServletRequest request)throws IOException, ServletException,IncorrectFileType{
          String[] skillIds = request.getParameterValues("skill");
        Set<Skill> skillIdsSet = new HashSet<>();
        User loggedInUser = (User) request.getSession(false).getAttribute("user");
        if(loggedInUser.getUserJobDetail() == null){
            loggedInUser.setUserJobDetail(new userJobDetail());
        }else  loggedInUser.setUserJobDetail(loggedInUser.getUserJobDetail());
          Part part = request.getPart("imgPath");

            if (part != null && part.getSize() > 0) {
                int dotIndex = part.getSubmittedFileName().indexOf('.');
                 String fileExtension = part.getSubmittedFileName().substring(dotIndex + 1);
                if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
                    try (InputStream is = part.getInputStream()) {
                        byte[] myImgData = loggedInUser.getProfileImg();
                        byte[] data = is.readAllBytes();
                        if (myImgData == null || !Arrays.equals(myImgData, data)) {
                            loggedInUser.setProfileImg(data);
                        }
                    }
                } else {
                    throw new IncorrectFileType("Only png, jpeg and jpg images are allowed. ");
                }

            }

            part = request.getPart("resumePath");

            if (part != null && part.getSize() > 0) {
                int dotIndex = part.getSubmittedFileName().indexOf('.');
                 String fileExtension = part.getSubmittedFileName().substring(dotIndex + 1);
                if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")) {
                    try (InputStream is = part.getInputStream()) {
                        byte[] myImgData = loggedInUser.getUserJobDetail().getResume();
                        byte[] data = is.readAllBytes();
                        if (myImgData == null || !Arrays.equals(myImgData, data)) {
                            loggedInUser.getUserJobDetail().setResume(data);
                            
                        }
                        loggedInUser.getUserJobDetail().setResumeName(part.getSubmittedFileName());
                    }
                } else {
                    throw new IncorrectFileType("Only png, jpeg and jpg images are allowed. ");
                }

            }
            
        if (skillIds != null) {
            skillService skillService = new skillService();
            for (String id : skillIds) {
                int skillId = Integer.parseInt(id);
                Skill skill = skillService.getSkillById(skillId);
                if (skill != null) {
                    skillIdsSet.add(skill);
                }
            }
        }
        loggedInUser.setUserSkills(skillIdsSet);

        loggedInUser.setFullName(request.getParameter("fullname"));
        loggedInUser.setEmail(request.getParameter("email"));
        loggedInUser.setPhoneNumber(request.getParameter("phone"));
        loggedInUser.setAboutMe(request.getParameter("aboutme"));
        loggedInUser.setEducation(request.getParameter("education"));
        loggedInUser.setAboutEducation(request.getParameter("abouteducation"));
        loggedInUser.setGender(request.getParameter("gender"));
        loggedInUser.setLanguage(request.getParameter("language"));
        loggedInUser.setAddress(request.getParameter("Address"));
        loggedInUser.setCity(request.getParameter("city"));

        
        
         loggedInUser.getUserJobDetail().setCategory(request.getParameter("category"));
         loggedInUser.getUserJobDetail().setExperience(request.getParameter("Experience"));
         loggedInUser.getUserJobDetail().setWorkLocation(request.getParameter("WorkLocation"));
         loggedInUser.getUserJobDetail().setWorkType(request.getParameter("WorkType"));
         loggedInUser.getUserJobDetail().setSalary_range(request.getParameter("salary"));
         loggedInUser.getUserJobDetail().setAboutexperience(request.getParameter("aboutexperience"));

         loggedInUser.setUserJobDetail(loggedInUser.getUserJobDetail());
        
        
         User updatedUser = userDaoImpl.createUserJobDetail(loggedInUser);
        if (updatedUser != null) {
            request.getSession(false).setAttribute("user", updatedUser);
            return true;
        }
        return false;
        
     }
    public User signIn(String email,String password){
        return userDaoImpl.signIn(email, password);
    }
    public boolean applyJob(HttpServletRequest request,int jobId){
        ArrayList<Job> allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
        List<Job> currentJob = allJobs.stream().filter(Job -> Job.getJob_id() == jobId).collect(Collectors.toList());
        
        User loggedInUser = (User) request.getSession(false).getAttribute("user");
        
        Set<Job> jobSet = loggedInUser.getAppliedJobs();
        
         if(loggedInUser.getAppliedJobs() != null && loggedInUser.getAppliedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.get(0).getJob_id()) == true){
           jobSet = jobSet.stream()
                   .filter(job -> job.getJob_id() != currentJob.get(0).getJob_id())
                   .collect(Collectors.toSet());
                
        }else{
            jobSet.add(currentJob.get(0));
         }
        loggedInUser.setAppliedJobs(jobSet);
        return userDaoImpl.applyJob(loggedInUser);
    }
    public boolean saveJob(HttpServletRequest request){
        int jobId =Integer.parseInt(request.getParameter("id"));
        ArrayList<Job> allJobs = (ArrayList<Job>) request.getSession(false).getAttribute("allJobs");
        List<Job> currentJob = allJobs.stream().filter(Job -> Job.getJob_id() == jobId).collect(Collectors.toList());
        
        User loggedInUser = (User) request.getSession(false).getAttribute("user");
        Set<Job> savedjobSet = loggedInUser.getSavedJobs();
         if(loggedInUser.getSavedJobs() != null && loggedInUser.getSavedJobs().stream().anyMatch(job -> job.getJob_id() == currentJob.get(0).getJob_id()) == true){
           savedjobSet = savedjobSet.stream()
                   .filter(job -> job.getJob_id() != currentJob.get(0).getJob_id())
                   .collect(Collectors.toSet());
                
        }else savedjobSet.add(currentJob.get(0));
        loggedInUser.setSavedJobs(savedjobSet);
        return userDaoImpl.saveJob(loggedInUser);
    }
    public List<Job> getSavedJobs(int userId){
        return userDaoImpl.getSavedJob(userId);
    }
    public List<Job> getAppliedJobs(int userId){
        return userDaoImpl.getAppliedJobs(userId);
    }
    public User getUserById(int userId){
        return userDaoImpl.getUserById(userId);
    }
    public boolean checkEmailExists(String email){
        return userDaoImpl.checkemailAlreadyExistsIndb(email);
    }
}
