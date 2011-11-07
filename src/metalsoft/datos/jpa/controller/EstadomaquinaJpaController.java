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
import metalsoft.datos.jpa.entity.Estadomaquina;
import metalsoft.datos.jpa.entity.Maquina;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadomaquinaJpaController implements Serializable {

    public EstadomaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadomaquina estadomaquina) throws PreexistingEntityException, Exception {
        if (estadomaquina.getMaquinaList() == null) {
            estadomaquina.setMaquinaList(new ArrayList<Maquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Maquina> attachedMaquinaList = new ArrayList<Maquina>();
            for (Maquina maquinaListMaquinaToAttach : estadomaquina.getMaquinaList()) {
                maquinaListMaquinaToAttach = em.getReference(maquinaListMaquinaToAttach.getClass(), maquinaListMaquinaToAttach.getIdmaquina());
                attachedMaquinaList.add(maquinaListMaquinaToAttach);
            }
            estadomaquina.setMaquinaList(attachedMaquinaList);
            em.persist(estadomaquina);
            for (Maquina maquinaListMaquina : estadomaquina.getMaquinaList()) {
                Estadomaquina oldEstadoOfMaquinaListMaquina = maquinaListMaquina.getEstado();
                maquinaListMaquina.setEstado(estadomaquina);
                maquinaListMaquina = em.merge(maquinaListMaquina);
                if (oldEstadoOfMaquinaListMaquina != null) {
                    oldEstadoOfMaquinaListMaquina.getMaquinaList().remove(maquinaListMaquina);
                    oldEstadoOfMaquinaListMaquina = em.merge(oldEstadoOfMaquinaListMaquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadomaquina(estadomaquina.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadomaquina " + estadomaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadomaquina estadomaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadomaquina persistentEstadomaquina = em.find(Estadomaquina.class, estadomaquina.getIdestado());
            List<Maquina> maquinaListOld = persistentEstadomaquina.getMaquinaList();
            List<Maquina> maquinaListNew = estadomaquina.getMaquinaList();
            List<Maquina> attachedMaquinaListNew = new ArrayList<Maquina>();
            for (Maquina maquinaListNewMaquinaToAttach : maquinaListNew) {
                maquinaListNewMaquinaToAttach = em.getReference(maquinaListNewMaquinaToAttach.getClass(), maquinaListNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaListNew.add(maquinaListNewMaquinaToAttach);
            }
            maquinaListNew = attachedMaquinaListNew;
            estadomaquina.setMaquinaList(maquinaListNew);
            estadomaquina = em.merge(estadomaquina);
            for (Maquina maquinaListOldMaquina : maquinaListOld) {
                if (!maquinaListNew.contains(maquinaListOldMaquina)) {
                    maquinaListOldMaquina.setEstado(null);
                    maquinaListOldMaquina = em.merge(maquinaListOldMaquina);
                }
            }
            for (Maquina maquinaListNewMaquina : maquinaListNew) {
                if (!maquinaListOld.contains(maquinaListNewMaquina)) {
                    Estadomaquina oldEstadoOfMaquinaListNewMaquina = maquinaListNewMaquina.getEstado();
                    maquinaListNewMaquina.setEstado(estadomaquina);
                    maquinaListNewMaquina = em.merge(maquinaListNewMaquina);
                    if (oldEstadoOfMaquinaListNewMaquina != null && !oldEstadoOfMaquinaListNewMaquina.equals(estadomaquina)) {
                        oldEstadoOfMaquinaListNewMaquina.getMaquinaList().remove(maquinaListNewMaquina);
                        oldEstadoOfMaquinaListNewMaquina = em.merge(oldEstadoOfMaquinaListNewMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadomaquina.getIdestado();
                if (findEstadomaquina(id) == null) {
                    throw new NonexistentEntityException("The estadomaquina with id " + id + " no longer exists.");
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
            Estadomaquina estadomaquina;
            try {
                estadomaquina = em.getReference(Estadomaquina.class, id);
                estadomaquina.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadomaquina with id " + id + " no longer exists.", enfe);
            }
            List<Maquina> maquinaList = estadomaquina.getMaquinaList();
            for (Maquina maquinaListMaquina : maquinaList) {
                maquinaListMaquina.setEstado(null);
                maquinaListMaquina = em.merge(maquinaListMaquina);
            }
            em.remove(estadomaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadomaquina> findEstadomaquinaEntities() {
        return findEstadomaquinaEntities(true, -1, -1);
    }

    public List<Estadomaquina> findEstadomaquinaEntities(int maxResults, int firstResult) {
        return findEstadomaquinaEntities(false, maxResults, firstResult);
    }

    private List<Estadomaquina> findEstadomaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadomaquina.class));
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

    public Estadomaquina findEstadomaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadomaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadomaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadomaquina> rt = cq.from(Estadomaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
