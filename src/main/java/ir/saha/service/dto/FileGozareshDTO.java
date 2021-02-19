package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ir.saha.domain.FileGozaresh} entity.
 */
public class FileGozareshDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] file;

    private String fileContentType;

    private Long hesabResiId;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Long getHesabResiId() {
        return hesabResiId;
    }

    public void setHesabResiId(Long gozareshId) {
        this.hesabResiId = gozareshId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileGozareshDTO fileGozareshDTO = (FileGozareshDTO) o;
        if (fileGozareshDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileGozareshDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileGozareshDTO{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", hesabResiId=" + getHesabResiId() +
            "}";
    }
}
