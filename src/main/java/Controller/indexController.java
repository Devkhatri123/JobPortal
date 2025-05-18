/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.indexService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Filter;
import model.Job;
import java.util.stream.Collectors;

/**
 *
 * @author Dev khatri
 */
public class indexController {

    public static void getJobs(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        indexService indexService1 = new indexService();
        ArrayList<Job> jobs = (ArrayList<Job>) indexService1.getAllJobs();
        if (session == null) {
            session = request.getSession(true);
        } else {
            session.removeAttribute("allJobs");
        }
        if (jobs != null) {
            session.setAttribute("allJobs", jobs);

        }
    }

    public static boolean getJobById(String jobId, HttpServletRequest request) {
        if(request.getSession(false) == null){
            return false;
        }
        List<Job> allJobs = (List<Job>)request.getSession(false).getAttribute("allJobs");
        List<Job> currentJob = allJobs.stream().filter(job -> job.getJob_id() == Integer.parseInt(jobId)).collect(Collectors.toList());
         if (currentJob != null) {
            request.setAttribute("currentJob", currentJob.get(0));
            return true;
        }
        return false;
    }

     public static Job getJobById(int jobId, HttpServletRequest request) {
       
        List<Job> allJobs = (List<Job>)request.getSession(false).getAttribute("allJobs");
        List<Job> currentJob = allJobs.stream().filter(job -> job.getJob_id() == jobId).collect(Collectors.toList());
         if (currentJob != null) {
            return currentJob.get(0);
        }
        return currentJob.get(0);
    }
    
    
    public static ArrayList<Job> filteredJobs(HttpServletRequest request, ArrayList<Job> allJobs) {

        if (request.getParameter("country").equals("allcountries") && request.getParameterValues("industry") == null && request.getParameterValues("salary") == null && request.getParameterValues("experience") == null && request.getParameterValues("jobType") == null && request.getParameterValues("workLocation") == null) {
            request.setAttribute("filteredJobs", allJobs);
            request.setAttribute("filter", null);
            return null;
        }

        ArrayList<Job> filteredJobs = new ArrayList<>();
        Filter filter = new Filter();
        if (request.getParameterValues("industry") != null) {
            for (String parameterValue : request.getParameterValues("industry")) {
                filter.addCategory(parameterValue);
            }
        }
        if (request.getParameterValues("salary") != null) {
            for (String parameterValue : request.getParameterValues("salary")) {
                filter.addSalary(parameterValue);
            }
        }
        if (request.getParameterValues("experience") != null) {
            for (String parameterValue : request.getParameterValues("experience")) {
                filter.addExperience(parameterValue);
            }
        }
        if (request.getParameterValues("jobType") != null) {
            for (String parameterValue : request.getParameterValues("jobType")) {
                filter.addJobtype(parameterValue);
            }
        }
        if (request.getParameterValues("workLocation") != null) {
            for (String parameterValue : request.getParameterValues("workLocation")) {
                filter.addWorkLocation(parameterValue);
            }
        }
        String selectedCountry = request.getParameter("country");
        filter.setCountry(request.getParameter("country"));
        boolean isAllCountries = selectedCountry != null && selectedCountry.equalsIgnoreCase("allcountries");

        boolean hasCategoryFilter = filter.getCategories() != null && !filter.getCategories().isEmpty();
        boolean hasSalaryFilter = filter.getSalaries() != null && !filter.getSalaries().isEmpty();
        boolean hasExperienceFilter = filter.getExperiences() != null && !filter.getExperiences().isEmpty();
        boolean hasJobTypeFilter = filter.getJobtype() != null && !filter.getJobtype().isEmpty();
        boolean hasWorkLocationFilter = filter.getWorkLocations() != null && !filter.getWorkLocations().isEmpty();
        boolean hasCountryFilter = filter.getCountry() != null && !filter.getCountry().trim().isEmpty() && !isAllCountries;

        for (Job job : allJobs) {
            boolean matched = false;

            // Handle individual filters one by one
            if (hasCategoryFilter && !hasSalaryFilter && !hasExperienceFilter && !hasJobTypeFilter && !hasWorkLocationFilter && !hasCountryFilter) {
                matched = filter.getCategories().contains(job.getCategory());
            } else if (hasSalaryFilter && !hasCategoryFilter && !hasExperienceFilter && !hasJobTypeFilter && !hasWorkLocationFilter && !hasCountryFilter) {
                matched = filter.getSalaries().contains(job.getSalary());
            } else if (hasExperienceFilter && !hasCategoryFilter && !hasSalaryFilter && !hasJobTypeFilter && !hasWorkLocationFilter && !hasCountryFilter) {
                matched = filter.getExperiences().contains(job.getExperience());
            } else if (hasJobTypeFilter && !hasCategoryFilter && !hasSalaryFilter && !hasExperienceFilter && !hasWorkLocationFilter && !hasCountryFilter) {
                matched = filter.getJobtype().contains(job.getType());
            } else if (hasWorkLocationFilter && !hasCategoryFilter && !hasSalaryFilter && !hasExperienceFilter && !hasJobTypeFilter && !hasCountryFilter) {
                matched = filter.getWorkLocations().contains(job.getWorkLocation());
            } else if (hasCountryFilter && !hasCategoryFilter && !hasSalaryFilter && !hasExperienceFilter && !hasJobTypeFilter && !hasWorkLocationFilter) {
                matched = filter.getCountry().trim().equalsIgnoreCase(job.getAddress().trim());
            } else {
                // Handle combination of filters (or all disabled)
                boolean categoryOk = !hasCategoryFilter || filter.getCategories().contains(job.getCategory());
                boolean salaryOk = !hasSalaryFilter || filter.getSalaries().contains(job.getSalary());
                boolean experienceOk = !hasExperienceFilter || filter.getExperiences().contains(job.getExperience());
                boolean jobTypeOk = !hasJobTypeFilter || filter.getJobtype().contains(job.getType());
                boolean workLocationOk = !hasWorkLocationFilter || filter.getWorkLocations().contains(job.getWorkLocation());
                boolean countryOk = !hasCountryFilter || filter.getCountry().trim().equalsIgnoreCase(job.getAddress().trim());

                if (categoryOk && salaryOk && experienceOk && jobTypeOk && workLocationOk && countryOk) {
                    matched = true;
                }
            }

            if (matched) {
                filteredJobs.add(job);
            }
        }

        request.setAttribute("filter", filter);
        request.setAttribute("filteredJobs", filteredJobs);
        return filteredJobs;
    }
    public static List<Job> checkedJobsClosingDate(List<Job> allJobs,HttpServletRequest request){
        List<Job> updatedJobsStatus = new indexService().updateJobsStatus(allJobs);
        return updatedJobsStatus;
       // request.getSession(false).setAttribute("allJobs", updateJobsStatus);
    }
}
