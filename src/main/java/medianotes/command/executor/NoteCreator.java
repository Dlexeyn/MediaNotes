package medianotes.command.executor;

import medianotes.context.UserContext;
import medianotes.model.Note;
import medianotes.command.CommandType;

public class NoteCreator extends AbstractCommandExecutor {


    @Override
    public int execute(String text) {
        return createNote(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_NOTE;
    }

    private int createNote(String command){
        String[] words = command.split(" ");

        String noteName = words[2];

        if(findNote(noteName).isPresent()){
            System.out.println("Note already exists");
            return -1;
        }

        var noteParentFolder = words[3];
        var folder = findFolder(noteParentFolder);

        if (folder.isEmpty()) {
            System.out.println("Folder not exists");
            return -1;
        }

        StringBuilder noteTextSb = new StringBuilder();
        for(int i = 4; i < words.length; i++){
            noteTextSb.append(words[i]);
        }
        String noteText = noteTextSb.toString();

        var authorEmail = UserContext.getUserLogin();

        Note newNote = new Note(noteName, noteText, folder.get(), authorEmail);
        NOTE_REPOSITORY.save(newNote);

        System.out.println("Note created");

        return 1;
    }
}
