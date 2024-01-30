package com.example.hw6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Сущность заметки.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String header;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime create_data = LocalDateTime.now();

    public Note(String header, String description, LocalDateTime create_data ) {
        this.header = header;
        this.description = description;
        this.create_data = create_data;
    }
}
