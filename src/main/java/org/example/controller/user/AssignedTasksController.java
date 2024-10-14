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

import java.io.IOException;
import java.util.List;


@WebServlet(name = "AssignedTasksController", value = "/user/assigned-tasks")
public class AssignedTasksController extends HttpServlet {


    private TaskRepository taskRepository = new TaskRepositoryImpl();

    private TokenRequestRepository tokenRequestRepository = new TokenRequestImpl();

    private TokenRepository tokenRepository = new TokenRepositoryImpl();

    private UserRepository userRepository = new UserRepositoryImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Long userId = user.getId();
        List<Task> tasks = taskRepository.findAllAssignedTasks(userId);
        List<Task> lastTasks = taskRepository.findLastFoorByUserId(userId);
        List<Token> tokens = tokenRepository.findTokenByUserId(userId);
        request.setAttribute("tasks", tasks);
        request.setAttribute("lastTasks", lastTasks);
        request.setAttribute("tokens", tokens);
        request.getRequestDispatcher("/views/user/assignedTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (request.getParameter("task_id") != null) {
            request.getSession().setAttribute("success", "Task updated successfully!");
            updateStatus(request);
        } else if (method.equalsIgnoreCase("delete")) {
            request.getSession().setAttribute("success", "Task deleted successfully!");
            deleteTask(request);

        } else{
            request.getSession().setAttribute("success", "Request for token sent successfully!");
            makeRequestForToken(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/user/assigned-tasks");
    }

    private void updateStatus(HttpServletRequest request) {
        Long taskId = Long.parseLong(request.getParameter("task_id"));
        Status status = Status.valueOf(request.getParameter("status"));
        taskRepository.updateStatus(status, taskId);
    }

    private void makeRequestForToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setTask(taskRepository.findById(Long.parseLong(request.getParameter("taskId"))));
        tokenRequest.setUser(userRepository.findById(Long.parseLong(request.getParameter("user_id"))));
        tokenRequest.setRequestType(Request.PENDING);
        tokenRequest.setTokenType(TokenType.MODIFY);
        tokenRequest.setMessage(request.getParameter("message"));
        tokenRequestRepository.save(tokenRequest);
        request.getSession().setAttribute("tokenRequest", tokenRequest);
    }

    private void deleteTask(HttpServletRequest request) {
        Long taskId = Long.parseLong(request.getParameter("id"));
        taskRepository.delete(taskId);
    }
}
