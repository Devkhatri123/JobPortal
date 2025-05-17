/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;
import model.Skill;
import org.hibernate.Session;
import java.util.ArrayList;
import org.hibernate.Transaction;
/**
 *
 * @author Dev khatri
 */
public class skillDaoImpl {
    static Session session = hibernate_Util.sessionfactory.getSession();
    public ArrayList<Skill> getAllSkills(){
        Transaction transaction = null;
        ArrayList<Skill> skills = new ArrayList<>();
        try{
         transaction = session.beginTransaction();
         skills = (ArrayList<Skill>) session.createQuery("from Skill",Skill.class).list();
        
        transaction.commit();
        }catch(Exception ex){
            if(transaction != null) transaction.rollback();
            System.out.println("Error :" + ex.getMessage());
        }
        return skills;
    }
    public Skill getSkillById(int id){
        Transaction transaction = null;
        Skill skill = null;
        try{
            transaction = session.beginTransaction();
            skill = session.get(Skill.class, id);
            transaction.commit();
        }catch(Exception ex){
            if(transaction != null) transaction.rollback();
            System.out.println(ex.getMessage());
        }
        return skill;
    }
}
