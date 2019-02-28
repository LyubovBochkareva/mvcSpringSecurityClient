package ru.lyubov.restclient.config;

import org.springframework.http.HttpStatus;
import ru.lyubov.restclient.model.UserDTO;

import java.util.List;

public interface UserClient {
    List<UserDTO> getAllUser();
    HttpStatus addUser(UserDTO userDTO);
    HttpStatus updateUser(UserDTO userDTO);
    HttpStatus deleteUser(Long id);
    UserDTO getUserByLogin(String login);
}
