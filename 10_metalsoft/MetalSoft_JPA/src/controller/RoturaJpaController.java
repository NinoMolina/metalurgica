/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Rotura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallemantenimientocorrectivo;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class RoturaJpaController {

    public RoturaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rotura rotura) throws PreexistingEntityException, Exception {
        if (rotura.getDetallemantenimientocorrectivoSet() == null) {
            rotura.setDetallemantenimientocorrectivoSet(new HashSet<Detallemantenimientocorrectivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoSet = new HashSet<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetDetallemantenimientocorrectivoToAttach : rotura.getDetallemantenimientocorrectivoSet()) {
                detallemantenimientocorrectivoSetDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoSetDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoSetDetallemantenimientocorrectivoToAttach.getDetallemantenimientocorrectivoPK());
                attachedDetallemantenimientocorrectivoSet.add(detallemantenimientocorrectivoSetDetallemantenimientocorrectivoToAttach);
            }
            rotura.setDetallemantenimientocorrectivoSet(attachedDetallemantenimientocorrectivoSet);
            em.persist(rotura);
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetDetallemantenimientocorrectivo : rotura.getDetallemantenimientocorrectivoSet()) {
                Rotura oldRoturaOfDetallemantenimientocorrectivoSetDetallemantenimientocorrectivo = detallemantenimientocorrectivoSetDetallemantenimientocorrectivo.getRotura();
                detallemantenimientocorrectivoSetDetallemantenimientocorrectivo.setRotura(rotura);
                detallemantenimientocorrectivoSetDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoSetDetallemantenimientocorrectivo);
                if (oldRoturaOfDetallemantenimientocorrectivoSetDetallemantenimientocorrectivo != null) {
                    oldRoturaOfDetallemantenimientocorrectivoSetDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivoSetDetallemantenimientocorrectivo);
                    oldRoturaOfDetallemantenimientocorrectivoSetDetallemantenimientocorrectivo = em.merge(oldRoturaOfDetallemantenimientocorrectivoSetDetallemantenimientocorrectivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRotura(rotura.getIdrotura()) != null) {
                throw new PreexistingEntityException("Rotura " + rotura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rotura rotura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rotura persistentRotura = em.find(Rotura.class, rotura.getIdrotura());
            Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivoSetOld = persistentRotura.getDetallemantenimientocorrectivoSet();
            Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivoSetNew = rotura.getDetallemantenimientocorrectivoSet();
            Set<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoSetNew = new HashSet<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivoToAttach : detallemantenimientocorrectivoSetNew) {
                detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivoToAttach.getDetallemantenimientocorrectivoPK());
                attachedDetallemantenimientocorrectivoSetNew.add(detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivoToAttach);
            }
            detallemantenimientocorrectivoSetNew = attachedDetallemantenimientocorrectivoSetNew;
            rotura.setDetallemantenimientocorrectivoSet(detallemantenimientocorrectivoSetNew);
            rotura = em.merge(rotura);
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetOldDetallemantenimientocorrectivo : detallemantenimientocorrectivoSetOld) {
                if (!detallemantenimientocorrectivoSetNew.contains(detallemantenimientocorrectivoSetOldDetallemantenimientocorrectivo)) {
                    detallemantenimientocorrectivoSetOldDetallemantenimientocorrectivo.setRotura(null);
                    detallemantenimientocorrectivoSetOldDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoSetOldDetallemantenimientocorrectivo);
                }
            }
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo : detallemantenimientocorrectivoSetNew) {
                if (!detallemantenimientocorrectivoSetOld.contains(detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo)) {
                    Rotura oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo = detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo.getRotura();
                    detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo.setRotura(rotura);
                    detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo);
                    if (oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo != null && !oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo.equals(rotura)) {
                        oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo);
                        oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo = em.merge(oldRoturaOfDetallemantenimientocorrectivoSetNewDetallemantenimientocorrectivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rotura.getIdrotura();
                if (findRotura(id) == null) {
                    throw new NonexistentEntityException("The rotura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rotura rotura;
            try {
                rotura = em.getReference(Rotura.class, id);
                rotura.getIdrotura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rotura with id " + id + " no longer exists.", enfe);
            }
            Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivoSet = rotura.getDetallemantenimientocorrectivoSet();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoSetDetallemantenimientocorrectivo : detallemantenimientocorrectivoSet) {
                detallemantenimientocorrectivoSetDetallemantenimientocorrectivo.setRotura(null);
                detallemantenimientocorrectivoSetDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoSetDetallemantenimientocorrectivo);
            }
            em.remove(rotura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rotura> findRoturaEntities() {
        return findRoturaEntities(true, -1, -1);
    }

    public List<Rotura> findRoturaEntities(int maxResults, int firstResult) {
        return findRoturaEntities(false, maxResults, firstResult);
    }

    private List<Rotura> findRoturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rotura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rotura findRotura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rotura.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rotura> rt = cq.from(Rotura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
