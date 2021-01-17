package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.GozareshHozoor} entity.
 */
public class GozareshHozoorDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GozareshHozoorDTO gozareshHozoorDTO = (GozareshHozoorDTO) o;
        if (gozareshHozoorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gozareshHozoorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GozareshHozoorDTO{" +
            "id=" + getId() +
            "}";
    }
}
