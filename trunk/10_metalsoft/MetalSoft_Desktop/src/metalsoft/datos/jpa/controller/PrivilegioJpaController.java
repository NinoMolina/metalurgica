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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Privilegio;
import metalsoft.datos.jpa.entity.Rolxprivilegio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class PrivilegioJpaController implements Serializable {

    public PrivilegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Privilegio privilegio) throws PreexistingEntityException, Exception {
        if (privilegio.getRolxprivilegioList() == null) {
            privilegio.setRolxprivilegioList(new ArrayList<Rolxprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rolxprivilegio> attachedRolxprivilegioList = new ArrayList<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioListRolxprivilegioToAttach : privilegio.getRolxprivilegioList()) {
                rolxprivilegioListRolxprivilegioToAttach = em.getReference(rolxprivilegioListRolxprivilegioToAttach.getClass(), rolxprivilegioListRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioList.add(rolxprivilegioListRolxprivilegioToAttach);
            }
            privilegio.setRolxprivilegioList(attachedRolxprivilegioList);
            em.persist(privilegio);
            for (Rolxprivilegio rolxprivilegioListRolxprivilegio : privilegio.getRolxprivilegioList()) {
                Privilegio oldPrivilegioOfRolxprivilegioListRolxprivilegio = rolxprivilegioListRolxprivilegio.getPrivilegio();
                rolxprivilegioListRolxprivilegio.setPrivilegio(privilegio);
                rolxprivilegioListRolxprivilegio = em.merge(rolxprivilegioListRolxprivilegio);
                if (oldPrivilegioOfRolxprivilegioListRolxprivilegio != null) {
                    oldPrivilegioOfRolxprivilegioListRolxprivilegio.getRolxprivilegioList().remove(rolxprivilegioListRolxprivilegio);
                    oldPrivilegioOfRolxprivilegioListRolxprivilegio = em.merge(oldPrivilegioOfRolxprivilegioListRolxprivilegio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrivilegio(privilegio.getIdprivilegio()) != null) {
                throw new PreexistingEntityException("Privilegio " + privilegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Privilegio privilegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getIdprivilegio());
            List<Rolxprivilegio> rolxprivilegioListOld = persistentPrivilegio.getRolxprivilegioList();
            List<Rolxprivilegio> rolxprivilegioListNew = privilegio.getRolxprivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Rolxprivilegio rolxprivilegioListOldRolxprivilegio : rolxprivilegioListOld) {
                if (!rolxprivilegioListNew.contains(rolxprivilegioListOldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioListOldRolxprivilegio + " since its privilegio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Rolxprivilegio> attachedRolxprivilegioListNew = new ArrayList<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioListNewRolxprivilegioToAttach : rolxprivilegioListNew) {
                rolxprivilegioListNewRolxprivilegioToAttach = em.getReference(rolxprivilegioListNewRolxprivilegioToAttach.getClass(), rolxprivilegioListNewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioListNew.add(rolxprivilegioListNewRolxprivilegioToAttach);
            }
            rolxprivilegioListNew = attachedRolxprivilegioListNew;
            privilegio.setRolxprivilegioList(rolxprivilegioListNew);
            privilegio = em.merge(privilegio);
            for (Rolxprivilegio rolxprivilegioListNewRolxprivilegio : rolxprivilegioListNew) {
                if (!rolxprivilegioListOld.contains(rolxprivilegioListNewRolxprivilegio)) {
                    Privilegio oldPrivilegioOfRolxprivilegioListNewRolxprivilegio = rolxprivilegioListNewRolxprivilegio.getPrivilegio();
                    rolxprivilegioListNewRolxprivilegio.setPrivilegio(privilegio);
                    rolxprivilegioListNewRolxprivilegio = em.merge(rolxprivilegioListNewRolxprivilegio);
                    if (oldPrivilegioOfRolxprivilegioListNewRolxprivilegio != null && !oldPrivilegioOfRolxprivilegioListNewRolxprivilegio.equals(privilegio)) {
                        oldPrivilegioOfRolxprivilegioListNewRolxprivilegio.getRolxprivilegioList().remove(rolxprivilegioListNewRolxprivilegio);
                        oldPrivilegioOfRolxprivilegioListNewRolxprivilegio = em.merge(oldPrivilegioOfRolxprivilegioListNewRolxprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = privilegio.getIdprivilegio();
                if (findPrivilegio(id) == null) {
                    throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio privilegio;
            try {
                privilegio = em.getReference(Privilegio.class, id);
                privilegio.getIdprivilegio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Rolxprivilegio> rolxprivilegioListOrphanCheck = privilegio.getRolxprivilegioList();
            for (Rolxprivilegio rolxprivilegioListOrphanCheckRolxprivilegio : rolxprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Privilegio (" + privilegio + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioListOrphanCheckRolxprivilegio + " in its rolxprivilegioList field has a non-nullable privilegio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(privilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Privilegio> findPrivilegioEntities() {
        return findPrivilegioEntities(true, -1, -1);
    }

    public List<Privilegio> findPrivilegioEntities(int maxResults, int firstResult) {
        return findPrivilegioEntities(false, maxResults, firstResult);
    }

    private List<Privilegio> findPrivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Privilegio.class));
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

    public Privilegio findPrivilegio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Privilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Privilegio> rt = cq.from(Privilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
