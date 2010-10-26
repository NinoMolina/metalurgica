/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Unidadmedida;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import java.util.HashSet;
import java.util.Set;
import entity.Pieza;
import entity.Maquina;
import entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class UnidadmedidaJpaController {

    public UnidadmedidaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidadmedida unidadmedida) throws PreexistingEntityException, Exception {
        if (unidadmedida.getMateriaprimaSet() == null) {
            unidadmedida.setMateriaprimaSet(new HashSet<Materiaprima>());
        }
        if (unidadmedida.getPiezaSet() == null) {
            unidadmedida.setPiezaSet(new HashSet<Pieza>());
        }
        if (unidadmedida.getMaquinaSet() == null) {
            unidadmedida.setMaquinaSet(new HashSet<Maquina>());
        }
        if (unidadmedida.getEtapadeproduccionSet() == null) {
            unidadmedida.setEtapadeproduccionSet(new HashSet<Etapadeproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Materiaprima> attachedMateriaprimaSet = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetMateriaprimaToAttach : unidadmedida.getMateriaprimaSet()) {
                materiaprimaSetMateriaprimaToAttach = em.getReference(materiaprimaSetMateriaprimaToAttach.getClass(), materiaprimaSetMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet.add(materiaprimaSetMateriaprimaToAttach);
            }
            unidadmedida.setMateriaprimaSet(attachedMateriaprimaSet);
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : unidadmedida.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            unidadmedida.setPiezaSet(attachedPiezaSet);
            Set<Maquina> attachedMaquinaSet = new HashSet<Maquina>();
            for (Maquina maquinaSetMaquinaToAttach : unidadmedida.getMaquinaSet()) {
                maquinaSetMaquinaToAttach = em.getReference(maquinaSetMaquinaToAttach.getClass(), maquinaSetMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet.add(maquinaSetMaquinaToAttach);
            }
            unidadmedida.setMaquinaSet(attachedMaquinaSet);
            Set<Etapadeproduccion> attachedEtapadeproduccionSet = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccionToAttach : unidadmedida.getEtapadeproduccionSet()) {
                etapadeproduccionSetEtapadeproduccionToAttach = em.getReference(etapadeproduccionSetEtapadeproduccionToAttach.getClass(), etapadeproduccionSetEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSet.add(etapadeproduccionSetEtapadeproduccionToAttach);
            }
            unidadmedida.setEtapadeproduccionSet(attachedEtapadeproduccionSet);
            em.persist(unidadmedida);
            for (Materiaprima materiaprimaSetMateriaprima : unidadmedida.getMateriaprimaSet()) {
                Unidadmedida oldUnidadmedidaOfMateriaprimaSetMateriaprima = materiaprimaSetMateriaprima.getUnidadmedida();
                materiaprimaSetMateriaprima.setUnidadmedida(unidadmedida);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
                if (oldUnidadmedidaOfMateriaprimaSetMateriaprima != null) {
                    oldUnidadmedidaOfMateriaprimaSetMateriaprima.getMateriaprimaSet().remove(materiaprimaSetMateriaprima);
                    oldUnidadmedidaOfMateriaprimaSetMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaSetMateriaprima);
                }
            }
            for (Pieza piezaSetPieza : unidadmedida.getPiezaSet()) {
                Unidadmedida oldUnidadmedidaOfPiezaSetPieza = piezaSetPieza.getUnidadmedida();
                piezaSetPieza.setUnidadmedida(unidadmedida);
                piezaSetPieza = em.merge(piezaSetPieza);
                if (oldUnidadmedidaOfPiezaSetPieza != null) {
                    oldUnidadmedidaOfPiezaSetPieza.getPiezaSet().remove(piezaSetPieza);
                    oldUnidadmedidaOfPiezaSetPieza = em.merge(oldUnidadmedidaOfPiezaSetPieza);
                }
            }
            for (Maquina maquinaSetMaquina : unidadmedida.getMaquinaSet()) {
                Unidadmedida oldIdunidadmedidaOfMaquinaSetMaquina = maquinaSetMaquina.getIdunidadmedida();
                maquinaSetMaquina.setIdunidadmedida(unidadmedida);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
                if (oldIdunidadmedidaOfMaquinaSetMaquina != null) {
                    oldIdunidadmedidaOfMaquinaSetMaquina.getMaquinaSet().remove(maquinaSetMaquina);
                    oldIdunidadmedidaOfMaquinaSetMaquina = em.merge(oldIdunidadmedidaOfMaquinaSetMaquina);
                }
            }
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccion : unidadmedida.getEtapadeproduccionSet()) {
                Unidadmedida oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion = etapadeproduccionSetEtapadeproduccion.getUnidaddemedida();
                etapadeproduccionSetEtapadeproduccion.setUnidaddemedida(unidadmedida);
                etapadeproduccionSetEtapadeproduccion = em.merge(etapadeproduccionSetEtapadeproduccion);
                if (oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion != null) {
                    oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion.getEtapadeproduccionSet().remove(etapadeproduccionSetEtapadeproduccion);
                    oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadmedida(unidadmedida.getIdunidadmedida()) != null) {
                throw new PreexistingEntityException("Unidadmedida " + unidadmedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidadmedida unidadmedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida persistentUnidadmedida = em.find(Unidadmedida.class, unidadmedida.getIdunidadmedida());
            Set<Materiaprima> materiaprimaSetOld = persistentUnidadmedida.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSetNew = unidadmedida.getMateriaprimaSet();
            Set<Pieza> piezaSetOld = persistentUnidadmedida.getPiezaSet();
            Set<Pieza> piezaSetNew = unidadmedida.getPiezaSet();
            Set<Maquina> maquinaSetOld = persistentUnidadmedida.getMaquinaSet();
            Set<Maquina> maquinaSetNew = unidadmedida.getMaquinaSet();
            Set<Etapadeproduccion> etapadeproduccionSetOld = persistentUnidadmedida.getEtapadeproduccionSet();
            Set<Etapadeproduccion> etapadeproduccionSetNew = unidadmedida.getEtapadeproduccionSet();
            Set<Materiaprima> attachedMateriaprimaSetNew = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetNewMateriaprimaToAttach : materiaprimaSetNew) {
                materiaprimaSetNewMateriaprimaToAttach = em.getReference(materiaprimaSetNewMateriaprimaToAttach.getClass(), materiaprimaSetNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSetNew.add(materiaprimaSetNewMateriaprimaToAttach);
            }
            materiaprimaSetNew = attachedMateriaprimaSetNew;
            unidadmedida.setMateriaprimaSet(materiaprimaSetNew);
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            unidadmedida.setPiezaSet(piezaSetNew);
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            unidadmedida.setMaquinaSet(maquinaSetNew);
            Set<Etapadeproduccion> attachedEtapadeproduccionSetNew = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSetNewEtapadeproduccionToAttach : etapadeproduccionSetNew) {
                etapadeproduccionSetNewEtapadeproduccionToAttach = em.getReference(etapadeproduccionSetNewEtapadeproduccionToAttach.getClass(), etapadeproduccionSetNewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSetNew.add(etapadeproduccionSetNewEtapadeproduccionToAttach);
            }
            etapadeproduccionSetNew = attachedEtapadeproduccionSetNew;
            unidadmedida.setEtapadeproduccionSet(etapadeproduccionSetNew);
            unidadmedida = em.merge(unidadmedida);
            for (Materiaprima materiaprimaSetOldMateriaprima : materiaprimaSetOld) {
                if (!materiaprimaSetNew.contains(materiaprimaSetOldMateriaprima)) {
                    materiaprimaSetOldMateriaprima.setUnidadmedida(null);
                    materiaprimaSetOldMateriaprima = em.merge(materiaprimaSetOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSetNewMateriaprima : materiaprimaSetNew) {
                if (!materiaprimaSetOld.contains(materiaprimaSetNewMateriaprima)) {
                    Unidadmedida oldUnidadmedidaOfMateriaprimaSetNewMateriaprima = materiaprimaSetNewMateriaprima.getUnidadmedida();
                    materiaprimaSetNewMateriaprima.setUnidadmedida(unidadmedida);
                    materiaprimaSetNewMateriaprima = em.merge(materiaprimaSetNewMateriaprima);
                    if (oldUnidadmedidaOfMateriaprimaSetNewMateriaprima != null && !oldUnidadmedidaOfMateriaprimaSetNewMateriaprima.equals(unidadmedida)) {
                        oldUnidadmedidaOfMateriaprimaSetNewMateriaprima.getMateriaprimaSet().remove(materiaprimaSetNewMateriaprima);
                        oldUnidadmedidaOfMateriaprimaSetNewMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaSetNewMateriaprima);
                    }
                }
            }
            for (Pieza piezaSetOldPieza : piezaSetOld) {
                if (!piezaSetNew.contains(piezaSetOldPieza)) {
                    piezaSetOldPieza.setUnidadmedida(null);
                    piezaSetOldPieza = em.merge(piezaSetOldPieza);
                }
            }
            for (Pieza piezaSetNewPieza : piezaSetNew) {
                if (!piezaSetOld.contains(piezaSetNewPieza)) {
                    Unidadmedida oldUnidadmedidaOfPiezaSetNewPieza = piezaSetNewPieza.getUnidadmedida();
                    piezaSetNewPieza.setUnidadmedida(unidadmedida);
                    piezaSetNewPieza = em.merge(piezaSetNewPieza);
                    if (oldUnidadmedidaOfPiezaSetNewPieza != null && !oldUnidadmedidaOfPiezaSetNewPieza.equals(unidadmedida)) {
                        oldUnidadmedidaOfPiezaSetNewPieza.getPiezaSet().remove(piezaSetNewPieza);
                        oldUnidadmedidaOfPiezaSetNewPieza = em.merge(oldUnidadmedidaOfPiezaSetNewPieza);
                    }
                }
            }
            for (Maquina maquinaSetOldMaquina : maquinaSetOld) {
                if (!maquinaSetNew.contains(maquinaSetOldMaquina)) {
                    maquinaSetOldMaquina.setIdunidadmedida(null);
                    maquinaSetOldMaquina = em.merge(maquinaSetOldMaquina);
                }
            }
            for (Maquina maquinaSetNewMaquina : maquinaSetNew) {
                if (!maquinaSetOld.contains(maquinaSetNewMaquina)) {
                    Unidadmedida oldIdunidadmedidaOfMaquinaSetNewMaquina = maquinaSetNewMaquina.getIdunidadmedida();
                    maquinaSetNewMaquina.setIdunidadmedida(unidadmedida);
                    maquinaSetNewMaquina = em.merge(maquinaSetNewMaquina);
                    if (oldIdunidadmedidaOfMaquinaSetNewMaquina != null && !oldIdunidadmedidaOfMaquinaSetNewMaquina.equals(unidadmedida)) {
                        oldIdunidadmedidaOfMaquinaSetNewMaquina.getMaquinaSet().remove(maquinaSetNewMaquina);
                        oldIdunidadmedidaOfMaquinaSetNewMaquina = em.merge(oldIdunidadmedidaOfMaquinaSetNewMaquina);
                    }
                }
            }
            for (Etapadeproduccion etapadeproduccionSetOldEtapadeproduccion : etapadeproduccionSetOld) {
                if (!etapadeproduccionSetNew.contains(etapadeproduccionSetOldEtapadeproduccion)) {
                    etapadeproduccionSetOldEtapadeproduccion.setUnidaddemedida(null);
                    etapadeproduccionSetOldEtapadeproduccion = em.merge(etapadeproduccionSetOldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionSetNewEtapadeproduccion : etapadeproduccionSetNew) {
                if (!etapadeproduccionSetOld.contains(etapadeproduccionSetNewEtapadeproduccion)) {
                    Unidadmedida oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion = etapadeproduccionSetNewEtapadeproduccion.getUnidaddemedida();
                    etapadeproduccionSetNewEtapadeproduccion.setUnidaddemedida(unidadmedida);
                    etapadeproduccionSetNewEtapadeproduccion = em.merge(etapadeproduccionSetNewEtapadeproduccion);
                    if (oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion != null && !oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion.equals(unidadmedida)) {
                        oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion.getEtapadeproduccionSet().remove(etapadeproduccionSetNewEtapadeproduccion);
                        oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadmedida.getIdunidadmedida();
                if (findUnidadmedida(id) == null) {
                    throw new NonexistentEntityException("The unidadmedida with id " + id + " no longer exists.");
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
            Unidadmedida unidadmedida;
            try {
                unidadmedida = em.getReference(Unidadmedida.class, id);
                unidadmedida.getIdunidadmedida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadmedida with id " + id + " no longer exists.", enfe);
            }
            Set<Materiaprima> materiaprimaSet = unidadmedida.getMateriaprimaSet();
            for (Materiaprima materiaprimaSetMateriaprima : materiaprimaSet) {
                materiaprimaSetMateriaprima.setUnidadmedida(null);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
            }
            Set<Pieza> piezaSet = unidadmedida.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setUnidadmedida(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Maquina> maquinaSet = unidadmedida.getMaquinaSet();
            for (Maquina maquinaSetMaquina : maquinaSet) {
                maquinaSetMaquina.setIdunidadmedida(null);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
            }
            Set<Etapadeproduccion> etapadeproduccionSet = unidadmedida.getEtapadeproduccionSet();
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccion : etapadeproduccionSet) {
                etapadeproduccionSetEtapadeproduccion.setUnidaddemedida(null);
                etapadeproduccionSetEtapadeproduccion = em.merge(etapadeproduccionSetEtapadeproduccion);
            }
            em.remove(unidadmedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidadmedida> findUnidadmedidaEntities() {
        return findUnidadmedidaEntities(true, -1, -1);
    }

    public List<Unidadmedida> findUnidadmedidaEntities(int maxResults, int firstResult) {
        return findUnidadmedidaEntities(false, maxResults, firstResult);
    }

    private List<Unidadmedida> findUnidadmedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidadmedida.class));
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

    public Unidadmedida findUnidadmedida(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidadmedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadmedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidadmedida> rt = cq.from(Unidadmedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
