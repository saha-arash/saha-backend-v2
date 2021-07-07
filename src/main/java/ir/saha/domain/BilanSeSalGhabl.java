package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BilanSeSalGhabl.
 */
@Entity
@Table(name = "bilan_se_sal_ghabl")
public class BilanSeSalGhabl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bilanSeSalGhabl")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "bilanSeSalGhabl")
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

    public BilanSeSalGhabl failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public BilanSeSalGhabl addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setBilanSeSalGhabl(this);
        return this;
    }

    public BilanSeSalGhabl removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setBilanSeSalGhabl(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public BilanSeSalGhabl hesabResi(HesabResi hesabResi) {
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
        if (!(o instanceof BilanSeSalGhabl)) {
            return false;
        }
        return id != null && id.equals(((BilanSeSalGhabl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BilanSeSalGhabl{" +
            "id=" + getId() +
            "}";
    }
}
