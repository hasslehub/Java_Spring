package com.example.hw8.repository;

import com.example.hw8.model.Task;
import com.example.hw8.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс TaskRepository предоставляет методы для работы с репозиторием задач в списке дел.
 * Расширяет интерфейс JpaRepository для работы с базой данных.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Метод для поиска всех задач по указанному статусу.
     * @param status Статус задачи, по которому осуществляется поиск.
     * @return Список задач с указанным статусом.
     */
    List<Task> findAllByStatus(TaskStatus status);
}
