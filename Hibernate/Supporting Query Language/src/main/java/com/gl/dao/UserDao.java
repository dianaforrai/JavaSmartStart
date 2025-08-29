package com.gl.dao;

import com.gl.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-unit");
    private EntityManager em = emf.createEntityManager();

    // JPQL Query - Users with more than X posts
    public List<User> findUsersWithMoreThanPosts(int count) {
        return em.createQuery(
                        "SELECT u FROM User u WHERE SIZE(u.posts) > :count", User.class)
                .setParameter("count", count)
                .getResultList();
    }

    // Native Query - Users with no posts
    public List<User> findUsersWithNoPosts() {
        return em.createNativeQuery(
                        "SELECT * FROM users u WHERE u.id NOT IN (SELECT user_id FROM posts)", User.class)
                .getResultList();
    }

    // Criteria API - Posts by user
    public List<Object> findPostsByUser(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Root<User> user = cq.from(User.class);
        cq.select(user.get("posts")).where(cb.equal(user.get("id"), userId));
        return em.createQuery(cq).getResultList();
    }

    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
