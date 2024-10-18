package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.enums.Request;
import org.example.models.enums.Status;
import org.example.repository.interfaces.StatisticsRepository;

public class StatisticsRepositoryImpl implements StatisticsRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    @Override
    public int findTasksCountByStatus(Status status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(t) FROM Task t WHERE t.status = :status", Long.class)
                    .setParameter("status", status)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int findTasksCountByToday() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(t) FROM Task t WHERE t.createdAt = CURRENT_DATE", Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int findTasksCountByWeek() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(t) FROM Task t WHERE t.createdAt BETWEEN CURRENT_DATE - 7 AND CURRENT_DATE", Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int findAssignedTasksCount(Long managerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(t) FROM Task t WHERE t.manager.id = :managerId AND t.user.id IS NOT NULL", Long.class)
                    .setParameter("managerId", managerId)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int findTokenUsesCountByRequest() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager.createQuery(
                            "SELECT COUNT(t) FROM TokenRequest t WHERE t.requestType = :requestType", Long.class)
                    .setParameter("requestType", Request.ACCEPTED)
                    .getSingleResult();
            return count != null ? count.intValue() : 0;
        } finally {
            entityManager.close();
        }
    }
}
