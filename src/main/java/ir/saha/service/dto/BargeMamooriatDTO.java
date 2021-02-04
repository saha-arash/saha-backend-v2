package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import ir.saha.domain.enumeration.VaziatBargeMamooriat;

/**
 * A DTO for the {@link ir.saha.domain.BargeMamooriat} entity.
 */
public class BargeMamooriatDTO implements Serializable {

    private Long id;

    private VaziatBargeMamooriat vaziat;

    private Integer saleMamooriat;

    private Instant shorooMamooriat;

    private Instant payanMamooriat;


    private Long sarparastId;

    private Long yeganId;

    private List<Long> binandegan;

    private List<Long> nafarat;

    private Long hesabResiId;

    private YeganDTO yeganDTO;

    private KarbarDTO sarparatDTO;

    public YeganDTO getYeganDTO() {
        return yeganDTO;
    }

    public void setYeganDTO(YeganDTO yeganDTO) {
        this.yeganDTO = yeganDTO;
    }

    public KarbarDTO getSarparatDTO() {
        return sarparatDTO;
    }

    public void setSarparatDTO(KarbarDTO sarparatDTO) {
        this.sarparatDTO = sarparatDTO;
    }

    public List<Long> getBinandegan() {
        return binandegan;
    }

    public void setBinandegan(List<Long> binandegan) {
        this.binandegan = binandegan;
    }

    public List<Long> getNafarat() {
        return nafarat;
    }

    public void setNafarat(List<Long> nafarat) {
        this.nafarat = nafarat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaziatBargeMamooriat getVaziat() {
        return vaziat;
    }

    public void setVaziat(VaziatBargeMamooriat vaziat) {
        this.vaziat = vaziat;
    }

    public Integer getSaleMamooriat() {
        return saleMamooriat;
    }

    public void setSaleMamooriat(Integer saleMamooriat) {
        this.saleMamooriat = saleMamooriat;
    }

    public Instant getShorooMamooriat() {
        return shorooMamooriat;
    }

    public void setShorooMamooriat(Instant shorooMamooriat) {
        this.shorooMamooriat = shorooMamooriat;
    }

    public Instant getPayanMamooriat() {
        return payanMamooriat;
    }

    public void setPayanMamooriat(Instant payanMamooriat) {
        this.payanMamooriat = payanMamooriat;
    }

    public Long getSarparastId() {
        return sarparastId;
    }

    public void setSarparastId(Long karbarId) {
        this.sarparastId = karbarId;
    }

    public Long getYeganId() {
        return yeganId;
    }

    public void setYeganId(Long yeganId) {
        this.yeganId = yeganId;
    }

    public Long getHesabResiId() {
        return hesabResiId;
    }

    public void setHesabResiId(Long hesabResiId) {
        this.hesabResiId = hesabResiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BargeMamooriatDTO bargeMamooriatDTO = (BargeMamooriatDTO) o;
        if (bargeMamooriatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bargeMamooriatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BargeMamooriatDTO{" +
            "id=" + getId() +
            ", vaziat='" + getVaziat() + "'" +
            ", saleMamooriat=" + getSaleMamooriat() +
            ", shorooMamooriat='" + getShorooMamooriat() + "'" +
            ", payanMamooriat='" + getPayanMamooriat() + "'" +
            ", sarparastId=" + getSarparastId() +
            ", yeganId=" + getYeganId() +
            ", hesabResiId=" + getHesabResiId() +
            "}";
    }
}
