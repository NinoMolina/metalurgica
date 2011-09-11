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
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Reclamoproveedor;
import metalsoft.datos.jpa.entity.Tiporeclamo;
import metalsoft.datos.jpa.entity.Estadoreclamo;
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Detallereclamoproveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class ReclamoproveedorJpaController implements Serializable {

    public ReclamoproveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoproveedor reclamoproveedor) throws PreexistingEntityException, Exception {
        if (reclamoproveedor.getDetallereclamoproveedorList() == null) {
            reclamoproveedor.setDetallereclamoproveedorList(new ArrayList<Detallereclamoproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamo);
            }
            Estadoreclamo idestadoreclamo = reclamoproveedor.getIdestadoreclamo();
            if (idestadoreclamo != null) {
                idestadoreclamo = em.getReference(idestadoreclamo.getClass(), idestadoreclamo.getIdestadoreclamo());
                reclamoproveedor.setIdestadoreclamo(idestadoreclamo);
            }
            Compra compra = reclamoproveedor.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getIdcompra());
                reclamoproveedor.setCompra(compra);
            }
            List<Detallereclamoproveedor> attachedDetallereclamoproveedorList = new ArrayList<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorListDetallereclamoproveedorToAttach : reclamoproveedor.getDetallereclamoproveedorList()) {
                detallereclamoproveedorListDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorListDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorListDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorList.add(detallereclamoproveedorListDetallereclamoproveedorToAttach);
            }
            reclamoproveedor.setDetallereclamoproveedorList(attachedDetallereclamoproveedorList);
            em.persist(reclamoproveedor);
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorList().add(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (idestadoreclamo != null) {
                idestadoreclamo.getReclamoproveedorList().add(reclamoproveedor);
                idestadoreclamo = em.merge(idestadoreclamo);
            }
            if (compra != null) {
                compra.getReclamoproveedorList().add(reclamoproveedor);
                compra = em.merge(compra);
            }
            for (Detallereclamoproveedor detallereclamoproveedorListDetallereclamoproveedor : reclamoproveedor.getDetallereclamoproveedorList()) {
                Reclamoproveedor oldReclamoproveedorOfDetallereclamoproveedorListDetallereclamoproveedor = detallereclamoproveedorListDetallereclamoproveedor.getReclamoproveedor();
                detallereclamoproveedorListDetallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
                detallereclamoproveedorListDetallereclamoproveedor = em.merge(detallereclamoproveedorListDetallereclamoproveedor);
                if (oldReclamoproveedorOfDetallereclamoproveedorListDetallereclamoproveedor != null) {
                    oldReclamoproveedorOfDetallereclamoproveedorListDetallereclamoproveedor.getDetallereclamoproveedorList().remove(detallereclamoproveedorListDetallereclamoproveedor);
                    oldReclamoproveedorOfDetallereclamoproveedorListDetallereclamoproveedor = em.merge(oldReclamoproveedorOfDetallereclamoproveedorListDetallereclamoproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamoproveedor(reclamoproveedor.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamoproveedor " + reclamoproveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamoproveedor reclamoproveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoproveedor persistentReclamoproveedor = em.find(Reclamoproveedor.class, reclamoproveedor.getIdreclamo());
            Tiporeclamo tiporeclamoOld = persistentReclamoproveedor.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamoproveedor.getTiporeclamo();
            Estadoreclamo idestadoreclamoOld = persistentReclamoproveedor.getIdestadoreclamo();
            Estadoreclamo idestadoreclamoNew = reclamoproveedor.getIdestadoreclamo();
            Compra compraOld = persistentReclamoproveedor.getCompra();
            Compra compraNew = reclamoproveedor.getCompra();
            List<Detallereclamoproveedor> detallereclamoproveedorListOld = persistentReclamoproveedor.getDetallereclamoproveedorList();
            List<Detallereclamoproveedor> detallereclamoproveedorListNew = reclamoproveedor.getDetallereclamoproveedorList();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoproveedor detallereclamoproveedorListOldDetallereclamoproveedor : detallereclamoproveedorListOld) {
                if (!detallereclamoproveedorListNew.contains(detallereclamoproveedorListOldDetallereclamoproveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoproveedor " + detallereclamoproveedorListOldDetallereclamoproveedor + " since its reclamoproveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamoNew);
            }
            if (idestadoreclamoNew != null) {
                idestadoreclamoNew = em.getReference(idestadoreclamoNew.getClass(), idestadoreclamoNew.getIdestadoreclamo());
                reclamoproveedor.setIdestadoreclamo(idestadoreclamoNew);
            }
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdcompra());
                reclamoproveedor.setCompra(compraNew);
            }
            List<Detallereclamoproveedor> attachedDetallereclamoproveedorListNew = new ArrayList<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorListNewDetallereclamoproveedorToAttach : detallereclamoproveedorListNew) {
                detallereclamoproveedorListNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorListNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorListNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorListNew.add(detallereclamoproveedorListNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorListNew = attachedDetallereclamoproveedorListNew;
            reclamoproveedor.setDetallereclamoproveedorList(detallereclamoproveedorListNew);
            reclamoproveedor = em.merge(reclamoproveedor);
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoproveedorList().remove(reclamoproveedor);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoproveedorList().add(reclamoproveedor);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (idestadoreclamoOld != null && !idestadoreclamoOld.equals(idestadoreclamoNew)) {
                idestadoreclamoOld.getReclamoproveedorList().remove(reclamoproveedor);
                idestadoreclamoOld = em.merge(idestadoreclamoOld);
            }
            if (idestadoreclamoNew != null && !idestadoreclamoNew.equals(idestadoreclamoOld)) {
                idestadoreclamoNew.getReclamoproveedorList().add(reclamoproveedor);
                idestadoreclamoNew = em.merge(idestadoreclamoNew);
            }
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getReclamoproveedorList().remove(reclamoproveedor);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getReclamoproveedorList().add(reclamoproveedor);
                compraNew = em.merge(compraNew);
            }
            for (Detallereclamoproveedor detallereclamoproveedorListNewDetallereclamoproveedor : detallereclamoproveedorListNew) {
                if (!detallereclamoproveedorListOld.contains(detallereclamoproveedorListNewDetallereclamoproveedor)) {
                    Reclamoproveedor oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor = detallereclamoproveedorListNewDetallereclamoproveedor.getReclamoproveedor();
                    detallereclamoproveedorListNewDetallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
                    detallereclamoproveedorListNewDetallereclamoproveedor = em.merge(detallereclamoproveedorListNewDetallereclamoproveedor);
                    if (oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor != null && !oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor.equals(reclamoproveedor)) {
                        oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor.getDetallereclamoproveedorList().remove(detallereclamoproveedorListNewDetallereclamoproveedor);
                        oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor = em.merge(oldReclamoproveedorOfDetallereclamoproveedorListNewDetallereclamoproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamoproveedor.getIdreclamo();
                if (findReclamoproveedor(id) == null) {
                    throw new NonexistentEntityException("The reclamoproveedor with id " + id + " no longer exists.");
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
            Reclamoproveedor reclamoproveedor;
            try {
                reclamoproveedor = em.getReference(Reclamoproveedor.class, id);
                reclamoproveedor.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamoproveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallereclamoproveedor> detallereclamoproveedorListOrphanCheck = reclamoproveedor.getDetallereclamoproveedorList();
            for (Detallereclamoproveedor detallereclamoproveedorListOrphanCheckDetallereclamoproveedor : detallereclamoproveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoproveedor (" + reclamoproveedor + ") cannot be destroyed since the Detallereclamoproveedor " + detallereclamoproveedorListOrphanCheckDetallereclamoproveedor + " in its detallereclamoproveedorList field has a non-nullable reclamoproveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorList().remove(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Estadoreclamo idestadoreclamo = reclamoproveedor.getIdestadoreclamo();
            if (idestadoreclamo != null) {
                idestadoreclamo.getReclamoproveedorList().remove(reclamoproveedor);
                idestadoreclamo = em.merge(idestadoreclamo);
            }
            Compra compra = reclamoproveedor.getCompra();
            if (compra != null) {
                compra.getReclamoproveedorList().remove(reclamoproveedor);
                compra = em.merge(compra);
            }
            em.remove(reclamoproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamoproveedor> findReclamoproveedorEntities() {
        return findReclamoproveedorEntities(true, -1, -1);
    }

    public List<Reclamoproveedor> findReclamoproveedorEntities(int maxResults, int firstResult) {
        return findReclamoproveedorEntities(false, maxResults, firstResult);
    }

    private List<Reclamoproveedor> findReclamoproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamoproveedor.class));
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

    public Reclamoproveedor findReclamoproveedor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamoproveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamoproveedor> rt = cq.from(Reclamoproveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
