/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallemantenimientocorrectivo;
import entity.DetallemantenimientocorrectivoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Mantenimientocorrectivo;
import entity.Rotura;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class DetallemantenimientocorrectivoJpaController {

    public DetallemantenimientocorrectivoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientocorrectivo detallemantenimientocorrectivo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK() == null) {
            detallemantenimientocorrectivo.setDetallemantenimientocorrectivoPK(new DetallemantenimientocorrectivoPK());
        }
        detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK().setIdmantenimientocorrectivo(detallemantenimientocorrectivo.getMantenimientocorrectivo1().getIdmantenimientocorrectivo());
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
        Mantenimientocorrectivo mantenimientocorrectivo1OrphanCheck = detallemantenimientocorrectivo.getMantenimientocorrectivo1();
        if (mantenimientocorrectivo1OrphanCheck != null) {
            Detallemantenimientocorrectivo oldDetallemantenimientocorrectivoOfMantenimientocorrectivo1 = mantenimientocorrectivo1OrphanCheck.getDetallemantenimientocorrectivo();
            if (oldDetallemantenimientocorrectivoOfMantenimientocorrectivo1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Mantenimientocorrectivo " + mantenimientocorrectivo1OrphanCheck + " already has an item of type Detallemantenimientocorrectivo whose mantenimientocorrectivo1 column cannot be null. Please make another selection for the mantenimientocorrectivo1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientocorrectivo mantenimientocorrectivo = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo = em.getReference(mantenimientocorrectivo.getClass(), mantenimientocorrectivo.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo(mantenimientocorrectivo);
            }
            Mantenimientocorrectivo mantenimientocorrectivo1 = detallemantenimientocorrectivo.getMantenimientocorrectivo1();
            if (mantenimientocorrectivo1 != null) {
                mantenimientocorrectivo1 = em.getReference(mantenimientocorrectivo1.getClass(), mantenimientocorrectivo1.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo1(mantenimientocorrectivo1);
            }
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura = em.getReference(rotura.getClass(), rotura.getIdrotura());
                detallemantenimientocorrectivo.setRotura(rotura);
            }
            Rotura rotura1 = detallemantenimientocorrectivo.getRotura1();
            if (rotura1 != null) {
                rotura1 = em.getReference(rotura1.getClass(), rotura1.getIdrotura());
                detallemantenimientocorrectivo.setRotura1(rotura1);
            }
            em.persist(detallemantenimientocorrectivo);
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            }
            if (mantenimientocorrectivo1 != null) {
                mantenimientocorrectivo1.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivo1 = em.merge(mantenimientocorrectivo1);
            }
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoSet().add(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            if (rotura1 != null) {
                rotura1.getDetallemantenimientocorrectivoSet().add(detallemantenimientocorrectivo);
                rotura1 = em.merge(rotura1);
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
        detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK().setIdmantenimientocorrectivo(detallemantenimientocorrectivo.getMantenimientocorrectivo1().getIdmantenimientocorrectivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientocorrectivo persistentDetallemantenimientocorrectivo = em.find(Detallemantenimientocorrectivo.class, detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK());
            Mantenimientocorrectivo mantenimientocorrectivoOld = persistentDetallemantenimientocorrectivo.getMantenimientocorrectivo();
            Mantenimientocorrectivo mantenimientocorrectivoNew = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            Mantenimientocorrectivo mantenimientocorrectivo1Old = persistentDetallemantenimientocorrectivo.getMantenimientocorrectivo1();
            Mantenimientocorrectivo mantenimientocorrectivo1New = detallemantenimientocorrectivo.getMantenimientocorrectivo1();
            Rotura roturaOld = persistentDetallemantenimientocorrectivo.getRotura();
            Rotura roturaNew = detallemantenimientocorrectivo.getRotura();
            Rotura rotura1Old = persistentDetallemantenimientocorrectivo.getRotura1();
            Rotura rotura1New = detallemantenimientocorrectivo.getRotura1();
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
            if (mantenimientocorrectivo1New != null && !mantenimientocorrectivo1New.equals(mantenimientocorrectivo1Old)) {
                Detallemantenimientocorrectivo oldDetallemantenimientocorrectivoOfMantenimientocorrectivo1 = mantenimientocorrectivo1New.getDetallemantenimientocorrectivo();
                if (oldDetallemantenimientocorrectivoOfMantenimientocorrectivo1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Mantenimientocorrectivo " + mantenimientocorrectivo1New + " already has an item of type Detallemantenimientocorrectivo whose mantenimientocorrectivo1 column cannot be null. Please make another selection for the mantenimientocorrectivo1 field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (mantenimientocorrectivoNew != null) {
                mantenimientocorrectivoNew = em.getReference(mantenimientocorrectivoNew.getClass(), mantenimientocorrectivoNew.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo(mantenimientocorrectivoNew);
            }
            if (mantenimientocorrectivo1New != null) {
                mantenimientocorrectivo1New = em.getReference(mantenimientocorrectivo1New.getClass(), mantenimientocorrectivo1New.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setMantenimientocorrectivo1(mantenimientocorrectivo1New);
            }
            if (roturaNew != null) {
                roturaNew = em.getReference(roturaNew.getClass(), roturaNew.getIdrotura());
                detallemantenimientocorrectivo.setRotura(roturaNew);
            }
            if (rotura1New != null) {
                rotura1New = em.getReference(rotura1New.getClass(), rotura1New.getIdrotura());
                detallemantenimientocorrectivo.setRotura1(rotura1New);
            }
            detallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivo);
            if (mantenimientocorrectivoOld != null && !mantenimientocorrectivoOld.equals(mantenimientocorrectivoNew)) {
                mantenimientocorrectivoOld.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivoOld = em.merge(mantenimientocorrectivoOld);
            }
            if (mantenimientocorrectivoNew != null && !mantenimientocorrectivoNew.equals(mantenimientocorrectivoOld)) {
                mantenimientocorrectivoNew.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivoNew = em.merge(mantenimientocorrectivoNew);
            }
            if (mantenimientocorrectivo1Old != null && !mantenimientocorrectivo1Old.equals(mantenimientocorrectivo1New)) {
                mantenimientocorrectivo1Old.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivo1Old = em.merge(mantenimientocorrectivo1Old);
            }
            if (mantenimientocorrectivo1New != null && !mantenimientocorrectivo1New.equals(mantenimientocorrectivo1Old)) {
                mantenimientocorrectivo1New.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
                mantenimientocorrectivo1New = em.merge(mantenimientocorrectivo1New);
            }
            if (roturaOld != null && !roturaOld.equals(roturaNew)) {
                roturaOld.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivo);
                roturaOld = em.merge(roturaOld);
            }
            if (roturaNew != null && !roturaNew.equals(roturaOld)) {
                roturaNew.getDetallemantenimientocorrectivoSet().add(detallemantenimientocorrectivo);
                roturaNew = em.merge(roturaNew);
            }
            if (rotura1Old != null && !rotura1Old.equals(rotura1New)) {
                rotura1Old.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivo);
                rotura1Old = em.merge(rotura1Old);
            }
            if (rotura1New != null && !rotura1New.equals(rotura1Old)) {
                rotura1New.getDetallemantenimientocorrectivoSet().add(detallemantenimientocorrectivo);
                rotura1New = em.merge(rotura1New);
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
            Mantenimientocorrectivo mantenimientocorrectivo = detallemantenimientocorrectivo.getMantenimientocorrectivo();
            if (mantenimientocorrectivo != null) {
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            }
            Mantenimientocorrectivo mantenimientocorrectivo1 = detallemantenimientocorrectivo.getMantenimientocorrectivo1();
            if (mantenimientocorrectivo1 != null) {
                mantenimientocorrectivo1.setDetallemantenimientocorrectivo(null);
                mantenimientocorrectivo1 = em.merge(mantenimientocorrectivo1);
            }
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            Rotura rotura1 = detallemantenimientocorrectivo.getRotura1();
            if (rotura1 != null) {
                rotura1.getDetallemantenimientocorrectivoSet().remove(detallemantenimientocorrectivo);
                rotura1 = em.merge(rotura1);
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
