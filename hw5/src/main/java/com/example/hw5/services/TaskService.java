package com.example.hw5.services;

import com.example.hw5.domain.Task;
import com.example.hw5.domain.Status;
import com.example.hw5.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepo;

    /**
     * Создание новой задачи.
     *
     * @param task объект задачи.
     * @return созданную задачу.
     */
    public Task add(Task task) {
        return taskRepo.save(task);
    }

    /**
     * Получение всех задач.
     *
     * @return список задач.
     */
    public List<Task> findAll(){
        return taskRepo.findAll();
    }

    /**
     * Получение задач по заданному статусу.
     *
     * @param status статус задачи.
     * @return список задач с заданным статусом.
     */
    public List<Task> findTasksByStatus(Status status){
        return taskRepo.findTasksByStatus(status);
    }

    /**
     * Обновление задачи.
     *
     * @param id уникальный идентификатор задачи.
     * @param task объект задачи.
     */

    @Transactional
    public Task updateById(Long id, Task task) {
        taskRepo.updateTaskById(task.getDescription(), task.getStatus(), id);
        return taskRepo.findById(id).get();
    }

    /**
     * Удаление задачи.
     *
     * @param id уникальный идентификатор задачи.
     */
    public void deleteById(Long id) {
        taskRepo.deleteById(id);
    }
}
