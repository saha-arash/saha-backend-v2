package ir.saha.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Ostan} entity.
 */
public class OstanDTO implements Serializable {

    private Long id;

    private String name;


    private Long mantagheId;

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

    public Long getMantagheId() {
        return mantagheId;
    }

    public void setMantagheId(Long mantagheId) {
        this.mantagheId = mantagheId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OstanDTO ostanDTO = (OstanDTO) o;
        if (ostanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ostanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OstanDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mantagheId=" + getMantagheId() +
            "}";
    }
}
