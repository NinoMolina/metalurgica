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
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.datos.jpa.entity.Tipomaterial;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Pieza;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Pedidomatriz;

/**
 *
 * @author Nino
 */
public class MatrizJpaController implements Serializable {

    public MatrizJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matriz matriz) throws PreexistingEntityException, Exception {
        if (matriz.getPiezaList() == null) {
            matriz.setPiezaList(new ArrayList<Pieza>());
        }
        if (matriz.getPedidomatrizList() == null) {
            matriz.setPedidomatrizList(new ArrayList<Pedidomatriz>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomaterial tipomaterial = matriz.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial = em.getReference(tipomaterial.getClass(), tipomaterial.getIdtipomaterial());
                matriz.setTipomaterial(tipomaterial);
            }
            Materiaprima materiaprima = matriz.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                matriz.setMateriaprima(materiaprima);
            }
            List<Pieza> attachedPiezaList = new ArrayList<Pieza>();
            for (Pieza piezaListPiezaToAttach : matriz.getPiezaList()) {
                piezaListPiezaToAttach = em.getReference(piezaListPiezaToAttach.getClass(), piezaListPiezaToAttach.getIdpieza());
                attachedPiezaList.add(piezaListPiezaToAttach);
            }
            matriz.setPiezaList(attachedPiezaList);
            List<Pedidomatriz> attachedPedidomatrizList = new ArrayList<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizListPedidomatrizToAttach : matriz.getPedidomatrizList()) {
                pedidomatrizListPedidomatrizToAttach = em.getReference(pedidomatrizListPedidomatrizToAttach.getClass(), pedidomatrizListPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizList.add(pedidomatrizListPedidomatrizToAttach);
            }
            matriz.setPedidomatrizList(attachedPedidomatrizList);
            em.persist(matriz);
            if (tipomaterial != null) {
                tipomaterial.getMatrizList().add(matriz);
                tipomaterial = em.merge(tipomaterial);
            }
            if (materiaprima != null) {
                materiaprima.getMatrizList().add(matriz);
                materiaprima = em.merge(materiaprima);
            }
            for (Pieza piezaListPieza : matriz.getPiezaList()) {
                Matriz oldMatrizOfPiezaListPieza = piezaListPieza.getMatriz();
                piezaListPieza.setMatriz(matriz);
                piezaListPieza = em.merge(piezaListPieza);
                if (oldMatrizOfPiezaListPieza != null) {
                    oldMatrizOfPiezaListPieza.getPiezaList().remove(piezaListPieza);
                    oldMatrizOfPiezaListPieza = em.merge(oldMatrizOfPiezaListPieza);
                }
            }
            for (Pedidomatriz pedidomatrizListPedidomatriz : matriz.getPedidomatrizList()) {
                Matriz oldIdmatrizOfPedidomatrizListPedidomatriz = pedidomatrizListPedidomatriz.getIdmatriz();
                pedidomatrizListPedidomatriz.setIdmatriz(matriz);
                pedidomatrizListPedidomatriz = em.merge(pedidomatrizListPedidomatriz);
                if (oldIdmatrizOfPedidomatrizListPedidomatriz != null) {
                    oldIdmatrizOfPedidomatrizListPedidomatriz.getPedidomatrizList().remove(pedidomatrizListPedidomatriz);
                    oldIdmatrizOfPedidomatrizListPedidomatriz = em.merge(oldIdmatrizOfPedidomatrizListPedidomatriz);
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
            Tipomaterial tipomaterialOld = persistentMatriz.getTipomaterial();
            Tipomaterial tipomaterialNew = matriz.getTipomaterial();
            Materiaprima materiaprimaOld = persistentMatriz.getMateriaprima();
            Materiaprima materiaprimaNew = matriz.getMateriaprima();
            List<Pieza> piezaListOld = persistentMatriz.getPiezaList();
            List<Pieza> piezaListNew = matriz.getPiezaList();
            List<Pedidomatriz> pedidomatrizListOld = persistentMatriz.getPedidomatrizList();
            List<Pedidomatriz> pedidomatrizListNew = matriz.getPedidomatrizList();
            if (tipomaterialNew != null) {
                tipomaterialNew = em.getReference(tipomaterialNew.getClass(), tipomaterialNew.getIdtipomaterial());
                matriz.setTipomaterial(tipomaterialNew);
            }
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                matriz.setMateriaprima(materiaprimaNew);
            }
            List<Pieza> attachedPiezaListNew = new ArrayList<Pieza>();
            for (Pieza piezaListNewPiezaToAttach : piezaListNew) {
                piezaListNewPiezaToAttach = em.getReference(piezaListNewPiezaToAttach.getClass(), piezaListNewPiezaToAttach.getIdpieza());
                attachedPiezaListNew.add(piezaListNewPiezaToAttach);
            }
            piezaListNew = attachedPiezaListNew;
            matriz.setPiezaList(piezaListNew);
            List<Pedidomatriz> attachedPedidomatrizListNew = new ArrayList<Pedidomatriz>();
            for (Pedidomatriz pedidomatrizListNewPedidomatrizToAttach : pedidomatrizListNew) {
                pedidomatrizListNewPedidomatrizToAttach = em.getReference(pedidomatrizListNewPedidomatrizToAttach.getClass(), pedidomatrizListNewPedidomatrizToAttach.getIdpedidomatriz());
                attachedPedidomatrizListNew.add(pedidomatrizListNewPedidomatrizToAttach);
            }
            pedidomatrizListNew = attachedPedidomatrizListNew;
            matriz.setPedidomatrizList(pedidomatrizListNew);
            matriz = em.merge(matriz);
            if (tipomaterialOld != null && !tipomaterialOld.equals(tipomaterialNew)) {
                tipomaterialOld.getMatrizList().remove(matriz);
                tipomaterialOld = em.merge(tipomaterialOld);
            }
            if (tipomaterialNew != null && !tipomaterialNew.equals(tipomaterialOld)) {
                tipomaterialNew.getMatrizList().add(matriz);
                tipomaterialNew = em.merge(tipomaterialNew);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getMatrizList().remove(matriz);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getMatrizList().add(matriz);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            for (Pieza piezaListOldPieza : piezaListOld) {
                if (!piezaListNew.contains(piezaListOldPieza)) {
                    piezaListOldPieza.setMatriz(null);
                    piezaListOldPieza = em.merge(piezaListOldPieza);
                }
            }
            for (Pieza piezaListNewPieza : piezaListNew) {
                if (!piezaListOld.contains(piezaListNewPieza)) {
                    Matriz oldMatrizOfPiezaListNewPieza = piezaListNewPieza.getMatriz();
                    piezaListNewPieza.setMatriz(matriz);
                    piezaListNewPieza = em.merge(piezaListNewPieza);
                    if (oldMatrizOfPiezaListNewPieza != null && !oldMatrizOfPiezaListNewPieza.equals(matriz)) {
                        oldMatrizOfPiezaListNewPieza.getPiezaList().remove(piezaListNewPieza);
                        oldMatrizOfPiezaListNewPieza = em.merge(oldMatrizOfPiezaListNewPieza);
                    }
                }
            }
            for (Pedidomatriz pedidomatrizListOldPedidomatriz : pedidomatrizListOld) {
                if (!pedidomatrizListNew.contains(pedidomatrizListOldPedidomatriz)) {
                    pedidomatrizListOldPedidomatriz.setIdmatriz(null);
                    pedidomatrizListOldPedidomatriz = em.merge(pedidomatrizListOldPedidomatriz);
                }
            }
            for (Pedidomatriz pedidomatrizListNewPedidomatriz : pedidomatrizListNew) {
                if (!pedidomatrizListOld.contains(pedidomatrizListNewPedidomatriz)) {
                    Matriz oldIdmatrizOfPedidomatrizListNewPedidomatriz = pedidomatrizListNewPedidomatriz.getIdmatriz();
                    pedidomatrizListNewPedidomatriz.setIdmatriz(matriz);
                    pedidomatrizListNewPedidomatriz = em.merge(pedidomatrizListNewPedidomatriz);
                    if (oldIdmatrizOfPedidomatrizListNewPedidomatriz != null && !oldIdmatrizOfPedidomatrizListNewPedidomatriz.equals(matriz)) {
                        oldIdmatrizOfPedidomatrizListNewPedidomatriz.getPedidomatrizList().remove(pedidomatrizListNewPedidomatriz);
                        oldIdmatrizOfPedidomatrizListNewPedidomatriz = em.merge(oldIdmatrizOfPedidomatrizListNewPedidomatriz);
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
            Tipomaterial tipomaterial = matriz.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial.getMatrizList().remove(matriz);
                tipomaterial = em.merge(tipomaterial);
            }
            Materiaprima materiaprima = matriz.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getMatrizList().remove(matriz);
                materiaprima = em.merge(materiaprima);
            }
            List<Pieza> piezaList = matriz.getPiezaList();
            for (Pieza piezaListPieza : piezaList) {
                piezaListPieza.setMatriz(null);
                piezaListPieza = em.merge(piezaListPieza);
            }
            List<Pedidomatriz> pedidomatrizList = matriz.getPedidomatrizList();
            for (Pedidomatriz pedidomatrizListPedidomatriz : pedidomatrizList) {
                pedidomatrizListPedidomatriz.setIdmatriz(null);
                pedidomatrizListPedidomatriz = em.merge(pedidomatrizListPedidomatriz);
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
