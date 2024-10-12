package org.example.controller.manager;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.exceptions.ValidationException;
import org.example.models.Tag;
import org.example.models.Task;
import org.example.models.User;
import org.example.models.enums.Status;
import org.example.models.enums.UserRole;
import org.example.repository.TagRepositoryImpl;
import org.example.repository.interfaces.TagRepository;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.UserRepository;
import org.example.scheduler.TaskStatusScheduler;
import org.example.services.manager.TaskService;
import org.example.validation.TaskValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "TaskController", value = "/manager/tasks")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class TaskController extends HttpServlet {

    private TaskService taskService;


    public TaskController() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        TagRepository tagRepository = new TagRepositoryImpl();
        TaskValidator taskValidator = new TaskValidator();
        this.taskService = new TaskService(taskRepository, userRepository, tagRepository, taskValidator);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = taskService.getUsersByRole(UserRole.USER);
        List<Task> tasks = taskService.getAllTasks();
        List<Task> lastTasks = taskService.getLastTasks();
        List<Tag> tags = taskService.getAllTags();

        request.setAttribute("users", users);
        request.setAttribute("tasks", tasks);
        request.setAttribute("lastTasks", lastTasks);
        request.setAttribute("tags", tags);

        request.getRequestDispatcher("/views/manager/tasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        try {
            if ("delete".equalsIgnoreCase(method)) {
                taskService.deleteTask(Long.parseLong(request.getParameter("id")));
            } else if (request.getParameter("userId") != null) {
                request.getSession().setAttribute("success", "Task updated successfully!");
                taskService.updateUserId(request);
            } else if (request.getParameter("status") != null) {
                request.getSession().setAttribute("success", "Task updated successfully!");
                updateStatus(request);
            } else {
                request.getSession().setAttribute("success", "Task saved successfully!");
                taskService.saveTask(request);
            }
            response.sendRedirect(request.getContextPath() + "/manager/tasks");
        } catch (ValidationException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/manager/tasks");
        }
    }

    private void updateStatus(HttpServletRequest request) {
        Long taskId = Long.parseLong(request.getParameter("task_id"));
        Status status = Status.valueOf(request.getParameter("status"));
        taskService.updateStatus(status, taskId);
    }
}
