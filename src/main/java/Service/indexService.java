/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import DaoImpl.indexImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Job;
/**
 *
 * @author Dev khatri
 */
public class indexService {
    private indexImpl indexDaoImpl;
   
    public List<Job> getAllJobs(){
      return new indexImpl().getJobs();
    }
    public Job getJobById(int Id){
        return new indexImpl().getJobById(Id);
    }
    public List<Job> updateJobsStatus(List<Job> allJobs){
        List<Job> newUpdatedJobStatus = new ArrayList<>();
        
        for (Job Job : allJobs) {
         
       if(( Job.getDeadline().compareTo(java.sql.Date.valueOf(LocalDate.now())) == 0 || 
            Job.getDeadline().compareTo(java.sql.Date.valueOf(LocalDate.now())) < 0) && 
            Job.IsOpened() == true){
            new indexImpl().updateJobsStatus(Job);
            Job.setIsOpened(false);
            }
            newUpdatedJobStatus.add(Job);
        }
      return newUpdatedJobStatus;
    }
}
