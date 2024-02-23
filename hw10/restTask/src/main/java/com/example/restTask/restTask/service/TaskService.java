package com.example.restTask.restTask.service;

import com.example.restTask.restTask.exception.TaskNotFoundException;
import com.example.restTask.restTask.domain.Task;
import com.example.restTask.restTask.domain.TaskStatus;
import com.example.restTask.restTask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean addTask(Task task) {
        if (task != null && task.getDescription() != null) {
            task.setCreatedAt(LocalDateTime.now());
            task.setStatus(TaskStatus.NOT_STARTED);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Получение задачи по ID
     * @param id - идетификатор задачи
     * @return Task
     */

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        throw new TaskNotFoundException();
    }

    public boolean updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        task.setStatus(newStatus);
        taskRepository.save(task);
        return true;
    }

    public boolean deleteTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        }

        throw new TaskNotFoundException();
    }
}
