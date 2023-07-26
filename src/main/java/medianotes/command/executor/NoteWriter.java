package medianotes.command.executor;

import medianotes.command.CommandType;

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
        var notes = NOTE_REPOSITORY.getAllNotes();

        for(var note : notes){
            var path = findFolderPath(note.getName());
            System.out.printf("Название: \"%s\", Текст: \"%s\", Автор: \"%s\". FullPath: %s %n",
                    note.getName(),
                    note.getText(),
                    note.getAuthor(),
                    path
            );
        }

        return 1;
    }
}
