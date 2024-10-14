//package org.example;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.scheduler.TaskStatusScheduler;
//import org.example.services.manager.TaskService;
//
//import java.io.IOException;
//
//
//@WebServlet(name = "Main", value = "/")
//public class Main extends HttpServlet {
//
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
//    }
//}