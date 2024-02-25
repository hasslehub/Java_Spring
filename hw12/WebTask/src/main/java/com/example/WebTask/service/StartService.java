package com.example.WebTask.service;

import com.example.WebTask.model.Task;
import com.example.WebTask.model.TaskStatus;

import java.util.List;

public interface StartService {
    List<Task> getAllTasks() ;
    List<Task> getTasksByStatus(TaskStatus status);
    Task updateTaskStatus(Long id, TaskStatus status);
    Task updateTask(Long id, Task updateTask);
    void deleteTask(Long id);
    Task getTaskById(Long id);
    Task save(String description);
}
