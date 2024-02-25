package com.example.RestTask.service;

import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;

import java.util.List;

public interface CRUDService {
    //Task
    boolean addTask(Task task);
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(TaskStatus status);
    Task getTaskById(Long id);
    boolean updateTaskStatus(Long taskId, TaskStatus newStatus);
    boolean deleteTask(Long taskId);
}
