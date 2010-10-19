package dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Nino
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory=new Configuration().configure(new File("D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\MetalSoft_Hibernate\\src\\hibernate.cfg.xml")).buildSessionFactory();
//            sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void limpiarSession(){
        if(sessionFactory==null || sessionFactory.getCurrentSession()==null)return;
        sessionFactory.getCurrentSession().clear();
    }
}
