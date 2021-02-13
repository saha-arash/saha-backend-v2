package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ir.saha.domain.Payam} entity.
 */
public class PayamDTO implements Serializable {

    private Long id;

    private String onvan;

    @Lob
    private String matn;


    private Long karbarErsalKonandeId;

    private KarbarDTO karbarErsalKonande;
    private KarbarDTO karbarDaryaftKonandeKonande;

    private Long karbarDaryaftKonandId;

    private Long yeganErsalKonanadeId;
    private YeganDTO yeganErsalKonanade;
    private YeganDTO yeganDaryaftKonande;

    private Long yeganDaryaftKonanadeId;


    public KarbarDTO getKarbarErsalKonande() {
        return karbarErsalKonande;
    }

    public void setKarbarErsalKonande(KarbarDTO karbarErsalKonande) {
        this.karbarErsalKonande = karbarErsalKonande;
    }

    public KarbarDTO getKarbarDaryaftKonandeKonande() {
        return karbarDaryaftKonandeKonande;
    }

    public void setKarbarDaryaftKonandeKonande(KarbarDTO karbarDaryaftKonandeKonande) {
        this.karbarDaryaftKonandeKonande = karbarDaryaftKonandeKonande;
    }

    public YeganDTO getYeganErsalKonanade() {
        return yeganErsalKonanade;
    }

    public void setYeganErsalKonanade(YeganDTO yeganErsalKonanade) {
        this.yeganErsalKonanade = yeganErsalKonanade;
    }

    public YeganDTO getYeganDaryaftKonande() {
        return yeganDaryaftKonande;
    }

    public void setYeganDaryaftKonande(YeganDTO yeganDaryaftKonande) {
        this.yeganDaryaftKonande = yeganDaryaftKonande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnvan() {
        return onvan;
    }

    public void setOnvan(String onvan) {
        this.onvan = onvan;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public Long getKarbarErsalKonandeId() {
        return karbarErsalKonandeId;
    }

    public void setKarbarErsalKonandeId(Long karbarId) {
        this.karbarErsalKonandeId = karbarId;
    }

    public Long getKarbarDaryaftKonandId() {
        return karbarDaryaftKonandId;
    }

    public void setKarbarDaryaftKonandId(Long karbarId) {
        this.karbarDaryaftKonandId = karbarId;
    }

    public Long getYeganErsalKonanadeId() {
        return yeganErsalKonanadeId;
    }

    public void setYeganErsalKonanadeId(Long yeganId) {
        this.yeganErsalKonanadeId = yeganId;
    }

    public Long getYeganDaryaftKonanadeId() {
        return yeganDaryaftKonanadeId;
    }

    public void setYeganDaryaftKonanadeId(Long yeganId) {
        this.yeganDaryaftKonanadeId = yeganId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PayamDTO payamDTO = (PayamDTO) o;
        if (payamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), payamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PayamDTO{" +
            "id=" + getId() +
            ", onvan='" + getOnvan() + "'" +
            ", matn='" + getMatn() + "'" +
            ", karbarErsalKonandeId=" + getKarbarErsalKonandeId() +
            ", karbarDaryaftKonandId=" + getKarbarDaryaftKonandId() +
            ", yeganErsalKonanadeId=" + getYeganErsalKonanadeId() +
            ", yeganDaryaftKonanadeId=" + getYeganDaryaftKonanadeId() +
            "}";
    }
}
