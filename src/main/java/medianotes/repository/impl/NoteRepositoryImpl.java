package medianotes.repository.impl;

import lombok.SneakyThrows;
import medianotes.config.ApplcationDataSource;
import medianotes.model.Note;
import medianotes.repository.NoteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class NoteRepositoryImpl implements NoteRepository {


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
        try(var st = ApplcationDataSource.getConnection()
                .prepareStatement("select * from note")){
            var result = st.executeQuery();

            return mapResultToNotes(result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<Note> mapResultToNotes(ResultSet resultSet) throws SQLException {
        Set<Note> notes = new HashSet<>();

        while (resultSet.next()){
            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var text = resultSet.getString("text");
            var authorEmail = resultSet.getString("authorEmail");

            notes.add(new Note(
                    id,
                    name,
                    text,
                    null,
                    authorEmail
            ));
        }
        return notes;
    }

    @Override
    public void save(Note note) {
        // to do
    }

    @Override
    public void remove(Note note) {
        // to do
    }

}
