package com.example.restTask.restTask.repository;

import com.example.restTask.restTask.domain.Task;
import com.example.restTask.restTask.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    Optional<Task> findById(Long id);
}
