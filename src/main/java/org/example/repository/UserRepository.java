package org.example.repository;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.NoArgsConstructor;
import org.example.models.User;

import java.util.List;

@NoArgsConstructor
public class UserRepository {


    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");


    public void save(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }


    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public User findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(User.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }


    public List<User> findLastFoor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> users = null;
        try {
            users = entityManager.createQuery("SELECT u FROM User u ORDER BY u.id DESC", User.class)
                    .setMaxResults(4) // Fetch only the last 4 users
                    .getResultList();
        } finally {
            entityManager.close();
        }
        return users;
    }

    public User findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }


}
