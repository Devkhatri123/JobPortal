/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Dev khatri
 */
@Entity
@Table(name="userJobDetail")
public class userJobDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "experience")
    private String experience;
    @Column(name = "category")
    private String category;
    @Column(name = "workType")
    private String workType;
    @Column(name = "workLocation")
    private String workLocation;
    @Column(name = "about_experience")
    private String Aboutexperience;
    public String getSalary_range() {
        return salary_range;
    }

    public String getAboutexperience() {
        return Aboutexperience;
    }

    public void setAboutexperience(String Aboutexperience) {
        this.Aboutexperience = Aboutexperience;
    }

    public void setSalary_range(String salary_range) {
        this.salary_range = salary_range;
    }
     @Column(name="salary_range")
    private String salary_range;
    @OneToOne(mappedBy =  "userJobDetail")
    private User user;

    public User getUser() {
        return user;
    }
    @Lob
    @Column(name = "resume",columnDefinition = "LONGBLOB")
    private byte[] resume;
    @Column(name = "resume_name",columnDefinition = "TEXT")
    private String resumeName;

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
}
