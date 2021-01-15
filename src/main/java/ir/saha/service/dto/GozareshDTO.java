package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.saha.domain.enumeration.VaziatGozaresh;

/**
 * A DTO for the {@link ir.saha.domain.Gozaresh} entity.
 */
public class GozareshDTO implements Serializable {

    private Long id;

    private VaziatGozaresh vaziat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaziatGozaresh getVaziat() {
        return vaziat;
    }

    public void setVaziat(VaziatGozaresh vaziat) {
        this.vaziat = vaziat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GozareshDTO gozareshDTO = (GozareshDTO) o;
        if (gozareshDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gozareshDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GozareshDTO{" +
            "id=" + getId() +
            ", vaziat='" + getVaziat() + "'" +
            "}";
    }
}
