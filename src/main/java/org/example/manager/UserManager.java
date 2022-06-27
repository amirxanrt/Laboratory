package org.example.manager;

import org.example.dto.UserDTO;
import org.example.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;


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

    public UserDTO create(final String name) {
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