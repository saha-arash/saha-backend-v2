package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GardeshkarBarnameHesabresi.
 */
@Entity
@Table(name = "gardeshkar_barname_hesabresi")
public class GardeshkarBarnameHesabresi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "gardeshkarBarnameHesabresi")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "gardeshkarBarnameHesabresi")
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

    public GardeshkarBarnameHesabresi failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public GardeshkarBarnameHesabresi addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setGardeshkarBarnameHesabresi(this);
        return this;
    }

    public GardeshkarBarnameHesabresi removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setGardeshkarBarnameHesabresi(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public GardeshkarBarnameHesabresi hesabResi(HesabResi hesabResi) {
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
        if (!(o instanceof GardeshkarBarnameHesabresi)) {
            return false;
        }
        return id != null && id.equals(((GardeshkarBarnameHesabresi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GardeshkarBarnameHesabresi{" +
            "id=" + getId() +
            "}";
    }
}
