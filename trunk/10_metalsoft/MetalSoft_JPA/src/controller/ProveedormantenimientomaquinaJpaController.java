/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Proveedormantenimientomaquina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Condicioniva;
import entity.Domicilio;
import entity.Responsable;
import entity.Mantenimientocorrectivo;
import java.util.HashSet;
import java.util.Set;
import entity.Mantenimientopreventivo;

/**
 *
 * @author Nino
 */
public class ProveedormantenimientomaquinaJpaController {

    public ProveedormantenimientomaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedormantenimientomaquina proveedormantenimientomaquina) throws PreexistingEntityException, Exception {
        if (proveedormantenimientomaquina.getMantenimientocorrectivoSet() == null) {
            proveedormantenimientomaquina.setMantenimientocorrectivoSet(new HashSet<Mantenimientocorrectivo>());
        }
        if (proveedormantenimientomaquina.getMantenimientopreventivoSet() == null) {
            proveedormantenimientomaquina.setMantenimientopreventivoSet(new HashSet<Mantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicioniva = proveedormantenimientomaquina.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva(condicioniva);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilio);
            }
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsable);
            }
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivoToAttach : proveedormantenimientomaquina.getMantenimientocorrectivoSet()) {
                mantenimientocorrectivoSetMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet.add(mantenimientocorrectivoSetMantenimientocorrectivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientocorrectivoSet(attachedMantenimientocorrectivoSet);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSet = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSetMantenimientopreventivoToAttach : proveedormantenimientomaquina.getMantenimientopreventivoSet()) {
                mantenimientopreventivoSetMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSetMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSetMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSet.add(mantenimientopreventivoSetMantenimientopreventivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientopreventivoSet(attachedMantenimientopreventivoSet);
            em.persist(proveedormantenimientomaquina);
            if (condicioniva != null) {
                condicioniva.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicioniva = em.merge(condicioniva);
            }
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : proveedormantenimientomaquina.getMantenimientocorrectivoSet()) {
                Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientocorrectivoSetMantenimientocorrectivo = mantenimientocorrectivoSetMantenimientocorrectivo.getProveedormantenimiento();
                mantenimientocorrectivoSetMantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientomaquina);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
                if (oldProveedormantenimientoOfMantenimientocorrectivoSetMantenimientocorrectivo != null) {
                    oldProveedormantenimientoOfMantenimientocorrectivoSetMantenimientocorrectivo.getMantenimientocorrectivoSet().remove(mantenimientocorrectivoSetMantenimientocorrectivo);
                    oldProveedormantenimientoOfMantenimientocorrectivoSetMantenimientocorrectivo = em.merge(oldProveedormantenimientoOfMantenimientocorrectivoSetMantenimientocorrectivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoSetMantenimientopreventivo : proveedormantenimientomaquina.getMantenimientopreventivoSet()) {
                Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientopreventivoSetMantenimientopreventivo = mantenimientopreventivoSetMantenimientopreventivo.getProveedormantenimiento();
                mantenimientopreventivoSetMantenimientopreventivo.setProveedormantenimiento(proveedormantenimientomaquina);
                mantenimientopreventivoSetMantenimientopreventivo = em.merge(mantenimientopreventivoSetMantenimientopreventivo);
                if (oldProveedormantenimientoOfMantenimientopreventivoSetMantenimientopreventivo != null) {
                    oldProveedormantenimientoOfMantenimientopreventivoSetMantenimientopreventivo.getMantenimientopreventivoSet().remove(mantenimientopreventivoSetMantenimientopreventivo);
                    oldProveedormantenimientoOfMantenimientopreventivoSetMantenimientopreventivo = em.merge(oldProveedormantenimientoOfMantenimientopreventivoSetMantenimientopreventivo);
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
            Condicioniva condicionivaOld = persistentProveedormantenimientomaquina.getCondicioniva();
            Condicioniva condicionivaNew = proveedormantenimientomaquina.getCondicioniva();
            Domicilio domicilioOld = persistentProveedormantenimientomaquina.getDomicilio();
            Domicilio domicilioNew = proveedormantenimientomaquina.getDomicilio();
            Responsable responsableOld = persistentProveedormantenimientomaquina.getResponsable();
            Responsable responsableNew = proveedormantenimientomaquina.getResponsable();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetOld = persistentProveedormantenimientomaquina.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetNew = proveedormantenimientomaquina.getMantenimientocorrectivoSet();
            Set<Mantenimientopreventivo> mantenimientopreventivoSetOld = persistentProveedormantenimientomaquina.getMantenimientopreventivoSet();
            Set<Mantenimientopreventivo> mantenimientopreventivoSetNew = proveedormantenimientomaquina.getMantenimientopreventivoSet();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva(condicionivaNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilioNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsableNew);
            }
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSetNew = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach : mantenimientocorrectivoSetNew) {
                mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSetNew.add(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSetNew = attachedMantenimientocorrectivoSetNew;
            proveedormantenimientomaquina.setMantenimientocorrectivoSet(mantenimientocorrectivoSetNew);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSetNew = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSetNewMantenimientopreventivoToAttach : mantenimientopreventivoSetNew) {
                mantenimientopreventivoSetNewMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSetNewMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSetNewMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSetNew.add(mantenimientopreventivoSetNewMantenimientopreventivoToAttach);
            }
            mantenimientopreventivoSetNew = attachedMantenimientopreventivoSetNew;
            proveedormantenimientomaquina.setMantenimientopreventivoSet(mantenimientopreventivoSetNew);
            proveedormantenimientomaquina = em.merge(proveedormantenimientomaquina);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilioNew = em.merge(domicilioNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsableNew = em.merge(responsableNew);
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetOldMantenimientocorrectivo : mantenimientocorrectivoSetOld) {
                if (!mantenimientocorrectivoSetNew.contains(mantenimientocorrectivoSetOldMantenimientocorrectivo)) {
                    mantenimientocorrectivoSetOldMantenimientocorrectivo.setProveedormantenimiento(null);
                    mantenimientocorrectivoSetOldMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetOldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivo : mantenimientocorrectivoSetNew) {
                if (!mantenimientocorrectivoSetOld.contains(mantenimientocorrectivoSetNewMantenimientocorrectivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo = mantenimientocorrectivoSetNewMantenimientocorrectivo.getProveedormantenimiento();
                    mantenimientocorrectivoSetNewMantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientomaquina);
                    mantenimientocorrectivoSetNewMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetNewMantenimientocorrectivo);
                    if (oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo != null && !oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo.getMantenimientocorrectivoSet().remove(mantenimientocorrectivoSetNewMantenimientocorrectivo);
                        oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo = em.merge(oldProveedormantenimientoOfMantenimientocorrectivoSetNewMantenimientocorrectivo);
                    }
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoSetOldMantenimientopreventivo : mantenimientopreventivoSetOld) {
                if (!mantenimientopreventivoSetNew.contains(mantenimientopreventivoSetOldMantenimientopreventivo)) {
                    mantenimientopreventivoSetOldMantenimientopreventivo.setProveedormantenimiento(null);
                    mantenimientopreventivoSetOldMantenimientopreventivo = em.merge(mantenimientopreventivoSetOldMantenimientopreventivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoSetNewMantenimientopreventivo : mantenimientopreventivoSetNew) {
                if (!mantenimientopreventivoSetOld.contains(mantenimientopreventivoSetNewMantenimientopreventivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo = mantenimientopreventivoSetNewMantenimientopreventivo.getProveedormantenimiento();
                    mantenimientopreventivoSetNewMantenimientopreventivo.setProveedormantenimiento(proveedormantenimientomaquina);
                    mantenimientopreventivoSetNewMantenimientopreventivo = em.merge(mantenimientopreventivoSetNewMantenimientopreventivo);
                    if (oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo != null && !oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo.getMantenimientopreventivoSet().remove(mantenimientopreventivoSetNewMantenimientopreventivo);
                        oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo = em.merge(oldProveedormantenimientoOfMantenimientopreventivoSetNewMantenimientopreventivo);
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
            Condicioniva condicioniva = proveedormantenimientomaquina.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                condicioniva = em.merge(condicioniva);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet = proveedormantenimientomaquina.getMantenimientocorrectivoSet();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : mantenimientocorrectivoSet) {
                mantenimientocorrectivoSetMantenimientocorrectivo.setProveedormantenimiento(null);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
            }
            Set<Mantenimientopreventivo> mantenimientopreventivoSet = proveedormantenimientomaquina.getMantenimientopreventivoSet();
            for (Mantenimientopreventivo mantenimientopreventivoSetMantenimientopreventivo : mantenimientopreventivoSet) {
                mantenimientopreventivoSetMantenimientopreventivo.setProveedormantenimiento(null);
                mantenimientopreventivoSetMantenimientopreventivo = em.merge(mantenimientopreventivoSetMantenimientopreventivo);
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
