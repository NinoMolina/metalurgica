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
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;

/**
 *
 * @author Nino
 */
public class ProveedormantenimientomaquinaJpaController implements Serializable {

    public ProveedormantenimientomaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedormantenimientomaquina proveedormantenimientomaquina) throws PreexistingEntityException, Exception {
        if (proveedormantenimientomaquina.getMantenimientocorrectivoList() == null) {
            proveedormantenimientomaquina.setMantenimientocorrectivoList(new ArrayList<Mantenimientocorrectivo>());
        }
        if (proveedormantenimientomaquina.getMantenimientopreventivoList() == null) {
            proveedormantenimientomaquina.setMantenimientopreventivoList(new ArrayList<Mantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsable);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilio);
            }
            Condicioniva condicioniva = proveedormantenimientomaquina.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva(condicioniva);
            }
            List<Mantenimientocorrectivo> attachedMantenimientocorrectivoList = new ArrayList<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivoToAttach : proveedormantenimientomaquina.getMantenimientocorrectivoList()) {
                mantenimientocorrectivoListMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoListMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoListMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoList.add(mantenimientocorrectivoListMantenimientocorrectivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientocorrectivoList(attachedMantenimientocorrectivoList);
            List<Mantenimientopreventivo> attachedMantenimientopreventivoList = new ArrayList<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivoToAttach : proveedormantenimientomaquina.getMantenimientopreventivoList()) {
                mantenimientopreventivoListMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoListMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoListMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoList.add(mantenimientopreventivoListMantenimientopreventivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientopreventivoList(attachedMantenimientopreventivoList);
            em.persist(proveedormantenimientomaquina);
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            if (condicioniva != null) {
                condicioniva.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                condicioniva = em.merge(condicioniva);
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivo : proveedormantenimientomaquina.getMantenimientocorrectivoList()) {
                Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientocorrectivoListMantenimientocorrectivo = mantenimientocorrectivoListMantenimientocorrectivo.getProveedormantenimiento();
                mantenimientocorrectivoListMantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientomaquina);
                mantenimientocorrectivoListMantenimientocorrectivo = em.merge(mantenimientocorrectivoListMantenimientocorrectivo);
                if (oldProveedormantenimientoOfMantenimientocorrectivoListMantenimientocorrectivo != null) {
                    oldProveedormantenimientoOfMantenimientocorrectivoListMantenimientocorrectivo.getMantenimientocorrectivoList().remove(mantenimientocorrectivoListMantenimientocorrectivo);
                    oldProveedormantenimientoOfMantenimientocorrectivoListMantenimientocorrectivo = em.merge(oldProveedormantenimientoOfMantenimientocorrectivoListMantenimientocorrectivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivo : proveedormantenimientomaquina.getMantenimientopreventivoList()) {
                Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientopreventivoListMantenimientopreventivo = mantenimientopreventivoListMantenimientopreventivo.getProveedormantenimiento();
                mantenimientopreventivoListMantenimientopreventivo.setProveedormantenimiento(proveedormantenimientomaquina);
                mantenimientopreventivoListMantenimientopreventivo = em.merge(mantenimientopreventivoListMantenimientopreventivo);
                if (oldProveedormantenimientoOfMantenimientopreventivoListMantenimientopreventivo != null) {
                    oldProveedormantenimientoOfMantenimientopreventivoListMantenimientopreventivo.getMantenimientopreventivoList().remove(mantenimientopreventivoListMantenimientopreventivo);
                    oldProveedormantenimientoOfMantenimientopreventivoListMantenimientopreventivo = em.merge(oldProveedormantenimientoOfMantenimientopreventivoListMantenimientopreventivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedormantenimientomaquina(proveedormantenimientomaquina.getIdproveedormantenimiento()) != null) {
                throw new PreexistingEntityException("Proveedormantenimientomaquina " + proveedormantenimientomaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedormantenimientomaquina proveedormantenimientomaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedormantenimientomaquina persistentProveedormantenimientomaquina = em.find(Proveedormantenimientomaquina.class, proveedormantenimientomaquina.getIdproveedormantenimiento());
            Responsable responsableOld = persistentProveedormantenimientomaquina.getResponsable();
            Responsable responsableNew = proveedormantenimientomaquina.getResponsable();
            Domicilio domicilioOld = persistentProveedormantenimientomaquina.getDomicilio();
            Domicilio domicilioNew = proveedormantenimientomaquina.getDomicilio();
            Condicioniva condicionivaOld = persistentProveedormantenimientomaquina.getCondicioniva();
            Condicioniva condicionivaNew = proveedormantenimientomaquina.getCondicioniva();
            List<Mantenimientocorrectivo> mantenimientocorrectivoListOld = persistentProveedormantenimientomaquina.getMantenimientocorrectivoList();
            List<Mantenimientocorrectivo> mantenimientocorrectivoListNew = proveedormantenimientomaquina.getMantenimientocorrectivoList();
            List<Mantenimientopreventivo> mantenimientopreventivoListOld = persistentProveedormantenimientomaquina.getMantenimientopreventivoList();
            List<Mantenimientopreventivo> mantenimientopreventivoListNew = proveedormantenimientomaquina.getMantenimientopreventivoList();
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsableNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilioNew);
            }
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva(condicionivaNew);
            }
            List<Mantenimientocorrectivo> attachedMantenimientocorrectivoListNew = new ArrayList<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoListNewMantenimientocorrectivoToAttach : mantenimientocorrectivoListNew) {
                mantenimientocorrectivoListNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoListNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoListNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoListNew.add(mantenimientocorrectivoListNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoListNew = attachedMantenimientocorrectivoListNew;
            proveedormantenimientomaquina.setMantenimientocorrectivoList(mantenimientocorrectivoListNew);
            List<Mantenimientopreventivo> attachedMantenimientopreventivoListNew = new ArrayList<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoListNewMantenimientopreventivoToAttach : mantenimientopreventivoListNew) {
                mantenimientopreventivoListNewMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoListNewMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoListNewMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoListNew.add(mantenimientopreventivoListNewMantenimientopreventivoToAttach);
            }
            mantenimientopreventivoListNew = attachedMantenimientopreventivoListNew;
            proveedormantenimientomaquina.setMantenimientopreventivoList(mantenimientopreventivoListNew);
            proveedormantenimientomaquina = em.merge(proveedormantenimientomaquina);
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                responsableNew = em.merge(responsableNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                domicilioNew = em.merge(domicilioNew);
            }
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getProveedormantenimientomaquinaList().add(proveedormantenimientomaquina);
                condicionivaNew = em.merge(condicionivaNew);
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListOldMantenimientocorrectivo : mantenimientocorrectivoListOld) {
                if (!mantenimientocorrectivoListNew.contains(mantenimientocorrectivoListOldMantenimientocorrectivo)) {
                    mantenimientocorrectivoListOldMantenimientocorrectivo.setProveedormantenimiento(null);
                    mantenimientocorrectivoListOldMantenimientocorrectivo = em.merge(mantenimientocorrectivoListOldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoListNewMantenimientocorrectivo : mantenimientocorrectivoListNew) {
                if (!mantenimientocorrectivoListOld.contains(mantenimientocorrectivoListNewMantenimientocorrectivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo = mantenimientocorrectivoListNewMantenimientocorrectivo.getProveedormantenimiento();
                    mantenimientocorrectivoListNewMantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientomaquina);
                    mantenimientocorrectivoListNewMantenimientocorrectivo = em.merge(mantenimientocorrectivoListNewMantenimientocorrectivo);
                    if (oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo != null && !oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo.getMantenimientocorrectivoList().remove(mantenimientocorrectivoListNewMantenimientocorrectivo);
                        oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo = em.merge(oldProveedormantenimientoOfMantenimientocorrectivoListNewMantenimientocorrectivo);
                    }
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoListOldMantenimientopreventivo : mantenimientopreventivoListOld) {
                if (!mantenimientopreventivoListNew.contains(mantenimientopreventivoListOldMantenimientopreventivo)) {
                    mantenimientopreventivoListOldMantenimientopreventivo.setProveedormantenimiento(null);
                    mantenimientopreventivoListOldMantenimientopreventivo = em.merge(mantenimientopreventivoListOldMantenimientopreventivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoListNewMantenimientopreventivo : mantenimientopreventivoListNew) {
                if (!mantenimientopreventivoListOld.contains(mantenimientopreventivoListNewMantenimientopreventivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo = mantenimientopreventivoListNewMantenimientopreventivo.getProveedormantenimiento();
                    mantenimientopreventivoListNewMantenimientopreventivo.setProveedormantenimiento(proveedormantenimientomaquina);
                    mantenimientopreventivoListNewMantenimientopreventivo = em.merge(mantenimientopreventivoListNewMantenimientopreventivo);
                    if (oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo != null && !oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo.getMantenimientopreventivoList().remove(mantenimientopreventivoListNewMantenimientopreventivo);
                        oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo = em.merge(oldProveedormantenimientoOfMantenimientopreventivoListNewMantenimientopreventivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proveedormantenimientomaquina.getIdproveedormantenimiento();
                if (findProveedormantenimientomaquina(id) == null) {
                    throw new NonexistentEntityException("The proveedormantenimientomaquina with id " + id + " no longer exists.");
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
            Proveedormantenimientomaquina proveedormantenimientomaquina;
            try {
                proveedormantenimientomaquina = em.getReference(Proveedormantenimientomaquina.class, id);
                proveedormantenimientomaquina.getIdproveedormantenimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedormantenimientomaquina with id " + id + " no longer exists.", enfe);
            }
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            Condicioniva condicioniva = proveedormantenimientomaquina.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquina);
                condicioniva = em.merge(condicioniva);
            }
            List<Mantenimientocorrectivo> mantenimientocorrectivoList = proveedormantenimientomaquina.getMantenimientocorrectivoList();
            for (Mantenimientocorrectivo mantenimientocorrectivoListMantenimientocorrectivo : mantenimientocorrectivoList) {
                mantenimientocorrectivoListMantenimientocorrectivo.setProveedormantenimiento(null);
                mantenimientocorrectivoListMantenimientocorrectivo = em.merge(mantenimientocorrectivoListMantenimientocorrectivo);
            }
            List<Mantenimientopreventivo> mantenimientopreventivoList = proveedormantenimientomaquina.getMantenimientopreventivoList();
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivo : mantenimientopreventivoList) {
                mantenimientopreventivoListMantenimientopreventivo.setProveedormantenimiento(null);
                mantenimientopreventivoListMantenimientopreventivo = em.merge(mantenimientopreventivoListMantenimientopreventivo);
            }
            em.remove(proveedormantenimientomaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedormantenimientomaquina> findProveedormantenimientomaquinaEntities() {
        return findProveedormantenimientomaquinaEntities(true, -1, -1);
    }

    public List<Proveedormantenimientomaquina> findProveedormantenimientomaquinaEntities(int maxResults, int firstResult) {
        return findProveedormantenimientomaquinaEntities(false, maxResults, firstResult);
    }

    private List<Proveedormantenimientomaquina> findProveedormantenimientomaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedormantenimientomaquina.class));
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

    public Proveedormantenimientomaquina findProveedormantenimientomaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedormantenimientomaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedormantenimientomaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedormantenimientomaquina> rt = cq.from(Proveedormantenimientomaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
