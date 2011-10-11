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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class MantenimientopreventivoJpaController implements Serializable {

    public MantenimientopreventivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientopreventivo mantenimientopreventivo) {
        if (mantenimientopreventivo.getDetallemantenimientopreventivoList() == null) {
            mantenimientopreventivo.setDetallemantenimientopreventivoList(new ArrayList<Detallemantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimiento);
            }
            List<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoList = new ArrayList<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach : mantenimientopreventivo.getDetallemantenimientopreventivoList()) {
                detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach.getIddetalle());
                attachedDetallemantenimientopreventivoList.add(detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach);
            }
            mantenimientopreventivo.setDetallemantenimientopreventivoList(attachedDetallemantenimientopreventivoList);
            em.persist(mantenimientopreventivo);
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoList().add(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListDetallemantenimientopreventivo : mantenimientopreventivo.getDetallemantenimientopreventivoList()) {
                Mantenimientopreventivo oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListDetallemantenimientopreventivo = detallemantenimientopreventivoListDetallemantenimientopreventivo.getIdmantenimientopreventivo();
                detallemantenimientopreventivoListDetallemantenimientopreventivo.setIdmantenimientopreventivo(mantenimientopreventivo);
                detallemantenimientopreventivoListDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListDetallemantenimientopreventivo);
                if (oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListDetallemantenimientopreventivo != null) {
                    oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListDetallemantenimientopreventivo.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivoListDetallemantenimientopreventivo);
                    oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListDetallemantenimientopreventivo = em.merge(oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListDetallemantenimientopreventivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimientopreventivo mantenimientopreventivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientopreventivo persistentMantenimientopreventivo = em.find(Mantenimientopreventivo.class, mantenimientopreventivo.getIdmantenimientopreventivo());
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientopreventivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientopreventivo.getProveedormantenimiento();
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoListOld = persistentMantenimientopreventivo.getDetallemantenimientopreventivoList();
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoListNew = mantenimientopreventivo.getDetallemantenimientopreventivoList();
            List<String> illegalOrphanMessages = null;
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListOldDetallemantenimientopreventivo : detallemantenimientopreventivoListOld) {
                if (!detallemantenimientopreventivoListNew.contains(detallemantenimientopreventivoListOldDetallemantenimientopreventivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallemantenimientopreventivo " + detallemantenimientopreventivoListOldDetallemantenimientopreventivo + " since its idmantenimientopreventivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            List<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoListNew = new ArrayList<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach : detallemantenimientopreventivoListNew) {
                detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach.getIddetalle());
                attachedDetallemantenimientopreventivoListNew.add(detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach);
            }
            detallemantenimientopreventivoListNew = attachedDetallemantenimientopreventivoListNew;
            mantenimientopreventivo.setDetallemantenimientopreventivoList(detallemantenimientopreventivoListNew);
            mantenimientopreventivo = em.merge(mantenimientopreventivo);
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientopreventivoList().remove(mantenimientopreventivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientopreventivoList().add(mantenimientopreventivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListNewDetallemantenimientopreventivo : detallemantenimientopreventivoListNew) {
                if (!detallemantenimientopreventivoListOld.contains(detallemantenimientopreventivoListNewDetallemantenimientopreventivo)) {
                    Mantenimientopreventivo oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo = detallemantenimientopreventivoListNewDetallemantenimientopreventivo.getIdmantenimientopreventivo();
                    detallemantenimientopreventivoListNewDetallemantenimientopreventivo.setIdmantenimientopreventivo(mantenimientopreventivo);
                    detallemantenimientopreventivoListNewDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                    if (oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo != null && !oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo.equals(mantenimientopreventivo)) {
                        oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                        oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo = em.merge(oldIdmantenimientopreventivoOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mantenimientopreventivo.getIdmantenimientopreventivo();
                if (findMantenimientopreventivo(id) == null) {
                    throw new NonexistentEntityException("The mantenimientopreventivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientopreventivo mantenimientopreventivo;
            try {
                mantenimientopreventivo = em.getReference(Mantenimientopreventivo.class, id);
                mantenimientopreventivo.getIdmantenimientopreventivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimientopreventivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoListOrphanCheck = mantenimientopreventivo.getDetallemantenimientopreventivoList();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListOrphanCheckDetallemantenimientopreventivo : detallemantenimientopreventivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientopreventivo (" + mantenimientopreventivo + ") cannot be destroyed since the Detallemantenimientopreventivo " + detallemantenimientopreventivoListOrphanCheckDetallemantenimientopreventivo + " in its detallemantenimientopreventivoList field has a non-nullable idmantenimientopreventivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoList().remove(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            em.remove(mantenimientopreventivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimientopreventivo> findMantenimientopreventivoEntities() {
        return findMantenimientopreventivoEntities(true, -1, -1);
    }

    public List<Mantenimientopreventivo> findMantenimientopreventivoEntities(int maxResults, int firstResult) {
        return findMantenimientopreventivoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimientopreventivo> findMantenimientopreventivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimientopreventivo.class));
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

    public Mantenimientopreventivo findMantenimientopreventivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimientopreventivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientopreventivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimientopreventivo> rt = cq.from(Mantenimientopreventivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
