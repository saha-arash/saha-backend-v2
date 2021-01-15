package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.YeganCode} entity.
 */
public class YeganCodeDTO implements Serializable {

    private Long id;

    private String name;

    private String code;


    private Long yeganId;

    private Long nirooCodeId;

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

    public Long getYeganId() {
        return yeganId;
    }

    public void setYeganId(Long yeganId) {
        this.yeganId = yeganId;
    }

    public Long getNirooCodeId() {
        return nirooCodeId;
    }

    public void setNirooCodeId(Long nirooCodeId) {
        this.nirooCodeId = nirooCodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        YeganCodeDTO yeganCodeDTO = (YeganCodeDTO) o;
        if (yeganCodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yeganCodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "YeganCodeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", yeganId=" + getYeganId() +
            ", nirooCodeId=" + getNirooCodeId() +
            "}";
    }
}
