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
import metalsoft.datos.jpa.entity.Maquina;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Marca;

/**
 *
 * @author Nino
 */
public class MarcaJpaController implements Serializable {

    public MarcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) throws PreexistingEntityException, Exception {
        if (marca.getMaquinaList() == null) {
            marca.setMaquinaList(new ArrayList<Maquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Maquina> attachedMaquinaList = new ArrayList<Maquina>();
            for (Maquina maquinaListMaquinaToAttach : marca.getMaquinaList()) {
                maquinaListMaquinaToAttach = em.getReference(maquinaListMaquinaToAttach.getClass(), maquinaListMaquinaToAttach.getIdmaquina());
                attachedMaquinaList.add(maquinaListMaquinaToAttach);
            }
            marca.setMaquinaList(attachedMaquinaList);
            em.persist(marca);
            for (Maquina maquinaListMaquina : marca.getMaquinaList()) {
                Marca oldMarcaOfMaquinaListMaquina = maquinaListMaquina.getMarca();
                maquinaListMaquina.setMarca(marca);
                maquinaListMaquina = em.merge(maquinaListMaquina);
                if (oldMarcaOfMaquinaListMaquina != null) {
                    oldMarcaOfMaquinaListMaquina.getMaquinaList().remove(maquinaListMaquina);
                    oldMarcaOfMaquinaListMaquina = em.merge(oldMarcaOfMaquinaListMaquina);
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
            List<Maquina> maquinaListOld = persistentMarca.getMaquinaList();
            List<Maquina> maquinaListNew = marca.getMaquinaList();
            List<Maquina> attachedMaquinaListNew = new ArrayList<Maquina>();
            for (Maquina maquinaListNewMaquinaToAttach : maquinaListNew) {
                maquinaListNewMaquinaToAttach = em.getReference(maquinaListNewMaquinaToAttach.getClass(), maquinaListNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaListNew.add(maquinaListNewMaquinaToAttach);
            }
            maquinaListNew = attachedMaquinaListNew;
            marca.setMaquinaList(maquinaListNew);
            marca = em.merge(marca);
            for (Maquina maquinaListOldMaquina : maquinaListOld) {
                if (!maquinaListNew.contains(maquinaListOldMaquina)) {
                    maquinaListOldMaquina.setMarca(null);
                    maquinaListOldMaquina = em.merge(maquinaListOldMaquina);
                }
            }
            for (Maquina maquinaListNewMaquina : maquinaListNew) {
                if (!maquinaListOld.contains(maquinaListNewMaquina)) {
                    Marca oldMarcaOfMaquinaListNewMaquina = maquinaListNewMaquina.getMarca();
                    maquinaListNewMaquina.setMarca(marca);
                    maquinaListNewMaquina = em.merge(maquinaListNewMaquina);
                    if (oldMarcaOfMaquinaListNewMaquina != null && !oldMarcaOfMaquinaListNewMaquina.equals(marca)) {
                        oldMarcaOfMaquinaListNewMaquina.getMaquinaList().remove(maquinaListNewMaquina);
                        oldMarcaOfMaquinaListNewMaquina = em.merge(oldMarcaOfMaquinaListNewMaquina);
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
            List<Maquina> maquinaList = marca.getMaquinaList();
            for (Maquina maquinaListMaquina : maquinaList) {
                maquinaListMaquina.setMarca(null);
                maquinaListMaquina = em.merge(maquinaListMaquina);
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
