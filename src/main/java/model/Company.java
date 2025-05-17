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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dev khatri
 */
@Entity
public class Company implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
    @Column(name = "c_name")
    private String c_name;
    @Column(name = "c_email")
    private String c_email;
    @Column(name = "c_password")
    private String c_password;
    @Column(name = "c_websiteURL")
    private String c_websiteurl;
    @Column(name = "c_logo",columnDefinition="LONGBLOB")
    @Lob
    private byte[] c_profileImg;
    @Column(name = "c_address")
    private String c_address;
    @Column(name = "c_phone",length = 20)
    private String c_phone;
    @Column(name="c_city")
    private String c_city;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getC_city() {
        return c_city;
    }

    public void setC_city(String c_city) {
        this.c_city = c_city;
    }

  

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getC_websiteurl() {
        return c_websiteurl;
    }

    public void setC_websiteurl(String c_websiteurl) {
        this.c_websiteurl = c_websiteurl;
    }

    public byte[] getC_profileImg() {
        return c_profileImg;
    }

    public void setC_profileImg(byte[] c_profileImg) {
        this.c_profileImg = c_profileImg;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_about() {
        return c_about;
    }

    public void setC_about(String c_about) {
        this.c_about = c_about;
    }

    public String getC_size() {
        return c_size;
    }

    public void setC_size(String c_size) {
        this.c_size = c_size;
    }
    @Column(name = "c_about",columnDefinition = "text")
    private String c_about;
    @Column (name="c_size")
    private String c_size;
}
