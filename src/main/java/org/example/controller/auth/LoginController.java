package org.example.controller.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.interfaces.UserRepository;
import org.example.scheduler.TaskStatusScheduler;
import org.example.scheduler.TokenDeleteScheduler;
import org.example.scheduler.TokenModifyScheduler;


import java.io.IOException;

@WebServlet(name = "LoginController", value = "/auth/login")
public class LoginController extends HttpServlet {



    private UserRepository userRepository = new UserRepositoryImpl();
    private TaskStatusScheduler scheduler;
    private TokenModifyScheduler tokenModifyScheduler;
    private TokenDeleteScheduler tokenDeleteScheduler;

    @Override
    public void init() throws ServletException {
        this.scheduler = new TaskStatusScheduler();
        this.scheduler.startScheduler();
        this.tokenModifyScheduler = new TokenModifyScheduler();
        this.tokenModifyScheduler.startScheduler();
        this.tokenDeleteScheduler = new TokenDeleteScheduler();
        this.tokenDeleteScheduler.startScheduler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean hasErrors = false;

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email is required.");
            hasErrors = true;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("passwordError", "Password is required.");
            hasErrors = true;
        }

        if (hasErrors) {
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
            return;
        }

        User user = userRepository.findByEmail(email);

        if (user != null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user.getRole().toString().equals("MANAGER")) {
                response.sendRedirect(request.getContextPath() + "/manager/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/dashboard");
            }
        } else {
            request.setAttribute("loginError", "Invalid email or password.");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }
    }





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/auth/login");
    }
}