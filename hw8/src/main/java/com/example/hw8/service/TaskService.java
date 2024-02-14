package com.example.hw8.service;

import com.example.hw8.aspect.TrackUserAction;
import com.example.hw8.exception.ResourceNotFoundException;
import com.example.hw8.model.Task;
import com.example.hw8.model.TaskStatus;
import com.example.hw8.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс TaskService предоставляет сервисные методы для управления задачами в списке дел.
 */
@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Метод для добавления новой задачи.
     * Устанавливает дату и время создания и статус "не начата".
     * @param task Новая задача для добавления.
     * @return Сохраненная задача.
     */
    @TrackUserAction
    public Task addTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }

    /**
     * Метод для получения всех задач из списка дел.
     * @return Список всех задач.
     */
    @TrackUserAction
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Метод для получения задач по указанному статусу.
     * @param status Статус задач, по которому осуществляется фильтрация.
     * @return Список задач с указанным статусом.
     */
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    /**
     * Метод для обновления статуса задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param status Новый статус задачи.
     * @return Обновленная задача.
     * @throws ResourceNotFoundException Если задача с указанным идентификатором не найдена.
     */
    @TrackUserAction
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    /**
     * Метод для обновления информации о задаче по ее идентификатору.
     * @param id Идентификатор задачи.
     * @param updateTask Обновленные данные задачи.
     * @return Обновленная задача.
     * @throws ResourceNotFoundException Если задача с указанным идентификатором не найдена.
     */
    public Task updateTask(Long id, Task updateTask) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
        task.setDescription(updateTask.getDescription());
        return taskRepository.save(task);
    }

    /**
     * Метод для удаления задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     */
    @TrackUserAction
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Метод для получения задачи по ее идентификатору.
     * @param id Идентификатор задачи.
     * @return Задача с указанным идентификатором.
     * @throws ResourceNotFoundException Если задача с указанным идентификатором не найдена.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid task ID"));
    }
}
