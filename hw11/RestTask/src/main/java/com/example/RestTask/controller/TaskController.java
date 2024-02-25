package com.example.RestTask.controller;

import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;
import com.example.RestTask.repository.TaskRepository;
import com.example.RestTask.service.TaskService;
import io.micrometer.core.instrument.Counter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



/**
 * рест контролёр
 */
@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class TaskController {


    private final TaskService taskService;

    private final TaskRepository taskRepository;

    private final Counter counter;



    /**
     * Добавить задау   localhost:8081/rest/add?description=Next Task
     * @param description описание задачи
     * @return задача
     */
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestParam String description) {
        Task task = new Task();
        task.setDescription(description);
        if (taskService.addTask(task)){
            counter.increment();
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * Получить список всех задач localhost:8081/rest
     * @return список задач
     */
    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    /**
     * Найти задачу по id  localhost:8081/rest/2
     * @param id идентификатор задачи
     * @return задача
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Найти задачу по статусу localhost:8081/rest/status/NOT_STARTED  (NOT_STARTED/IN_PROGRESS/COMPLETED)
     * @param status статус задачи
     * @return задача
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAllTaskByStatus(@PathVariable("status") TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    /**
     * Изменить задачу  localhost:8081/rest/4?description=Edit Task
     * @param id идентификатор задачи
     * @param description описание задачи
     * @return обновлённая задача
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateById(@PathVariable Long id, @RequestParam String description) {
        Task oldTask = taskRepository.findById(id).orElse(null);
        if (oldTask != null) {
            oldTask.setCreatedAt(LocalDateTime.now());
            oldTask.setDescription(description);
            return new ResponseEntity<>(taskRepository.save(oldTask), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удалить задачу по id
     * @param id идентификатор задачи
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Task target = taskRepository.findById(id).orElse(null);
        if (target != null) {
            taskRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Метод POST обновление статуса задачи. localhost:8081/rest/1/status/NOT_STARTED  (NOT_STARTED/IN_PROGRESS/COMPLETED)
     * @param id Идентификатор задачи для обновления статуса.
     * @param status Новый статус задачи
     * @return Редирект на страницу со списком задач после успешного обновления статуса.
     */
    @PostMapping("/{id}/status/{status}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @PathVariable TaskStatus status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskService.updateTaskStatus(id, status);
            return new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
