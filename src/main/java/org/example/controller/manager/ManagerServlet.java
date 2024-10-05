package org.example.controller.manager;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
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
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "ManagerServlet", value = "/manager/dashboard")
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Part filePart = request.getPart("profile");
        String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Path uploadPath = Paths.get(getServletContext().getRealPath("public/uploads"));
        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(filePart.getInputStream(), filePath);

        // Hash the password using bcrypt
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);  // Use the hashed password
        user.setRole(UserRole.valueOf(role));
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setProfile("public/uploads/" + fileName);

        userRepository.save(user);

        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userRepository.findAll();
        List<User> lastUsers = userRepository.findLastFoor();

        request.setAttribute("users", users);
        request.setAttribute("last_users", lastUsers);

        request.getRequestDispatcher("/views/manager/dashboard.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("user_id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        User user = userRepository.findById(id);
        user.setUsername(username);
        user.setEmail(email);
        userRepository.update(user);

        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        userRepository.delete(id);
        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
    }
}
