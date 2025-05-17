/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;
/**
 *
 * @author Dev khatri
 */
@Entity
@Table(name = "Skill")

public class Skill implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "skillName")
    private String skillName;

    @ManyToMany(mappedBy = "skills")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "Jobskills")
    private Set<Job> jobs = new HashSet<>();
    
    public Set<User> getUsers() {
        return users;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public void setUsers(Set<User> usersList) {
        this.users = usersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
