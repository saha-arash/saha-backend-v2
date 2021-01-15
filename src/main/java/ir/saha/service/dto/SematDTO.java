package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Semat} entity.
 */
public class SematDTO implements Serializable {

    private Long id;

    private String onvanShoghli;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnvanShoghli() {
        return onvanShoghli;
    }

    public void setOnvanShoghli(String onvanShoghli) {
        this.onvanShoghli = onvanShoghli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SematDTO sematDTO = (SematDTO) o;
        if (sematDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sematDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SematDTO{" +
            "id=" + getId() +
            ", onvanShoghli='" + getOnvanShoghli() + "'" +
            "}";
    }
}
