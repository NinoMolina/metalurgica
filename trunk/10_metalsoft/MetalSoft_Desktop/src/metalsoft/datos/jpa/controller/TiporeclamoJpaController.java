/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Reclamoproveedor;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;
import metalsoft.datos.jpa.entity.Reclamocliente;
import metalsoft.datos.jpa.entity.Tiporeclamo;

/**
 *
 * @author Nino
 */
public class TiporeclamoJpaController {

    public TiporeclamoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiporeclamo tiporeclamo) throws PreexistingEntityException, Exception {
        if (tiporeclamo.getReclamoproveedorList() == null) {
            tiporeclamo.setReclamoproveedorList(new ArrayList<Reclamoproveedor>());
        }
        if (tiporeclamo.getReclamoempresametalurgicaList() == null) {
            tiporeclamo.setReclamoempresametalurgicaList(new ArrayList<Reclamoempresametalurgica>());
        }
        if (tiporeclamo.getReclamoclienteList() == null) {
            tiporeclamo.setReclamoclienteList(new ArrayList<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reclamoproveedor> attachedReclamoproveedorList = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedorToAttach : tiporeclamo.getReclamoproveedorList()) {
                reclamoproveedorListReclamoproveedorToAttach = em.getReference(reclamoproveedorListReclamoproveedorToAttach.getClass(), reclamoproveedorListReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorList.add(reclamoproveedorListReclamoproveedorToAttach);
            }
            tiporeclamo.setReclamoproveedorList(attachedReclamoproveedorList);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaList = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgicaToAttach : tiporeclamo.getReclamoempresametalurgicaList()) {
                reclamoempresametalurgicaListReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaList.add(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach);
            }
            tiporeclamo.setReclamoempresametalurgicaList(attachedReclamoempresametalurgicaList);
            List<Reclamocliente> attachedReclamoclienteList = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListReclamoclienteToAttach : tiporeclamo.getReclamoclienteList()) {
                reclamoclienteListReclamoclienteToAttach = em.getReference(reclamoclienteListReclamoclienteToAttach.getClass(), reclamoclienteListReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteList.add(reclamoclienteListReclamoclienteToAttach);
            }
            tiporeclamo.setReclamoclienteList(attachedReclamoclienteList);
            em.persist(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : tiporeclamo.getReclamoproveedorList()) {
                Tiporeclamo oldTiporeclamoOfReclamoproveedorListReclamoproveedor = reclamoproveedorListReclamoproveedor.getTiporeclamo();
                reclamoproveedorListReclamoproveedor.setTiporeclamo(tiporeclamo);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
                if (oldTiporeclamoOfReclamoproveedorListReclamoproveedor != null) {
                    oldTiporeclamoOfReclamoproveedorListReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListReclamoproveedor);
                    oldTiporeclamoOfReclamoproveedorListReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorListReclamoproveedor);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : tiporeclamo.getReclamoempresametalurgicaList()) {
                Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaListReclamoempresametalurgica = reclamoempresametalurgicaListReclamoempresametalurgica.getTiporeclamo();
                reclamoempresametalurgicaListReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
                if (oldTiporeclamoOfReclamoempresametalurgicaListReclamoempresametalurgica != null) {
                    oldTiporeclamoOfReclamoempresametalurgicaListReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListReclamoempresametalurgica);
                    oldTiporeclamoOfReclamoempresametalurgicaListReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaListReclamoempresametalurgica);
                }
            }
            for (Reclamocliente reclamoclienteListReclamocliente : tiporeclamo.getReclamoclienteList()) {
                Tiporeclamo oldTiporeclamoOfReclamoclienteListReclamocliente = reclamoclienteListReclamocliente.getTiporeclamo();
                reclamoclienteListReclamocliente.setTiporeclamo(tiporeclamo);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
                if (oldTiporeclamoOfReclamoclienteListReclamocliente != null) {
                    oldTiporeclamoOfReclamoclienteListReclamocliente.getReclamoclienteList().remove(reclamoclienteListReclamocliente);
                    oldTiporeclamoOfReclamoclienteListReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteListReclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiporeclamo(tiporeclamo.getIdtiporeclamo()) != null) {
                throw new PreexistingEntityException("Tiporeclamo " + tiporeclamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiporeclamo tiporeclamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo persistentTiporeclamo = em.find(Tiporeclamo.class, tiporeclamo.getIdtiporeclamo());
            List<Reclamoproveedor> reclamoproveedorListOld = persistentTiporeclamo.getReclamoproveedorList();
            List<Reclamoproveedor> reclamoproveedorListNew = tiporeclamo.getReclamoproveedorList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListOld = persistentTiporeclamo.getReclamoempresametalurgicaList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListNew = tiporeclamo.getReclamoempresametalurgicaList();
            List<Reclamocliente> reclamoclienteListOld = persistentTiporeclamo.getReclamoclienteList();
            List<Reclamocliente> reclamoclienteListNew = tiporeclamo.getReclamoclienteList();
            List<Reclamoproveedor> attachedReclamoproveedorListNew = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedorToAttach : reclamoproveedorListNew) {
                reclamoproveedorListNewReclamoproveedorToAttach = em.getReference(reclamoproveedorListNewReclamoproveedorToAttach.getClass(), reclamoproveedorListNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorListNew.add(reclamoproveedorListNewReclamoproveedorToAttach);
            }
            reclamoproveedorListNew = attachedReclamoproveedorListNew;
            tiporeclamo.setReclamoproveedorList(reclamoproveedorListNew);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaListNew = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaListNew) {
                reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaListNew.add(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaListNew = attachedReclamoempresametalurgicaListNew;
            tiporeclamo.setReclamoempresametalurgicaList(reclamoempresametalurgicaListNew);
            List<Reclamocliente> attachedReclamoclienteListNew = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListNewReclamoclienteToAttach : reclamoclienteListNew) {
                reclamoclienteListNewReclamoclienteToAttach = em.getReference(reclamoclienteListNewReclamoclienteToAttach.getClass(), reclamoclienteListNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteListNew.add(reclamoclienteListNewReclamoclienteToAttach);
            }
            reclamoclienteListNew = attachedReclamoclienteListNew;
            tiporeclamo.setReclamoclienteList(reclamoclienteListNew);
            tiporeclamo = em.merge(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorListOldReclamoproveedor : reclamoproveedorListOld) {
                if (!reclamoproveedorListNew.contains(reclamoproveedorListOldReclamoproveedor)) {
                    reclamoproveedorListOldReclamoproveedor.setTiporeclamo(null);
                    reclamoproveedorListOldReclamoproveedor = em.merge(reclamoproveedorListOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedor : reclamoproveedorListNew) {
                if (!reclamoproveedorListOld.contains(reclamoproveedorListNewReclamoproveedor)) {
                    Tiporeclamo oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor = reclamoproveedorListNewReclamoproveedor.getTiporeclamo();
                    reclamoproveedorListNewReclamoproveedor.setTiporeclamo(tiporeclamo);
                    reclamoproveedorListNewReclamoproveedor = em.merge(reclamoproveedorListNewReclamoproveedor);
                    if (oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor != null && !oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListNewReclamoproveedor);
                        oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorListNewReclamoproveedor);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListOldReclamoempresametalurgica : reclamoempresametalurgicaListOld) {
                if (!reclamoempresametalurgicaListNew.contains(reclamoempresametalurgicaListOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaListOldReclamoempresametalurgica.setTiporeclamo(null);
                    reclamoempresametalurgicaListOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgica : reclamoempresametalurgicaListNew) {
                if (!reclamoempresametalurgicaListOld.contains(reclamoempresametalurgicaListNewReclamoempresametalurgica)) {
                    Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = reclamoempresametalurgicaListNewReclamoempresametalurgica.getTiporeclamo();
                    reclamoempresametalurgicaListNewReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                    reclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                    if (oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica != null && !oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                        oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaListNewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamocliente reclamoclienteListOldReclamocliente : reclamoclienteListOld) {
                if (!reclamoclienteListNew.contains(reclamoclienteListOldReclamocliente)) {
                    reclamoclienteListOldReclamocliente.setTiporeclamo(null);
                    reclamoclienteListOldReclamocliente = em.merge(reclamoclienteListOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteListNewReclamocliente : reclamoclienteListNew) {
                if (!reclamoclienteListOld.contains(reclamoclienteListNewReclamocliente)) {
                    Tiporeclamo oldTiporeclamoOfReclamoclienteListNewReclamocliente = reclamoclienteListNewReclamocliente.getTiporeclamo();
                    reclamoclienteListNewReclamocliente.setTiporeclamo(tiporeclamo);
                    reclamoclienteListNewReclamocliente = em.merge(reclamoclienteListNewReclamocliente);
                    if (oldTiporeclamoOfReclamoclienteListNewReclamocliente != null && !oldTiporeclamoOfReclamoclienteListNewReclamocliente.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoclienteListNewReclamocliente.getReclamoclienteList().remove(reclamoclienteListNewReclamocliente);
                        oldTiporeclamoOfReclamoclienteListNewReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteListNewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tiporeclamo.getIdtiporeclamo();
                if (findTiporeclamo(id) == null) {
                    throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.");
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
            Tiporeclamo tiporeclamo;
            try {
                tiporeclamo = em.getReference(Tiporeclamo.class, id);
                tiporeclamo.getIdtiporeclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.", enfe);
            }
            List<Reclamoproveedor> reclamoproveedorList = tiporeclamo.getReclamoproveedorList();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : reclamoproveedorList) {
                reclamoproveedorListReclamoproveedor.setTiporeclamo(null);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
            }
            List<Reclamoempresametalurgica> reclamoempresametalurgicaList = tiporeclamo.getReclamoempresametalurgicaList();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : reclamoempresametalurgicaList) {
                reclamoempresametalurgicaListReclamoempresametalurgica.setTiporeclamo(null);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
            }
            List<Reclamocliente> reclamoclienteList = tiporeclamo.getReclamoclienteList();
            for (Reclamocliente reclamoclienteListReclamocliente : reclamoclienteList) {
                reclamoclienteListReclamocliente.setTiporeclamo(null);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
            }
            em.remove(tiporeclamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiporeclamo> findTiporeclamoEntities() {
        return findTiporeclamoEntities(true, -1, -1);
    }

    public List<Tiporeclamo> findTiporeclamoEntities(int maxResults, int firstResult) {
        return findTiporeclamoEntities(false, maxResults, firstResult);
    }

    private List<Tiporeclamo> findTiporeclamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiporeclamo.class));
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

    public Tiporeclamo findTiporeclamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiporeclamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiporeclamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiporeclamo> rt = cq.from(Tiporeclamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
