/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernate_Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Dev khatri
 */
public class sessionfactory {
    private static SessionFactory factory;
    
    static {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public static Session getSession(){
        Session session = null;
        if(session == null){
             session = factory.openSession();
             return session;
        }
        return null;
    }
}
