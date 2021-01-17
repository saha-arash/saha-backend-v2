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

    private Long bankEtelaatiId;

    private Long rafeIradatId;

    private Long mostaKhrejeId;

    private Long bilanSeSalGhablId;

    private Long mohasebeHazineMamooriatId;

    private Long chekideGardeshKarId;

    private Long gozareshHozoorId;

    private Long bilanSalGhablId;

    private Long madarekId;

    private Long gardeshkarBarnameHesabresiId;

    private Long dastoorAmalEjraEId;

    private Long namehId;

    private Long kholaseGozareshId;

    private Long gardeshKarId;

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

    public Long getBankEtelaatiId() {
        return bankEtelaatiId;
    }

    public void setBankEtelaatiId(Long bankEtelaatiId) {
        this.bankEtelaatiId = bankEtelaatiId;
    }

    public Long getRafeIradatId() {
        return rafeIradatId;
    }

    public void setRafeIradatId(Long rafeIradatId) {
        this.rafeIradatId = rafeIradatId;
    }

    public Long getMostaKhrejeId() {
        return mostaKhrejeId;
    }

    public void setMostaKhrejeId(Long mostaKhrejeId) {
        this.mostaKhrejeId = mostaKhrejeId;
    }

    public Long getBilanSeSalGhablId() {
        return bilanSeSalGhablId;
    }

    public void setBilanSeSalGhablId(Long bilanSeSalGhablId) {
        this.bilanSeSalGhablId = bilanSeSalGhablId;
    }

    public Long getMohasebeHazineMamooriatId() {
        return mohasebeHazineMamooriatId;
    }

    public void setMohasebeHazineMamooriatId(Long mohasebeHazineMamooriatId) {
        this.mohasebeHazineMamooriatId = mohasebeHazineMamooriatId;
    }

    public Long getChekideGardeshKarId() {
        return chekideGardeshKarId;
    }

    public void setChekideGardeshKarId(Long chekideGardeshKarId) {
        this.chekideGardeshKarId = chekideGardeshKarId;
    }

    public Long getGozareshHozoorId() {
        return gozareshHozoorId;
    }

    public void setGozareshHozoorId(Long gozareshHozoorId) {
        this.gozareshHozoorId = gozareshHozoorId;
    }

    public Long getBilanSalGhablId() {
        return bilanSalGhablId;
    }

    public void setBilanSalGhablId(Long bilanSalGhablId) {
        this.bilanSalGhablId = bilanSalGhablId;
    }

    public Long getMadarekId() {
        return madarekId;
    }

    public void setMadarekId(Long madarekId) {
        this.madarekId = madarekId;
    }

    public Long getGardeshkarBarnameHesabresiId() {
        return gardeshkarBarnameHesabresiId;
    }

    public void setGardeshkarBarnameHesabresiId(Long gardeshkarBarnameHesabresiId) {
        this.gardeshkarBarnameHesabresiId = gardeshkarBarnameHesabresiId;
    }

    public Long getDastoorAmalEjraEId() {
        return dastoorAmalEjraEId;
    }

    public void setDastoorAmalEjraEId(Long dastoorAmalEjraEId) {
        this.dastoorAmalEjraEId = dastoorAmalEjraEId;
    }

    public Long getNamehId() {
        return namehId;
    }

    public void setNamehId(Long namehId) {
        this.namehId = namehId;
    }

    public Long getKholaseGozareshId() {
        return kholaseGozareshId;
    }

    public void setKholaseGozareshId(Long kholaseGozareshId) {
        this.kholaseGozareshId = kholaseGozareshId;
    }

    public Long getGardeshKarId() {
        return gardeshKarId;
    }

    public void setGardeshKarId(Long gardeshKarId) {
        this.gardeshKarId = gardeshKarId;
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
            ", bankEtelaatiId=" + getBankEtelaatiId() +
            ", rafeIradatId=" + getRafeIradatId() +
            ", mostaKhrejeId=" + getMostaKhrejeId() +
            ", bilanSeSalGhablId=" + getBilanSeSalGhablId() +
            ", mohasebeHazineMamooriatId=" + getMohasebeHazineMamooriatId() +
            ", chekideGardeshKarId=" + getChekideGardeshKarId() +
            ", gozareshHozoorId=" + getGozareshHozoorId() +
            ", bilanSalGhablId=" + getBilanSalGhablId() +
            ", madarekId=" + getMadarekId() +
            ", gardeshkarBarnameHesabresiId=" + getGardeshkarBarnameHesabresiId() +
            ", dastoorAmalEjraEId=" + getDastoorAmalEjraEId() +
            ", namehId=" + getNamehId() +
            ", kholaseGozareshId=" + getKholaseGozareshId() +
            ", gardeshKarId=" + getGardeshKarId() +
            ", barnameHesabResiId=" + getBarnameHesabResiId() +
            "}";
    }
}
