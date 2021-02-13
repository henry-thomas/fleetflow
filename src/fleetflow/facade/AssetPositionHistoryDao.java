/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.facade;

import fleetflow.entity.AssetPositionHistory;
import fleetflow.facade.exceptions.NonexistentEntityException;
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
public class AssetPositionHistoryDao implements Serializable {

    public AssetPositionHistoryDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AssetPositionHistory assetPositionHistory) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(assetPositionHistory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AssetPositionHistory assetPositionHistory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            assetPositionHistory = em.merge(assetPositionHistory);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = assetPositionHistory.getId();
                if (findAssetPositionHistory(id) == null) {
                    throw new NonexistentEntityException("The assetPositionHistory with id " + id + " no longer exists.");
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
            AssetPositionHistory assetPositionHistory;
            try {
                assetPositionHistory = em.getReference(AssetPositionHistory.class, id);
                assetPositionHistory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assetPositionHistory with id " + id + " no longer exists.", enfe);
            }
            em.remove(assetPositionHistory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AssetPositionHistory> findAssetPositionHistoryEntities() {
        return findAssetPositionHistoryEntities(true, -1, -1);
    }

    public List<AssetPositionHistory> findAssetPositionHistoryEntities(int maxResults, int firstResult) {
        return findAssetPositionHistoryEntities(false, maxResults, firstResult);
    }

    private List<AssetPositionHistory> findAssetPositionHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AssetPositionHistory.class));
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

    public AssetPositionHistory findAssetPositionHistory(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AssetPositionHistory.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssetPositionHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AssetPositionHistory> rt = cq.from(AssetPositionHistory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
