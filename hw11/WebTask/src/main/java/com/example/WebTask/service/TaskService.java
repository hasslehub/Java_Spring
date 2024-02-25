package com.example.WebTask.service;

import com.example.WebTask.aspect.TrackUserAction;
import com.example.WebTask.exception.ResourceNotFoundException;
import com.example.WebTask.model.Task;
import com.example.WebTask.model.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;



/**
 * Класс TaskService предоставляет сервисные методы для управления задачами в списке дел.
 */
@Service
@AllArgsConstructor
public class TaskService { ;

    /**
     * Метод для получения всех задач из списка дел.
     * @return Список всех задач.
     */
    //GET http://localhost:8765/rest
    @TrackUserAction
    public List<Task> getAllTasks() {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = template.exchange("http://localhost:8765/rest", HttpMethod.GET, entity, List.class);
        return (List<Task>) response.getBody();
    }

    /**
     * Метод для получения задач по указанному статусу.
     * @param status Статус задач, по которому осуществляется фильтрация.
     * @return Список задач с указанным статусом.
     */
    //GET localhost:8081/rest/status/NOT_STARTED
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = template.exchange("http://localhost:8765/rest/status/" + status, HttpMethod.GET, entity, List.class);
        return (List<Task>) response.getBody();
    }

    /**
     * Метод для обновления статуса задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param status Новый статус задачи.
     * @return Новый статус задачи.
     */
    //POST "/{id}/status/{status}"
    @TrackUserAction
    public Task updateTaskStatus(Long id, TaskStatus status) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String url = String.format("http://localhost:8765/rest/%x/status/%s", id, status);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> response = template.exchange(url, HttpMethod.POST, entity, Task.class);
        return response.getBody();
    }


    /**
     * Метод для обновления информации о задаче по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param updateTask Обновленные данные задачи.
     * @return Обновленная задача.
     * @throws ResourceNotFoundException Если задача с указанным идентификатором не найдена.
     */
    // PUT  localhost:8081/rest/4?description=Edit Task
    public Task updateTask(Long id, Task updateTask) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpMethod method;
        String url = "http://localhost:8765/rest/" + id;
        url += "?description=" + updateTask.getDescription();
        method = HttpMethod.PUT;
        HttpEntity<Task> entity = new HttpEntity<>(updateTask, headers);
        ResponseEntity<Task> response = template.exchange(url, method, entity, Task.class);
        return response.getBody();

    }

    /**
     * Метод для удаления задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     */
    // DELETE localhost:8081/rest/2
    @TrackUserAction
    public void deleteTask(Long id) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> response = template.exchange("http://localhost:8765/rest/" + id, HttpMethod.DELETE, entity, Task.class);
        response.getBody();
    }

    /**
     * Метод для получения задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     * @throws ResourceNotFoundException Если задача с указанным идентификатором не найдена.
     */
    // GET localhost:8081/rest/2
    public Task getTaskById(Long id) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> response = template.exchange("http://localhost:8765/rest/" + id, HttpMethod.GET, entity, Task.class);
        return response.getBody();
    }

    /**
     * Сохранение новой задачии
     * @param description
     * @return
     */
    // POST localhost:8081/rest/add?description=Next Task
    public Task save(String description) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String url = "http://localhost:8765/rest";
        HttpMethod method;
        Task task = new Task(description, TaskStatus.NOT_STARTED);
        url += "/add?description=" + description;
        method = HttpMethod.POST;
        HttpEntity<Task> entity = new HttpEntity<>(task, headers);
        ResponseEntity<Task> response = template.exchange(url, method, entity, Task.class);
        return response.getBody();
    }
}
