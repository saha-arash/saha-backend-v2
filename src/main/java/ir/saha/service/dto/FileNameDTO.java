package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ir.saha.domain.FileName} entity.
 */
public class FileNameDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] madrak;

    private String madrakContentType;

    private Long nameId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getMadrak() {
        return madrak;
    }

    public void setMadrak(byte[] madrak) {
        this.madrak = madrak;
    }

    public String getMadrakContentType() {
        return madrakContentType;
    }

    public void setMadrakContentType(String madrakContentType) {
        this.madrakContentType = madrakContentType;
    }

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long payamId) {
        this.nameId = payamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileNameDTO fileNameDTO = (FileNameDTO) o;
        if (fileNameDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileNameDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileNameDTO{" +
            "id=" + getId() +
            ", madrak='" + getMadrak() + "'" +
            ", nameId=" + getNameId() +
            "}";
    }
}
