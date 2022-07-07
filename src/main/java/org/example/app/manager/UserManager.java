package org.example.app.manager;

import org.example.app.dto.UserDTO;
import org.example.app.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManager {

    private long nextId = 1;
    private final List<UserDTO> users = new ArrayList<>();
    public List<UserDTO> getAll() {
        return users;
    }

    public UserDTO getById(final long id) {
        for (UserDTO user : users){
            if (user.getId() == id){
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public UserDTO create(String name) {
        final UserDTO user = new UserDTO(nextId++, name);
        users.add(user);
        return user;
    }

    public UserDTO deleteById(final long id) {
        for (UserDTO user : users){
            if (user.getId()==id){
                users.remove(user);
            }
            return user;
        }
        throw new UserNotFoundException();
    }
}