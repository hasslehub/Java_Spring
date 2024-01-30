package com.example.hw6.services;

import com.example.hw6.models.Note;
import com.example.hw6.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository repository;
    /**
     * Получение всех заметок.
     * @return список заметок.
     */
    public List<Note> getAllNotes(){
        return repository.findAll();
    }

    /**
     * Получение конкретной заметки.
     * @param id идентификатор заметки.
     * @return объект заметки.
     */
    public Optional<Note> getNoteById(Long id){
        return repository.findById(id);
    }

    /**
     * Создание заметки.
     * @param note объект заметки.
     * @return созданный объект заметки.
     */
    public Optional<Note> addNote(Note note) {
        Optional<Note> optional = Optional.empty();
        if (note.getDescription() != null && note.getHeader() != null) {
            optional = Optional.of(note);
            repository.save(note);
            return optional;
        }
        return optional;
    }

    /**
     * Обновление заметки.
     * @param note объект заметки с обновленными данными.
     * @return обновленный объект заметки.
     */
    public Note updateNote(Note note){
        return repository.save(note);
    }

    /**
     * Удаление заметки.
     * @param id идентификатор заметки.
     */
    public void deleteNote(Long id) {
        repository.deleteById(id);
    }
}
