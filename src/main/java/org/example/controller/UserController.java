package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.UserDTO;
import org.example.manager.UserManager;

import java.io.IOException;
import java.util.List;

public class UserController {
    private final UserManager manager;
    private final Gson gson;

    public UserController(UserManager manager, Gson gson) {
        this.manager = manager;
        this.gson = gson;
    }

    public void getAll(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        List<UserDTO> responseDTO = manager.getAll();
        res.getWriter().write(gson.toJson(responseDTO));
    }

    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        UserDTO responseDTO = manager.getById(id);
        res.getWriter().write(gson.toJson(responseDTO));
    }

    public void create(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        UserDTO responseDTO = manager.create(req.getParameter("name"));
        res.getWriter().write(gson.toJson(responseDTO));
    }

    public void deleteById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        UserDTO responseDTO = manager.deleteById(id);
        res.getWriter().write(gson.toJson(responseDTO));
    }
}