package com.example.hw3.controllers;

import com.example.hw3.domain.User;
import com.example.hw3.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер пользователей.
 */

@RestController
@RequestMapping("/user") //localhost:8080/user
public class UserController {


    @Autowired
    private RegistrationService service; // Сервис регистрации пользователе

    /**
     * Читаем из базы лист юзеров localhost:8080/user
     * @return
     */
    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }


    /**
     * Добавление нового пользователя.
     * @RequestBody тело запроса.
     * @return подтверждение добавления пользователя.
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody(required=false) User user)
    {
        if (user != null){
            service.getDataProcessingService().getRepository().addUser(user);//.getUsers().add(user);
            return "User added from body!";
        }
        return "Request is NULL!";
    }

    /**
     * Добавление юзера через параметры HTTP запроса
     * @param name
     * @param age
     * @param email
     * @return
     */
    @GetMapping("/new") // localhost:8080/user/new
    public String userAddFromParam(@RequestParam(value = "name", defaultValue = "Petr") String name,
                                   @RequestParam(value = "age", defaultValue = "20") int age,
                                   @RequestParam(value = "email", defaultValue = "Petr@mail.com") String email) {
        service.processRegistration(name, age, email);
        return "User added from HTTP request!";
    }
}
