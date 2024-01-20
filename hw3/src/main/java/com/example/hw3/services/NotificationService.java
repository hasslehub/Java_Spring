package com.example.hw3.services;

import com.example.hw3.domain.User;
import org.springframework.stereotype.Service;

/**
 * Сервис уведомлений.
 */
@Service
public class NotificationService {

    //Вывод уведомления в консоль о создании нового пользователя
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    //Вывод уведомления в консоль
    public void sendNotification(String s) {
        System.out.println(s);
    }
}