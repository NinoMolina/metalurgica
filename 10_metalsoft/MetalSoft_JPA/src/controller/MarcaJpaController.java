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
        if (marca.getMaquinaSet1() == null) {
            marca.setMaquinaSet1(new HashSet<Maquina>());
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
            Set<Maquina> attachedMaquinaSet1 = new HashSet<Maquina>();
            for (Maquina maquinaSet1MaquinaToAttach : marca.getMaquinaSet1()) {
                maquinaSet1MaquinaToAttach = em.getReference(maquinaSet1MaquinaToAttach.getClass(), maquinaSet1MaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1.add(maquinaSet1MaquinaToAttach);
            }
            marca.setMaquinaSet1(attachedMaquinaSet1);
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
            for (Maquina maquinaSet1Maquina : marca.getMaquinaSet1()) {
                Marca oldMarca1OfMaquinaSet1Maquina = maquinaSet1Maquina.getMarca1();
                maquinaSet1Maquina.setMarca1(marca);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
                if (oldMarca1OfMaquinaSet1Maquina != null) {
                    oldMarca1OfMaquinaSet1Maquina.getMaquinaSet1().remove(maquinaSet1Maquina);
                    oldMarca1OfMaquinaSet1Maquina = em.merge(oldMarca1OfMaquinaSet1Maquina);
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
            Set<Maquina> maquinaSet1Old = persistentMarca.getMaquinaSet1();
            Set<Maquina> maquinaSet1New = marca.getMaquinaSet1();
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            marca.setMaquinaSet(maquinaSetNew);
            Set<Maquina> attachedMaquinaSet1New = new HashSet<Maquina>();
            for (Maquina maquinaSet1NewMaquinaToAttach : maquinaSet1New) {
                maquinaSet1NewMaquinaToAttach = em.getReference(maquinaSet1NewMaquinaToAttach.getClass(), maquinaSet1NewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1New.add(maquinaSet1NewMaquinaToAttach);
            }
            maquinaSet1New = attachedMaquinaSet1New;
            marca.setMaquinaSet1(maquinaSet1New);
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
            for (Maquina maquinaSet1OldMaquina : maquinaSet1Old) {
                if (!maquinaSet1New.contains(maquinaSet1OldMaquina)) {
                    maquinaSet1OldMaquina.setMarca1(null);
                    maquinaSet1OldMaquina = em.merge(maquinaSet1OldMaquina);
                }
            }
            for (Maquina maquinaSet1NewMaquina : maquinaSet1New) {
                if (!maquinaSet1Old.contains(maquinaSet1NewMaquina)) {
                    Marca oldMarca1OfMaquinaSet1NewMaquina = maquinaSet1NewMaquina.getMarca1();
                    maquinaSet1NewMaquina.setMarca1(marca);
                    maquinaSet1NewMaquina = em.merge(maquinaSet1NewMaquina);
                    if (oldMarca1OfMaquinaSet1NewMaquina != null && !oldMarca1OfMaquinaSet1NewMaquina.equals(marca)) {
                        oldMarca1OfMaquinaSet1NewMaquina.getMaquinaSet1().remove(maquinaSet1NewMaquina);
                        oldMarca1OfMaquinaSet1NewMaquina = em.merge(oldMarca1OfMaquinaSet1NewMaquina);
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
            Set<Maquina> maquinaSet1 = marca.getMaquinaSet1();
            for (Maquina maquinaSet1Maquina : maquinaSet1) {
                maquinaSet1Maquina.setMarca1(null);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
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
