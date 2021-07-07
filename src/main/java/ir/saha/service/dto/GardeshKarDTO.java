package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.GardeshKar} entity.
 */
public class GardeshKarDTO implements Serializable {

    private Long id;

    private Instant tarikh;

    private String mozoo;

    private Integer shomare;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTarikh() {
        return tarikh;
    }

    public void setTarikh(Instant tarikh) {
        this.tarikh = tarikh;
    }

    public String getMozoo() {
        return mozoo;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public Integer getShomare() {
        return shomare;
    }

    public void setShomare(Integer shomare) {
        this.shomare = shomare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GardeshKarDTO gardeshKarDTO = (GardeshKarDTO) o;
        if (gardeshKarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gardeshKarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GardeshKarDTO{" +
            "id=" + getId() +
            ", tarikh='" + getTarikh() + "'" +
            ", mozoo='" + getMozoo() + "'" +
            ", shomare=" + getShomare() +
            "}";
    }
}
