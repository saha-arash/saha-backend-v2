package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import ir.saha.domain.enumeration.FileType;

/**
 * A DTO for the {@link ir.saha.domain.FileHesabResi} entity.
 */
public class FileHesabResiDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] file;

    private String fileContentType;
    private Integer shomare;

    private Instant tarikhName;

    private String mozoo;

    private FileType fileType;


    private Long hesabResiId;

    private Long barnameHesabResiId;

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

    public Integer getShomare() {
        return shomare;
    }

    public void setShomare(Integer shomare) {
        this.shomare = shomare;
    }

    public Instant getTarikhName() {
        return tarikhName;
    }

    public void setTarikhName(Instant tarikhName) {
        this.tarikhName = tarikhName;
    }

    public String getMozoo() {
        return mozoo;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getHesabResiId() {
        return hesabResiId;
    }

    public void setHesabResiId(Long hesabResiId) {
        this.hesabResiId = hesabResiId;
    }

    public Long getBarnameHesabResiId() {
        return barnameHesabResiId;
    }

    public void setBarnameHesabResiId(Long barnameHesabResiId) {
        this.barnameHesabResiId = barnameHesabResiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileHesabResiDTO fileHesabResiDTO = (FileHesabResiDTO) o;
        if (fileHesabResiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileHesabResiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileHesabResiDTO{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", shomare=" + getShomare() +
            ", tarikhName='" + getTarikhName() + "'" +
            ", mozoo='" + getMozoo() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", hesabResiId=" + getHesabResiId() +
            ", barnameHesabResiId=" + getBarnameHesabResiId() +
            "}";
    }
}
