/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;
import Exception.EmailisAlreadyInUse;
import java.util.List;
import java.util.Set;
import model.User;
import model.Job;
/**
 *
 * @author Dev khatri
 */
public interface userDao {
    public boolean createUser(User user);
    public User createUserJobDetail(User user);
    public User signIn(String email,String password);
    public boolean applyJob(User user);
    public List<Job> getSavedJob(int userId);
    public List<Job> getAppliedJobs(int userId);
    public User getUserById(int userId);
    public boolean checkemailAlreadyExistsIndb(String email);
}
