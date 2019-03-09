package ru.lyubov.restclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.lyubov.restclient.model.UserDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class UserClientImpl implements UserClient {

    final RestTemplate restTemplate;

    final String ROOT_URI = "http://localhost:8081/api/users";

    @Autowired
    public UserClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.basicAuthentication("two", "two2").build();
    }


    @Override
    public List<UserDTO> getAllUser() {
            ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(ROOT_URI + "/", UserDTO[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

   @Override
    public UserDTO getUserByLogin(String login) {
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(ROOT_URI + "/get/"+login, UserDTO.class);
        return response.getBody();
    }

    @Override
    public HttpStatus addUser(UserDTO userDTO) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI + "/add", userDTO, HttpStatus.class);
        return response.getStatusCode();
    }

    @Override
    public HttpStatus updateUser(UserDTO userDTO) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI + "/update", userDTO, HttpStatus.class);
        return response.getStatusCode();
    }

    @Override
    public HttpStatus deleteUser(Long id) {
        restTemplate.delete(ROOT_URI + "/delete/" + id);
        return HttpStatus.OK;
    }
}
