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
import metalsoft.datos.jpa.entity.Estadomaquina;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Marca;
import metalsoft.datos.jpa.entity.Tipomaquina;
import metalsoft.datos.jpa.entity.Unidadmedida;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class MaquinaJpaController {

    public MaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) throws PreexistingEntityException, Exception {
        if (maquina.getDetalleplanificacionproduccionList() == null) {
            maquina.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (maquina.getEtapadeproduccionList() == null) {
            maquina.setEtapadeproduccionList(new ArrayList<Etapadeproduccion>());
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
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : maquina.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            maquina.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Etapadeproduccion> attachedEtapadeproduccionList = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccionToAttach : maquina.getEtapadeproduccionList()) {
                etapadeproduccionListEtapadeproduccionToAttach = em.getReference(etapadeproduccionListEtapadeproduccionToAttach.getClass(), etapadeproduccionListEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionList.add(etapadeproduccionListEtapadeproduccionToAttach);
            }
            maquina.setEtapadeproduccionList(attachedEtapadeproduccionList);
            em.persist(maquina);
            if (estado != null) {
                estado.getMaquinaList().add(maquina);
                estado = em.merge(estado);
            }
            if (marca != null) {
                marca.getMaquinaList().add(maquina);
                marca = em.merge(marca);
            }
            if (tipomaquina != null) {
                tipomaquina.getMaquinaList().add(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaList().add(maquina);
                idunidadmedida = em.merge(idunidadmedida);
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
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : maquina.getEtapadeproduccionList()) {
                Maquina oldMaquinaOfEtapadeproduccionListEtapadeproduccion = etapadeproduccionListEtapadeproduccion.getMaquina();
                etapadeproduccionListEtapadeproduccion.setMaquina(maquina);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
                if (oldMaquinaOfEtapadeproduccionListEtapadeproduccion != null) {
                    oldMaquinaOfEtapadeproduccionListEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListEtapadeproduccion);
                    oldMaquinaOfEtapadeproduccionListEtapadeproduccion = em.merge(oldMaquinaOfEtapadeproduccionListEtapadeproduccion);
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
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentMaquina.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = maquina.getDetalleplanificacionproduccionList();
            List<Etapadeproduccion> etapadeproduccionListOld = persistentMaquina.getEtapadeproduccionList();
            List<Etapadeproduccion> etapadeproduccionListNew = maquina.getEtapadeproduccionList();
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
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            maquina.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Etapadeproduccion> attachedEtapadeproduccionListNew = new ArrayList<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccionToAttach : etapadeproduccionListNew) {
                etapadeproduccionListNewEtapadeproduccionToAttach = em.getReference(etapadeproduccionListNewEtapadeproduccionToAttach.getClass(), etapadeproduccionListNewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionListNew.add(etapadeproduccionListNewEtapadeproduccionToAttach);
            }
            etapadeproduccionListNew = attachedEtapadeproduccionListNew;
            maquina.setEtapadeproduccionList(etapadeproduccionListNew);
            maquina = em.merge(maquina);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getMaquinaList().remove(maquina);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getMaquinaList().add(maquina);
                estadoNew = em.merge(estadoNew);
            }
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getMaquinaList().remove(maquina);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getMaquinaList().add(maquina);
                marcaNew = em.merge(marcaNew);
            }
            if (tipomaquinaOld != null && !tipomaquinaOld.equals(tipomaquinaNew)) {
                tipomaquinaOld.getMaquinaList().remove(maquina);
                tipomaquinaOld = em.merge(tipomaquinaOld);
            }
            if (tipomaquinaNew != null && !tipomaquinaNew.equals(tipomaquinaOld)) {
                tipomaquinaNew.getMaquinaList().add(maquina);
                tipomaquinaNew = em.merge(tipomaquinaNew);
            }
            if (idunidadmedidaOld != null && !idunidadmedidaOld.equals(idunidadmedidaNew)) {
                idunidadmedidaOld.getMaquinaList().remove(maquina);
                idunidadmedidaOld = em.merge(idunidadmedidaOld);
            }
            if (idunidadmedidaNew != null && !idunidadmedidaNew.equals(idunidadmedidaOld)) {
                idunidadmedidaNew.getMaquinaList().add(maquina);
                idunidadmedidaNew = em.merge(idunidadmedidaNew);
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
            for (Etapadeproduccion etapadeproduccionListOldEtapadeproduccion : etapadeproduccionListOld) {
                if (!etapadeproduccionListNew.contains(etapadeproduccionListOldEtapadeproduccion)) {
                    etapadeproduccionListOldEtapadeproduccion.setMaquina(null);
                    etapadeproduccionListOldEtapadeproduccion = em.merge(etapadeproduccionListOldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionListNewEtapadeproduccion : etapadeproduccionListNew) {
                if (!etapadeproduccionListOld.contains(etapadeproduccionListNewEtapadeproduccion)) {
                    Maquina oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion = etapadeproduccionListNewEtapadeproduccion.getMaquina();
                    etapadeproduccionListNewEtapadeproduccion.setMaquina(maquina);
                    etapadeproduccionListNewEtapadeproduccion = em.merge(etapadeproduccionListNewEtapadeproduccion);
                    if (oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion != null && !oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion.equals(maquina)) {
                        oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion.getEtapadeproduccionList().remove(etapadeproduccionListNewEtapadeproduccion);
                        oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion = em.merge(oldMaquinaOfEtapadeproduccionListNewEtapadeproduccion);
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
                estado.getMaquinaList().remove(maquina);
                estado = em.merge(estado);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca.getMaquinaList().remove(maquina);
                marca = em.merge(marca);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina.getMaquinaList().remove(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaList().remove(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = maquina.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdmaquina(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Etapadeproduccion> etapadeproduccionList = maquina.getEtapadeproduccionList();
            for (Etapadeproduccion etapadeproduccionListEtapadeproduccion : etapadeproduccionList) {
                etapadeproduccionListEtapadeproduccion.setMaquina(null);
                etapadeproduccionListEtapadeproduccion = em.merge(etapadeproduccionListEtapadeproduccion);
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
