package src.command.executor;

import src.command.CommandType;

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
        NOTE_REPOSITORY.remove(noteName);

        return 1;
    }
}
