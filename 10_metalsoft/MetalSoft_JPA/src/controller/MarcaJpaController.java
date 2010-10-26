/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Maquina;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class MarcaJpaController {

    public MarcaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) throws PreexistingEntityException, Exception {
        if (marca.getMaquinaSet() == null) {
            marca.setMaquinaSet(new HashSet<Maquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Maquina> attachedMaquinaSet = new HashSet<Maquina>();
            for (Maquina maquinaSetMaquinaToAttach : marca.getMaquinaSet()) {
                maquinaSetMaquinaToAttach = em.getReference(maquinaSetMaquinaToAttach.getClass(), maquinaSetMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet.add(maquinaSetMaquinaToAttach);
            }
            marca.setMaquinaSet(attachedMaquinaSet);
            em.persist(marca);
            for (Maquina maquinaSetMaquina : marca.getMaquinaSet()) {
                Marca oldMarcaOfMaquinaSetMaquina = maquinaSetMaquina.getMarca();
                maquinaSetMaquina.setMarca(marca);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
                if (oldMarcaOfMaquinaSetMaquina != null) {
                    oldMarcaOfMaquinaSetMaquina.getMaquinaSet().remove(maquinaSetMaquina);
                    oldMarcaOfMaquinaSetMaquina = em.merge(oldMarcaOfMaquinaSetMaquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMarca(marca.getIdmarca()) != null) {
                throw new PreexistingEntityException("Marca " + marca + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marca marca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca persistentMarca = em.find(Marca.class, marca.getIdmarca());
            Set<Maquina> maquinaSetOld = persistentMarca.getMaquinaSet();
            Set<Maquina> maquinaSetNew = marca.getMaquinaSet();
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            marca.setMaquinaSet(maquinaSetNew);
            marca = em.merge(marca);
            for (Maquina maquinaSetOldMaquina : maquinaSetOld) {
                if (!maquinaSetNew.contains(maquinaSetOldMaquina)) {
                    maquinaSetOldMaquina.setMarca(null);
                    maquinaSetOldMaquina = em.merge(maquinaSetOldMaquina);
                }
            }
            for (Maquina maquinaSetNewMaquina : maquinaSetNew) {
                if (!maquinaSetOld.contains(maquinaSetNewMaquina)) {
                    Marca oldMarcaOfMaquinaSetNewMaquina = maquinaSetNewMaquina.getMarca();
                    maquinaSetNewMaquina.setMarca(marca);
                    maquinaSetNewMaquina = em.merge(maquinaSetNewMaquina);
                    if (oldMarcaOfMaquinaSetNewMaquina != null && !oldMarcaOfMaquinaSetNewMaquina.equals(marca)) {
                        oldMarcaOfMaquinaSetNewMaquina.getMaquinaSet().remove(maquinaSetNewMaquina);
                        oldMarcaOfMaquinaSetNewMaquina = em.merge(oldMarcaOfMaquinaSetNewMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = marca.getIdmarca();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getIdmarca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            Set<Maquina> maquinaSet = marca.getMaquinaSet();
            for (Maquina maquinaSetMaquina : maquinaSet) {
                maquinaSetMaquina.setMarca(null);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
            }
            em.remove(marca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
