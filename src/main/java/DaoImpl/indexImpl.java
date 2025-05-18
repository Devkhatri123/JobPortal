/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import hibernate_Util.sessionfactory;
import java.util.List;
import model.Job;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dev khatri
 */
public class indexImpl implements Dao.index{
    private Session Session = null;
     public List<Job> getJobs(){
          Session = sessionfactory.getSession();
          Transaction transaction = null;
          List<Job> allJobs = null;
         try{
           transaction = Session.beginTransaction();
           allJobs = Session.createQuery("from Job",Job.class).getResultList();
           transaction.commit();
           Session.close();
        }catch(Exception ex){
           if(transaction != null) transaction.rollback();
           System.out.println("Error in IndexDaoImpl : " + ex.getMessage());
       }
       return allJobs;
    }
    public Job getJobById(int Id){
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        Job job = null;
        try{
           transaction = Session.beginTransaction();
           job = (Job) Session.get(Job.class,Id);
           transaction.commit();
           Session.close();
        }catch(Exception ex){
            System.out.println("Exception in getting job : " + ex.getMessage());
        }
        return job;
    }
    @Override
    public void updateJobsStatus(Job Job){
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        Job job = null;
        try{
           transaction = Session.beginTransaction();
           job = (Job) Session.get(Job.class,Job.getJob_id());
           Session.merge(job);
           transaction.commit();
           
        }catch(Exception ex){
            System.out.println("Exception in getting job : " + ex.getMessage());
        }finally{
            Session.close();
        }
    }
}
