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
import metalsoft.datos.jpa.entity.Estadoreclamo;
import metalsoft.datos.jpa.entity.Reclamoproveedor;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamoempresamantenimiento;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;
import metalsoft.datos.jpa.entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class EstadoreclamoJpaController implements Serializable {

    public EstadoreclamoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoreclamo estadoreclamo) throws PreexistingEntityException, Exception {
        if (estadoreclamo.getReclamoproveedorList() == null) {
            estadoreclamo.setReclamoproveedorList(new ArrayList<Reclamoproveedor>());
        }
        if (estadoreclamo.getReclamoempresamantenimientoList() == null) {
            estadoreclamo.setReclamoempresamantenimientoList(new ArrayList<Reclamoempresamantenimiento>());
        }
        if (estadoreclamo.getReclamoempresametalurgicaList() == null) {
            estadoreclamo.setReclamoempresametalurgicaList(new ArrayList<Reclamoempresametalurgica>());
        }
        if (estadoreclamo.getReclamoclienteList() == null) {
            estadoreclamo.setReclamoclienteList(new ArrayList<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reclamoproveedor> attachedReclamoproveedorList = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedorToAttach : estadoreclamo.getReclamoproveedorList()) {
                reclamoproveedorListReclamoproveedorToAttach = em.getReference(reclamoproveedorListReclamoproveedorToAttach.getClass(), reclamoproveedorListReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorList.add(reclamoproveedorListReclamoproveedorToAttach);
            }
            estadoreclamo.setReclamoproveedorList(attachedReclamoproveedorList);
            List<Reclamoempresamantenimiento> attachedReclamoempresamantenimientoList = new ArrayList<Reclamoempresamantenimiento>();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimientoToAttach : estadoreclamo.getReclamoempresamantenimientoList()) {
                reclamoempresamantenimientoListReclamoempresamantenimientoToAttach = em.getReference(reclamoempresamantenimientoListReclamoempresamantenimientoToAttach.getClass(), reclamoempresamantenimientoListReclamoempresamantenimientoToAttach.getIdreclamo());
                attachedReclamoempresamantenimientoList.add(reclamoempresamantenimientoListReclamoempresamantenimientoToAttach);
            }
            estadoreclamo.setReclamoempresamantenimientoList(attachedReclamoempresamantenimientoList);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaList = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgicaToAttach : estadoreclamo.getReclamoempresametalurgicaList()) {
                reclamoempresametalurgicaListReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaList.add(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach);
            }
            estadoreclamo.setReclamoempresametalurgicaList(attachedReclamoempresametalurgicaList);
            List<Reclamocliente> attachedReclamoclienteList = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListReclamoclienteToAttach : estadoreclamo.getReclamoclienteList()) {
                reclamoclienteListReclamoclienteToAttach = em.getReference(reclamoclienteListReclamoclienteToAttach.getClass(), reclamoclienteListReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteList.add(reclamoclienteListReclamoclienteToAttach);
            }
            estadoreclamo.setReclamoclienteList(attachedReclamoclienteList);
            em.persist(estadoreclamo);
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : estadoreclamo.getReclamoproveedorList()) {
                Estadoreclamo oldIdestadoreclamoOfReclamoproveedorListReclamoproveedor = reclamoproveedorListReclamoproveedor.getIdestadoreclamo();
                reclamoproveedorListReclamoproveedor.setIdestadoreclamo(estadoreclamo);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
                if (oldIdestadoreclamoOfReclamoproveedorListReclamoproveedor != null) {
                    oldIdestadoreclamoOfReclamoproveedorListReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListReclamoproveedor);
                    oldIdestadoreclamoOfReclamoproveedorListReclamoproveedor = em.merge(oldIdestadoreclamoOfReclamoproveedorListReclamoproveedor);
                }
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimiento : estadoreclamo.getReclamoempresamantenimientoList()) {
                Estadoreclamo oldIdestadoreclamoOfReclamoempresamantenimientoListReclamoempresamantenimiento = reclamoempresamantenimientoListReclamoempresamantenimiento.getIdestadoreclamo();
                reclamoempresamantenimientoListReclamoempresamantenimiento.setIdestadoreclamo(estadoreclamo);
                reclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListReclamoempresamantenimiento);
                if (oldIdestadoreclamoOfReclamoempresamantenimientoListReclamoempresamantenimiento != null) {
                    oldIdestadoreclamoOfReclamoempresamantenimientoListReclamoempresamantenimiento.getReclamoempresamantenimientoList().remove(reclamoempresamantenimientoListReclamoempresamantenimiento);
                    oldIdestadoreclamoOfReclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(oldIdestadoreclamoOfReclamoempresamantenimientoListReclamoempresamantenimiento);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : estadoreclamo.getReclamoempresametalurgicaList()) {
                Estadoreclamo oldIdestadoreclamoOfReclamoempresametalurgicaListReclamoempresametalurgica = reclamoempresametalurgicaListReclamoempresametalurgica.getIdestadoreclamo();
                reclamoempresametalurgicaListReclamoempresametalurgica.setIdestadoreclamo(estadoreclamo);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
                if (oldIdestadoreclamoOfReclamoempresametalurgicaListReclamoempresametalurgica != null) {
                    oldIdestadoreclamoOfReclamoempresametalurgicaListReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListReclamoempresametalurgica);
                    oldIdestadoreclamoOfReclamoempresametalurgicaListReclamoempresametalurgica = em.merge(oldIdestadoreclamoOfReclamoempresametalurgicaListReclamoempresametalurgica);
                }
            }
            for (Reclamocliente reclamoclienteListReclamocliente : estadoreclamo.getReclamoclienteList()) {
                Estadoreclamo oldIdestadoreclamoOfReclamoclienteListReclamocliente = reclamoclienteListReclamocliente.getIdestadoreclamo();
                reclamoclienteListReclamocliente.setIdestadoreclamo(estadoreclamo);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
                if (oldIdestadoreclamoOfReclamoclienteListReclamocliente != null) {
                    oldIdestadoreclamoOfReclamoclienteListReclamocliente.getReclamoclienteList().remove(reclamoclienteListReclamocliente);
                    oldIdestadoreclamoOfReclamoclienteListReclamocliente = em.merge(oldIdestadoreclamoOfReclamoclienteListReclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoreclamo(estadoreclamo.getIdestadoreclamo()) != null) {
                throw new PreexistingEntityException("Estadoreclamo " + estadoreclamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoreclamo estadoreclamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoreclamo persistentEstadoreclamo = em.find(Estadoreclamo.class, estadoreclamo.getIdestadoreclamo());
            List<Reclamoproveedor> reclamoproveedorListOld = persistentEstadoreclamo.getReclamoproveedorList();
            List<Reclamoproveedor> reclamoproveedorListNew = estadoreclamo.getReclamoproveedorList();
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoListOld = persistentEstadoreclamo.getReclamoempresamantenimientoList();
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoListNew = estadoreclamo.getReclamoempresamantenimientoList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListOld = persistentEstadoreclamo.getReclamoempresametalurgicaList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListNew = estadoreclamo.getReclamoempresametalurgicaList();
            List<Reclamocliente> reclamoclienteListOld = persistentEstadoreclamo.getReclamoclienteList();
            List<Reclamocliente> reclamoclienteListNew = estadoreclamo.getReclamoclienteList();
            List<Reclamoproveedor> attachedReclamoproveedorListNew = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedorToAttach : reclamoproveedorListNew) {
                reclamoproveedorListNewReclamoproveedorToAttach = em.getReference(reclamoproveedorListNewReclamoproveedorToAttach.getClass(), reclamoproveedorListNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorListNew.add(reclamoproveedorListNewReclamoproveedorToAttach);
            }
            reclamoproveedorListNew = attachedReclamoproveedorListNew;
            estadoreclamo.setReclamoproveedorList(reclamoproveedorListNew);
            List<Reclamoempresamantenimiento> attachedReclamoempresamantenimientoListNew = new ArrayList<Reclamoempresamantenimiento>();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach : reclamoempresamantenimientoListNew) {
                reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach = em.getReference(reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach.getClass(), reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach.getIdreclamo());
                attachedReclamoempresamantenimientoListNew.add(reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach);
            }
            reclamoempresamantenimientoListNew = attachedReclamoempresamantenimientoListNew;
            estadoreclamo.setReclamoempresamantenimientoList(reclamoempresamantenimientoListNew);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaListNew = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaListNew) {
                reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaListNew.add(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaListNew = attachedReclamoempresametalurgicaListNew;
            estadoreclamo.setReclamoempresametalurgicaList(reclamoempresametalurgicaListNew);
            List<Reclamocliente> attachedReclamoclienteListNew = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListNewReclamoclienteToAttach : reclamoclienteListNew) {
                reclamoclienteListNewReclamoclienteToAttach = em.getReference(reclamoclienteListNewReclamoclienteToAttach.getClass(), reclamoclienteListNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteListNew.add(reclamoclienteListNewReclamoclienteToAttach);
            }
            reclamoclienteListNew = attachedReclamoclienteListNew;
            estadoreclamo.setReclamoclienteList(reclamoclienteListNew);
            estadoreclamo = em.merge(estadoreclamo);
            for (Reclamoproveedor reclamoproveedorListOldReclamoproveedor : reclamoproveedorListOld) {
                if (!reclamoproveedorListNew.contains(reclamoproveedorListOldReclamoproveedor)) {
                    reclamoproveedorListOldReclamoproveedor.setIdestadoreclamo(null);
                    reclamoproveedorListOldReclamoproveedor = em.merge(reclamoproveedorListOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedor : reclamoproveedorListNew) {
                if (!reclamoproveedorListOld.contains(reclamoproveedorListNewReclamoproveedor)) {
                    Estadoreclamo oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor = reclamoproveedorListNewReclamoproveedor.getIdestadoreclamo();
                    reclamoproveedorListNewReclamoproveedor.setIdestadoreclamo(estadoreclamo);
                    reclamoproveedorListNewReclamoproveedor = em.merge(reclamoproveedorListNewReclamoproveedor);
                    if (oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor != null && !oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor.equals(estadoreclamo)) {
                        oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListNewReclamoproveedor);
                        oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor = em.merge(oldIdestadoreclamoOfReclamoproveedorListNewReclamoproveedor);
                    }
                }
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListOldReclamoempresamantenimiento : reclamoempresamantenimientoListOld) {
                if (!reclamoempresamantenimientoListNew.contains(reclamoempresamantenimientoListOldReclamoempresamantenimiento)) {
                    reclamoempresamantenimientoListOldReclamoempresamantenimiento.setIdestadoreclamo(null);
                    reclamoempresamantenimientoListOldReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListOldReclamoempresamantenimiento);
                }
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListNewReclamoempresamantenimiento : reclamoempresamantenimientoListNew) {
                if (!reclamoempresamantenimientoListOld.contains(reclamoempresamantenimientoListNewReclamoempresamantenimiento)) {
                    Estadoreclamo oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento = reclamoempresamantenimientoListNewReclamoempresamantenimiento.getIdestadoreclamo();
                    reclamoempresamantenimientoListNewReclamoempresamantenimiento.setIdestadoreclamo(estadoreclamo);
                    reclamoempresamantenimientoListNewReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListNewReclamoempresamantenimiento);
                    if (oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento != null && !oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento.equals(estadoreclamo)) {
                        oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento.getReclamoempresamantenimientoList().remove(reclamoempresamantenimientoListNewReclamoempresamantenimiento);
                        oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento = em.merge(oldIdestadoreclamoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListOldReclamoempresametalurgica : reclamoempresametalurgicaListOld) {
                if (!reclamoempresametalurgicaListNew.contains(reclamoempresametalurgicaListOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaListOldReclamoempresametalurgica.setIdestadoreclamo(null);
                    reclamoempresametalurgicaListOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgica : reclamoempresametalurgicaListNew) {
                if (!reclamoempresametalurgicaListOld.contains(reclamoempresametalurgicaListNewReclamoempresametalurgica)) {
                    Estadoreclamo oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = reclamoempresametalurgicaListNewReclamoempresametalurgica.getIdestadoreclamo();
                    reclamoempresametalurgicaListNewReclamoempresametalurgica.setIdestadoreclamo(estadoreclamo);
                    reclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                    if (oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica != null && !oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.equals(estadoreclamo)) {
                        oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                        oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(oldIdestadoreclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamocliente reclamoclienteListOldReclamocliente : reclamoclienteListOld) {
                if (!reclamoclienteListNew.contains(reclamoclienteListOldReclamocliente)) {
                    reclamoclienteListOldReclamocliente.setIdestadoreclamo(null);
                    reclamoclienteListOldReclamocliente = em.merge(reclamoclienteListOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteListNewReclamocliente : reclamoclienteListNew) {
                if (!reclamoclienteListOld.contains(reclamoclienteListNewReclamocliente)) {
                    Estadoreclamo oldIdestadoreclamoOfReclamoclienteListNewReclamocliente = reclamoclienteListNewReclamocliente.getIdestadoreclamo();
                    reclamoclienteListNewReclamocliente.setIdestadoreclamo(estadoreclamo);
                    reclamoclienteListNewReclamocliente = em.merge(reclamoclienteListNewReclamocliente);
                    if (oldIdestadoreclamoOfReclamoclienteListNewReclamocliente != null && !oldIdestadoreclamoOfReclamoclienteListNewReclamocliente.equals(estadoreclamo)) {
                        oldIdestadoreclamoOfReclamoclienteListNewReclamocliente.getReclamoclienteList().remove(reclamoclienteListNewReclamocliente);
                        oldIdestadoreclamoOfReclamoclienteListNewReclamocliente = em.merge(oldIdestadoreclamoOfReclamoclienteListNewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoreclamo.getIdestadoreclamo();
                if (findEstadoreclamo(id) == null) {
                    throw new NonexistentEntityException("The estadoreclamo with id " + id + " no longer exists.");
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
            Estadoreclamo estadoreclamo;
            try {
                estadoreclamo = em.getReference(Estadoreclamo.class, id);
                estadoreclamo.getIdestadoreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoreclamo with id " + id + " no longer exists.", enfe);
            }
            List<Reclamoproveedor> reclamoproveedorList = estadoreclamo.getReclamoproveedorList();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : reclamoproveedorList) {
                reclamoproveedorListReclamoproveedor.setIdestadoreclamo(null);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
            }
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoList = estadoreclamo.getReclamoempresamantenimientoList();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimiento : reclamoempresamantenimientoList) {
                reclamoempresamantenimientoListReclamoempresamantenimiento.setIdestadoreclamo(null);
                reclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListReclamoempresamantenimiento);
            }
            List<Reclamoempresametalurgica> reclamoempresametalurgicaList = estadoreclamo.getReclamoempresametalurgicaList();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : reclamoempresametalurgicaList) {
                reclamoempresametalurgicaListReclamoempresametalurgica.setIdestadoreclamo(null);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
            }
            List<Reclamocliente> reclamoclienteList = estadoreclamo.getReclamoclienteList();
            for (Reclamocliente reclamoclienteListReclamocliente : reclamoclienteList) {
                reclamoclienteListReclamocliente.setIdestadoreclamo(null);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
            }
            em.remove(estadoreclamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoreclamo> findEstadoreclamoEntities() {
        return findEstadoreclamoEntities(true, -1, -1);
    }

    public List<Estadoreclamo> findEstadoreclamoEntities(int maxResults, int firstResult) {
        return findEstadoreclamoEntities(false, maxResults, firstResult);
    }

    private List<Estadoreclamo> findEstadoreclamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoreclamo.class));
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

    public Estadoreclamo findEstadoreclamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoreclamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoreclamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoreclamo> rt = cq.from(Estadoreclamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
