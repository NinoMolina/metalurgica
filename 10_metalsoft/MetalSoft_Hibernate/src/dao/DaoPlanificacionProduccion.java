/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import org.hibernate.Session;

/**
 *
 * @author Nino
 */
public class DaoPlanificacionProduccion<T> extends Dao<T>{

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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
