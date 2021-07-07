package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Nameh.
 */
@Entity
@Table(name = "nameh")
public class Nameh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shomare")
    private String shomare;

    @Column(name = "tarikh_eblagh")
    private Instant tarikhEblagh;

    @OneToMany(mappedBy = "nameh")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "nameh")
    @JsonIgnore
    private HesabResi hesabResi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShomare() {
        return shomare;
    }

    public Nameh shomare(String shomare) {
        this.shomare = shomare;
        return this;
    }

    public void setShomare(String shomare) {
        this.shomare = shomare;
    }

    public Instant getTarikhEblagh() {
        return tarikhEblagh;
    }

    public Nameh tarikhEblagh(Instant tarikhEblagh) {
        this.tarikhEblagh = tarikhEblagh;
        return this;
    }

    public void setTarikhEblagh(Instant tarikhEblagh) {
        this.tarikhEblagh = tarikhEblagh;
    }

    public Set<FileHesabResi> getFailhas() {
        return failhas;
    }

    public Nameh failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public Nameh addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setNameh(this);
        return this;
    }

    public Nameh removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setNameh(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public Nameh hesabResi(HesabResi hesabResi) {
        this.hesabResi = hesabResi;
        return this;
    }

    public void setHesabResi(HesabResi hesabResi) {
        this.hesabResi = hesabResi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nameh)) {
            return false;
        }
        return id != null && id.equals(((Nameh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Nameh{" +
            "id=" + getId() +
            ", shomare='" + getShomare() + "'" +
            ", tarikhEblagh='" + getTarikhEblagh() + "'" +
            "}";
    }
}
