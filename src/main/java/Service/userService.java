/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import model.User;
import DaoImpl.userDaoImpl;
import java.util.List;
import Exception.EmailisAlreadyInUse;
import model.Job;
/**
 *
 * @author Dev khatri
 */
public class userService{
    
    private userDaoImpl userDaoImpl;
    public userService(){
        this.userDaoImpl = new userDaoImpl();
    }
    
    public boolean createUser(User user){
       return userDaoImpl.createUser(user);
    }
    public User createUserJobDetail(User user){
        return userDaoImpl.createUserJobDetail(user);
    }
    public User signIn(String email,String password){
        return userDaoImpl.signIn(email, password);
    }
    public boolean applyJob(User user){
        return userDaoImpl.applyJob(user);
    }
    public boolean saveJob(User loggedInUser){
        return userDaoImpl.saveJob(loggedInUser);
    }
    public List<Job> getSavedJobs(int userId){
        return userDaoImpl.getSavedJob(userId);
    }
    public List<Job> getAppliedJobs(int userId){
        return userDaoImpl.getAppliedJobs(userId);
    }
    public User getUserById(int userId){
        return userDaoImpl.getUserById(userId);
    }
    public boolean checkEmailExists(String email){
        return userDaoImpl.checkemailAlreadyExistsIndb(email);
    }
}
