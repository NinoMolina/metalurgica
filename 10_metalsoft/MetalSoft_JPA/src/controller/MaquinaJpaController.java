/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Maquina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadomaquina;
import entity.Marca;
import entity.Tipomaquina;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class MaquinaJpaController {

    public MaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) throws PreexistingEntityException, Exception {
        if (maquina.getDetalleplanificacionproduccionSet() == null) {
            maquina.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                maquina.setEstado(estado);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca = em.getReference(marca.getClass(), marca.getIdmarca());
                maquina.setMarca(marca);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina = em.getReference(tipomaquina.getClass(), tipomaquina.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquina);
            }
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida = em.getReference(idunidadmedida.getClass(), idunidadmedida.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedida);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : maquina.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            maquina.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            em.persist(maquina);
            if (estado != null) {
                estado.getMaquinaSet().add(maquina);
                estado = em.merge(estado);
            }
            if (marca != null) {
                marca.getMaquinaSet().add(maquina);
                marca = em.merge(marca);
            }
            if (tipomaquina != null) {
                tipomaquina.getMaquinaSet().add(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaSet().add(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : maquina.getDetalleplanificacionproduccionSet()) {
                Maquina oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdmaquina();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdmaquina(maquina);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaquina(maquina.getIdmaquina()) != null) {
                throw new PreexistingEntityException("Maquina " + maquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquina maquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina persistentMaquina = em.find(Maquina.class, maquina.getIdmaquina());
            Estadomaquina estadoOld = persistentMaquina.getEstado();
            Estadomaquina estadoNew = maquina.getEstado();
            Marca marcaOld = persistentMaquina.getMarca();
            Marca marcaNew = maquina.getMarca();
            Tipomaquina tipomaquinaOld = persistentMaquina.getTipomaquina();
            Tipomaquina tipomaquinaNew = maquina.getTipomaquina();
            Unidadmedida idunidadmedidaOld = persistentMaquina.getIdunidadmedida();
            Unidadmedida idunidadmedidaNew = maquina.getIdunidadmedida();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentMaquina.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = maquina.getDetalleplanificacionproduccionSet();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                maquina.setEstado(estadoNew);
            }
            if (marcaNew != null) {
                marcaNew = em.getReference(marcaNew.getClass(), marcaNew.getIdmarca());
                maquina.setMarca(marcaNew);
            }
            if (tipomaquinaNew != null) {
                tipomaquinaNew = em.getReference(tipomaquinaNew.getClass(), tipomaquinaNew.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquinaNew);
            }
            if (idunidadmedidaNew != null) {
                idunidadmedidaNew = em.getReference(idunidadmedidaNew.getClass(), idunidadmedidaNew.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedidaNew);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            maquina.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            maquina = em.merge(maquina);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getMaquinaSet().remove(maquina);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getMaquinaSet().add(maquina);
                estadoNew = em.merge(estadoNew);
            }
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getMaquinaSet().remove(maquina);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getMaquinaSet().add(maquina);
                marcaNew = em.merge(marcaNew);
            }
            if (tipomaquinaOld != null && !tipomaquinaOld.equals(tipomaquinaNew)) {
                tipomaquinaOld.getMaquinaSet().remove(maquina);
                tipomaquinaOld = em.merge(tipomaquinaOld);
            }
            if (tipomaquinaNew != null && !tipomaquinaNew.equals(tipomaquinaOld)) {
                tipomaquinaNew.getMaquinaSet().add(maquina);
                tipomaquinaNew = em.merge(tipomaquinaNew);
            }
            if (idunidadmedidaOld != null && !idunidadmedidaOld.equals(idunidadmedidaNew)) {
                idunidadmedidaOld.getMaquinaSet().remove(maquina);
                idunidadmedidaOld = em.merge(idunidadmedidaOld);
            }
            if (idunidadmedidaNew != null && !idunidadmedidaNew.equals(idunidadmedidaOld)) {
                idunidadmedidaNew.getMaquinaSet().add(maquina);
                idunidadmedidaNew = em.merge(idunidadmedidaNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdmaquina(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Maquina oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdmaquina();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdmaquina(maquina);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(maquina)) {
                        oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = maquina.getIdmaquina();
                if (findMaquina(id) == null) {
                    throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.");
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
            Maquina maquina;
            try {
                maquina = em.getReference(Maquina.class, id);
                maquina.getIdmaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.", enfe);
            }
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado.getMaquinaSet().remove(maquina);
                estado = em.merge(estado);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca.getMaquinaSet().remove(maquina);
                marca = em.merge(marca);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina.getMaquinaSet().remove(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaSet().remove(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = maquina.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdmaquina(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            em.remove(maquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquina> findMaquinaEntities() {
        return findMaquinaEntities(true, -1, -1);
    }

    public List<Maquina> findMaquinaEntities(int maxResults, int firstResult) {
        return findMaquinaEntities(false, maxResults, firstResult);
    }

    private List<Maquina> findMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Maquina.findAll");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Maquina findMaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquina> rt = cq.from(Maquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
