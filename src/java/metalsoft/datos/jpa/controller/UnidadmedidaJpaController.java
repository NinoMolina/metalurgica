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
import metalsoft.datos.jpa.entity.Materiaprima;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Unidadmedida;

/**
 *
 * @author Nino
 */
public class UnidadmedidaJpaController implements Serializable {

    public UnidadmedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidadmedida unidadmedida) throws PreexistingEntityException, Exception {
        if (unidadmedida.getMateriaprimaList() == null) {
            unidadmedida.setMateriaprimaList(new ArrayList<Materiaprima>());
        }
        if (unidadmedida.getPiezaList() == null) {
            unidadmedida.setPiezaList(new ArrayList<Pieza>());
        }
        if (unidadmedida.getMaquinaList() == null) {
            unidadmedida.setMaquinaList(new ArrayList<Maquina>());
        }
        if (unidadmedida.getEtapadeproduccionList() == null) {
            unidadmedida.setEtapadeproduccionList(new ArrayList<Etapadeproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materiaprima> attachedMateriaprimaList = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListMateriaprimaToAttach : unidadmedida.getMateriaprimaList()) {
                materiaprimaListMateriaprimaToAttach = em.getReference(materiaprimaListMateriaprimaToAttach.getClass(), materiaprimaListMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaList.add(materiaprimaListMateriaprimaToAttach);
            }
            unidadmedida.setMateriaprimaList(attachedMateriaprimaList);
            List<Pieza> attachedPiezaList = new ArrayList<Pieza>();
            for (Pieza piezaListPiezaToAttach : unidadmedida.getPiezaList()) {
                piezaListPiezaToAttach = em.getReference(piezaListPiezaToAttach.getClass(), piezaListPiezaToAttach.getIdpieza());
                attachedPiezaList.add(piezaListPiezaToAttach);
            }
            unidadmedida.setPiezaList(attachedPiezaList);
            List<Maquina> attachedMaquinaList = new ArrayList<Maquina>();
            for (Maquina maquinaListMaquinaToAttach : unidadmedida.getMaquinaList()) {
                maquinaListMaquinaToAttach = em.getReference(maquinaListMaquinaToAttach.getClass(), maquinaListMaquinaToAttach.getIdmaquina());
                attachedMaquinaList.add(maquinaListMaquinaToAttach);
            }
            unidadmedida.setMaquinaList(attachedMaquinaList);
            List<Etapadeproduccion> attachedEtapadeproduccionList = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccionToAttach : unidadmedida.getEtapadeproduccionList()) {
                etapadeproduccionListEtapadeproduccionToAttach = em.getReference(etapadeproduccionListEtapadeproduccionToAttach.getClass(), etapadeproduccionListEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionList.add(etapadeproduccionListEtapadeproduccionToAttach);
            }
            unidadmedida.setEtapadeproduccionList(attachedEtapadeproduccionList);
            em.persist(unidadmedida);
            for (Materiaprima materiaprimaListMateriaprima : unidadmedida.getMateriaprimaList()) {
                Unidadmedida oldUnidadmedidaOfMateriaprimaListMateriaprima = materiaprimaListMateriaprima.getUnidadmedida();
                materiaprimaListMateriaprima.setUnidadmedida(unidadmedida);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
                if (oldUnidadmedidaOfMateriaprimaListMateriaprima != null) {
                    oldUnidadmedidaOfMateriaprimaListMateriaprima.getMateriaprimaList().remove(materiaprimaListMateriaprima);
                    oldUnidadmedidaOfMateriaprimaListMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaListMateriaprima);
                }
            }
            for (Pieza piezaListPieza : unidadmedida.getPiezaList()) {
                Unidadmedida oldUnidadmedidaOfPiezaListPieza = piezaListPieza.getUnidadmedida();
                piezaListPieza.setUnidadmedida(unidadmedida);
                piezaListPieza = em.merge(piezaListPieza);
                if (oldUnidadmedidaOfPiezaListPieza != null) {
                    oldUnidadmedidaOfPiezaListPieza.getPiezaList().remove(piezaListPieza);
                    oldUnidadmedidaOfPiezaListPieza = em.merge(oldUnidadmedidaOfPiezaListPieza);
                }
            }
            for (Maquina maquinaListMaquina : unidadmedida.getMaquinaList()) {
                Unidadmedida oldIdunidadmedidaOfMaquinaListMaquina = maquinaListMaquina.getIdunidadmedida();
                maquinaListMaquina.setIdunidadmedida(unidadmedida);
                maquinaListMaquina = em.merge(maquinaListMaquina);
                if (oldIdunidadmedidaOfMaquinaListMaquina != null) {
                    oldIdunidadmedidaOfMaquinaListMaquina.getMaquinaList().remove(maquinaListMaquina);
                    oldIdunidadmedidaOfMaquinaListMaquina = em.merge(oldIdunidadmedidaOfMaquinaListMaquina);
                }
            }
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : unidadmedida.getEtapadeproduccionList()) {
                Unidadmedida oldUnidaddemedidaOfEtapadeproduccionListEtapadeproduccion = etapadeproduccionListEtapadeproduccion.getUnidaddemedida();
                etapadeproduccionListEtapadeproduccion.setUnidaddemedida(unidadmedida);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
                if (oldUnidaddemedidaOfEtapadeproduccionListEtapadeproduccion != null) {
                    oldUnidaddemedidaOfEtapadeproduccionListEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListEtapadeproduccion);
                    oldUnidaddemedidaOfEtapadeproduccionListEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionListEtapadeproduccion);
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
            List<Materiaprima> materiaprimaListOld = persistentUnidadmedida.getMateriaprimaList();
            List<Materiaprima> materiaprimaListNew = unidadmedida.getMateriaprimaList();
            List<Pieza> piezaListOld = persistentUnidadmedida.getPiezaList();
            List<Pieza> piezaListNew = unidadmedida.getPiezaList();
            List<Maquina> maquinaListOld = persistentUnidadmedida.getMaquinaList();
            List<Maquina> maquinaListNew = unidadmedida.getMaquinaList();
            List<Etapadeproduccion> etapadeproduccionListOld = persistentUnidadmedida.getEtapadeproduccionList();
            List<Etapadeproduccion> etapadeproduccionListNew = unidadmedida.getEtapadeproduccionList();
            List<Materiaprima> attachedMateriaprimaListNew = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListNewMateriaprimaToAttach : materiaprimaListNew) {
                materiaprimaListNewMateriaprimaToAttach = em.getReference(materiaprimaListNewMateriaprimaToAttach.getClass(), materiaprimaListNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaListNew.add(materiaprimaListNewMateriaprimaToAttach);
            }
            materiaprimaListNew = attachedMateriaprimaListNew;
            unidadmedida.setMateriaprimaList(materiaprimaListNew);
            List<Pieza> attachedPiezaListNew = new ArrayList<Pieza>();
            for (Pieza piezaListNewPiezaToAttach : piezaListNew) {
                piezaListNewPiezaToAttach = em.getReference(piezaListNewPiezaToAttach.getClass(), piezaListNewPiezaToAttach.getIdpieza());
                attachedPiezaListNew.add(piezaListNewPiezaToAttach);
            }
            piezaListNew = attachedPiezaListNew;
            unidadmedida.setPiezaList(piezaListNew);
            List<Maquina> attachedMaquinaListNew = new ArrayList<Maquina>();
            for (Maquina maquinaListNewMaquinaToAttach : maquinaListNew) {
                maquinaListNewMaquinaToAttach = em.getReference(maquinaListNewMaquinaToAttach.getClass(), maquinaListNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaListNew.add(maquinaListNewMaquinaToAttach);
            }
            maquinaListNew = attachedMaquinaListNew;
            unidadmedida.setMaquinaList(maquinaListNew);
            List<Etapadeproduccion> attachedEtapadeproduccionListNew = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccionToAttach : etapadeproduccionListNew) {
                etapadeproduccionListNewEtapadeproduccionToAttach = em.getReference(etapadeproduccionListNewEtapadeproduccionToAttach.getClass(), etapadeproduccionListNewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionListNew.add(etapadeproduccionListNewEtapadeproduccionToAttach);
            }
            etapadeproduccionListNew = attachedEtapadeproduccionListNew;
            unidadmedida.setEtapadeproduccionList(etapadeproduccionListNew);
            unidadmedida = em.merge(unidadmedida);
            for (Materiaprima materiaprimaListOldMateriaprima : materiaprimaListOld) {
                if (!materiaprimaListNew.contains(materiaprimaListOldMateriaprima)) {
                    materiaprimaListOldMateriaprima.setUnidadmedida(null);
                    materiaprimaListOldMateriaprima = em.merge(materiaprimaListOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaListNewMateriaprima : materiaprimaListNew) {
                if (!materiaprimaListOld.contains(materiaprimaListNewMateriaprima)) {
                    Unidadmedida oldUnidadmedidaOfMateriaprimaListNewMateriaprima = materiaprimaListNewMateriaprima.getUnidadmedida();
                    materiaprimaListNewMateriaprima.setUnidadmedida(unidadmedida);
                    materiaprimaListNewMateriaprima = em.merge(materiaprimaListNewMateriaprima);
                    if (oldUnidadmedidaOfMateriaprimaListNewMateriaprima != null && !oldUnidadmedidaOfMateriaprimaListNewMateriaprima.equals(unidadmedida)) {
                        oldUnidadmedidaOfMateriaprimaListNewMateriaprima.getMateriaprimaList().remove(materiaprimaListNewMateriaprima);
                        oldUnidadmedidaOfMateriaprimaListNewMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaListNewMateriaprima);
                    }
                }
            }
            for (Pieza piezaListOldPieza : piezaListOld) {
                if (!piezaListNew.contains(piezaListOldPieza)) {
                    piezaListOldPieza.setUnidadmedida(null);
                    piezaListOldPieza = em.merge(piezaListOldPieza);
                }
            }
            for (Pieza piezaListNewPieza : piezaListNew) {
                if (!piezaListOld.contains(piezaListNewPieza)) {
                    Unidadmedida oldUnidadmedidaOfPiezaListNewPieza = piezaListNewPieza.getUnidadmedida();
                    piezaListNewPieza.setUnidadmedida(unidadmedida);
                    piezaListNewPieza = em.merge(piezaListNewPieza);
                    if (oldUnidadmedidaOfPiezaListNewPieza != null && !oldUnidadmedidaOfPiezaListNewPieza.equals(unidadmedida)) {
                        oldUnidadmedidaOfPiezaListNewPieza.getPiezaList().remove(piezaListNewPieza);
                        oldUnidadmedidaOfPiezaListNewPieza = em.merge(oldUnidadmedidaOfPiezaListNewPieza);
                    }
                }
            }
            for (Maquina maquinaListOldMaquina : maquinaListOld) {
                if (!maquinaListNew.contains(maquinaListOldMaquina)) {
                    maquinaListOldMaquina.setIdunidadmedida(null);
                    maquinaListOldMaquina = em.merge(maquinaListOldMaquina);
                }
            }
            for (Maquina maquinaListNewMaquina : maquinaListNew) {
                if (!maquinaListOld.contains(maquinaListNewMaquina)) {
                    Unidadmedida oldIdunidadmedidaOfMaquinaListNewMaquina = maquinaListNewMaquina.getIdunidadmedida();
                    maquinaListNewMaquina.setIdunidadmedida(unidadmedida);
                    maquinaListNewMaquina = em.merge(maquinaListNewMaquina);
                    if (oldIdunidadmedidaOfMaquinaListNewMaquina != null && !oldIdunidadmedidaOfMaquinaListNewMaquina.equals(unidadmedida)) {
                        oldIdunidadmedidaOfMaquinaListNewMaquina.getMaquinaList().remove(maquinaListNewMaquina);
                        oldIdunidadmedidaOfMaquinaListNewMaquina = em.merge(oldIdunidadmedidaOfMaquinaListNewMaquina);
                    }
                }
            }
            for (Etapadeproduccion etapadeproduccionListOldEtapadeproduccion : etapadeproduccionListOld) {
                if (!etapadeproduccionListNew.contains(etapadeproduccionListOldEtapadeproduccion)) {
                    etapadeproduccionListOldEtapadeproduccion.setUnidaddemedida(null);
                    etapadeproduccionListOldEtapadeproduccion = em.merge(etapadeproduccionListOldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccion : etapadeproduccionListNew) {
                if (!etapadeproduccionListOld.contains(etapadeproduccionListNewEtapadeproduccion)) {
                    Unidadmedida oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion = etapadeproduccionListNewEtapadeproduccion.getUnidaddemedida();
                    etapadeproduccionListNewEtapadeproduccion.setUnidaddemedida(unidadmedida);
                    etapadeproduccionListNewEtapadeproduccion = em.merge(etapadeproduccionListNewEtapadeproduccion);
                    if (oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion != null && !oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion.equals(unidadmedida)) {
                        oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListNewEtapadeproduccion);
                        oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionListNewEtapadeproduccion);
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
            List<Materiaprima> materiaprimaList = unidadmedida.getMateriaprimaList();
            for (Materiaprima materiaprimaListMateriaprima : materiaprimaList) {
                materiaprimaListMateriaprima.setUnidadmedida(null);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
            }
            List<Pieza> piezaList = unidadmedida.getPiezaList();
            for (Pieza piezaListPieza : piezaList) {
                piezaListPieza.setUnidadmedida(null);
                piezaListPieza = em.merge(piezaListPieza);
            }
            List<Maquina> maquinaList = unidadmedida.getMaquinaList();
            for (Maquina maquinaListMaquina : maquinaList) {
                maquinaListMaquina.setIdunidadmedida(null);
                maquinaListMaquina = em.merge(maquinaListMaquina);
            }
            List<Etapadeproduccion> etapadeproduccionList = unidadmedida.getEtapadeproduccionList();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : etapadeproduccionList) {
                etapadeproduccionListEtapadeproduccion.setUnidaddemedida(null);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
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
