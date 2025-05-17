/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;
import model.Job;

/**
 *
 * @author Dev khatri
 */
public interface index {
    public List<Job> getJobs();
    public void updateJobsStatus(Job Job);
}
