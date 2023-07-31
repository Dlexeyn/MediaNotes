package medianotes.command.executor;

import medianotes.command.CommandType;
import medianotes.context.UserContext;
import medianotes.model.Note;

import java.util.Comparator;

public class NoteWriter extends AbstractCommandExecutor {
    @Override
    public int execute(String text) {
        return viewAllNotes(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.WRITE_ALL_NOTES;
    }

    private int viewAllNotes(String command){
        var isNeedsFiltering = command.contains("-f");
        var isNeedsSorting = command.contains("-s");

        var notes = NOTE_REPOSITORY.getAllNotes().stream().toList();
        var userEmail = UserContext.getUserLogin();

        if (isNeedsFiltering){
            notes = notes.stream()
                    .filter(note -> note.getAuthorEmail().equals(userEmail))
                    .toList();
        }

        if (isNeedsSorting){
            notes = notes.stream()
                    .sorted(Comparator.comparing(Note::getCreationDate))
                    .toList();
        }

        for(var note : notes){
            //var path = findFolderPath(note.getName()); todo
            System.out.printf("Название: \"%s\", Текст: \"%s\", Автор: \"%s\". FullPath: %s %n",
                    note.getName(),
                    note.getText(),
                    note.getAuthorEmail(),
                    "empy path"
            );
        }

        return 1;
    }
}
