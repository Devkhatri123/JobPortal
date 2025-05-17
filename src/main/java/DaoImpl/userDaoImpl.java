/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Dao.userDao;
import model.User;
import org.hibernate.Session;
import hibernate_Util.sessionfactory;
import java.util.List;
import java.util.Set;
import model.Job;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.authUtil;
import Exception.EmailisAlreadyInUse;

/**
 *
 * @author Dev khatri
 */
public class userDaoImpl implements userDao {

    private Session Session = null;

    @Override
    public boolean createUser(User user) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        try {
            transaction = Session.beginTransaction();
            Session.persist(user);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in userDaoImpl : " + ex.getMessage());
        } finally {
            Session.close();
        }
        return false;
    }

    @Override
    public User createUserJobDetail(User user) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;

        User ExistingUser = null;

        try {
            transaction = Session.beginTransaction();

            ExistingUser = Session.find(User.class, user.getId());
            ExistingUser = user;
            Session.merge(ExistingUser);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                Session.refresh(user);
            }

            System.out.println(ex.getMessage());
        }finally{
        Session.close();
    }
        return ExistingUser;
    }

    @Override
    public User signIn(String email, String password) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = Session.beginTransaction();
            Query query = Session.createQuery("from User where email=:email", User.class);
            query.setParameter("email", email);
            user = (User) query.uniqueResult();
            if(!authUtil.checkhashedPw(password,user.getPassword())) return null;
            user.getAppliedJobs().size();
            user.getSavedJobs().size();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            Session.close();
        }
        return user;
    }

    @Override
    public boolean applyJob(User user) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;

        try {
            transaction = Session.beginTransaction();
            User ExistingUser = Session.find(User.class, user.getId());
            ExistingUser.setAppliedJobs(user.getAppliedJobs());
            Session.merge(ExistingUser);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Session.refresh(user);
            System.out.println("Error While applying job..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return false;
    }

    public boolean saveJob(User loggedInUser) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;

        try {
            transaction = Session.beginTransaction();
            User ExistingUser = Session.find(User.class, loggedInUser.getId());
            ExistingUser.setSavedJobs(loggedInUser.getSavedJobs());
            Session.merge(ExistingUser);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            Session.refresh(loggedInUser);
            System.out.println("Error While saving job..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return false;
    }

    public List<Job> getSavedJob(int userId) {
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        List<Job> savedJobs = null;
        try {
            transaction = Session.beginTransaction();
            Query<Job> query = Session.createQuery(
                    "select j from User u "
                    + "join u.savedJobs j "
                    + "where u.id = :id", Job.class);
            query.setParameter("id", userId);
            savedJobs = query.getResultList();
           

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error While Fetching savedJobs..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return savedJobs;
    }
    public List<Job> getAppliedJobs(int userId){
         Session = sessionfactory.getSession();
        Transaction transaction = null;
        List<Job> appliedJobs = null;
        try {
            transaction = Session.beginTransaction();
            Query<Job> query = Session.createQuery(
                    "select j from User u "
                    + "join u.appliedJobs j "
                    + "where u.id = :id", Job.class);
            query.setParameter("id", userId);
            appliedJobs = query.getResultList();
           

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error While Fetching appliedJobs..." + ex.getMessage());
        } finally {
            Session.close();
        }
        return appliedJobs;
    }
    public User getUserById(int userId){
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        User ExistingUser = null;

        try {
            transaction = Session.beginTransaction();

            ExistingUser = Session.find(User.class, userId);
         } catch (Exception ex) {
           
            System.out.println(ex.getMessage());
        }finally{
        Session.close();
    }
        return ExistingUser;
    }
    public boolean checkemailAlreadyExistsIndb(String email){
        Session = sessionfactory.getSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = Session.beginTransaction();
            Query query = Session.createQuery("from User where email=:email", User.class);
            query.setParameter("email", email);
            user = (User) query.uniqueResult();
            transaction.commit();
            
        } catch (Exception ex) {
           
        } finally {
            Session.close();
        }
        if(user != null) return true;
        return false;
    }
}
