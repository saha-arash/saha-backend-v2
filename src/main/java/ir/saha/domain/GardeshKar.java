package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A GardeshKar.
 */
@Entity
@Table(name = "gardesh_kar")
public class GardeshKar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tarikh")
    private Instant tarikh;

    @Column(name = "mozoo")
    private String mozoo;

    @Column(name = "shomare")
    private Integer shomare;

    @OneToMany(mappedBy = "gardeshKar")
    private Set<FileHesabResi> failhas = new HashSet<>();

    @OneToOne(mappedBy = "gardeshKar")
    @JsonIgnore
    private HesabResi hesabResi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTarikh() {
        return tarikh;
    }

    public GardeshKar tarikh(Instant tarikh) {
        this.tarikh = tarikh;
        return this;
    }

    public void setTarikh(Instant tarikh) {
        this.tarikh = tarikh;
    }

    public String getMozoo() {
        return mozoo;
    }

    public GardeshKar mozoo(String mozoo) {
        this.mozoo = mozoo;
        return this;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public Integer getShomare() {
        return shomare;
    }

    public GardeshKar shomare(Integer shomare) {
        this.shomare = shomare;
        return this;
    }

    public void setShomare(Integer shomare) {
        this.shomare = shomare;
    }

    public Set<FileHesabResi> getFailhas() {
        return failhas;
    }

    public GardeshKar failhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
        return this;
    }

    public GardeshKar addFailha(FileHesabResi fileHesabResi) {
        this.failhas.add(fileHesabResi);
        fileHesabResi.setGardeshKar(this);
        return this;
    }

    public GardeshKar removeFailha(FileHesabResi fileHesabResi) {
        this.failhas.remove(fileHesabResi);
        fileHesabResi.setGardeshKar(null);
        return this;
    }

    public void setFailhas(Set<FileHesabResi> fileHesabResis) {
        this.failhas = fileHesabResis;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public GardeshKar hesabResi(HesabResi hesabResi) {
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
        if (!(o instanceof GardeshKar)) {
            return false;
        }
        return id != null && id.equals(((GardeshKar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GardeshKar{" +
            "id=" + getId() +
            ", tarikh='" + getTarikh() + "'" +
            ", mozoo='" + getMozoo() + "'" +
            ", shomare=" + getShomare() +
            "}";
    }
}
