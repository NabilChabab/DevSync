package org.example.controller.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Task;
import org.example.models.User;
import org.example.models.enums.Status;
import org.example.repository.StatisticsRepositoryImpl;
import org.example.repository.TagRepositoryImpl;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.TagRepository;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.interfaces.UserRepository;
import org.example.manager.StatisticsService;
import org.example.services.manager.TaskService;
import org.example.validation.TaskValidator;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "StatisticsController", value = "/manager/statistics")
public class StatisticsController extends HttpServlet {


    private StatisticsService service;
    private TaskService taskService;

    public StatisticsController() {
        this.service = new StatisticsService(new StatisticsRepositoryImpl());
        TaskRepository taskRepository = new TaskRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        TagRepository tagRepository = new TagRepositoryImpl();
        TaskValidator taskValidator = new TaskValidator();
        this.taskService = new TaskService(taskRepository, userRepository, tagRepository, taskValidator);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int tasksTodo = findTasksCountByStatusTodo();
        int tasksInProgress = findTasksCountByStatusInProgress();
        int tasksDone = findTasksCountByStatusDone();
        int tasksCanceled = findTasksCountByStatusCanceled();

        // Retrieve counts for time periods
        int tasksToday = findTasksCountByToday();


        // Retrieve assigned tasks count for manager

        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getId();
        int assignedTasksCount = findAssignedTasksCount(userId);
        List<Task> tasks = taskService.getAllTasksByManagerId(userId);

        // Retrieve token usage count
        int tokenUses = findTokenUsesCountByRequest();

        // Set attributes for the JSP
        req.setAttribute("tasks", tasks);
        req.setAttribute("tasksTodo", tasksTodo);
        req.setAttribute("tasksInProgress", tasksInProgress);
        req.setAttribute("tasksDone", tasksDone);
        req.setAttribute("tasksCanceled", tasksCanceled);
        req.setAttribute("tasksToday", tasksToday);

        req.setAttribute("assignedTasksCount", assignedTasksCount);
        req.setAttribute("tokenUses", tokenUses);

        // Forward to JSP page
        req.getRequestDispatcher("/views/manager/statistics.jsp").forward(req, resp);
    }


    public int findTasksCountByStatusTodo() {
        return service.findTasksCountByStatus(String.valueOf(Status.TODO));
    }

    public int findTasksCountByStatusInProgress() {
        return service.findTasksCountByStatus(String.valueOf(Status.IN_PROGRESS));
    }

    public int findTasksCountByStatusDone() {
        return service.findTasksCountByStatus(String.valueOf(Status.DONE));
    }

    public int findTasksCountByStatusCanceled() {
        return service.findTasksCountByStatus(String.valueOf(Status.CANCELLED));
    }

    public int findTasksCountByToday() {
        return service.findTasksCountByToday();
    }


    public int findAssignedTasksCount(Long managerId) {
        return service.findAssignedTasksCount(managerId);
    }

    public int findTokenUsesCountByRequest() {
        return service.findTokenUsesCountByRequest();
    }




}
