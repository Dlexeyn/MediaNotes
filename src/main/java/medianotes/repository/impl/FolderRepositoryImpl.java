package medianotes.repository.impl;

import medianotes.model.Folder;
import medianotes.model.Note;
import medianotes.repository.FolderRepository;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FolderRepositoryImpl implements FolderRepository{

    private static final Set<Folder> FOLDERS = new HashSet<>();
    private static final String DATA_FILE_NAME = "data-folder.dat";


    static {
        //loadDataFromFile();

        if(FOLDERS.stream().noneMatch(folder -> folder.name().equals("root"))){
            FOLDERS.add(new Folder("root", null));
        }
    }

    private FolderRepositoryImpl() {}

    private static final FolderRepository SINGLETON = new FolderRepositoryImpl();

    public static FolderRepository getSingleton() {
        return SINGLETON;
    }

    @Override
    public Set<Folder> findAll () {
        return FOLDERS;
    }

    @Override
    public void save(Folder newFolder) {
        FOLDERS.add(newFolder);
        flush();
    }

    private static void flush(){
        saveDataToFile();
    }

    private static void saveDataToFile(){

        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(DATA_FILE_NAME))) {

            stream.writeObject(FOLDERS);

        } catch (FileNotFoundException e){
            System.out.println("Oops... File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadDataFromFile(){
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(DATA_FILE_NAME))) {

            Set<Folder> loadedNotes = (Set<Folder>) stream.readObject();
            FOLDERS.addAll(loadedNotes);

        } catch (FileNotFoundException e){
            System.out.println("Oops... File not found!");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
