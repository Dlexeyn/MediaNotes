package src.repository;

import src.model.Note;
import java.util.Set;

public interface NoteRepository {
    void save(Note note);

    Set<Note> getAllNotes();

    void remove(String name);
}
