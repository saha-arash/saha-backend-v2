package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.YeganType} entity.
 */
public class YeganTypeDTO implements Serializable {

    private Long id;

    private String name;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        YeganTypeDTO yeganTypeDTO = (YeganTypeDTO) o;
        if (yeganTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yeganTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "YeganTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
