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
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Tipomaquina;

/**
 *
 * @author Nino
 */
public class TipomaquinaJpaController implements Serializable {

    public TipomaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomaquina tipomaquina) throws PreexistingEntityException, Exception {
        if (tipomaquina.getMaquinaList() == null) {
            tipomaquina.setMaquinaList(new ArrayList<Maquina>());
        }
        if (tipomaquina.getEtapadeproduccionList() == null) {
            tipomaquina.setEtapadeproduccionList(new ArrayList<Etapadeproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Maquina> attachedMaquinaList = new ArrayList<Maquina>();
            for (Maquina maquinaListMaquinaToAttach : tipomaquina.getMaquinaList()) {
                maquinaListMaquinaToAttach = em.getReference(maquinaListMaquinaToAttach.getClass(), maquinaListMaquinaToAttach.getIdmaquina());
                attachedMaquinaList.add(maquinaListMaquinaToAttach);
            }
            tipomaquina.setMaquinaList(attachedMaquinaList);
            List<Etapadeproduccion> attachedEtapadeproduccionList = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccionToAttach : tipomaquina.getEtapadeproduccionList()) {
                etapadeproduccionListEtapadeproduccionToAttach = em.getReference(etapadeproduccionListEtapadeproduccionToAttach.getClass(), etapadeproduccionListEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionList.add(etapadeproduccionListEtapadeproduccionToAttach);
            }
            tipomaquina.setEtapadeproduccionList(attachedEtapadeproduccionList);
            em.persist(tipomaquina);
            for (Maquina maquinaListMaquina : tipomaquina.getMaquinaList()) {
                Tipomaquina oldTipomaquinaOfMaquinaListMaquina = maquinaListMaquina.getTipomaquina();
                maquinaListMaquina.setTipomaquina(tipomaquina);
                maquinaListMaquina = em.merge(maquinaListMaquina);
                if (oldTipomaquinaOfMaquinaListMaquina != null) {
                    oldTipomaquinaOfMaquinaListMaquina.getMaquinaList().remove(maquinaListMaquina);
                    oldTipomaquinaOfMaquinaListMaquina = em.merge(oldTipomaquinaOfMaquinaListMaquina);
                }
            }
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : tipomaquina.getEtapadeproduccionList()) {
                Tipomaquina oldTipomaquinaOfEtapadeproduccionListEtapadeproduccion = etapadeproduccionListEtapadeproduccion.getTipomaquina();
                etapadeproduccionListEtapadeproduccion.setTipomaquina(tipomaquina);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
                if (oldTipomaquinaOfEtapadeproduccionListEtapadeproduccion != null) {
                    oldTipomaquinaOfEtapadeproduccionListEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListEtapadeproduccion);
                    oldTipomaquinaOfEtapadeproduccionListEtapadeproduccion = em.merge(oldTipomaquinaOfEtapadeproduccionListEtapadeproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipomaquina(tipomaquina.getIdtipomaquina()) != null) {
                throw new PreexistingEntityException("Tipomaquina " + tipomaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipomaquina tipomaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomaquina persistentTipomaquina = em.find(Tipomaquina.class, tipomaquina.getIdtipomaquina());
            List<Maquina> maquinaListOld = persistentTipomaquina.getMaquinaList();
            List<Maquina> maquinaListNew = tipomaquina.getMaquinaList();
            List<Etapadeproduccion> etapadeproduccionListOld = persistentTipomaquina.getEtapadeproduccionList();
            List<Etapadeproduccion> etapadeproduccionListNew = tipomaquina.getEtapadeproduccionList();
            List<Maquina> attachedMaquinaListNew = new ArrayList<Maquina>();
            for (Maquina maquinaListNewMaquinaToAttach : maquinaListNew) {
                maquinaListNewMaquinaToAttach = em.getReference(maquinaListNewMaquinaToAttach.getClass(), maquinaListNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaListNew.add(maquinaListNewMaquinaToAttach);
            }
            maquinaListNew = attachedMaquinaListNew;
            tipomaquina.setMaquinaList(maquinaListNew);
            List<Etapadeproduccion> attachedEtapadeproduccionListNew = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccionToAttach : etapadeproduccionListNew) {
                etapadeproduccionListNewEtapadeproduccionToAttach = em.getReference(etapadeproduccionListNewEtapadeproduccionToAttach.getClass(), etapadeproduccionListNewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionListNew.add(etapadeproduccionListNewEtapadeproduccionToAttach);
            }
            etapadeproduccionListNew = attachedEtapadeproduccionListNew;
            tipomaquina.setEtapadeproduccionList(etapadeproduccionListNew);
            tipomaquina = em.merge(tipomaquina);
            for (Maquina maquinaListOldMaquina : maquinaListOld) {
                if (!maquinaListNew.contains(maquinaListOldMaquina)) {
                    maquinaListOldMaquina.setTipomaquina(null);
                    maquinaListOldMaquina = em.merge(maquinaListOldMaquina);
                }
            }
            for (Maquina maquinaListNewMaquina : maquinaListNew) {
                if (!maquinaListOld.contains(maquinaListNewMaquina)) {
                    Tipomaquina oldTipomaquinaOfMaquinaListNewMaquina = maquinaListNewMaquina.getTipomaquina();
                    maquinaListNewMaquina.setTipomaquina(tipomaquina);
                    maquinaListNewMaquina = em.merge(maquinaListNewMaquina);
                    if (oldTipomaquinaOfMaquinaListNewMaquina != null && !oldTipomaquinaOfMaquinaListNewMaquina.equals(tipomaquina)) {
                        oldTipomaquinaOfMaquinaListNewMaquina.getMaquinaList().remove(maquinaListNewMaquina);
                        oldTipomaquinaOfMaquinaListNewMaquina = em.merge(oldTipomaquinaOfMaquinaListNewMaquina);
                    }
                }
            }
            for (Etapadeproduccion etapadeproduccionListOldEtapadeproduccion : etapadeproduccionListOld) {
                if (!etapadeproduccionListNew.contains(etapadeproduccionListOldEtapadeproduccion)) {
                    etapadeproduccionListOldEtapadeproduccion.setTipomaquina(null);
                    etapadeproduccionListOldEtapadeproduccion = em.merge(etapadeproduccionListOldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccion : etapadeproduccionListNew) {
                if (!etapadeproduccionListOld.contains(etapadeproduccionListNewEtapadeproduccion)) {
                    Tipomaquina oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion = etapadeproduccionListNewEtapadeproduccion.getTipomaquina();
                    etapadeproduccionListNewEtapadeproduccion.setTipomaquina(tipomaquina);
                    etapadeproduccionListNewEtapadeproduccion = em.merge(etapadeproduccionListNewEtapadeproduccion);
                    if (oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion != null && !oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion.equals(tipomaquina)) {
                        oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListNewEtapadeproduccion);
                        oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion = em.merge(oldTipomaquinaOfEtapadeproduccionListNewEtapadeproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipomaquina.getIdtipomaquina();
                if (findTipomaquina(id) == null) {
                    throw new NonexistentEntityException("The tipomaquina with id " + id + " no longer exists.");
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
            Tipomaquina tipomaquina;
            try {
                tipomaquina = em.getReference(Tipomaquina.class, id);
                tipomaquina.getIdtipomaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipomaquina with id " + id + " no longer exists.", enfe);
            }
            List<Maquina> maquinaList = tipomaquina.getMaquinaList();
            for (Maquina maquinaListMaquina : maquinaList) {
                maquinaListMaquina.setTipomaquina(null);
                maquinaListMaquina = em.merge(maquinaListMaquina);
            }
            List<Etapadeproduccion> etapadeproduccionList = tipomaquina.getEtapadeproduccionList();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : etapadeproduccionList) {
                etapadeproduccionListEtapadeproduccion.setTipomaquina(null);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
            }
            em.remove(tipomaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipomaquina> findTipomaquinaEntities() {
        return findTipomaquinaEntities(true, -1, -1);
    }

    public List<Tipomaquina> findTipomaquinaEntities(int maxResults, int firstResult) {
        return findTipomaquinaEntities(false, maxResults, firstResult);
    }

    private List<Tipomaquina> findTipomaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipomaquina.class));
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

    public Tipomaquina findTipomaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipomaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipomaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipomaquina> rt = cq.from(Tipomaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
