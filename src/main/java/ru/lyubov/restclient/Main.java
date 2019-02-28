package ru.lyubov.restclient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import ru.lyubov.restclient.config.AppConfig;
import ru.lyubov.restclient.config.UserClient;
import ru.lyubov.restclient.model.RoleDTO;
import ru.lyubov.restclient.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        UserClient client = applicationContext.getBean(UserClient.class);

        System.out.println("Getting list of all users:");

        for (UserDTO userDTO : client.getAllUser()) {
            System.out.println(userDTO);
        }

        System.out.println("Adding a User");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("restClient");
        userDTO.setPassword("rest1");
        userDTO.setName("RestClient");
        userDTO.setAge(1);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        RoleDTO roleAdmin = new RoleDTO();
        roleAdmin.setId(1L);
        roleAdmin.setName("ADMIN");
        roleDTOList.add(roleAdmin);
        userDTO.setRoles(roleDTOList);

        HttpStatus statusAdd = client.addUser(userDTO);
        System.out.println("Add User response = " + statusAdd);

        System.out.println("Getting list of all users:");

        for (UserDTO userDTO1 : client.getAllUser()) {
            System.out.println(userDTO1);
        }

        System.out.println("Update a User");
        UserDTO userDTO1 = client.getUserByLogin("restClient");
        userDTO1.setUsername("restClientMain");
        userDTO1.setPassword("two2");
        userDTO1.setName("UpdateClient");
        List<RoleDTO> roleDTOListUpdate = new ArrayList<>();
        RoleDTO roleUser = new RoleDTO();
        roleUser.setId(2L);
        roleUser.setName("User");
        roleDTOListUpdate.add(roleUser);
        userDTO1.setRoles(roleDTOListUpdate);
        HttpStatus statusUpdate = client.updateUser(userDTO1);
        System.out.println("Update User response = " + statusUpdate);

        System.out.println("Getting list of all users:");

        for (UserDTO userDTO2 : client.getAllUser()) {
            System.out.println(userDTO2);
        }

        System.out.println("Delete a User");
        UserDTO userDTO2 = client.getUserByLogin("restClientMain");
        HttpStatus statusDelete = client.deleteUser(userDTO2.getId());
        System.out.println("Delete User response = " + statusDelete);

        System.out.println("Getting list of all users:");

        for (UserDTO userDTO3 : client.getAllUser()) {
            System.out.println(userDTO3);
        }
        applicationContext.close();
    }
}
