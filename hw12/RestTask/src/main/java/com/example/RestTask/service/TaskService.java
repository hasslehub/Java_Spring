package com.example.RestTask.service;

import com.example.RestTask.exception.TaskNotFoundException;
import com.example.RestTask.domain.Task;
import com.example.RestTask.domain.TaskStatus;
import com.example.RestTask.repository.TaskRepository;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements CRUDService {

    private final TaskRepository taskRepository;
    private final Counter counter;
    private final FileGateway fileGateway;


    //public TaskService(TaskRepository taskRepository) {
    //    this.taskRepository = taskRepository;
    //}




    public boolean addTask(Task task) {
        if (task != null && task.getDescription() != null) {
            task.setCreatedAt(LocalDateTime.now());
            task.setStatus(TaskStatus.NOT_STARTED);
            taskRepository.save(task);
            counter.increment();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < 3 - task.getId().toString().length(); i++){
                id.append("0");
            }
            fileGateway.writeToFile("Task_" + id.append(task.getId()) + ".txt", task);
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
