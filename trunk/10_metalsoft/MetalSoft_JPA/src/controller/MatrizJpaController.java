/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Matriz;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Pieza;
import java.util.HashSet;
import java.util.Set;
import entity.Pedidomatriz;

/**
 *
 * @author Nino
 */
public class MatrizJpaController {

    public MatrizJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matriz matriz) throws PreexistingEntityException, Exception {
        if (matriz.getPiezaSet() == null) {
            matriz.setPiezaSet(new HashSet<Pieza>());
        }
        if (matriz.getPedidomatrizSet() == null) {
            matriz.setPedidomatrizSet(new HashSet<Pedidomatriz>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima = matriz.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                matriz.setMateriaprima(materiaprima);
            }
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : matriz.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            matriz.setPiezaSet(attachedPiezaSet);
            Set<Pedidomatriz> attachedPedidomatrizSet = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSetPedidomatrizToAttach : matriz.getPedidomatrizSet()) {
                pedidomatrizSetPedidomatrizToAttach = em.getReference(pedidomatrizSetPedidomatrizToAttach.getClass(), pedidomatrizSetPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSet.add(pedidomatrizSetPedidomatrizToAttach);
            }
            matriz.setPedidomatrizSet(attachedPedidomatrizSet);
            em.persist(matriz);
            if (materiaprima != null) {
                materiaprima.getMatrizSet().add(matriz);
                materiaprima = em.merge(materiaprima);
            }
            for (Pieza piezaSetPieza : matriz.getPiezaSet()) {
                Matriz oldMatrizOfPiezaSetPieza = piezaSetPieza.getMatriz();
                piezaSetPieza.setMatriz(matriz);
                piezaSetPieza = em.merge(piezaSetPieza);
                if (oldMatrizOfPiezaSetPieza != null) {
                    oldMatrizOfPiezaSetPieza.getPiezaSet().remove(piezaSetPieza);
                    oldMatrizOfPiezaSetPieza = em.merge(oldMatrizOfPiezaSetPieza);
                }
            }
            for (Pedidomatriz pedidomatrizSetPedidomatriz : matriz.getPedidomatrizSet()) {
                Matriz oldIdmatrizOfPedidomatrizSetPedidomatriz = pedidomatrizSetPedidomatriz.getIdmatriz();
                pedidomatrizSetPedidomatriz.setIdmatriz(matriz);
                pedidomatrizSetPedidomatriz = em.merge(pedidomatrizSetPedidomatriz);
                if (oldIdmatrizOfPedidomatrizSetPedidomatriz != null) {
                    oldIdmatrizOfPedidomatrizSetPedidomatriz.getPedidomatrizSet().remove(pedidomatrizSetPedidomatriz);
                    oldIdmatrizOfPedidomatrizSetPedidomatriz = em.merge(oldIdmatrizOfPedidomatrizSetPedidomatriz);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatriz(matriz.getIdmatriz()) != null) {
                throw new PreexistingEntityException("Matriz " + matriz + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matriz matriz) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matriz persistentMatriz = em.find(Matriz.class, matriz.getIdmatriz());
            Materiaprima materiaprimaOld = persistentMatriz.getMateriaprima();
            Materiaprima materiaprimaNew = matriz.getMateriaprima();
            Set<Pieza> piezaSetOld = persistentMatriz.getPiezaSet();
            Set<Pieza> piezaSetNew = matriz.getPiezaSet();
            Set<Pedidomatriz> pedidomatrizSetOld = persistentMatriz.getPedidomatrizSet();
            Set<Pedidomatriz> pedidomatrizSetNew = matriz.getPedidomatrizSet();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                matriz.setMateriaprima(materiaprimaNew);
            }
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            matriz.setPiezaSet(piezaSetNew);
            Set<Pedidomatriz> attachedPedidomatrizSetNew = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSetNewPedidomatrizToAttach : pedidomatrizSetNew) {
                pedidomatrizSetNewPedidomatrizToAttach = em.getReference(pedidomatrizSetNewPedidomatrizToAttach.getClass(), pedidomatrizSetNewPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSetNew.add(pedidomatrizSetNewPedidomatrizToAttach);
            }
            pedidomatrizSetNew = attachedPedidomatrizSetNew;
            matriz.setPedidomatrizSet(pedidomatrizSetNew);
            matriz = em.merge(matriz);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getMatrizSet().remove(matriz);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getMatrizSet().add(matriz);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            for (Pieza piezaSetOldPieza : piezaSetOld) {
                if (!piezaSetNew.contains(piezaSetOldPieza)) {
                    piezaSetOldPieza.setMatriz(null);
                    piezaSetOldPieza = em.merge(piezaSetOldPieza);
                }
            }
            for (Pieza piezaSetNewPieza : piezaSetNew) {
                if (!piezaSetOld.contains(piezaSetNewPieza)) {
                    Matriz oldMatrizOfPiezaSetNewPieza = piezaSetNewPieza.getMatriz();
                    piezaSetNewPieza.setMatriz(matriz);
                    piezaSetNewPieza = em.merge(piezaSetNewPieza);
                    if (oldMatrizOfPiezaSetNewPieza != null && !oldMatrizOfPiezaSetNewPieza.equals(matriz)) {
                        oldMatrizOfPiezaSetNewPieza.getPiezaSet().remove(piezaSetNewPieza);
                        oldMatrizOfPiezaSetNewPieza = em.merge(oldMatrizOfPiezaSetNewPieza);
                    }
                }
            }
            for (Pedidomatriz pedidomatrizSetOldPedidomatriz : pedidomatrizSetOld) {
                if (!pedidomatrizSetNew.contains(pedidomatrizSetOldPedidomatriz)) {
                    pedidomatrizSetOldPedidomatriz.setIdmatriz(null);
                    pedidomatrizSetOldPedidomatriz = em.merge(pedidomatrizSetOldPedidomatriz);
                }
            }
            for (Pedidomatriz pedidomatrizSetNewPedidomatriz : pedidomatrizSetNew) {
                if (!pedidomatrizSetOld.contains(pedidomatrizSetNewPedidomatriz)) {
                    Matriz oldIdmatrizOfPedidomatrizSetNewPedidomatriz = pedidomatrizSetNewPedidomatriz.getIdmatriz();
                    pedidomatrizSetNewPedidomatriz.setIdmatriz(matriz);
                    pedidomatrizSetNewPedidomatriz = em.merge(pedidomatrizSetNewPedidomatriz);
                    if (oldIdmatrizOfPedidomatrizSetNewPedidomatriz != null && !oldIdmatrizOfPedidomatrizSetNewPedidomatriz.equals(matriz)) {
                        oldIdmatrizOfPedidomatrizSetNewPedidomatriz.getPedidomatrizSet().remove(pedidomatrizSetNewPedidomatriz);
                        oldIdmatrizOfPedidomatrizSetNewPedidomatriz = em.merge(oldIdmatrizOfPedidomatrizSetNewPedidomatriz);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = matriz.getIdmatriz();
                if (findMatriz(id) == null) {
                    throw new NonexistentEntityException("The matriz with id " + id + " no longer exists.");
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
            Matriz matriz;
            try {
                matriz = em.getReference(Matriz.class, id);
                matriz.getIdmatriz();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matriz with id " + id + " no longer exists.", enfe);
            }
            Materiaprima materiaprima = matriz.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getMatrizSet().remove(matriz);
                materiaprima = em.merge(materiaprima);
            }
            Set<Pieza> piezaSet = matriz.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setMatriz(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Pedidomatriz> pedidomatrizSet = matriz.getPedidomatrizSet();
            for (Pedidomatriz pedidomatrizSetPedidomatriz : pedidomatrizSet) {
                pedidomatrizSetPedidomatriz.setIdmatriz(null);
                pedidomatrizSetPedidomatriz = em.merge(pedidomatrizSetPedidomatriz);
            }
            em.remove(matriz);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matriz> findMatrizEntities() {
        return findMatrizEntities(true, -1, -1);
    }

    public List<Matriz> findMatrizEntities(int maxResults, int firstResult) {
        return findMatrizEntities(false, maxResults, firstResult);
    }

    private List<Matriz> findMatrizEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matriz.class));
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

    public Matriz findMatriz(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matriz.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatrizCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matriz> rt = cq.from(Matriz.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
