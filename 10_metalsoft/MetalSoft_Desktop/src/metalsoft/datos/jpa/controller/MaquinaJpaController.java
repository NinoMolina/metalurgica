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
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Unidadmedida;
import metalsoft.datos.jpa.entity.Tipomaquina;
import metalsoft.datos.jpa.entity.Marca;
import metalsoft.datos.jpa.entity.Estadomaquina;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;

/**
 *
 * @author Nino
 */
public class MaquinaJpaController implements Serializable {

    public MaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) throws PreexistingEntityException, Exception {
        if (maquina.getDetalleplanificacionproduccionList() == null) {
            maquina.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (maquina.getDetalleplanificacioncalidadList() == null) {
            maquina.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida = em.getReference(idunidadmedida.getClass(), idunidadmedida.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedida);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina = em.getReference(tipomaquina.getClass(), tipomaquina.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquina);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca = em.getReference(marca.getClass(), marca.getIdmarca());
                maquina.setMarca(marca);
            }
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                maquina.setEstado(estado);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : maquina.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            maquina.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : maquina.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            maquina.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            em.persist(maquina);
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaList().add(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            if (tipomaquina != null) {
                tipomaquina.getMaquinaList().add(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            if (marca != null) {
                marca.getMaquinaList().add(maquina);
                marca = em.merge(marca);
            }
            if (estado != null) {
                estado.getMaquinaList().add(maquina);
                estado = em.merge(estado);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : maquina.getDetalleplanificacionproduccionList()) {
                Maquina oldIdmaquinaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdmaquina();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdmaquina(maquina);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdmaquinaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdmaquinaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdmaquinaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : maquina.getDetalleplanificacioncalidadList()) {
                Maquina oldMaquinaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getMaquina();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setMaquina(maquina);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldMaquinaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldMaquinaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldMaquinaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldMaquinaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
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
            Unidadmedida idunidadmedidaOld = persistentMaquina.getIdunidadmedida();
            Unidadmedida idunidadmedidaNew = maquina.getIdunidadmedida();
            Tipomaquina tipomaquinaOld = persistentMaquina.getTipomaquina();
            Tipomaquina tipomaquinaNew = maquina.getTipomaquina();
            Marca marcaOld = persistentMaquina.getMarca();
            Marca marcaNew = maquina.getMarca();
            Estadomaquina estadoOld = persistentMaquina.getEstado();
            Estadomaquina estadoNew = maquina.getEstado();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentMaquina.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = maquina.getDetalleplanificacionproduccionList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentMaquina.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = maquina.getDetalleplanificacioncalidadList();
            if (idunidadmedidaNew != null) {
                idunidadmedidaNew = em.getReference(idunidadmedidaNew.getClass(), idunidadmedidaNew.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedidaNew);
            }
            if (tipomaquinaNew != null) {
                tipomaquinaNew = em.getReference(tipomaquinaNew.getClass(), tipomaquinaNew.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquinaNew);
            }
            if (marcaNew != null) {
                marcaNew = em.getReference(marcaNew.getClass(), marcaNew.getIdmarca());
                maquina.setMarca(marcaNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                maquina.setEstado(estadoNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            maquina.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            maquina.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            maquina = em.merge(maquina);
            if (idunidadmedidaOld != null && !idunidadmedidaOld.equals(idunidadmedidaNew)) {
                idunidadmedidaOld.getMaquinaList().remove(maquina);
                idunidadmedidaOld = em.merge(idunidadmedidaOld);
            }
            if (idunidadmedidaNew != null && !idunidadmedidaNew.equals(idunidadmedidaOld)) {
                idunidadmedidaNew.getMaquinaList().add(maquina);
                idunidadmedidaNew = em.merge(idunidadmedidaNew);
            }
            if (tipomaquinaOld != null && !tipomaquinaOld.equals(tipomaquinaNew)) {
                tipomaquinaOld.getMaquinaList().remove(maquina);
                tipomaquinaOld = em.merge(tipomaquinaOld);
            }
            if (tipomaquinaNew != null && !tipomaquinaNew.equals(tipomaquinaOld)) {
                tipomaquinaNew.getMaquinaList().add(maquina);
                tipomaquinaNew = em.merge(tipomaquinaNew);
            }
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getMaquinaList().remove(maquina);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getMaquinaList().add(maquina);
                marcaNew = em.merge(marcaNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getMaquinaList().remove(maquina);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getMaquinaList().add(maquina);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIdmaquina(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Maquina oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdmaquina();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdmaquina(maquina);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(maquina)) {
                        oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad.setMaquina(null);
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Maquina oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getMaquina();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setMaquina(maquina);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(maquina)) {
                        oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldMaquinaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
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
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaList().remove(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina.getMaquinaList().remove(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca.getMaquinaList().remove(maquina);
                marca = em.merge(marca);
            }
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado.getMaquinaList().remove(maquina);
                estado = em.merge(estado);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = maquina.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdmaquina(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadList = maquina.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleplanificacioncalidadList) {
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setMaquina(null);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
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
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquina.class));
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
