package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ChekideGardeshKar.
 */
@Entity
@Table(name = "chekide_gardesh_kar")
public class ChekideGardeshKar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chekideGardeshKar")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "chekideGardeshKar")
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

    public ChekideGardeshKar failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public ChekideGardeshKar addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setChekideGardeshKar(this);
        return this;
    }

    public ChekideGardeshKar removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setChekideGardeshKar(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public ChekideGardeshKar hesabResi(HesabResi hesabResi) {
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
        if (!(o instanceof ChekideGardeshKar)) {
            return false;
        }
        return id != null && id.equals(((ChekideGardeshKar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChekideGardeshKar{" +
            "id=" + getId() +
            "}";
    }
}
