package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.saha.domain.enumeration.VaziateHesabResi;

/**
 * A DTO for the {@link ir.saha.domain.HesabResi} entity.
 */
public class HesabResiDTO implements Serializable {

    private Long id;

    private Integer sal;

    private VaziateHesabResi vaziateHesabResi;


    private Long gozareshId;

    private Long barnameHesabResiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public VaziateHesabResi getVaziateHesabResi() {
        return vaziateHesabResi;
    }

    public void setVaziateHesabResi(VaziateHesabResi vaziateHesabResi) {
        this.vaziateHesabResi = vaziateHesabResi;
    }

    public Long getGozareshId() {
        return gozareshId;
    }

    public void setGozareshId(Long gozareshId) {
        this.gozareshId = gozareshId;
    }

    public Long getBarnameHesabResiId() {
        return barnameHesabResiId;
    }

    public void setBarnameHesabResiId(Long barnameHesabResiId) {
        this.barnameHesabResiId = barnameHesabResiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HesabResiDTO hesabResiDTO = (HesabResiDTO) o;
        if (hesabResiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hesabResiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HesabResiDTO{" +
            "id=" + getId() +
            ", sal=" + getSal() +
            ", vaziateHesabResi='" + getVaziateHesabResi() + "'" +
            ", gozareshId=" + getGozareshId() +
            ", barnameHesabResiId=" + getBarnameHesabResiId() +
            "}";
    }
}
