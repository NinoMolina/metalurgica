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
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadoremito;
import metalsoft.datos.jpa.entity.Remito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadoremitoJpaController implements Serializable {

    public EstadoremitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoremito estadoremito) throws PreexistingEntityException, Exception {
        if (estadoremito.getRemitoList() == null) {
            estadoremito.setRemitoList(new ArrayList<Remito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Remito> attachedRemitoList = new ArrayList<Remito>();
            for (Remito remitoListRemitoToAttach : estadoremito.getRemitoList()) {
                remitoListRemitoToAttach = em.getReference(remitoListRemitoToAttach.getClass(), remitoListRemitoToAttach.getIdremito());
                attachedRemitoList.add(remitoListRemitoToAttach);
            }
            estadoremito.setRemitoList(attachedRemitoList);
            em.persist(estadoremito);
            for (Remito remitoListRemito : estadoremito.getRemitoList()) {
                Estadoremito oldEstadoOfRemitoListRemito = remitoListRemito.getEstado();
                remitoListRemito.setEstado(estadoremito);
                remitoListRemito = em.merge(remitoListRemito);
                if (oldEstadoOfRemitoListRemito != null) {
                    oldEstadoOfRemitoListRemito.getRemitoList().remove(remitoListRemito);
                    oldEstadoOfRemitoListRemito = em.merge(oldEstadoOfRemitoListRemito);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoremito(estadoremito.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoremito " + estadoremito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoremito estadoremito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoremito persistentEstadoremito = em.find(Estadoremito.class, estadoremito.getIdestado());
            List<Remito> remitoListOld = persistentEstadoremito.getRemitoList();
            List<Remito> remitoListNew = estadoremito.getRemitoList();
            List<Remito> attachedRemitoListNew = new ArrayList<Remito>();
            for (Remito remitoListNewRemitoToAttach : remitoListNew) {
                remitoListNewRemitoToAttach = em.getReference(remitoListNewRemitoToAttach.getClass(), remitoListNewRemitoToAttach.getIdremito());
                attachedRemitoListNew.add(remitoListNewRemitoToAttach);
            }
            remitoListNew = attachedRemitoListNew;
            estadoremito.setRemitoList(remitoListNew);
            estadoremito = em.merge(estadoremito);
            for (Remito remitoListOldRemito : remitoListOld) {
                if (!remitoListNew.contains(remitoListOldRemito)) {
                    remitoListOldRemito.setEstado(null);
                    remitoListOldRemito = em.merge(remitoListOldRemito);
                }
            }
            for (Remito remitoListNewRemito : remitoListNew) {
                if (!remitoListOld.contains(remitoListNewRemito)) {
                    Estadoremito oldEstadoOfRemitoListNewRemito = remitoListNewRemito.getEstado();
                    remitoListNewRemito.setEstado(estadoremito);
                    remitoListNewRemito = em.merge(remitoListNewRemito);
                    if (oldEstadoOfRemitoListNewRemito != null && !oldEstadoOfRemitoListNewRemito.equals(estadoremito)) {
                        oldEstadoOfRemitoListNewRemito.getRemitoList().remove(remitoListNewRemito);
                        oldEstadoOfRemitoListNewRemito = em.merge(oldEstadoOfRemitoListNewRemito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoremito.getIdestado();
                if (findEstadoremito(id) == null) {
                    throw new NonexistentEntityException("The estadoremito with id " + id + " no longer exists.");
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
            Estadoremito estadoremito;
            try {
                estadoremito = em.getReference(Estadoremito.class, id);
                estadoremito.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoremito with id " + id + " no longer exists.", enfe);
            }
            List<Remito> remitoList = estadoremito.getRemitoList();
            for (Remito remitoListRemito : remitoList) {
                remitoListRemito.setEstado(null);
                remitoListRemito = em.merge(remitoListRemito);
            }
            em.remove(estadoremito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoremito> findEstadoremitoEntities() {
        return findEstadoremitoEntities(true, -1, -1);
    }

    public List<Estadoremito> findEstadoremitoEntities(int maxResults, int firstResult) {
        return findEstadoremitoEntities(false, maxResults, firstResult);
    }

    private List<Estadoremito> findEstadoremitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoremito.class));
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

    public Estadoremito findEstadoremito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoremito.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoremitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoremito> rt = cq.from(Estadoremito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
