package org.example.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Task;
import org.example.models.Token;
import org.example.models.TokenRequest;
import org.example.models.User;
import org.example.models.enums.Request;
import org.example.models.enums.Status;
import org.example.models.enums.TokenType;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.TokenRepositoryImpl;
import org.example.repository.TokenRequestImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.interfaces.TokenRepository;
import org.example.repository.interfaces.TokenRequestRepository;
import org.example.repository.interfaces.UserRepository;
import org.example.services.user.AssignedTasksService;
import org.example.validation.AssignedTasksValidator;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "AssignedTasksController", value = "/user/assigned-tasks")
public class AssignedTasksController extends HttpServlet {

    private final AssignedTasksService service = new AssignedTasksService(
            new TaskRepositoryImpl(),
            new TokenRequestImpl(),
            new TokenRepositoryImpl(),
            new UserRepositoryImpl(),
            new AssignedTasksValidator()
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = ((User) request.getSession().getAttribute("user")).getId();
        List<Task> tasks = service.getAssignedTasks(userId);
        List<Task> lastTasks = service.getLastFourTasks(userId);
        List<Token> tokens = service.getUserTokens(userId);

        request.setAttribute("tasks", tasks);
        request.setAttribute("lastTasks", lastTasks);
        request.setAttribute("tokens", tokens);
        request.getRequestDispatcher("/views/user/assignedTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        if (request.getParameter("task_id") != null) {
            service.updateTaskStatus(Long.parseLong(request.getParameter("task_id")), request.getParameter("status"));
            request.getSession().setAttribute("success", "Task updated successfully!");
        } else if ("delete".equals(method)) {
            service.deleteTask(Long.parseLong(request.getParameter("id")));
            request.getSession().setAttribute("success", "Task deleted successfully!");
        } else {
            service.requestToken(request);
            request.getSession().setAttribute("success", "Request for token sent successfully!");
        }
        response.sendRedirect(request.getContextPath() + "/user/assigned-tasks");
    }
}
