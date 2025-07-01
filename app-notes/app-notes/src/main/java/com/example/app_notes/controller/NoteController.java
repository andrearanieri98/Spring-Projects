package com.example.app_notes.controller;

import com.example.app_notes.model.Note;
import com.example.app_notes.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return repository.save(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updated) {
        return repository.findById(id).map(note -> {
            note.setTitle(updated.getTitle());
            note.setContent(updated.getContent());
            return repository.save(note);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
