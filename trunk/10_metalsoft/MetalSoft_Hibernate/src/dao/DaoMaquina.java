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
public class DaoMaquina extends Dao{

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
    public Object findById(Long id, String clase) {
        return null;
    }

}
