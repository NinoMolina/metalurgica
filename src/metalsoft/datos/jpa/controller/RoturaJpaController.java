/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Rotura;

/**
 *
 * @author Nino
 */
public class RoturaJpaController implements Serializable {

    public RoturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rotura rotura) {
        if (rotura.getDetallemantenimientocorrectivoList() == null) {
            rotura.setDetallemantenimientocorrectivoList(new ArrayList<Detallemantenimientocorrectivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoList = new ArrayList<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach : rotura.getDetallemantenimientocorrectivoList()) {
                detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach.getIddetalle());
                attachedDetallemantenimientocorrectivoList.add(detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach);
            }
            rotura.setDetallemantenimientocorrectivoList(attachedDetallemantenimientocorrectivoList);
            em.persist(rotura);
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListDetallemantenimientocorrectivo : rotura.getDetallemantenimientocorrectivoList()) {
                Rotura oldRoturaOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo = detallemantenimientocorrectivoListDetallemantenimientocorrectivo.getRotura();
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo.setRotura(rotura);
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                if (oldRoturaOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo != null) {
                    oldRoturaOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                    oldRoturaOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo = em.merge(oldRoturaOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                }
            }
            em.getTransaction().commit();
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
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoListOld = persistentRotura.getDetallemantenimientocorrectivoList();
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoListNew = rotura.getDetallemantenimientocorrectivoList();
            List<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoListNew = new ArrayList<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach : detallemantenimientocorrectivoListNew) {
                detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach.getIddetalle());
                attachedDetallemantenimientocorrectivoListNew.add(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach);
            }
            detallemantenimientocorrectivoListNew = attachedDetallemantenimientocorrectivoListNew;
            rotura.setDetallemantenimientocorrectivoList(detallemantenimientocorrectivoListNew);
            rotura = em.merge(rotura);
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo : detallemantenimientocorrectivoListOld) {
                if (!detallemantenimientocorrectivoListNew.contains(detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo)) {
                    detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo.setRotura(null);
                    detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo);
                }
            }
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo : detallemantenimientocorrectivoListNew) {
                if (!detallemantenimientocorrectivoListOld.contains(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo)) {
                    Rotura oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.getRotura();
                    detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.setRotura(rotura);
                    detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
                    if (oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo != null && !oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.equals(rotura)) {
                        oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
                        oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = em.merge(oldRoturaOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
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
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoList = rotura.getDetallemantenimientocorrectivoList();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListDetallemantenimientocorrectivo : detallemantenimientocorrectivoList) {
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo.setRotura(null);
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListDetallemantenimientocorrectivo);
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
