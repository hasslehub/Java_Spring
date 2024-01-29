package com.example.hw5.controller;

import com.example.hw5.domain.Status;
import com.example.hw5.domain.Task;
import com.example.hw5.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;


    /**
     * addTask
     * POST запрос localhost:8080
     *
     * @return новая задача
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.add(new Task(task));
    }

    /**
     * getAllTasks
     * GET запрос localhost:8080
     *
     * @return list всех задач
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }


    /**
     * getTasksByStatus
     * GET запрос localhost:8080/status/{status}.
     *
     * @param status   статус задачи.
     * @return list всех задач с заданным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Status status) {
        return taskService.findTasksByStatus(status);
    }


    /**
     * updateTaskStatus
     * PUT запрос "localhost:8080/{id}".
     *
     * @param id   ID задачи, которой нужно изметь статус.
     * @param task задача.
     * @return задача с измененным статусом
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateById(id, task);
    }


    /**
     * Delete Task
     * DELETE запрос "localhost:8080/{id}" удаление задачи по ID.
     *
     * @param id ID задачи которую надо удалить.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
