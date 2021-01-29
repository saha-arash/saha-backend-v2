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

    private String username;
    private String password;


    private Set<YeganDTO> zirYegans = new HashSet<>();

    private Long nirooCodeId;

    private Long shahrId;

    private Long yeganTypeId;

    private NirooCodeDTO nirooCodeDTO;

    private ShahrDTO shahrDTO;

    private YeganTypeDTO yeganTypeDTO;

    public NirooCodeDTO getNirooCodeDTO() {
        return nirooCodeDTO;
    }

    public void setNirooCodeDTO(NirooCodeDTO nirooCodeDTO) {
        this.nirooCodeDTO = nirooCodeDTO;
    }

    public ShahrDTO getShahrDTO() {
        return shahrDTO;
    }

    public void setShahrDTO(ShahrDTO shahrDTO) {
        this.shahrDTO = shahrDTO;
    }

    public YeganTypeDTO getYeganTypeDTO() {
        return yeganTypeDTO;
    }

    public void setYeganTypeDTO(YeganTypeDTO yeganTypeDTO) {
        this.yeganTypeDTO = yeganTypeDTO;
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
