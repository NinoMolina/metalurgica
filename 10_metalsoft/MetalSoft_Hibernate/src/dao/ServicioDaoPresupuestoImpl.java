package dao;


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
public class ServicioDaoPresupuestoImpl<T> extends ServicioDao<T> {

    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();


    @Override
    public T findById(Long id, String clase) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("FROM " + clase + " WHERE id=" + id);
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
