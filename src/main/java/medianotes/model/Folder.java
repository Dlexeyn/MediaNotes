package medianotes.model;

import java.io.Serializable;

public record Folder (String name, Folder parentFolder) implements Serializable {}
