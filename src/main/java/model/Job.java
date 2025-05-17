/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev khatri
 */
@Entity
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int job_id;
    @Column(name = "title")
    private String title;
    @Column(name = "address")
    private String address;
    @Column(name = "work_location")
    private String workLocation;
    @Column(name = "field")
    private String category;
    @Column(name = "jobtype")
    private String type;
    @Column(name = "salary", nullable = false)
    private String salary;
    @Column(name = "experience")
    private String experience;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users.add(users);
    }
    @ManyToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    @JoinTable(
    name = "job_skills",
    joinColumns = @JoinColumn(name = "job_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> Jobskills = new HashSet<>();
    @Column(name = "postedOn")
    private Date postedOn;
    @Column(name = "deadline")
    private Date deadline;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(name = "is_opened",columnDefinition = "boolean")
    private boolean isOpened;

    @ManyToMany(mappedBy = "appliedJobs")
    private Set<User> users = new HashSet<>();
    @ManyToMany(mappedBy = "savedJobs")
    private Set<User> JobSavedByUsers = new HashSet<>();
    
    public boolean IsOpened() {
        return isOpened;
    }

    public Set<User> getJobSavedByUsers() {
        return JobSavedByUsers;
    }

    public void setJobSavedByUsers(Set<User> JobSavedByUsers) {
        this.JobSavedByUsers = JobSavedByUsers;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
    @Column(name = "mini_description")
    private String minidescription;

    public String getMinidescription() {
        return minidescription;
    }

    public void setMinidescription(String minidescription) {
        this.minidescription = minidescription;
    }
   

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Skill> getJobSkills() {
        return Jobskills;
    }

    public void setJobSkills(Set<Skill> jobSkills) {
        this.Jobskills = jobSkills;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Job{"
                + "job_id=" + job_id
                + ", title='" + title + '\''
                + ", address='" + address + '\''
                + ", workLocation='" + workLocation + '\''
                + ", category='" + category + '\''
                + ", type='" + type + '\''
                + ", salary='" + salary + '\''
                + ", experience='" + experience + '\''
                + ", description='" + description + '\''
                + ", skills=" + Jobskills
                + ", postedOn=" + postedOn
                + ", deadline=" + deadline
                + ", company=" + (company != null ? company.getC_name() : "null")
                + '}';
    }

}
