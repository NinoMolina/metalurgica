/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metalsoft.datos.jpa.entity.Accioncalidad;

/**
 *
 * @author Nino
 */
public class JpaUtil {
    
    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
        return emf.createEntityManager();
    }

    public static List query(String query, Class clase){
        EntityManager em=JpaUtil.getEntityManager();
        TypedQuery q=em.createQuery(query, clase);
        return q.getResultList();
    }

    public static Query getNamedQuery(String name){
        return getEntityManager().createNamedQuery(name);
    }
}
