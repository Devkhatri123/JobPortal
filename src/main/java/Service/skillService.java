/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import model.Skill;
import java.util.ArrayList;
import DaoImpl.skillDaoImpl;
/**
 *
 * @author Dev khatri
 */
public class skillService{
    public ArrayList<Skill> getSkills(){
        
        skillDaoImpl skillDaoImpl = new skillDaoImpl();
        return skillDaoImpl.getAllSkills();
    }
     public Skill getSkillById(int id){
         return  new skillDaoImpl().getSkillById(id);
     }
}
