package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.saha.domain.enumeration.NoeBarnameHesabResi;

/**
 * A DTO for the {@link ir.saha.domain.BarnameHesabResi} entity.
 */
public class BarnameHesabResiDTO implements Serializable {

    private Long id;

    private NoeBarnameHesabResi noeBarnameHesabResi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoeBarnameHesabResi getNoeBarnameHesabResi() {
        return noeBarnameHesabResi;
    }

    public void setNoeBarnameHesabResi(NoeBarnameHesabResi noeBarnameHesabResi) {
        this.noeBarnameHesabResi = noeBarnameHesabResi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BarnameHesabResiDTO barnameHesabResiDTO = (BarnameHesabResiDTO) o;
        if (barnameHesabResiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), barnameHesabResiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BarnameHesabResiDTO{" +
            "id=" + getId() +
            ", noeBarnameHesabResi='" + getNoeBarnameHesabResi() + "'" +
            "}";
    }
}
