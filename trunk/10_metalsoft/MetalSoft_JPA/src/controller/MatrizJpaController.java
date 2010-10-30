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
        if (matriz.getPiezaSet1() == null) {
            matriz.setPiezaSet1(new HashSet<Pieza>());
        }
        if (matriz.getPedidomatrizSet() == null) {
            matriz.setPedidomatrizSet(new HashSet<Pedidomatriz>());
        }
        if (matriz.getPedidomatrizSet1() == null) {
            matriz.setPedidomatrizSet1(new HashSet<Pedidomatriz>());
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
            Materiaprima materiaprima1 = matriz.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1 = em.getReference(materiaprima1.getClass(), materiaprima1.getIdmateriaprima());
                matriz.setMateriaprima1(materiaprima1);
            }
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : matriz.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            matriz.setPiezaSet(attachedPiezaSet);
            Set<Pieza> attachedPiezaSet1 = new HashSet<Pieza>();
            for (Pieza piezaSet1PiezaToAttach : matriz.getPiezaSet1()) {
                piezaSet1PiezaToAttach = em.getReference(piezaSet1PiezaToAttach.getClass(), piezaSet1PiezaToAttach.getIdpieza());
                attachedPiezaSet1.add(piezaSet1PiezaToAttach);
            }
            matriz.setPiezaSet1(attachedPiezaSet1);
            Set<Pedidomatriz> attachedPedidomatrizSet = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSetPedidomatrizToAttach : matriz.getPedidomatrizSet()) {
                pedidomatrizSetPedidomatrizToAttach = em.getReference(pedidomatrizSetPedidomatrizToAttach.getClass(), pedidomatrizSetPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSet.add(pedidomatrizSetPedidomatrizToAttach);
            }
            matriz.setPedidomatrizSet(attachedPedidomatrizSet);
            Set<Pedidomatriz> attachedPedidomatrizSet1 = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSet1PedidomatrizToAttach : matriz.getPedidomatrizSet1()) {
                pedidomatrizSet1PedidomatrizToAttach = em.getReference(pedidomatrizSet1PedidomatrizToAttach.getClass(), pedidomatrizSet1PedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSet1.add(pedidomatrizSet1PedidomatrizToAttach);
            }
            matriz.setPedidomatrizSet1(attachedPedidomatrizSet1);
            em.persist(matriz);
            if (materiaprima != null) {
                materiaprima.getMatrizSet().add(matriz);
                materiaprima = em.merge(materiaprima);
            }
            if (materiaprima1 != null) {
                materiaprima1.getMatrizSet().add(matriz);
                materiaprima1 = em.merge(materiaprima1);
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
            for (Pieza piezaSet1Pieza : matriz.getPiezaSet1()) {
                Matriz oldMatriz1OfPiezaSet1Pieza = piezaSet1Pieza.getMatriz1();
                piezaSet1Pieza.setMatriz1(matriz);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
                if (oldMatriz1OfPiezaSet1Pieza != null) {
                    oldMatriz1OfPiezaSet1Pieza.getPiezaSet1().remove(piezaSet1Pieza);
                    oldMatriz1OfPiezaSet1Pieza = em.merge(oldMatriz1OfPiezaSet1Pieza);
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
            for (Pedidomatriz pedidomatrizSet1Pedidomatriz : matriz.getPedidomatrizSet1()) {
                Matriz oldIdmatriz1OfPedidomatrizSet1Pedidomatriz = pedidomatrizSet1Pedidomatriz.getIdmatriz1();
                pedidomatrizSet1Pedidomatriz.setIdmatriz1(matriz);
                pedidomatrizSet1Pedidomatriz = em.merge(pedidomatrizSet1Pedidomatriz);
                if (oldIdmatriz1OfPedidomatrizSet1Pedidomatriz != null) {
                    oldIdmatriz1OfPedidomatrizSet1Pedidomatriz.getPedidomatrizSet1().remove(pedidomatrizSet1Pedidomatriz);
                    oldIdmatriz1OfPedidomatrizSet1Pedidomatriz = em.merge(oldIdmatriz1OfPedidomatrizSet1Pedidomatriz);
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
            Materiaprima materiaprima1Old = persistentMatriz.getMateriaprima1();
            Materiaprima materiaprima1New = matriz.getMateriaprima1();
            Set<Pieza> piezaSetOld = persistentMatriz.getPiezaSet();
            Set<Pieza> piezaSetNew = matriz.getPiezaSet();
            Set<Pieza> piezaSet1Old = persistentMatriz.getPiezaSet1();
            Set<Pieza> piezaSet1New = matriz.getPiezaSet1();
            Set<Pedidomatriz> pedidomatrizSetOld = persistentMatriz.getPedidomatrizSet();
            Set<Pedidomatriz> pedidomatrizSetNew = matriz.getPedidomatrizSet();
            Set<Pedidomatriz> pedidomatrizSet1Old = persistentMatriz.getPedidomatrizSet1();
            Set<Pedidomatriz> pedidomatrizSet1New = matriz.getPedidomatrizSet1();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                matriz.setMateriaprima(materiaprimaNew);
            }
            if (materiaprima1New != null) {
                materiaprima1New = em.getReference(materiaprima1New.getClass(), materiaprima1New.getIdmateriaprima());
                matriz.setMateriaprima1(materiaprima1New);
            }
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            matriz.setPiezaSet(piezaSetNew);
            Set<Pieza> attachedPiezaSet1New = new HashSet<Pieza>();
            for (Pieza piezaSet1NewPiezaToAttach : piezaSet1New) {
                piezaSet1NewPiezaToAttach = em.getReference(piezaSet1NewPiezaToAttach.getClass(), piezaSet1NewPiezaToAttach.getIdpieza());
                attachedPiezaSet1New.add(piezaSet1NewPiezaToAttach);
            }
            piezaSet1New = attachedPiezaSet1New;
            matriz.setPiezaSet1(piezaSet1New);
            Set<Pedidomatriz> attachedPedidomatrizSetNew = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSetNewPedidomatrizToAttach : pedidomatrizSetNew) {
                pedidomatrizSetNewPedidomatrizToAttach = em.getReference(pedidomatrizSetNewPedidomatrizToAttach.getClass(), pedidomatrizSetNewPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSetNew.add(pedidomatrizSetNewPedidomatrizToAttach);
            }
            pedidomatrizSetNew = attachedPedidomatrizSetNew;
            matriz.setPedidomatrizSet(pedidomatrizSetNew);
            Set<Pedidomatriz> attachedPedidomatrizSet1New = new HashSet<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizSet1NewPedidomatrizToAttach : pedidomatrizSet1New) {
                pedidomatrizSet1NewPedidomatrizToAttach = em.getReference(pedidomatrizSet1NewPedidomatrizToAttach.getClass(), pedidomatrizSet1NewPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizSet1New.add(pedidomatrizSet1NewPedidomatrizToAttach);
            }
            pedidomatrizSet1New = attachedPedidomatrizSet1New;
            matriz.setPedidomatrizSet1(pedidomatrizSet1New);
            matriz = em.merge(matriz);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getMatrizSet().remove(matriz);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getMatrizSet().add(matriz);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (materiaprima1Old != null && !materiaprima1Old.equals(materiaprima1New)) {
                materiaprima1Old.getMatrizSet().remove(matriz);
                materiaprima1Old = em.merge(materiaprima1Old);
            }
            if (materiaprima1New != null && !materiaprima1New.equals(materiaprima1Old)) {
                materiaprima1New.getMatrizSet().add(matriz);
                materiaprima1New = em.merge(materiaprima1New);
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
            for (Pieza piezaSet1OldPieza : piezaSet1Old) {
                if (!piezaSet1New.contains(piezaSet1OldPieza)) {
                    piezaSet1OldPieza.setMatriz1(null);
                    piezaSet1OldPieza = em.merge(piezaSet1OldPieza);
                }
            }
            for (Pieza piezaSet1NewPieza : piezaSet1New) {
                if (!piezaSet1Old.contains(piezaSet1NewPieza)) {
                    Matriz oldMatriz1OfPiezaSet1NewPieza = piezaSet1NewPieza.getMatriz1();
                    piezaSet1NewPieza.setMatriz1(matriz);
                    piezaSet1NewPieza = em.merge(piezaSet1NewPieza);
                    if (oldMatriz1OfPiezaSet1NewPieza != null && !oldMatriz1OfPiezaSet1NewPieza.equals(matriz)) {
                        oldMatriz1OfPiezaSet1NewPieza.getPiezaSet1().remove(piezaSet1NewPieza);
                        oldMatriz1OfPiezaSet1NewPieza = em.merge(oldMatriz1OfPiezaSet1NewPieza);
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
            for (Pedidomatriz pedidomatrizSet1OldPedidomatriz : pedidomatrizSet1Old) {
                if (!pedidomatrizSet1New.contains(pedidomatrizSet1OldPedidomatriz)) {
                    pedidomatrizSet1OldPedidomatriz.setIdmatriz1(null);
                    pedidomatrizSet1OldPedidomatriz = em.merge(pedidomatrizSet1OldPedidomatriz);
                }
            }
            for (Pedidomatriz pedidomatrizSet1NewPedidomatriz : pedidomatrizSet1New) {
                if (!pedidomatrizSet1Old.contains(pedidomatrizSet1NewPedidomatriz)) {
                    Matriz oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz = pedidomatrizSet1NewPedidomatriz.getIdmatriz1();
                    pedidomatrizSet1NewPedidomatriz.setIdmatriz1(matriz);
                    pedidomatrizSet1NewPedidomatriz = em.merge(pedidomatrizSet1NewPedidomatriz);
                    if (oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz != null && !oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz.equals(matriz)) {
                        oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz.getPedidomatrizSet1().remove(pedidomatrizSet1NewPedidomatriz);
                        oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz = em.merge(oldIdmatriz1OfPedidomatrizSet1NewPedidomatriz);
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
            Materiaprima materiaprima1 = matriz.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1.getMatrizSet().remove(matriz);
                materiaprima1 = em.merge(materiaprima1);
            }
            Set<Pieza> piezaSet = matriz.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setMatriz(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Pieza> piezaSet1 = matriz.getPiezaSet1();
            for (Pieza piezaSet1Pieza : piezaSet1) {
                piezaSet1Pieza.setMatriz1(null);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
            }
            Set<Pedidomatriz> pedidomatrizSet = matriz.getPedidomatrizSet();
            for (Pedidomatriz pedidomatrizSetPedidomatriz : pedidomatrizSet) {
                pedidomatrizSetPedidomatriz.setIdmatriz(null);
                pedidomatrizSetPedidomatriz = em.merge(pedidomatrizSetPedidomatriz);
            }
            Set<Pedidomatriz> pedidomatrizSet1 = matriz.getPedidomatrizSet1();
            for (Pedidomatriz pedidomatrizSet1Pedidomatriz : pedidomatrizSet1) {
                pedidomatrizSet1Pedidomatriz.setIdmatriz1(null);
                pedidomatrizSet1Pedidomatriz = em.merge(pedidomatrizSet1Pedidomatriz);
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
