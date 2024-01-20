package com.example.hw3.services;

import com.example.hw3.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис создания пользователей.
 */
@Service
public class UserService {

    // @Autowired
    private NotificationService notificationService;

    // Внедрение зависимости через конструктор
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Создание нового пользователя.
     * @param name имя пользователя.
     * @param age возраст пользователя.
     * @param email email пользователя.
     * @return созданного пользователя.
     */
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);

        return user;
    }
}