package com.example.hw5.repository;

import com.example.hw5.domain.Status;
import com.example.hw5.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find Tasks by Status
     *
     * @param status Статус искомой задачи.
     * @return List<Task>: Список задач.
     */
    List<Task> findTasksByStatus(Status status);


    @Modifying
    @Query("update tasks u set u.description = ?1, u.status = ?2 where u.id = ?3")
    void updateTaskById(String description, Status status, Long id);
}
