package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.BilanSeSalGhabl} entity.
 */
public class BilanSeSalGhablDTO implements Serializable {

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

        BilanSeSalGhablDTO bilanSeSalGhablDTO = (BilanSeSalGhablDTO) o;
        if (bilanSeSalGhablDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bilanSeSalGhablDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BilanSeSalGhablDTO{" +
            "id=" + getId() +
            "}";
    }
}
