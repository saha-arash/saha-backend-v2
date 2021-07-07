package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GozareshHozoor.
 */
@Entity
@Table(name = "gozaresh_hozoor")
public class GozareshHozoor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "gozareshHozoor")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "gozareshHozoor")
    @JsonIgnore
    private HesabResi hesabResi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<FileHesabResi> getFailhas() {
        return failhas;
    }

    public GozareshHozoor failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public GozareshHozoor addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setGozareshHozoor(this);
        return this;
    }

    public GozareshHozoor removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setGozareshHozoor(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public GozareshHozoor hesabResi(HesabResi hesabResi) {
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
        if (!(o instanceof GozareshHozoor)) {
            return false;
        }
        return id != null && id.equals(((GozareshHozoor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GozareshHozoor{" +
            "id=" + getId() +
            "}";
    }
}
