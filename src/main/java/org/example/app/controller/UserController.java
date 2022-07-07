package org.example.app.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserDTO;
import org.example.app.manager.UserManager;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserManager manager;
    Gson gson = new Gson();
    String login = "petya";


    public void getAll(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        List<UserDTO> responseDTO = manager.getAll();
        res.getWriter().write(gson.toJson(responseDTO));
    }

    public void getById(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        UserDTO responseDTO = manager.getById(Integer.parseInt(req.getParameter("id")));
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