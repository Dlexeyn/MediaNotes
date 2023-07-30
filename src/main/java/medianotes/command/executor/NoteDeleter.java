package medianotes.command.executor;

import medianotes.command.CommandType;

public class NoteDeleter extends AbstractCommandExecutor {
    @Override
    public int execute(String text) {
        removeNote(text);
        return 0;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.REMOVE_NOTE;
    }

    private int removeNote(String command){
        String[] words = command.split(" ");

        String noteName = words[2];

        var note = findNote(noteName);

        note.ifPresent(NOTE_REPOSITORY::remove);

        return 1;
    }
}
