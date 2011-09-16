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
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Detallepresupuesto;
import metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detallepiezapresupuesto;

/**
 *
 * @author Nino
 */
public class DetalleproductopresupuestoJpaController implements Serializable {

    public DetalleproductopresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductopresupuesto detalleproductopresupuesto) throws PreexistingEntityException, Exception {
        if (detalleproductopresupuesto.getDetallepiezacalidadpresupuestoList() == null) {
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoList(new ArrayList<Detallepiezacalidadpresupuesto>());
        }
        if (detalleproductopresupuesto.getDetallepiezapresupuestoList() == null) {
            detalleproductopresupuesto.setDetallepiezapresupuestoList(new ArrayList<Detallepiezapresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpieza);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprima);
            }
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto = em.getReference(iddetallepresupuesto.getClass(), iddetallepresupuesto.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuesto);
            }
            List<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoList = new ArrayList<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoList()) {
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoList.add(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoList(attachedDetallepiezacalidadpresupuestoList);
            List<Detallepiezapresupuesto> attachedDetallepiezapresupuestoList = new ArrayList<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuestoToAttach : detalleproductopresupuesto.getDetallepiezapresupuestoList()) {
                detallepiezapresupuestoListDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoListDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoListDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoList.add(detallepiezapresupuestoListDetallepiezapresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezapresupuestoList(attachedDetallepiezapresupuestoList);
            em.persist(detalleproductopresupuesto);
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoList()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuesto : detalleproductopresupuesto.getDetallepiezapresupuestoList()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListDetallepiezapresupuesto = detallepiezapresupuestoListDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                detallepiezapresupuestoListDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezapresupuestoListDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListDetallepiezapresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListDetallepiezapresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListDetallepiezapresupuesto.getDetallepiezapresupuestoList().remove(detallepiezapresupuestoListDetallepiezapresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListDetallepiezapresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproductopresupuesto(detalleproductopresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detalleproductopresupuesto " + detalleproductopresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproductopresupuesto detalleproductopresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductopresupuesto persistentDetalleproductopresupuesto = em.find(Detalleproductopresupuesto.class, detalleproductopresupuesto.getIddetalle());
            Pieza idpiezaOld = persistentDetalleproductopresupuesto.getIdpieza();
            Pieza idpiezaNew = detalleproductopresupuesto.getIdpieza();
            Materiaprima idmateriaprimaOld = persistentDetalleproductopresupuesto.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detalleproductopresupuesto.getIdmateriaprima();
            Detallepresupuesto iddetallepresupuestoOld = persistentDetalleproductopresupuesto.getIddetallepresupuesto();
            Detallepresupuesto iddetallepresupuestoNew = detalleproductopresupuesto.getIddetallepresupuesto();
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoListOld = persistentDetalleproductopresupuesto.getDetallepiezacalidadpresupuestoList();
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoListNew = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoList();
            List<Detallepiezapresupuesto> detallepiezapresupuestoListOld = persistentDetalleproductopresupuesto.getDetallepiezapresupuestoList();
            List<Detallepiezapresupuesto> detallepiezapresupuestoListNew = detalleproductopresupuesto.getDetallepiezapresupuestoList();
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpiezaNew);
            }
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprimaNew);
            }
            if (iddetallepresupuestoNew != null) {
                iddetallepresupuestoNew = em.getReference(iddetallepresupuestoNew.getClass(), iddetallepresupuestoNew.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuestoNew);
            }
            List<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoListNew = new ArrayList<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoListNew) {
                detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoListNew.add(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoListNew = attachedDetallepiezacalidadpresupuestoListNew;
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoList(detallepiezacalidadpresupuestoListNew);
            List<Detallepiezapresupuesto> attachedDetallepiezapresupuestoListNew = new ArrayList<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoListNew) {
                detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoListNew.add(detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoListNew = attachedDetallepiezapresupuestoListNew;
            detalleproductopresupuesto.setDetallepiezapresupuestoList(detallepiezapresupuestoListNew);
            detalleproductopresupuesto = em.merge(detalleproductopresupuesto);
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                idpiezaNew = em.merge(idpiezaNew);
            }
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (iddetallepresupuestoOld != null && !iddetallepresupuestoOld.equals(iddetallepresupuestoNew)) {
                iddetallepresupuestoOld.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                iddetallepresupuestoOld = em.merge(iddetallepresupuestoOld);
            }
            if (iddetallepresupuestoNew != null && !iddetallepresupuestoNew.equals(iddetallepresupuestoOld)) {
                iddetallepresupuestoNew.getDetalleproductopresupuestoList().add(detalleproductopresupuesto);
                iddetallepresupuestoNew = em.merge(iddetallepresupuestoNew);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoListOld) {
                if (!detallepiezacalidadpresupuestoListNew.contains(detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoListNew) {
                if (!detallepiezacalidadpresupuestoListOld.contains(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                    detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListOldDetallepiezapresupuesto : detallepiezapresupuestoListOld) {
                if (!detallepiezapresupuestoListNew.contains(detallepiezapresupuestoListOldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoListOldDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezapresupuestoListOldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListOldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListNewDetallepiezapresupuesto : detallepiezapresupuestoListNew) {
                if (!detallepiezapresupuestoListOld.contains(detallepiezapresupuestoListNewDetallepiezapresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto = detallepiezapresupuestoListNewDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                    detallepiezapresupuestoListNewDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezapresupuestoListNewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListNewDetallepiezapresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto.getDetallepiezapresupuestoList().remove(detallepiezapresupuestoListNewDetallepiezapresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoListNewDetallepiezapresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleproductopresupuesto.getIddetalle();
                if (findDetalleproductopresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.");
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
            Detalleproductopresupuesto detalleproductopresupuesto;
            try {
                detalleproductopresupuesto = em.getReference(Detalleproductopresupuesto.class, id);
                detalleproductopresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.", enfe);
            }
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoList = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoList();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoList) {
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
            }
            List<Detallepiezapresupuesto> detallepiezapresupuestoList = detalleproductopresupuesto.getDetallepiezapresupuestoList();
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuesto : detallepiezapresupuestoList) {
                detallepiezapresupuestoListDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezapresupuestoListDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListDetallepiezapresupuesto);
            }
            em.remove(detalleproductopresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities() {
        return findDetalleproductopresupuestoEntities(true, -1, -1);
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(int maxResults, int firstResult) {
        return findDetalleproductopresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproductopresupuesto.class));
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

    public Detalleproductopresupuesto findDetalleproductopresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproductopresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductopresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproductopresupuesto> rt = cq.from(Detalleproductopresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
