package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Karbar} entity.
 */
public class KarbarDTO implements Serializable {

    private Long id;

    private String name;

    private String shoghlSazmani;

    private String shoghlAmali;

    private String codePerseneli;

    private Boolean bezaneshate;

    private Boolean sazmani;

    private Instant tarikhBazneshastegi;

    private Instant tarikhEstekhdam;

    private String username;
    private String password;

    private Set<BargeMamooriatDTO> bargeMamoorits = new HashSet<>();

    private Set<BargeMamooriatDTO> binanadeBargeMamoorits = new HashSet<>();

    private Set<MorkhasiDTO> morkhasiDTOS=new HashSet<>();

    private Set<DoreDTO> doreDTOS=new HashSet<>();

    private Set<NegahbaniDTO> negahbanis=new HashSet<>();

    private Long yeganId;

    private Long yeganCodeId;

    private Long darajeId;

    private Long sematId;

    private YeganDTO yeganDTO;

    private DarajeDTO darajeDTO;

    private SematDTO sematDTO;

    public YeganDTO getYeganDTO() {
        return yeganDTO;
    }

    public void setYeganDTO(YeganDTO yeganDTO) {
        this.yeganDTO = yeganDTO;
    }

    public DarajeDTO getDarajeDTO() {
        return darajeDTO;
    }

    public void setDarajeDTO(DarajeDTO darajeDTO) {
        this.darajeDTO = darajeDTO;
    }

    public SematDTO getSematDTO() {
        return sematDTO;
    }

    public void setSematDTO(SematDTO sematDTO) {
        this.sematDTO = sematDTO;
    }

    public Set<MorkhasiDTO> getMorkhasiDTOS() {
        return morkhasiDTOS;
    }

    public void setMorkhasiDTOS(Set<MorkhasiDTO> morkhasiDTOS) {
        this.morkhasiDTOS = morkhasiDTOS;
    }

    public Set<DoreDTO> getDoreDTOS() {
        return doreDTOS;
    }

    public void setDoreDTOS(Set<DoreDTO> doreDTOS) {
        this.doreDTOS = doreDTOS;
    }

    public Set<NegahbaniDTO> getNegahbanis() {
        return negahbanis;
    }

    public void setNegahbanis(Set<NegahbaniDTO> negahbanis) {
        this.negahbanis = negahbanis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getShoghlSazmani() {
        return shoghlSazmani;
    }

    public void setShoghlSazmani(String shoghlSazmani) {
        this.shoghlSazmani = shoghlSazmani;
    }

    public String getShoghlAmali() {
        return shoghlAmali;
    }

    public void setShoghlAmali(String shoghlAmali) {
        this.shoghlAmali = shoghlAmali;
    }

    public String getCodePerseneli() {
        return codePerseneli;
    }

    public void setCodePerseneli(String codePerseneli) {
        this.codePerseneli = codePerseneli;
    }

    public Boolean isBezaneshate() {
        return bezaneshate;
    }

    public void setBezaneshate(Boolean bezaneshate) {
        this.bezaneshate = bezaneshate;
    }

    public Boolean isSazmani() {
        return sazmani;
    }

    public void setSazmani(Boolean sazmani) {
        this.sazmani = sazmani;
    }

    public Instant getTarikhBazneshastegi() {
        return tarikhBazneshastegi;
    }

    public void setTarikhBazneshastegi(Instant tarikhBazneshastegi) {
        this.tarikhBazneshastegi = tarikhBazneshastegi;
    }

    public Instant getTarikhEstekhdam() {
        return tarikhEstekhdam;
    }

    public void setTarikhEstekhdam(Instant tarikhEstekhdam) {
        this.tarikhEstekhdam = tarikhEstekhdam;
    }

    public Set<BargeMamooriatDTO> getBargeMamoorits() {
        return bargeMamoorits;
    }

    public void setBargeMamoorits(Set<BargeMamooriatDTO> bargeMamooriats) {
        this.bargeMamoorits = bargeMamooriats;
    }

    public Set<BargeMamooriatDTO> getBinanadeBargeMamoorits() {
        return binanadeBargeMamoorits;
    }

    public void setBinanadeBargeMamoorits(Set<BargeMamooriatDTO> bargeMamooriats) {
        this.binanadeBargeMamoorits = bargeMamooriats;
    }

    public Long getYeganId() {
        return yeganId;
    }

    public void setYeganId(Long yeganId) {
        this.yeganId = yeganId;
    }

    public Long getYeganCodeId() {
        return yeganCodeId;
    }

    public void setYeganCodeId(Long yeganCodeId) {
        this.yeganCodeId = yeganCodeId;
    }

    public Long getDarajeId() {
        return darajeId;
    }

    public void setDarajeId(Long darajeId) {
        this.darajeId = darajeId;
    }

    public Long getSematId() {
        return sematId;
    }

    public void setSematId(Long sematId) {
        this.sematId = sematId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KarbarDTO karbarDTO = (KarbarDTO) o;
        if (karbarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), karbarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KarbarDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shoghlSazmani='" + getShoghlSazmani() + "'" +
            ", shoghlAmali='" + getShoghlAmali() + "'" +
            ", codePerseneli='" + getCodePerseneli() + "'" +
            ", bezaneshate='" + isBezaneshate() + "'" +
            ", sazmani='" + isSazmani() + "'" +
            ", tarikhBazneshastegi='" + getTarikhBazneshastegi() + "'" +
            ", tarikhEstekhdam='" + getTarikhEstekhdam() + "'" +
            ", yeganId=" + getYeganId() +
            ", yeganCodeId=" + getYeganCodeId() +
            ", darajeId=" + getDarajeId() +
            ", sematId=" + getSematId() +
            "}";
    }
}
