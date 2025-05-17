/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev khatri
 */
@Entity
@Table(name = "User")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "u_fullname" ,length = 50)
    private String fullName;
    @Column(name = "u_email",unique = true,length = 25)
    private String email;
    @Column(name = "u_password",unique = true,columnDefinition = "text")
    private String password;
    @Column(name = "u_gender",length = 10)
    private String gender;
    @Lob
    @Column(name = "u_profileImage_data",columnDefinition = "LONGBLOB")
    private byte[] profileImg;
    @Column(name = "u_address",length = 100)
    private String address;
    @Column(name = "u_phonenumber",length = 11)
    private String phoneNumber;
    @Column(name = "u_education",length = 20)
    private String education;
    @Column(name = "u_aboutme",columnDefinition = "TEXT")
    private String aboutMe;
    @Column(name = "u_languages")
    private String language ;
    @Column(name = "u_city")
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
    name = "user_skills",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "user_applied_jobs",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<Job> appliedJobs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "user_saved_jobs",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<Job> savedJobs = new HashSet<>();

    public Set<Job> getSavedJobs() {
        return savedJobs;
    }

    public void setSavedJobs(Set<Job> savedJobs) {
        this.savedJobs = savedJobs;
    }
    
    public Set<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(Set<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }
    


    public Set<Skill> getUserSkills() {
        return skills;
    }

    public void setUserSkills(Set<Skill> userSkills) {
        this.skills = userSkills;
    }

    public String getAboutEducation() {
        return aboutEducation;
    }

    public void setAboutEducation(String aboutEducation) {
        this.aboutEducation = aboutEducation;
    }

    public userJobDetail getUserJobDetail() {
        return userJobDetail;
    }

    public void setUserJobDetail(userJobDetail userJobDetail) {
        this.userJobDetail = userJobDetail;
    }
    @Column(name = "u_abouteducation",columnDefinition = "TEXT")
    private String aboutEducation;
    
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "jobdetail_id")
    private userJobDetail userJobDetail;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
   // public void setImageByteArrayLength(byt)
}
