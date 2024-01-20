package com.example.hw3.services;

import com.example.hw3.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис регистрации новых пользователей.
 */
@Service
public class RegistrationService {

    @Autowired
    private DataProcessingService dataProcessingService;

    //Поля UserService, NotificationService
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;


    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }


    //Метод processRegistration
    /**
     * Добавление нового пользователя
     * @param name name
     * @param age age
     * @param email email
     */
    public void processRegistration(String name, int age, String email){
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        notificationService.sendNotification("Новый пользователь добавлен в БД");
    }
}