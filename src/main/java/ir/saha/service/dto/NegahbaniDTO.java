package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.saha.domain.Negahbani} entity.
 */
public class NegahbaniDTO implements Serializable {

    private Long id;

    private Instant begin;

    private Instant end;


    private Long karbarId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getBegin() {
        return begin;
    }

    public void setBegin(Instant begin) {
        this.begin = begin;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public Long getKarbarId() {
        return karbarId;
    }

    public void setKarbarId(Long karbarId) {
        this.karbarId = karbarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NegahbaniDTO negahbaniDTO = (NegahbaniDTO) o;
        if (negahbaniDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), negahbaniDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NegahbaniDTO{" +
            "id=" + getId() +
            ", begin='" + getBegin() + "'" +
            ", end='" + getEnd() + "'" +
            ", karbarId=" + getKarbarId() +
            "}";
    }
}
