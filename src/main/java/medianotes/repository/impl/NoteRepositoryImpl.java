package medianotes.repository.impl;

import medianotes.model.Note;
import medianotes.repository.NoteRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class NoteRepositoryImpl implements NoteRepository {

    private static final String DATA_FILE_NAME = "data-note.dat";

    private static final Set<Note> NOTES = new HashSet<>(); // Используем множество, а не список или массив, так как договорились,
    // что хотим хранить только уникальные заметки. Уникальность заметок
    // определяется с помощью методов Note#equals и Note#hashcode.

    static {
        loadDataFromFile();
    }

    private static final NoteRepositoryImpl SINGLETON = new NoteRepositoryImpl();   // Используем паттерн singleton,
    // то есть когда мы создаем внутри класса ровно 1 объект
    // на все приложение и потом выдаем его другим классам, чтобы они его использовали.
    // При этом прячем конструктор, делая его приватным.

    private NoteRepositoryImpl() {}

    public static NoteRepository getSingleton() {
        return SINGLETON;
    }

    @Override
    public Set<Note> getAllNotes() {
        return NOTES;
    }

    @Override
    public void save(Note note) {
        NOTES.add(note);

        flush();
    }

    @Override
    public void remove(Note note) {
        NOTES.remove(note);

        flush();
    }

    private static void flush() {
        saveDataToFile();
    }

    @SuppressWarnings("unchecked")
    private static void loadDataFromFile() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(DATA_FILE_NAME))) {

            Set<Note> loadedNotes = (Set<Note>) stream.readObject();
            NOTES.addAll(loadedNotes);

        } catch (FileNotFoundException ex) {
            System.out.println("ooops");

            // noting.
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveDataToFile() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_NAME))) {

            stream.writeObject(NOTES);

        } catch (FileNotFoundException ex) {
            System.out.println("ooops");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
