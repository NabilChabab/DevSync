package org.example.controller.manager;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Tag;
import org.example.repository.TagRepositoryImpl;
import org.example.repository.interfaces.TagRepository;

import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.delete;

@WebServlet(name = "TagController", value = "/manager/tags")
public class TagController extends HttpServlet {

    private TagRepository tagRepository;


    public TagController() {
        this.tagRepository = new TagRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        save(request);
        response.sendRedirect(request.getContextPath() + "/manager/tasks");
        request.getSession().setAttribute("success", "Tag created successfully.");
    }

    public void save(HttpServletRequest request) {
        String name = request.getParameter("name");
        Tag tag = new Tag();
        tag.setName(name);
        tagRepository.save(tag);
    }
}
