/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Nino
 */
public class DaoPedido<T> extends Dao<T>{

    private Session session=HibernateUtil.getSessionFactory().getCurrentSession();

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session s) {
        session=s;
    }

    @Override
    public T findById(Long id, String clase) {
        getSession().beginTransaction();
        Query query = getSession().createQuery("FROM " + clase + " WHERE id=" + id);
        T a = (T) query.list().get(0);
        getSession().getTransaction().commit();
        return a;
    }
}
