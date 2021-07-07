package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.MohasebeHazineMamooriat} entity.
 */
public class MohasebeHazineMamooriatDTO implements Serializable {

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

        MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO = (MohasebeHazineMamooriatDTO) o;
        if (mohasebeHazineMamooriatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mohasebeHazineMamooriatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MohasebeHazineMamooriatDTO{" +
            "id=" + getId() +
            "}";
    }
}
