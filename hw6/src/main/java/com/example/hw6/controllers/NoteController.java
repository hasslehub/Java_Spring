package com.example.hw6.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hw6.models.Note;
import com.example.hw6.services.NoteService;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер обработки заметок.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class NoteController {
    /**
     * Сервис обработки заметок.
     */
    private final NoteService service;

    /**
     * Получение всех заметок.
     * @return ответ со списком заметок.
     */
    @GetMapping()
    public ResponseEntity<List<Note>> allNotes(){
        return ResponseEntity.ok().body(service.getAllNotes());
    }

    /**
     * Получение заметки.
     * @param id идентификатор заметки.
     * @return  заметка.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id){
        Optional<Note> noteById = service.getNoteById(id);
        return noteById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Добавление новой заметки.
     * @param note объект заметки.
     * @return сохраненная заметка.
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Optional<Note> optional = service.addNote(note);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Обновление существующей заметки.
     * @param id идентификатор обновляемой заметки.
     * @param note объект обновляемой заметки.
     * @return обновленная заметка.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        if (id != null && (note.getDescription() != null && note.getHeader() != null)) {
            Optional<Note> noteById = service.getNoteById(id);
            if (noteById.isPresent()) {
                return new ResponseEntity<>(service.updateNote(note), HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Удаление заметки.
     * @param id идентификатор заметки.
     * @return ответ со статусом успешного выполнения.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        Optional<Note> noteById = service.getNoteById(id);
        if (noteById.isPresent()) {
            service.deleteNote(id);
            return ResponseEntity.ok("Выполнено!");
        }
        return ResponseEntity.notFound().build();
    }
}
