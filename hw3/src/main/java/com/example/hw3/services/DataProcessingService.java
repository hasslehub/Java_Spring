package com.example.hw3.services;

import com.example.hw3.domain.User;
import com.example.hw3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис работы с хранилищем пользователей.
 */
@Service
public class DataProcessingService {

    public UserRepository getRepository() {
        return repository;
    }

    @Autowired
    private UserRepository repository;

    // Сортировка пользователей по возрасту.
    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    // Фильтр списка пользователей по возрасту
    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    // Cреднbq возраст пользователей
    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    // Добавление пользователя
    public void  addUserToList(User user)
    {
        repository.addUser(user);
    }
}