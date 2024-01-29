package com.example.hw5.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Сущность задачи.
 */
@Data    // Lombok аннотация - генерация getter, setter, toString, etc.
@Entity  // JPA entity
@Table(name = "tasks") // Таблица базы данных
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "LOCALDATETIME", nullable = false)
    private LocalDateTime localDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;


    public Task() {}

    public Task(Task task) {
        this.id = task.getId();
        if (task.getDescription() != null)
            this.description = task.getDescription();
        if (task.status == null)
            this.status = Status.CREATE;
        else this.status = task.getStatus();
        if (task.localDateTime == null)
            this.localDateTime = LocalDateTime.now();
        else this.localDateTime = task.localDateTime;
    }
}
