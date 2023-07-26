package medianotes.repository.impl;

import medianotes.model.Folder;
import medianotes.repository.FolderRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FolderRepositoryImpl implements FolderRepository{

    private static final Set<Folder> FOLDERS = new HashSet<>();


    static {
        FOLDERS.add(new Folder("root", null));
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
    }
}
