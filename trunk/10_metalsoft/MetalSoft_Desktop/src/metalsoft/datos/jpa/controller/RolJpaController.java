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
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Rolxprivilegio;

/**
 *
 * @author Nino
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) throws PreexistingEntityException, Exception {
        if (rol.getUsuarioxrolList() == null) {
            rol.setUsuarioxrolList(new ArrayList<Usuarioxrol>());
        }
        if (rol.getRolxprivilegioList() == null) {
            rol.setRolxprivilegioList(new ArrayList<Rolxprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuarioxrol> attachedUsuarioxrolList = new ArrayList<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolListUsuarioxrolToAttach : rol.getUsuarioxrolList()) {
                usuarioxrolListUsuarioxrolToAttach = em.getReference(usuarioxrolListUsuarioxrolToAttach.getClass(), usuarioxrolListUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolList.add(usuarioxrolListUsuarioxrolToAttach);
            }
            rol.setUsuarioxrolList(attachedUsuarioxrolList);
            List<Rolxprivilegio> attachedRolxprivilegioList = new ArrayList<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioListRolxprivilegioToAttach : rol.getRolxprivilegioList()) {
                rolxprivilegioListRolxprivilegioToAttach = em.getReference(rolxprivilegioListRolxprivilegioToAttach.getClass(), rolxprivilegioListRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioList.add(rolxprivilegioListRolxprivilegioToAttach);
            }
            rol.setRolxprivilegioList(attachedRolxprivilegioList);
            em.persist(rol);
            for (Usuarioxrol usuarioxrolListUsuarioxrol : rol.getUsuarioxrolList()) {
                Rol oldRolOfUsuarioxrolListUsuarioxrol = usuarioxrolListUsuarioxrol.getRol();
                usuarioxrolListUsuarioxrol.setRol(rol);
                usuarioxrolListUsuarioxrol = em.merge(usuarioxrolListUsuarioxrol);
                if (oldRolOfUsuarioxrolListUsuarioxrol != null) {
                    oldRolOfUsuarioxrolListUsuarioxrol.getUsuarioxrolList().remove(usuarioxrolListUsuarioxrol);
                    oldRolOfUsuarioxrolListUsuarioxrol = em.merge(oldRolOfUsuarioxrolListUsuarioxrol);
                }
            }
            for (Rolxprivilegio rolxprivilegioListRolxprivilegio : rol.getRolxprivilegioList()) {
                Rol oldRolOfRolxprivilegioListRolxprivilegio = rolxprivilegioListRolxprivilegio.getRol();
                rolxprivilegioListRolxprivilegio.setRol(rol);
                rolxprivilegioListRolxprivilegio = em.merge(rolxprivilegioListRolxprivilegio);
                if (oldRolOfRolxprivilegioListRolxprivilegio != null) {
                    oldRolOfRolxprivilegioListRolxprivilegio.getRolxprivilegioList().remove(rolxprivilegioListRolxprivilegio);
                    oldRolOfRolxprivilegioListRolxprivilegio = em.merge(oldRolOfRolxprivilegioListRolxprivilegio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRol(rol.getIdrol()) != null) {
                throw new PreexistingEntityException("Rol " + rol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getIdrol());
            List<Usuarioxrol> usuarioxrolListOld = persistentRol.getUsuarioxrolList();
            List<Usuarioxrol> usuarioxrolListNew = rol.getUsuarioxrolList();
            List<Rolxprivilegio> rolxprivilegioListOld = persistentRol.getRolxprivilegioList();
            List<Rolxprivilegio> rolxprivilegioListNew = rol.getRolxprivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Usuarioxrol usuarioxrolListOldUsuarioxrol : usuarioxrolListOld) {
                if (!usuarioxrolListNew.contains(usuarioxrolListOldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolListOldUsuarioxrol + " since its rol field is not nullable.");
                }
            }
            for (Rolxprivilegio rolxprivilegioListOldRolxprivilegio : rolxprivilegioListOld) {
                if (!rolxprivilegioListNew.contains(rolxprivilegioListOldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioListOldRolxprivilegio + " since its rol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuarioxrol> attachedUsuarioxrolListNew = new ArrayList<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolListNewUsuarioxrolToAttach : usuarioxrolListNew) {
                usuarioxrolListNewUsuarioxrolToAttach = em.getReference(usuarioxrolListNewUsuarioxrolToAttach.getClass(), usuarioxrolListNewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolListNew.add(usuarioxrolListNewUsuarioxrolToAttach);
            }
            usuarioxrolListNew = attachedUsuarioxrolListNew;
            rol.setUsuarioxrolList(usuarioxrolListNew);
            List<Rolxprivilegio> attachedRolxprivilegioListNew = new ArrayList<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioListNewRolxprivilegioToAttach : rolxprivilegioListNew) {
                rolxprivilegioListNewRolxprivilegioToAttach = em.getReference(rolxprivilegioListNewRolxprivilegioToAttach.getClass(), rolxprivilegioListNewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioListNew.add(rolxprivilegioListNewRolxprivilegioToAttach);
            }
            rolxprivilegioListNew = attachedRolxprivilegioListNew;
            rol.setRolxprivilegioList(rolxprivilegioListNew);
            rol = em.merge(rol);
            for (Usuarioxrol usuarioxrolListNewUsuarioxrol : usuarioxrolListNew) {
                if (!usuarioxrolListOld.contains(usuarioxrolListNewUsuarioxrol)) {
                    Rol oldRolOfUsuarioxrolListNewUsuarioxrol = usuarioxrolListNewUsuarioxrol.getRol();
                    usuarioxrolListNewUsuarioxrol.setRol(rol);
                    usuarioxrolListNewUsuarioxrol = em.merge(usuarioxrolListNewUsuarioxrol);
                    if (oldRolOfUsuarioxrolListNewUsuarioxrol != null && !oldRolOfUsuarioxrolListNewUsuarioxrol.equals(rol)) {
                        oldRolOfUsuarioxrolListNewUsuarioxrol.getUsuarioxrolList().remove(usuarioxrolListNewUsuarioxrol);
                        oldRolOfUsuarioxrolListNewUsuarioxrol = em.merge(oldRolOfUsuarioxrolListNewUsuarioxrol);
                    }
                }
            }
            for (Rolxprivilegio rolxprivilegioListNewRolxprivilegio : rolxprivilegioListNew) {
                if (!rolxprivilegioListOld.contains(rolxprivilegioListNewRolxprivilegio)) {
                    Rol oldRolOfRolxprivilegioListNewRolxprivilegio = rolxprivilegioListNewRolxprivilegio.getRol();
                    rolxprivilegioListNewRolxprivilegio.setRol(rol);
                    rolxprivilegioListNewRolxprivilegio = em.merge(rolxprivilegioListNewRolxprivilegio);
                    if (oldRolOfRolxprivilegioListNewRolxprivilegio != null && !oldRolOfRolxprivilegioListNewRolxprivilegio.equals(rol)) {
                        oldRolOfRolxprivilegioListNewRolxprivilegio.getRolxprivilegioList().remove(rolxprivilegioListNewRolxprivilegio);
                        oldRolOfRolxprivilegioListNewRolxprivilegio = em.merge(oldRolOfRolxprivilegioListNewRolxprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rol.getIdrol();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getIdrol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuarioxrol> usuarioxrolListOrphanCheck = rol.getUsuarioxrolList();
            for (Usuarioxrol usuarioxrolListOrphanCheckUsuarioxrol : usuarioxrolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolListOrphanCheckUsuarioxrol + " in its usuarioxrolList field has a non-nullable rol field.");
            }
            List<Rolxprivilegio> rolxprivilegioListOrphanCheck = rol.getRolxprivilegioList();
            for (Rolxprivilegio rolxprivilegioListOrphanCheckRolxprivilegio : rolxprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioListOrphanCheckRolxprivilegio + " in its rolxprivilegioList field has a non-nullable rol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
