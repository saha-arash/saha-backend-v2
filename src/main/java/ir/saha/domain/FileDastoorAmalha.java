package ir.saha.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A FileGozaresh.
 */
@Entity
@Table(name = "dastoor_amalha")
public class FileDastoorAmalha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "file")
    private byte[] file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
