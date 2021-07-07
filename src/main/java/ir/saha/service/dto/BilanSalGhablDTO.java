package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.BilanSalGhabl} entity.
 */
public class BilanSalGhablDTO implements Serializable {

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

        BilanSalGhablDTO bilanSalGhablDTO = (BilanSalGhablDTO) o;
        if (bilanSalGhablDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bilanSalGhablDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BilanSalGhablDTO{" +
            "id=" + getId() +
            "}";
    }
}
