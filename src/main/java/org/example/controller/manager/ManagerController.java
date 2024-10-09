package org.example.controller.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.User;
import org.example.repository.interfaces.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.services.manager.ManagerService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ManagerController", value = "/manager/dashboard")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ManagerController extends HttpServlet {
    private ManagerService managerService;

    @Override
    public void init() {
        UserRepository userRepository = new UserRepositoryImpl();
        managerService = new ManagerService(userRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        String userId = request.getParameter("user_id");

        if ("delete".equalsIgnoreCase(method)) {
            managerService.deleteUser(request);
        } else if (userId != null && !userId.isEmpty()) {
            managerService.updateUser(request);
        } else {
            managerService.saveUser(request);
        }

        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = managerService.getAllUsers();
        List<User> lastUsers = managerService.getLastUsers();

        request.setAttribute("users", users);
        request.setAttribute("last_users", lastUsers);

        request.getRequestDispatcher("/views/manager/dashboard.jsp").forward(request, response);
    }
}
