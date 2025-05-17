/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Dao.companyDao;
import hibernate_Util.sessionfactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Company;
import model.Job;
import model.User;
import org.hibernate.query.Query;

/**
 *
 * @author Dev khatri
 */
public class companyDaoImpl implements companyDao {

    private Session Session = null;

    @Override
    public boolean createAccount(Company company) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        try {
            transaction = Session.beginTransaction();
            Session.persist(company);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in companyDaoImpl : " + ex.getMessage());
        } finally {
            Session.close();
        }
        return false;
    }

    @Override
    public Company signIn(String email, String password) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        Company company = null;
        try {
            Query query = Session.createQuery("from Company where c_email=:email and c_password=:password", Company.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            company = (Company) query.uniqueResult();
            System.out.println("company name : " + company.getC_name());
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            Session.close();
        }
        return company;
    }

    public boolean updateProfile(Company company) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        try {
            transaction = Session.beginTransaction();
            Company existingCompany = Session.find(Company.class, company.getC_id());
            existingCompany = company;
            Session.merge(existingCompany);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Session.refresh(company);
            System.out.println("Error in companyDaoImpl : " + ex.getMessage());
        } finally {
            Session.close();
        }
        return false;
    }

    public boolean postJob(Job job) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        try {
            transaction = Session.beginTransaction();
            Session.merge(job);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in companyDaoImpl : " + ex.getMessage());
            // Session.refresh(job);
        } finally {
            Session.close();
        }
        return false;
    }

    public List<Job> getPostedJobs(int companyId) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        List<Job> postedJobs = null;
        try {
            transaction = Session.beginTransaction();
            Query<Job> query = Session.createQuery(
                    "select j from Company C "
                    + "join C.jobs j "
                    + "where C.c_id = :id", Job.class);
            query.setParameter("id", companyId);
            postedJobs = query.getResultList();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error While Fetching appliedJobs..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return postedJobs;
    }

    public List<User> getJobApplicants(int jobid) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        List<User> postedJobs = null;
        try {
            transaction = Session.beginTransaction();
            Query<User> query = Session.createQuery(
                    "select U from User U "
                    + "join U.appliedJobs aj "
                    + "where aj.job_id = :id", User.class);

            query.setParameter("id", jobid);
            postedJobs = query.getResultList();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error While Fetching appliedJobs..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return postedJobs;
    }
    @Override
    public Company getCompanyById(int companyid){
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        Company ExistingCompany = null;

        try {
            transaction = Session.beginTransaction();
            ExistingCompany = Session.find(Company.class, companyid);
         } catch (Exception ex) {
           System.out.println(ex.getMessage());
        }finally{
            
        Session.close();
    }
        return ExistingCompany;
    }
}
