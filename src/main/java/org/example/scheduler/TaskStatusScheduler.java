package org.example.scheduler;

import org.example.models.Task;
import org.example.models.enums.Status;
import org.example.repository.TagRepositoryImpl;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.services.manager.TaskService;
import org.example.validation.TaskValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TaskStatusScheduler {
    private final TaskService taskService;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public TaskStatusScheduler() {
        this.taskService = new TaskService(new TaskRepositoryImpl(), new UserRepositoryImpl(), new TagRepositoryImpl(), new TaskValidator());
    }

    public void startScheduler() {
        scheduler.scheduleAtFixedRate(this::checkAndUpdateTaskStatuses, 0, 1, TimeUnit.DAYS);
    }

    private void checkAndUpdateTaskStatuses() {
        List<Task> tasks = taskService.getAllTasks();
        LocalDate currentDate = LocalDate.now();

        for (Task task : tasks) {
            if (task.getEndDate().isBefore(currentDate) && task.getStatus() != Status.DONE) {
                taskService.updateStatus(Status.CANCELLED, task.getId());
            }
        }
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }
}