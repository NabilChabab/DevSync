package org.example.controller.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.models.User;
import org.example.models.enums.UserRole;
import org.example.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ManagerServlet extends HttpServlet {

    private UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        String userId = request.getParameter("user_id");

        if ("delete".equalsIgnoreCase(method)) {
            delete(request, response);
        } else if (userId != null && !userId.isEmpty()) {
            update(request, response);
        } else {
            save(request, response);
        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        Part filePart = request.getPart("profile");
        String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Path uploadPath = Paths.get(getServletContext().getRealPath("uploads"));
        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(filePart.getInputStream(), filePath);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole(UserRole.valueOf(role));
        user.setProfile("uploads/" + fileName);

        userRepository.save(user);


        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userRepository.findAll();
        List<User> lastUsers = userRepository.findLastFoor();

        request.setAttribute("users", users);
        request.setAttribute("last_users", lastUsers);

        request.getRequestDispatcher("/manager/dashboard.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("user_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = userRepository.findById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.update(user);

        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        userRepository.delete(id);
        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }
}
