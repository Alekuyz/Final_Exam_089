/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Exam.Exam_Pws;

import Final_Exam.Exam_Pws.exceptions.NonexistentEntityException;
import Final_Exam.Exam_Pws.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author AXEL
 */
public class DatapenJpaController implements Serializable {

    public DatapenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Final_Exam_Exam_Pws_jar_0.0.1-SNAPSHOTPU");

    public DatapenJpaController() {
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datapen datapen) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datapen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatapen(datapen.getId()) != null) {
                throw new PreexistingEntityException("Datapen " + datapen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datapen datapen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datapen = em.merge(datapen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datapen.getId();
                if (findDatapen(id) == null) {
                    throw new NonexistentEntityException("The datapen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datapen datapen;
            try {
                datapen = em.getReference(Datapen.class, id);
                datapen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datapen with id " + id + " no longer exists.", enfe);
            }
            em.remove(datapen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datapen> findDatapenEntities() {
        return findDatapenEntities(true, -1, -1);
    }

    public List<Datapen> findDatapenEntities(int maxResults, int firstResult) {
        return findDatapenEntities(false, maxResults, firstResult);
    }

    private List<Datapen> findDatapenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datapen.class));
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

    public Datapen findDatapen(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datapen.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatapenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datapen> rt = cq.from(Datapen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
