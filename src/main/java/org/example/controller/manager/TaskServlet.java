package org.example.controller.manager;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.models.Task;
import org.example.models.User;
import org.example.models.enums.Status;
import org.example.models.enums.UserRole;
import org.example.repository.interfaces.TaskRepository;
import org.example.repository.TaskRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "TaskServlet", value = "/manager/tasks")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class TaskServlet extends HttpServlet {


    private TaskRepository taskRepository = new TaskRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Task> tasks = taskRepository.findAll();
        List<Task> lastTasks = taskRepository.findLastFoor();
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
            update(request, response);
        } else {
            save(request, response);
        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("start_date"));
        LocalDate endDate = LocalDate.parse(request.getParameter("end_date"));
        Long userId = Long.parseLong(request.getParameter("user_id"));
        Long managerId = Long.parseLong(request.getParameter("manager_id"));

        Part filePart = request.getPart("file");
        String filePath = null;

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            Path uploadPath = Paths.get(getServletContext().getRealPath("public/uploads"));
            Files.createDirectories(uploadPath);
            filePath = "public/uploads/" + fileName;
            Files.copy(filePart.getInputStream(), uploadPath.resolve(fileName));
        }

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(Status.PENDING);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setUser(userRepository.findById(userId));
        task.setManager(userRepository.findById(managerId));
        task.setFile(filePath); // filePath will be null if no file is uploaded

        taskRepository.save(task);

        response.sendRedirect(request.getContextPath() + "/manager/tasks");



    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("task_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("start_date"));
        LocalDate endDate = LocalDate.parse(request.getParameter("end_date"));
        Long userId = Long.parseLong(request.getParameter("user_id"));
        Long managerId = Long.parseLong(request.getParameter("manager_id"));

        Task task = taskRepository.findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setUser(userRepository.findById(userId));
        task.setManager(userRepository.findById(managerId));

        taskRepository.update(task);

        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        taskRepository.delete(id);
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
    }
}
