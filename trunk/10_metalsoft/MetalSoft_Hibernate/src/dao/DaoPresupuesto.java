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
public class DaoPresupuesto<T> extends Dao<T> {

    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();


    @Override
    public T findById(Long id, String clase) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("FROM " + clase + " WHERE id=" + id);
        List list=query.list();
        if(list==null || list.isEmpty())return null;
        T a = (T) query.list().get(0);
        getSession().getTransaction().commit();
        return a;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session s) {
        session=s;
    }

}
