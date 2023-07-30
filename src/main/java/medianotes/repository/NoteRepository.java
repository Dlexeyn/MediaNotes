package medianotes.repository;

import medianotes.model.Note;
import java.util.Set;

public interface NoteRepository {
    void save(Note note);

    Set<Note> getAllNotes();

    void remove(Note note);
}
