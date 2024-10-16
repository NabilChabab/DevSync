package org.example.services.user;

import jakarta.servlet.http.HttpServletRequest;
import org.example.models.Task;
import org.example.models.Token;
import org.example.models.TokenRequest;
import org.example.models.enums.Request;
import org.example.models.enums.Status;
import org.example.models.enums.TokenType;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.interfaces.TokenRepository;
import org.example.repository.interfaces.TokenRequestRepository;
import org.example.repository.interfaces.UserRepository;
import org.example.validation.AssignedTasksValidator;

import java.util.List;

public class AssignedTasksService {

    private final TaskRepository taskRepository;
    private final TokenRequestRepository tokenRequestRepository;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final AssignedTasksValidator validator;

    public AssignedTasksService(TaskRepository taskRepository, TokenRequestRepository tokenRequestRepository,
                                TokenRepository tokenRepository, UserRepository userRepository,
                                AssignedTasksValidator validator) {
        this.taskRepository = taskRepository;
        this.tokenRequestRepository = tokenRequestRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public List<Task> getAssignedTasks(Long userId) {
        return taskRepository.findAllAssignedTasks(userId);
    }

    public List<Task> getLastFourTasks(Long userId) {
        return taskRepository.findLastFoorByUserId(userId);
    }

    public List<Token> getUserTokens(Long userId) {
        return tokenRepository.findTokenByUserId(userId);
    }

    public void updateTaskStatus(Long taskId, String statusStr) {
        Status status = Status.valueOf(statusStr);
        taskRepository.updateStatus(status, taskId);
    }

    public void requestToken(HttpServletRequest request) {
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        Long userId = Long.parseLong(request.getParameter("user_id"));
        String message = request.getParameter("message");

        if (validator.isValidTokenRequest(taskId, userId)) {
            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setTask(taskRepository.findById(taskId));
            tokenRequest.setUser(userRepository.findById(userId));
            tokenRequest.setRequestType(Request.PENDING);
            tokenRequest.setTokenType(TokenType.MODIFY);
            tokenRequest.setMessage(message);
            tokenRequestRepository.save(tokenRequest);
            request.getSession().setAttribute("tokenRequest", tokenRequest);
        } else {
            throw new IllegalArgumentException("Invalid token request parameters");
        }
    }

    public void deleteTask(Long taskId) {
        taskRepository.delete(taskId);
    }
}
