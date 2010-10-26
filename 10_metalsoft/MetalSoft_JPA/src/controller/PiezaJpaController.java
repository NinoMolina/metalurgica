/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Pieza;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Matriz;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Detalleproductopresupuesto;

/**
 *
 * @author Nino
 */
public class PiezaJpaController {

    public PiezaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pieza pieza) throws PreexistingEntityException, Exception {
        if (pieza.getDetalleplanificacionproduccionSet() == null) {
            pieza.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (pieza.getDetalleproductopresupuestoSet() == null) {
            pieza.setDetalleproductopresupuestoSet(new HashSet<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                pieza.setMateriaprima(materiaprima);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz = em.getReference(matriz.getClass(), matriz.getIdmatriz());
                pieza.setMatriz(matriz);
            }
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedida);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : pieza.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            pieza.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : pieza.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            pieza.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            em.persist(pieza);
            if (materiaprima != null) {
                materiaprima.getPiezaSet().add(pieza);
                materiaprima = em.merge(materiaprima);
            }
            if (matriz != null) {
                matriz.getPiezaSet().add(pieza);
                matriz = em.merge(matriz);
            }
            if (unidadmedida != null) {
                unidadmedida.getPiezaSet().add(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : pieza.getDetalleplanificacionproduccionSet()) {
                Pieza oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdpieza();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdpieza(pieza);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : pieza.getDetalleproductopresupuestoSet()) {
                Pieza oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = detalleproductopresupuestoSetDetalleproductopresupuesto.getIdpieza();
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdpieza(pieza);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
                if (oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto != null) {
                    oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetDetalleproductopresupuesto);
                    oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPieza(pieza.getIdpieza()) != null) {
                throw new PreexistingEntityException("Pieza " + pieza + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pieza pieza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pieza persistentPieza = em.find(Pieza.class, pieza.getIdpieza());
            Materiaprima materiaprimaOld = persistentPieza.getMateriaprima();
            Materiaprima materiaprimaNew = pieza.getMateriaprima();
            Matriz matrizOld = persistentPieza.getMatriz();
            Matriz matrizNew = pieza.getMatriz();
            Unidadmedida unidadmedidaOld = persistentPieza.getUnidadmedida();
            Unidadmedida unidadmedidaNew = pieza.getUnidadmedida();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentPieza.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = pieza.getDetalleplanificacionproduccionSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentPieza.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = pieza.getDetalleproductopresupuestoSet();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                pieza.setMateriaprima(materiaprimaNew);
            }
            if (matrizNew != null) {
                matrizNew = em.getReference(matrizNew.getClass(), matrizNew.getIdmatriz());
                pieza.setMatriz(matrizNew);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedidaNew);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            pieza.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            pieza.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            pieza = em.merge(pieza);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getPiezaSet().remove(pieza);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getPiezaSet().add(pieza);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (matrizOld != null && !matrizOld.equals(matrizNew)) {
                matrizOld.getPiezaSet().remove(pieza);
                matrizOld = em.merge(matrizOld);
            }
            if (matrizNew != null && !matrizNew.equals(matrizOld)) {
                matrizNew.getPiezaSet().add(pieza);
                matrizNew = em.merge(matrizNew);
            }
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getPiezaSet().remove(pieza);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getPiezaSet().add(pieza);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdpieza(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Pieza oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdpieza();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdpieza(pieza);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(pieza)) {
                        oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetOldDetalleproductopresupuesto : detalleproductopresupuestoSetOld) {
                if (!detalleproductopresupuestoSetNew.contains(detalleproductopresupuestoSetOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto.setIdpieza(null);
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuesto : detalleproductopresupuestoSetNew) {
                if (!detalleproductopresupuestoSetOld.contains(detalleproductopresupuestoSetNewDetalleproductopresupuesto)) {
                    Pieza oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = detalleproductopresupuestoSetNewDetalleproductopresupuesto.getIdpieza();
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto.setIdpieza(pieza);
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    if (oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto != null && !oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.equals(pieza)) {
                        oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                        oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pieza.getIdpieza();
                if (findPieza(id) == null) {
                    throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.");
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
            Pieza pieza;
            try {
                pieza = em.getReference(Pieza.class, id);
                pieza.getIdpieza();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.", enfe);
            }
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getPiezaSet().remove(pieza);
                materiaprima = em.merge(materiaprima);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz.getPiezaSet().remove(pieza);
                matriz = em.merge(matriz);
            }
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getPiezaSet().remove(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = pieza.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdpieza(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = pieza.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdpieza(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            em.remove(pieza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pieza> findPiezaEntities() {
        return findPiezaEntities(true, -1, -1);
    }

    public List<Pieza> findPiezaEntities(int maxResults, int firstResult) {
        return findPiezaEntities(false, maxResults, firstResult);
    }

    private List<Pieza> findPiezaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pieza.class));
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

    public Pieza findPieza(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pieza.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pieza> rt = cq.from(Pieza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
