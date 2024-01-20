package com.example.hw3.controllers;

import com.example.hw3.domain.User;
import com.example.hw3.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер обработки задач.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service; // Сервис обработки задач

    /**
     * Получение списка задач.
     * @return список задач.
     */
    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }

    /**
     * Получение списка пользователей отсортированных по возрасту.
     * @return JSON список пользователей.
     */
    @GetMapping("/sort") //localhost:8080/tasks/sort
    public List<User> sortUsersByAge()
    {
        return service.sortUsersByAge(service.getRepository().getUsers());
    }

    //метод filterUsersByAge
    //Подсказка  @GetMapping("/filter/{age}")
    /**
     * Фильтрация Users по возрасту
     * @param age age
     * @return Отфильтрованный список пользователей
     */
    @GetMapping("/filter/{age}") //localhost:8080/tasks/filter/23
    public List<User> filterUsersByAge(@PathVariable("age") int age) {
        return service.filterUsersByAge(service.getRepository().getUsers(), age);
    }

    //метод calculateAverageAge
    //Подсказка  @GetMapping("/calc")
    /**
     * Средний возраст Users
     * @return
     */
    @GetMapping("/calc") //localhost:8080/tasks/calc
    public double calculateAverageAge() {
        return service.calculateAverageAge(service.getRepository().getUsers());
    }
}