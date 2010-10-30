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
        if (proveedormantenimientomaquina.getMantenimientocorrectivoSet1() == null) {
            proveedormantenimientomaquina.setMantenimientocorrectivoSet1(new HashSet<Mantenimientocorrectivo>());
        }
        if (proveedormantenimientomaquina.getMantenimientopreventivoSet() == null) {
            proveedormantenimientomaquina.setMantenimientopreventivoSet(new HashSet<Mantenimientopreventivo>());
        }
        if (proveedormantenimientomaquina.getMantenimientopreventivoSet1() == null) {
            proveedormantenimientomaquina.setMantenimientopreventivoSet1(new HashSet<Mantenimientopreventivo>());
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
            Condicioniva condicioniva1 = proveedormantenimientomaquina.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1 = em.getReference(condicioniva1.getClass(), condicioniva1.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva1(condicioniva1);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = proveedormantenimientomaquina.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio1(domicilio1);
            }
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsable);
            }
            Responsable responsable1 = proveedormantenimientomaquina.getResponsable1();
            if (responsable1 != null) {
                responsable1 = em.getReference(responsable1.getClass(), responsable1.getIdresponsable());
                proveedormantenimientomaquina.setResponsable1(responsable1);
            }
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivoToAttach : proveedormantenimientomaquina.getMantenimientocorrectivoSet()) {
                mantenimientocorrectivoSetMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet.add(mantenimientocorrectivoSetMantenimientocorrectivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientocorrectivoSet(attachedMantenimientocorrectivoSet);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet1 = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1MantenimientocorrectivoToAttach : proveedormantenimientomaquina.getMantenimientocorrectivoSet1()) {
                mantenimientocorrectivoSet1MantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSet1MantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSet1MantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet1.add(mantenimientocorrectivoSet1MantenimientocorrectivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientocorrectivoSet1(attachedMantenimientocorrectivoSet1);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSet = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSetMantenimientopreventivoToAttach : proveedormantenimientomaquina.getMantenimientopreventivoSet()) {
                mantenimientopreventivoSetMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSetMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSetMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSet.add(mantenimientopreventivoSetMantenimientopreventivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientopreventivoSet(attachedMantenimientopreventivoSet);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSet1 = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSet1MantenimientopreventivoToAttach : proveedormantenimientomaquina.getMantenimientopreventivoSet1()) {
                mantenimientopreventivoSet1MantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSet1MantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSet1MantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSet1.add(mantenimientopreventivoSet1MantenimientopreventivoToAttach);
            }
            proveedormantenimientomaquina.setMantenimientopreventivoSet1(attachedMantenimientopreventivoSet1);
            em.persist(proveedormantenimientomaquina);
            if (condicioniva != null) {
                condicioniva.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicioniva = em.merge(condicioniva);
            }
            if (condicioniva1 != null) {
                condicioniva1.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicioniva1 = em.merge(condicioniva1);
            }
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilio1 = em.merge(domicilio1);
            }
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            if (responsable1 != null) {
                responsable1.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsable1 = em.merge(responsable1);
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
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1Mantenimientocorrectivo : proveedormantenimientomaquina.getMantenimientocorrectivoSet1()) {
                Proveedormantenimientomaquina oldProveedormantenimiento1OfMantenimientocorrectivoSet1Mantenimientocorrectivo = mantenimientocorrectivoSet1Mantenimientocorrectivo.getProveedormantenimiento1();
                mantenimientocorrectivoSet1Mantenimientocorrectivo.setProveedormantenimiento1(proveedormantenimientomaquina);
                mantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1Mantenimientocorrectivo);
                if (oldProveedormantenimiento1OfMantenimientocorrectivoSet1Mantenimientocorrectivo != null) {
                    oldProveedormantenimiento1OfMantenimientocorrectivoSet1Mantenimientocorrectivo.getMantenimientocorrectivoSet1().remove(mantenimientocorrectivoSet1Mantenimientocorrectivo);
                    oldProveedormantenimiento1OfMantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(oldProveedormantenimiento1OfMantenimientocorrectivoSet1Mantenimientocorrectivo);
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
            for (Mantenimientopreventivo mantenimientopreventivoSet1Mantenimientopreventivo : proveedormantenimientomaquina.getMantenimientopreventivoSet1()) {
                Proveedormantenimientomaquina oldProveedormantenimiento1OfMantenimientopreventivoSet1Mantenimientopreventivo = mantenimientopreventivoSet1Mantenimientopreventivo.getProveedormantenimiento1();
                mantenimientopreventivoSet1Mantenimientopreventivo.setProveedormantenimiento1(proveedormantenimientomaquina);
                mantenimientopreventivoSet1Mantenimientopreventivo = em.merge(mantenimientopreventivoSet1Mantenimientopreventivo);
                if (oldProveedormantenimiento1OfMantenimientopreventivoSet1Mantenimientopreventivo != null) {
                    oldProveedormantenimiento1OfMantenimientopreventivoSet1Mantenimientopreventivo.getMantenimientopreventivoSet1().remove(mantenimientopreventivoSet1Mantenimientopreventivo);
                    oldProveedormantenimiento1OfMantenimientopreventivoSet1Mantenimientopreventivo = em.merge(oldProveedormantenimiento1OfMantenimientopreventivoSet1Mantenimientopreventivo);
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
            Condicioniva condicioniva1Old = persistentProveedormantenimientomaquina.getCondicioniva1();
            Condicioniva condicioniva1New = proveedormantenimientomaquina.getCondicioniva1();
            Domicilio domicilioOld = persistentProveedormantenimientomaquina.getDomicilio();
            Domicilio domicilioNew = proveedormantenimientomaquina.getDomicilio();
            Domicilio domicilio1Old = persistentProveedormantenimientomaquina.getDomicilio1();
            Domicilio domicilio1New = proveedormantenimientomaquina.getDomicilio1();
            Responsable responsableOld = persistentProveedormantenimientomaquina.getResponsable();
            Responsable responsableNew = proveedormantenimientomaquina.getResponsable();
            Responsable responsable1Old = persistentProveedormantenimientomaquina.getResponsable1();
            Responsable responsable1New = proveedormantenimientomaquina.getResponsable1();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetOld = persistentProveedormantenimientomaquina.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSetNew = proveedormantenimientomaquina.getMantenimientocorrectivoSet();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1Old = persistentProveedormantenimientomaquina.getMantenimientocorrectivoSet1();
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1New = proveedormantenimientomaquina.getMantenimientocorrectivoSet1();
            Set<Mantenimientopreventivo> mantenimientopreventivoSetOld = persistentProveedormantenimientomaquina.getMantenimientopreventivoSet();
            Set<Mantenimientopreventivo> mantenimientopreventivoSetNew = proveedormantenimientomaquina.getMantenimientopreventivoSet();
            Set<Mantenimientopreventivo> mantenimientopreventivoSet1Old = persistentProveedormantenimientomaquina.getMantenimientopreventivoSet1();
            Set<Mantenimientopreventivo> mantenimientopreventivoSet1New = proveedormantenimientomaquina.getMantenimientopreventivoSet1();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva(condicionivaNew);
            }
            if (condicioniva1New != null) {
                condicioniva1New = em.getReference(condicioniva1New.getClass(), condicioniva1New.getIdcondicioniva());
                proveedormantenimientomaquina.setCondicioniva1(condicioniva1New);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                proveedormantenimientomaquina.setDomicilio1(domicilio1New);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedormantenimientomaquina.setResponsable(responsableNew);
            }
            if (responsable1New != null) {
                responsable1New = em.getReference(responsable1New.getClass(), responsable1New.getIdresponsable());
                proveedormantenimientomaquina.setResponsable1(responsable1New);
            }
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSetNew = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach : mantenimientocorrectivoSetNew) {
                mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSetNew.add(mantenimientocorrectivoSetNewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSetNew = attachedMantenimientocorrectivoSetNew;
            proveedormantenimientomaquina.setMantenimientocorrectivoSet(mantenimientocorrectivoSetNew);
            Set<Mantenimientocorrectivo> attachedMantenimientocorrectivoSet1New = new HashSet<Mantenimientocorrectivo>();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach : mantenimientocorrectivoSet1New) {
                mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach = em.getReference(mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach.getClass(), mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach.getIdmantenimientocorrectivo());
                attachedMantenimientocorrectivoSet1New.add(mantenimientocorrectivoSet1NewMantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivoSet1New = attachedMantenimientocorrectivoSet1New;
            proveedormantenimientomaquina.setMantenimientocorrectivoSet1(mantenimientocorrectivoSet1New);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSetNew = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSetNewMantenimientopreventivoToAttach : mantenimientopreventivoSetNew) {
                mantenimientopreventivoSetNewMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSetNewMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSetNewMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSetNew.add(mantenimientopreventivoSetNewMantenimientopreventivoToAttach);
            }
            mantenimientopreventivoSetNew = attachedMantenimientopreventivoSetNew;
            proveedormantenimientomaquina.setMantenimientopreventivoSet(mantenimientopreventivoSetNew);
            Set<Mantenimientopreventivo> attachedMantenimientopreventivoSet1New = new HashSet<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoSet1NewMantenimientopreventivoToAttach : mantenimientopreventivoSet1New) {
                mantenimientopreventivoSet1NewMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoSet1NewMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoSet1NewMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoSet1New.add(mantenimientopreventivoSet1NewMantenimientopreventivoToAttach);
            }
            mantenimientopreventivoSet1New = attachedMantenimientopreventivoSet1New;
            proveedormantenimientomaquina.setMantenimientopreventivoSet1(mantenimientopreventivoSet1New);
            proveedormantenimientomaquina = em.merge(proveedormantenimientomaquina);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (condicioniva1Old != null && !condicioniva1Old.equals(condicioniva1New)) {
                condicioniva1Old.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                condicioniva1Old = em.merge(condicioniva1Old);
            }
            if (condicioniva1New != null && !condicioniva1New.equals(condicioniva1Old)) {
                condicioniva1New.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                condicioniva1New = em.merge(condicioniva1New);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                domicilio1New = em.merge(domicilio1New);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsableNew = em.merge(responsableNew);
            }
            if (responsable1Old != null && !responsable1Old.equals(responsable1New)) {
                responsable1Old.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsable1Old = em.merge(responsable1Old);
            }
            if (responsable1New != null && !responsable1New.equals(responsable1Old)) {
                responsable1New.getProveedormantenimientomaquinaSet().add(proveedormantenimientomaquina);
                responsable1New = em.merge(responsable1New);
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
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1OldMantenimientocorrectivo : mantenimientocorrectivoSet1Old) {
                if (!mantenimientocorrectivoSet1New.contains(mantenimientocorrectivoSet1OldMantenimientocorrectivo)) {
                    mantenimientocorrectivoSet1OldMantenimientocorrectivo.setProveedormantenimiento1(null);
                    mantenimientocorrectivoSet1OldMantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1OldMantenimientocorrectivo);
                }
            }
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1NewMantenimientocorrectivo : mantenimientocorrectivoSet1New) {
                if (!mantenimientocorrectivoSet1Old.contains(mantenimientocorrectivoSet1NewMantenimientocorrectivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo = mantenimientocorrectivoSet1NewMantenimientocorrectivo.getProveedormantenimiento1();
                    mantenimientocorrectivoSet1NewMantenimientocorrectivo.setProveedormantenimiento1(proveedormantenimientomaquina);
                    mantenimientocorrectivoSet1NewMantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1NewMantenimientocorrectivo);
                    if (oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo != null && !oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo.getMantenimientocorrectivoSet1().remove(mantenimientocorrectivoSet1NewMantenimientocorrectivo);
                        oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo = em.merge(oldProveedormantenimiento1OfMantenimientocorrectivoSet1NewMantenimientocorrectivo);
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
            for (Mantenimientopreventivo mantenimientopreventivoSet1OldMantenimientopreventivo : mantenimientopreventivoSet1Old) {
                if (!mantenimientopreventivoSet1New.contains(mantenimientopreventivoSet1OldMantenimientopreventivo)) {
                    mantenimientopreventivoSet1OldMantenimientopreventivo.setProveedormantenimiento1(null);
                    mantenimientopreventivoSet1OldMantenimientopreventivo = em.merge(mantenimientopreventivoSet1OldMantenimientopreventivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoSet1NewMantenimientopreventivo : mantenimientopreventivoSet1New) {
                if (!mantenimientopreventivoSet1Old.contains(mantenimientopreventivoSet1NewMantenimientopreventivo)) {
                    Proveedormantenimientomaquina oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo = mantenimientopreventivoSet1NewMantenimientopreventivo.getProveedormantenimiento1();
                    mantenimientopreventivoSet1NewMantenimientopreventivo.setProveedormantenimiento1(proveedormantenimientomaquina);
                    mantenimientopreventivoSet1NewMantenimientopreventivo = em.merge(mantenimientopreventivoSet1NewMantenimientopreventivo);
                    if (oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo != null && !oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo.equals(proveedormantenimientomaquina)) {
                        oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo.getMantenimientopreventivoSet1().remove(mantenimientopreventivoSet1NewMantenimientopreventivo);
                        oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo = em.merge(oldProveedormantenimiento1OfMantenimientopreventivoSet1NewMantenimientopreventivo);
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
            Condicioniva condicioniva1 = proveedormantenimientomaquina.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                condicioniva1 = em.merge(condicioniva1);
            }
            Domicilio domicilio = proveedormantenimientomaquina.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = proveedormantenimientomaquina.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                domicilio1 = em.merge(domicilio1);
            }
            Responsable responsable = proveedormantenimientomaquina.getResponsable();
            if (responsable != null) {
                responsable.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsable = em.merge(responsable);
            }
            Responsable responsable1 = proveedormantenimientomaquina.getResponsable1();
            if (responsable1 != null) {
                responsable1.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquina);
                responsable1 = em.merge(responsable1);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet = proveedormantenimientomaquina.getMantenimientocorrectivoSet();
            for (Mantenimientocorrectivo mantenimientocorrectivoSetMantenimientocorrectivo : mantenimientocorrectivoSet) {
                mantenimientocorrectivoSetMantenimientocorrectivo.setProveedormantenimiento(null);
                mantenimientocorrectivoSetMantenimientocorrectivo = em.merge(mantenimientocorrectivoSetMantenimientocorrectivo);
            }
            Set<Mantenimientocorrectivo> mantenimientocorrectivoSet1 = proveedormantenimientomaquina.getMantenimientocorrectivoSet1();
            for (Mantenimientocorrectivo mantenimientocorrectivoSet1Mantenimientocorrectivo : mantenimientocorrectivoSet1) {
                mantenimientocorrectivoSet1Mantenimientocorrectivo.setProveedormantenimiento1(null);
                mantenimientocorrectivoSet1Mantenimientocorrectivo = em.merge(mantenimientocorrectivoSet1Mantenimientocorrectivo);
            }
            Set<Mantenimientopreventivo> mantenimientopreventivoSet = proveedormantenimientomaquina.getMantenimientopreventivoSet();
            for (Mantenimientopreventivo mantenimientopreventivoSetMantenimientopreventivo : mantenimientopreventivoSet) {
                mantenimientopreventivoSetMantenimientopreventivo.setProveedormantenimiento(null);
                mantenimientopreventivoSetMantenimientopreventivo = em.merge(mantenimientopreventivoSetMantenimientopreventivo);
            }
            Set<Mantenimientopreventivo> mantenimientopreventivoSet1 = proveedormantenimientomaquina.getMantenimientopreventivoSet1();
            for (Mantenimientopreventivo mantenimientopreventivoSet1Mantenimientopreventivo : mantenimientopreventivoSet1) {
                mantenimientopreventivoSet1Mantenimientopreventivo.setProveedormantenimiento1(null);
                mantenimientopreventivoSet1Mantenimientopreventivo = em.merge(mantenimientopreventivoSet1Mantenimientopreventivo);
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
