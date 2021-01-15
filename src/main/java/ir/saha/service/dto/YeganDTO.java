package ir.saha.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Yegan} entity.
 */
public class YeganDTO implements Serializable {

    private Long id;

    private String name;

    private String code;


    private Set<YeganDTO> zirYegans = new HashSet<>();

    private Long nirooCodeId;

    private Long shahrId;

    private Long yeganTypeId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<YeganDTO> getZirYegans() {
        return zirYegans;
    }

    public void setZirYegans(Set<YeganDTO> yegans) {
        this.zirYegans = yegans;
    }

    public Long getNirooCodeId() {
        return nirooCodeId;
    }

    public void setNirooCodeId(Long nirooCodeId) {
        this.nirooCodeId = nirooCodeId;
    }

    public Long getShahrId() {
        return shahrId;
    }

    public void setShahrId(Long shahrId) {
        this.shahrId = shahrId;
    }

    public Long getYeganTypeId() {
        return yeganTypeId;
    }

    public void setYeganTypeId(Long yeganTypeId) {
        this.yeganTypeId = yeganTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        YeganDTO yeganDTO = (YeganDTO) o;
        if (yeganDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yeganDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "YeganDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", nirooCodeId=" + getNirooCodeId() +
            ", shahrId=" + getShahrId() +
            ", yeganTypeId=" + getYeganTypeId() +
            "}";
    }
}
