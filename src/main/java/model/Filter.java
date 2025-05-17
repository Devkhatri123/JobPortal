/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;


/**
 *
 * @author Dev khatri
 */
public class Filter {
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    private Set<String> jobCategories;
    private Set<String> salaries;
    private Set<String> experiences;
    private Set<String> Jobtype;
    private Set<String> workLocation;

    public Set<String> getWorkLocations() {
        return workLocation;
    }

    public void addWorkLocation(String workLocation) {
        this.workLocation.add(workLocation);
    }

    public Set<String> getExperiences() {
        return experiences;
    }

    public void addExperience(String experience) {
        this.experiences.add(experience);
    }
    
    public Filter(){
        this.jobCategories = new HashSet<>();
        this.salaries = new HashSet<>();
        this.experiences = new HashSet<>();
        this.Jobtype = new HashSet<>();
        this.workLocation = new HashSet<>();
    }

    public Set<String> getJobtype() {
        return Jobtype;
    }

    public void addJobtype(String Jobtype) {
        this.Jobtype.add(Jobtype);
    }

    public void addCategory(String category){
        this.jobCategories.add(category);
    }
    public void addSalary(String salary){
        this.salaries.add(salary);
    }
    public Set<String> getCategories(){
        return jobCategories;
    }
    public Set<String> getSalaries(){
        return salaries;
    }
    
}
