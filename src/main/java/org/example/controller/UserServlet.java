package org.example.controller;

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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@WebServlet
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class UserServlet extends HttpServlet {

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
        String phone = request.getParameter("phone");

        Part filePart = request.getPart("profile");
        String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Path uploadPath = Paths.get(getServletContext().getRealPath("uploads"));

        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(filePart.getInputStream(), filePath);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(UserRole.MANAGER);
        user.setProfile("uploads/" + fileName);

        userRepository.save(user);

        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userRepository.findAll();
        List<User> lastUsers = userRepository.findLastFoor();

        // Set attributes in the request
        request.setAttribute("users", users);
        request.setAttribute("last_users", lastUsers);

        // Forward only once after gathering all the data
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get form data
        Long id = Long.parseLong(request.getParameter("user_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Find user by id
        User user = userRepository.findById(id);

        // Update user
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.update(user);

        // Redirect to index.jsp after updating the user
        response.sendRedirect("users");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        userRepository.delete(id);

        response.sendRedirect("users");
    }


}
