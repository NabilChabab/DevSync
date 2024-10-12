package org.example.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Task;
import org.example.models.User;
import org.example.models.enums.Status;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.interfaces.TaskRepository;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "AssignedTasksController", value = "/user/assigned-tasks")
public class AssignedTasksController extends HttpServlet {


    private TaskRepository taskRepository = new TaskRepositoryImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Long userId = user.getId();
        List<Task> tasks = taskRepository.findAllAssignedTasks(userId);
        List<Task> lastTasks = taskRepository.findLastFoorByUserId(userId);
        request.setAttribute("tasks", tasks);
        request.setAttribute("lastTasks", lastTasks);
        request.getRequestDispatcher("/views/user/assignedTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("success", "Task updated successfully!");
        updateStatus(request);
        response.sendRedirect(request.getContextPath() + "/user/assigned-tasks");
    }

    private void updateStatus(HttpServletRequest request) {
        Long taskId = Long.parseLong(request.getParameter("task_id"));
        Status status = Status.valueOf(request.getParameter("status"));
        taskRepository.updateStatus(status, taskId);
    }
}
