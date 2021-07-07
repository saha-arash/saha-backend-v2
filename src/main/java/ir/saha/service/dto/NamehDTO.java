package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Nameh} entity.
 */
public class NamehDTO implements Serializable {

    private Long id;

    private String shomare;

    private Instant tarikhEblagh;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public Instant getTarikhEblagh() {
        return tarikhEblagh;
    }

    public void setTarikhEblagh(Instant tarikhEblagh) {
        this.tarikhEblagh = tarikhEblagh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NamehDTO namehDTO = (NamehDTO) o;
        if (namehDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), namehDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NamehDTO{" +
            "id=" + getId() +
            ", shomare='" + getShomare() + "'" +
            ", tarikhEblagh='" + getTarikhEblagh() + "'" +
            "}";
    }
}
