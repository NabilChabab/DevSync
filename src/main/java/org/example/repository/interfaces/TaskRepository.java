package org.example.repository.interfaces;

import org.example.models.Task;

import java.util.List;

public interface TaskRepository {
    void save(Task task);

    List<Task> findAll();

    Task findById(Long id);

    void update(Task task);

    void delete(Long id);

    List<Task> findLastFoor();

    List<Task> findByUserId(Long userId);
}
