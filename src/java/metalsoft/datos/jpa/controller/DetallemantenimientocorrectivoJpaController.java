/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import metalsoft.datos.jpa.entity.DetallemantenimientocorrectivoPK;
import metalsoft.datos.jpa.entity.Rotura;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class DetallemantenimientocorrectivoJpaController implements Serializable {

    public DetallemantenimientocorrectivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientocorrectivo detallemantenimientocorrectivo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK() == null) {
            detallemantenimientocorrectivo.setDetallemantenimientocorrectivoPK(new DetallemantenimientocorrectivoPK());
        }
        detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK().setIdmantenimientocorrectivo(detallemantenimientocorrectivo.getMantenimientocorrectivo().getIdmantenimientocorrectivo());
        List<String> illegalOrphanMessages = null;
        Mantenimientocorrectivo mantenimientocorrectivoOrphanCheck = detallemantenimientocorrectivo.getMantenimientocorrectivo();
        if (mantenimientocorrectivoOrphanCheck != null) {
            Detallemantenimientocorrectivo oldDetallemantenimientocorrectivoOfMantenimientocorrectivo = mantenimientocorrectivoOrphanCheck.getDetallemantenimientocorrectivo();
            if (oldDetallemantenimientocorrectivoOfMantenimientocorrectivo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Mantenimientocorrectivo " + mantenimientocorrectivoOrphanCheck + " already has an item of type Detallemantenimientocorrectivo whose mantenimientocorrectivo column cannot be null. Please make another selection for the mantenimientocorrectivo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura = em.getReference(rotura.getClass(), rotura.getIdrotura());
                detallemantenimientocorrectivo.setRotura(rotura);
            }
            Mantenimientocorrectivo mantenimientocorrectivo = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo = em.getReference(mantenimientocorrectivo.getClass(), mantenimientocorrectivo.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo(mantenimientocorrectivo);
            }
            em.persist(detallemantenimientocorrectivo);
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallemantenimientocorrectivo(detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK()) != null) {
                throw new PreexistingEntityException("Detallemantenimientocorrectivo " + detallemantenimientocorrectivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallemantenimientocorrectivo detallemantenimientocorrectivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK().setIdmantenimientocorrectivo(detallemantenimientocorrectivo.getMantenimientocorrectivo().getIdmantenimientocorrectivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientocorrectivo persistentDetallemantenimientocorrectivo = em.find(Detallemantenimientocorrectivo.class, detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK());
            Rotura roturaOld = persistentDetallemantenimientocorrectivo.getRotura();
            Rotura roturaNew = detallemantenimientocorrectivo.getRotura();
            Mantenimientocorrectivo mantenimientocorrectivoOld = persistentDetallemantenimientocorrectivo.getMantenimientocorrectivo();
            Mantenimientocorrectivo mantenimientocorrectivoNew = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            List<String> illegalOrphanMessages = null;
            if (mantenimientocorrectivoNew != null && !mantenimientocorrectivoNew.equals(mantenimientocorrectivoOld)) {
                Detallemantenimientocorrectivo oldDetallemantenimientocorrectivoOfMantenimientocorrectivo = mantenimientocorrectivoNew.getDetallemantenimientocorrectivo();
                if (oldDetallemantenimientocorrectivoOfMantenimientocorrectivo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Mantenimientocorrectivo " + mantenimientocorrectivoNew + " already has an item of type Detallemantenimientocorrectivo whose mantenimientocorrectivo column cannot be null. Please make another selection for the mantenimientocorrectivo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roturaNew != null) {
                roturaNew = em.getReference(roturaNew.getClass(), roturaNew.getIdrotura());
                detallemantenimientocorrectivo.setRotura(roturaNew);
            }
            if (mantenimientocorrectivoNew != null) {
                mantenimientocorrectivoNew = em.getReference(mantenimientocorrectivoNew.getClass(), mantenimientocorrectivoNew.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo(mantenimientocorrectivoNew);
            }
            detallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivo);
            if (roturaOld != null && !roturaOld.equals(roturaNew)) {
                roturaOld.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                roturaOld = em.merge(roturaOld);
            }
            if (roturaNew != null && !roturaNew.equals(roturaOld)) {
                roturaNew.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                roturaNew = em.merge(roturaNew);
            }
            if (mantenimientocorrectivoOld != null && !mantenimientocorrectivoOld.equals(mantenimientocorrectivoNew)) {
                mantenimientocorrectivoOld.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivoOld = em.merge(mantenimientocorrectivoOld);
            }
            if (mantenimientocorrectivoNew != null && !mantenimientocorrectivoNew.equals(mantenimientocorrectivoOld)) {
                mantenimientocorrectivoNew.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivoNew = em.merge(mantenimientocorrectivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallemantenimientocorrectivoPK id = detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK();
                if (findDetallemantenimientocorrectivo(id) == null) {
                    throw new NonexistentEntityException("The detallemantenimientocorrectivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallemantenimientocorrectivoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientocorrectivo detallemantenimientocorrectivo;
            try {
                detallemantenimientocorrectivo = em.getReference(Detallemantenimientocorrectivo.class, id);
                detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallemantenimientocorrectivo with id " + id + " no longer exists.", enfe);
            }
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            Mantenimientocorrectivo mantenimientocorrectivo = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            }
            em.remove(detallemantenimientocorrectivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities() {
        return findDetallemantenimientocorrectivoEntities(true, -1, -1);
    }

    public List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities(int maxResults, int firstResult) {
        return findDetallemantenimientocorrectivoEntities(false, maxResults, firstResult);
    }

    private List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallemantenimientocorrectivo.class));
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

    public Detallemantenimientocorrectivo findDetallemantenimientocorrectivo(DetallemantenimientocorrectivoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallemantenimientocorrectivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallemantenimientocorrectivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallemantenimientocorrectivo> rt = cq.from(Detallemantenimientocorrectivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
