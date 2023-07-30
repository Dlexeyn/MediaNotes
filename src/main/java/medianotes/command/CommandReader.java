package medianotes.command;

import medianotes.command.executor.*;

import java.util.Map;
import java.util.Scanner;

public class CommandReader {


    private static final Map<CommandType, CommandExecutor> COMMAND_EXECUTORS_GROUPED_BY_COMMAND =
            Map.of(
                    CommandType.CREATE_NOTE, new NoteCreator(),
                    CommandType.REMOVE_NOTE, new NoteDeleter(),
                    CommandType.WRITE_ALL_NOTES, new NoteWriter(),
                    CommandType.CREATE_FOLDER, new FolderCreator()
            );

    public static void startReadCommand(){
        System.out.println("Program started! Write your command: ");

        Scanner scanner = new Scanner(System.in);

        int out = 1;
        while (out != 0){
            out = readCommand(scanner);
        }

    }

    /*
     * create note <name> <folder> <text>
     * notes;
     * remove note <name>
     *
     * example: create note someName someFolder someText
     */
    public static int readCommand(Scanner s) {

//        System.out.print("\033[H\033[2J");
//        System.out.flush();

        System.out.print("> ");
        String command = s.nextLine();
        if(command.contains("create note"))
            return COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(CommandType.CREATE_NOTE).execute(command);
        else if(command.contains("notes"))
            return COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(CommandType.WRITE_ALL_NOTES).execute(command);
        else if(command.contains("remove note"))
            return COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(CommandType.REMOVE_NOTE).execute(command);
        else if(command.contains("create folder"))
            return COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(CommandType.CREATE_FOLDER).execute(command);
        else if(command.contains("exit"))
            return 0;

        return -1;
    }

}
