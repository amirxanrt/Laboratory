package org.example.app.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserRequestDTO;
import org.example.app.dto.UserResponseDTO;
import org.example.app.manager.UserManager;
import org.example.framework.security.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserManager manager;
    Gson gson = new Gson();

    @GetMapping("/users")
    public List<UserResponseDTO> getAll(@RequestAttribute final Authentication authentication) {
        final List<UserResponseDTO> responseDTO = manager.getAll();
        return responseDTO;
    }

    @GetMapping("/users/{id}")
    public UserResponseDTO getById(@RequestAttribute final Authentication authentication, @PathVariable final long id) {
        final UserResponseDTO responseDTO = manager.getById(id);
        return  responseDTO;
    }
    @PostMapping("/users")
    public UserResponseDTO create(@RequestBody final UserRequestDTO requestDTO) {
        final UserResponseDTO responseDTO = manager.create(requestDTO);
        return responseDTO;
    }

    @DeleteMapping("/users/{id}")
    public UserResponseDTO deleteById(@PathVariable final long id)  {
        final UserResponseDTO responseDTO = manager.deleteById(id);
        return responseDTO;
    }

}