package org.example.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.auth.LoginService;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/auth/login")
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       loginService.login(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.logout(request, response);
    }
}