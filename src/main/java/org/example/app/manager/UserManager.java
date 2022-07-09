package org.example.app.manager;

import lombok.RequiredArgsConstructor;
import org.example.app.dto.UserRequestDTO;
import org.example.app.dto.UserResponseDTO;
import org.example.app.exception.UserLoginAlreadyRegisteredException;
import org.example.app.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final RowMapper<UserResponseDTO> rowMapper = (rs, rowNum) ->
            new UserResponseDTO(rs.getLong("id"), rs.getString("login"));

    public List<UserResponseDTO> getAll() {
        return jdbcOperations.query(
                // language=PostgreSQL
                """
                SELECT id, login FROM users
                """,
                rowMapper
        );
    }

    public UserResponseDTO getById(final long id) {
        try {
            return jdbcOperations.queryForObject(
                    // language=PostgreSQL
                    """
                SELECT id, login FROM users WHERE id = :id
                """,
                    Map.of("id", id),
                    rowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    public UserResponseDTO create(final UserRequestDTO requestDTO) {

        final boolean loginAlreadyRegistered = jdbcOperations.queryForObject(
                // language=PostgreSQL
                """
                SELECT count(*) != 0 FROM users 
                WHERE login = :login
                """,
                Map.of("login", requestDTO.getLogin()),
                Boolean.class
        );
        if (loginAlreadyRegistered) {
            throw new UserLoginAlreadyRegisteredException(requestDTO.getLogin());
        }

        return jdbcOperations.queryForObject(
                // language=PostgreSQL
                """
                INSERT INTO users(login, password)
                VALUES (:login, :password) 
                RETURNING id, login
                """,
                Map.of(
                        "login", requestDTO.getLogin(),
                        "password", requestDTO.getPassword()
                ),
                BeanPropertyRowMapper.newInstance(UserResponseDTO.class)
        );
    }

    public UserRequestDTO deleteById(final long id) {
        try {
            jdbcOperations.queryForObject(
                // language=PostgreSQL
                """
                DELETE FROM users WHERE id = :id
                """,
                Map.of("id", id),
                rowMapper
            );
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException();
        }
    }
}