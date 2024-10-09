package org.example.controller.manager;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Task;
import org.example.models.User;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.UserRepository;
import org.example.services.manager.TaskService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskController", value = "/manager/tasks")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class TaskController extends HttpServlet {


    private TaskService taskService;

    @Override
    public void init(){
        TaskRepository taskRepository = new TaskRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        taskService = new TaskService(taskRepository, userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = taskService.getUsersByRole();
        List<Task> tasks = taskService.getAllTasks();
        List<Task> lastTasks = taskService.getLastTasks();
        request.setAttribute("users", users);
        request.setAttribute("tasks", tasks);
        request.setAttribute("lastTasks", lastTasks);
        request.getRequestDispatcher("/views/manager/tasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        String userId = request.getParameter("userId");

        if ("delete".equalsIgnoreCase(method)) {
            delete(request, response);
        } else if (userId != null && !userId.isEmpty()) {
            updateUserId(request, response);
        } else {
            save(request, response);
        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        taskService.saveTask(request);
        request.getSession().setAttribute("success", "Task created successfully!");
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        taskService.updateTask(request);
        request.getSession().setAttribute("success", "Task updated successfully!");
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }

    public void updateUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        taskService.updateUserId(request);
        request.getSession().setAttribute("success", "Assignment updated successfully!");
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        taskService.deleteTask(id);
        request.getSession().setAttribute("success", "Task deleted successfully!");
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }
}
