/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;
import Dao.categoryDao;
import java.util.ArrayList;
import model.category;
import model.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Dev khatri
 */
public class categoryDaoImpl implements categoryDao{
    @Override
    public ArrayList<category> getCategories(){
         Session session = hibernate_Util.sessionfactory.getSession();
        Transaction transaction = null;
        ArrayList<category> categories = new ArrayList<>();
        try{
         transaction = session.beginTransaction();
         categories = (ArrayList<category>) session.createQuery("from category",category.class).list();
        }catch(Exception ex){
            if(transaction != null) transaction.rollback();
            System.out.println(ex.getMessage());
        }
        return categories;
    }
}
