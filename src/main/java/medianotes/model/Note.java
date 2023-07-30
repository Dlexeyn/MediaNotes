package medianotes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Note implements Serializable {

    private Folder parentFolder;

    @EqualsAndHashCode.Include
    private final String name;

    private String text;

    private final String authorEmail;

    private final Instant creationDate;
    private Instant updateDate;

    public Note(String name, String text, Folder parentFolder, String authorEmail) {
        this.parentFolder = parentFolder;

        this.name = name;
        this.text = text;

        this.authorEmail = authorEmail;

        creationDate = Instant.now();
    }

    public void setText(String text) {
        this.text = text;
        this.updateDate = Instant.now();
    }
}
