package src.command.executor;

import src.command.CommandType;
import src.model.Folder;
import src.model.Note;

public class FolderCreator extends AbstractCommandExecutor{
    @Override
    public int execute(String text) {

        return createFolder(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_FOLDER;
    }

    private int createFolder(String command) {
        String[] words = command.split(" ");

        String folderName = words[2];

//        if(findNote(folderName).isPresent()){
//            System.out.println("Note already exists");
//            return -1;
//        }

        var noteParentFolder = words[3];
        var folder = findFolder(noteParentFolder);

        if (folder.isEmpty()) {
            System.out.println("Folder not exists");
            return -1;
        }

        Folder newFolder = new Folder(folderName, folder.get());

        FOLDER_REPOSITORY.save(newFolder);

        System.out.println("Folder created");

        return 1;
    }
}
