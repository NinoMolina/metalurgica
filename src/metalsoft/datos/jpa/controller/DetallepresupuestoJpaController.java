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
import metalsoft.datos.jpa.entity.Detallepresupuesto;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.datos.jpa.entity.Presupuesto;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetallepresupuestoJpaController implements Serializable {

    public DetallepresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepresupuesto detallepresupuesto) throws PreexistingEntityException, Exception {
        if (detallepresupuesto.getDetalleproductopresupuestoList() == null) {
            detallepresupuesto.setDetalleproductopresupuestoList(new ArrayList<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdproducto());
                detallepresupuesto.setIdproducto(idproducto);
            }
            Presupuesto idpresupuesto = detallepresupuesto.getIdpresupuesto();
            if (idpresupuesto != null) {
                idpresupuesto = em.getReference(idpresupuesto.getClass(), idpresupuesto.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto(idpresupuesto);
            }
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoList = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuestoToAttach : detallepresupuesto.getDetalleproductopresupuestoList()) {
                detalleproductopresupuestoListDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoList.add(detalleproductopresupuestoListDetalleproductopresupuestoToAttach);
            }
            detallepresupuesto.setDetalleproductopresupuestoList(attachedDetalleproductopresupuestoList);
            em.persist(detallepresupuesto);
            if (idproducto != null) {
                idproducto.getDetallepresupuestoList().add(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            if (idpresupuesto != null) {
                idpresupuesto.getDetallepresupuestoList().add(detallepresupuesto);
                idpresupuesto = em.merge(idpresupuesto);
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : detallepresupuesto.getDetalleproductopresupuestoList()) {
                Detallepresupuesto oldIddetallepresupuestoOfDetalleproductopresupuestoListDetalleproductopresupuesto = detalleproductopresupuestoListDetalleproductopresupuesto.getIddetallepresupuesto();
                detalleproductopresupuestoListDetalleproductopresupuesto.setIddetallepresupuesto(detallepresupuesto);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
                if (oldIddetallepresupuestoOfDetalleproductopresupuestoListDetalleproductopresupuesto != null) {
                    oldIddetallepresupuestoOfDetalleproductopresupuestoListDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListDetalleproductopresupuesto);
                    oldIddetallepresupuestoOfDetalleproductopresupuestoListDetalleproductopresupuesto = em.merge(oldIddetallepresupuestoOfDetalleproductopresupuestoListDetalleproductopresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepresupuesto(detallepresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallepresupuesto " + detallepresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepresupuesto detallepresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepresupuesto persistentDetallepresupuesto = em.find(Detallepresupuesto.class, detallepresupuesto.getIddetalle());
            Producto idproductoOld = persistentDetallepresupuesto.getIdproducto();
            Producto idproductoNew = detallepresupuesto.getIdproducto();
            Presupuesto idpresupuestoOld = persistentDetallepresupuesto.getIdpresupuesto();
            Presupuesto idpresupuestoNew = detallepresupuesto.getIdpresupuesto();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListOld = persistentDetallepresupuesto.getDetalleproductopresupuestoList();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListNew = detallepresupuesto.getDetalleproductopresupuestoList();
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detallepresupuesto.setIdproducto(idproductoNew);
            }
            if (idpresupuestoNew != null) {
                idpresupuestoNew = em.getReference(idpresupuestoNew.getClass(), idpresupuestoNew.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto(idpresupuestoNew);
            }
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoListNew = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoListNew) {
                detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoListNew.add(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoListNew = attachedDetalleproductopresupuestoListNew;
            detallepresupuesto.setDetalleproductopresupuestoList(detalleproductopresupuestoListNew);
            detallepresupuesto = em.merge(detallepresupuesto);
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetallepresupuestoList().remove(detallepresupuesto);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetallepresupuestoList().add(detallepresupuesto);
                idproductoNew = em.merge(idproductoNew);
            }
            if (idpresupuestoOld != null && !idpresupuestoOld.equals(idpresupuestoNew)) {
                idpresupuestoOld.getDetallepresupuestoList().remove(detallepresupuesto);
                idpresupuestoOld = em.merge(idpresupuestoOld);
            }
            if (idpresupuestoNew != null && !idpresupuestoNew.equals(idpresupuestoOld)) {
                idpresupuestoNew.getDetallepresupuestoList().add(detallepresupuesto);
                idpresupuestoNew = em.merge(idpresupuestoNew);
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListOldDetalleproductopresupuesto : detalleproductopresupuestoListOld) {
                if (!detalleproductopresupuestoListNew.contains(detalleproductopresupuestoListOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoListOldDetalleproductopresupuesto.setIddetallepresupuesto(null);
                    detalleproductopresupuestoListOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuesto : detalleproductopresupuestoListNew) {
                if (!detalleproductopresupuestoListOld.contains(detalleproductopresupuestoListNewDetalleproductopresupuesto)) {
                    Detallepresupuesto oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = detalleproductopresupuestoListNewDetalleproductopresupuesto.getIddetallepresupuesto();
                    detalleproductopresupuestoListNewDetalleproductopresupuesto.setIddetallepresupuesto(detallepresupuesto);
                    detalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                    if (oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto != null && !oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.equals(detallepresupuesto)) {
                        oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                        oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(oldIddetallepresupuestoOfDetalleproductopresupuestoListNewDetalleproductopresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallepresupuesto.getIddetalle();
                if (findDetallepresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detallepresupuesto with id " + id + " no longer exists.");
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
            Detallepresupuesto detallepresupuesto;
            try {
                detallepresupuesto = em.getReference(Detallepresupuesto.class, id);
                detallepresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepresupuesto with id " + id + " no longer exists.", enfe);
            }
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetallepresupuestoList().remove(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            Presupuesto idpresupuesto = detallepresupuesto.getIdpresupuesto();
            if (idpresupuesto != null) {
                idpresupuesto.getDetallepresupuestoList().remove(detallepresupuesto);
                idpresupuesto = em.merge(idpresupuesto);
            }
            List<Detalleproductopresupuesto> detalleproductopresupuestoList = detallepresupuesto.getDetalleproductopresupuestoList();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : detalleproductopresupuestoList) {
                detalleproductopresupuestoListDetalleproductopresupuesto.setIddetallepresupuesto(null);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
            }
            em.remove(detallepresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepresupuesto> findDetallepresupuestoEntities() {
        return findDetallepresupuestoEntities(true, -1, -1);
    }

    public List<Detallepresupuesto> findDetallepresupuestoEntities(int maxResults, int firstResult) {
        return findDetallepresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detallepresupuesto> findDetallepresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepresupuesto.class));
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

    public Detallepresupuesto findDetallepresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepresupuesto> rt = cq.from(Detallepresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
