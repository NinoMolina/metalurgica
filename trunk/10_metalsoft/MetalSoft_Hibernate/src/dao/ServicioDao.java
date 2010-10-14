package dao;


import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nino
 */
public abstract class ServicioDao<T> {

    public abstract Session getSession();
    public abstract void setSession(Session s);
    public void save(T a) {
        if(!getSession().isOpen()){
            setSession(getSession().getSessionFactory().openSession());
        }
        getSession().beginTransaction();
        getSession().save(a);
        getSession().getTransaction().commit();
    }

    public void update(T a) {
        if(!getSession().isOpen()){
            setSession(getSession().getSessionFactory().openSession());
        }
        getSession().beginTransaction();
        getSession().update(a);
        getSession().getTransaction().commit();
    }
    public void delete(T a){
        if(!getSession().isOpen()){
            setSession(getSession().getSessionFactory().openSession());
        }
        getSession().beginTransaction();
        getSession().delete(a);
        getSession().getTransaction().commit();
    }
    public List<T> findAll(String nomClase){
        if(!getSession().isOpen()){
            setSession(getSession().getSessionFactory().openSession());
        }
        getSession().beginTransaction();
        Query query = getSession().createQuery("FROM " + nomClase);
        List<T> a =  query.list();
        getSession().getTransaction().commit();
        return a;
    }
    public abstract T findById(Long id, String clase);
}
