package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Shahr} entity.
 */
public class ShahrDTO implements Serializable {

    private Long id;

    private String name;

    private Integer zaribAboHava;

    private Integer zaribTashilat;

    private Integer masafatTaMarkaz;


    private Long ostanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZaribAboHava() {
        return zaribAboHava;
    }

    public void setZaribAboHava(Integer zaribAboHava) {
        this.zaribAboHava = zaribAboHava;
    }

    public Integer getZaribTashilat() {
        return zaribTashilat;
    }

    public void setZaribTashilat(Integer zaribTashilat) {
        this.zaribTashilat = zaribTashilat;
    }

    public Integer getMasafatTaMarkaz() {
        return masafatTaMarkaz;
    }

    public void setMasafatTaMarkaz(Integer masafatTaMarkaz) {
        this.masafatTaMarkaz = masafatTaMarkaz;
    }

    public Long getOstanId() {
        return ostanId;
    }

    public void setOstanId(Long ostanId) {
        this.ostanId = ostanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShahrDTO shahrDTO = (ShahrDTO) o;
        if (shahrDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shahrDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShahrDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", zaribAboHava=" + getZaribAboHava() +
            ", zaribTashilat=" + getZaribTashilat() +
            ", masafatTaMarkaz=" + getMasafatTaMarkaz() +
            ", ostanId=" + getOstanId() +
            "}";
    }
}
