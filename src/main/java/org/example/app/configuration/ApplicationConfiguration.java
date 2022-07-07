package org.example.app.configuration;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.app.controller.UserController;
import org.example.app.manager.UserManager;
import org.example.framework.handler.WebHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public Map<String, WebHandler> handlers(final UserController userController) {
        return Map.of(
                "/users.getAll", userController::getAll,
                "/users.getById", userController::getById,
                "/users.create", userController::create,
                "/users.deleteById", userController::deleteById
        );
    }
}
