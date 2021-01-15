package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ir.saha.domain.FileBargeMamooriat} entity.
 */
public class FileBargeMamooriatDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] madarek;

    private String madarekContentType;

    private Long bargeMamooriatId;

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

    public byte[] getMadarek() {
        return madarek;
    }

    public void setMadarek(byte[] madarek) {
        this.madarek = madarek;
    }

    public String getMadarekContentType() {
        return madarekContentType;
    }

    public void setMadarekContentType(String madarekContentType) {
        this.madarekContentType = madarekContentType;
    }

    public Long getBargeMamooriatId() {
        return bargeMamooriatId;
    }

    public void setBargeMamooriatId(Long bargeMamooriatId) {
        this.bargeMamooriatId = bargeMamooriatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileBargeMamooriatDTO fileBargeMamooriatDTO = (FileBargeMamooriatDTO) o;
        if (fileBargeMamooriatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileBargeMamooriatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileBargeMamooriatDTO{" +
            "id=" + getId() +
            ", madarek='" + getMadarek() + "'" +
            ", bargeMamooriatId=" + getBargeMamooriatId() +
            "}";
    }
}
