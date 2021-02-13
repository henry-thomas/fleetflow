/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.facade;

import fleetflow.entity.VehicleAsset;
import fleetflow.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henry
 */
public class VehicleAssetDao implements Serializable {

    public VehicleAssetDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VehicleAsset vehicleAsset) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vehicleAsset);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VehicleAsset vehicleAsset) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vehicleAsset = em.merge(vehicleAsset);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vehicleAsset.getId();
                if (findVehicleAsset(id) == null) {
                    throw new NonexistentEntityException("The vehicleAsset with id " + id + " no longer exists.");
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
            VehicleAsset vehicleAsset;
            try {
                vehicleAsset = em.getReference(VehicleAsset.class, id);
                vehicleAsset.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehicleAsset with id " + id + " no longer exists.", enfe);
            }
            em.remove(vehicleAsset);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VehicleAsset> findVehicleAssetEntities() {
        return findVehicleAssetEntities(true, -1, -1);
    }

    public List<VehicleAsset> findVehicleAssetEntities(int maxResults, int firstResult) {
        return findVehicleAssetEntities(false, maxResults, firstResult);
    }

    private List<VehicleAsset> findVehicleAssetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VehicleAsset.class));
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

    public VehicleAsset findVehicleAsset(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VehicleAsset.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehicleAssetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VehicleAsset> rt = cq.from(VehicleAsset.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
