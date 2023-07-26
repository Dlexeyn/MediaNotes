package medianotes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Note {

    private Folder parentFolder;

    @EqualsAndHashCode.Include
    private final String name;

    private String text;

    private final String author;

    private final Instant creationDate;

    private Instant updateDate;

    public Note(String name, String text, Folder parentFolder){
        this.parentFolder = parentFolder;
        this.name = name;
        this.text = text;

        author = null;
        creationDate = Instant.now();
    }

    public void setText(String text) {
        this.text = text;
        updateDate = Instant.now();
    }

}
