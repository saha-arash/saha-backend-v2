package ir.saha.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import ir.saha.domain.enumeration.FileType;

/**
 * A FileHesabResi.
 */
@Entity
@Table(name = "file_hesab_resi")
public class FileHesabResi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "shomare")
    private Integer shomare;

    @Column(name = "tarikh_name")
    private Instant tarikhName;

    @Column(name = "mozoo")
    private String mozoo;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @ManyToOne
    @JsonIgnoreProperties("filehas")
    private HesabResi hesabResi;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private BarnameHesabResi barnameHesabResi;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private BankEtelaati bankEtelaati;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private RafeIradat rafeIradat;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private MostaKhreje mostaKhreje;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private BilanSeSalGhabl bilanSeSalGhabl;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private MohasebeHazineMamooriat mohasebeHazineMamooriat;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private ChekideGardeshKar chekideGardeshKar;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private GozareshHozoor gozareshHozoor;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private BilanSalGhabl bilanSalGhabl;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private Madarek madarek;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private DastoorAmalEjraE dastoorAmalEjraE;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private Nameh nameh;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private KholaseGozaresh kholaseGozaresh;

    @ManyToOne
    @JsonIgnoreProperties("failhas")
    private GardeshKar gardeshKar;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public FileHesabResi file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public FileHesabResi fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Integer getShomare() {
        return shomare;
    }

    public FileHesabResi shomare(Integer shomare) {
        this.shomare = shomare;
        return this;
    }

    public void setShomare(Integer shomare) {
        this.shomare = shomare;
    }

    public Instant getTarikhName() {
        return tarikhName;
    }

    public FileHesabResi tarikhName(Instant tarikhName) {
        this.tarikhName = tarikhName;
        return this;
    }

    public void setTarikhName(Instant tarikhName) {
        this.tarikhName = tarikhName;
    }

    public String getMozoo() {
        return mozoo;
    }

    public FileHesabResi mozoo(String mozoo) {
        this.mozoo = mozoo;
        return this;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public FileType getFileType() {
        return fileType;
    }

    public FileHesabResi fileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public HesabResi getHesabResi() {
        return hesabResi;
    }

    public FileHesabResi hesabResi(HesabResi hesabResi) {
        this.hesabResi = hesabResi;
        return this;
    }

    public void setHesabResi(HesabResi hesabResi) {
        this.hesabResi = hesabResi;
    }

    public BarnameHesabResi getBarnameHesabResi() {
        return barnameHesabResi;
    }

    public FileHesabResi barnameHesabResi(BarnameHesabResi barnameHesabResi) {
        this.barnameHesabResi = barnameHesabResi;
        return this;
    }

    public void setBarnameHesabResi(BarnameHesabResi barnameHesabResi) {
        this.barnameHesabResi = barnameHesabResi;
    }

    public BankEtelaati getBankEtelaati() {
        return bankEtelaati;
    }

    public FileHesabResi bankEtelaati(BankEtelaati bankEtelaati) {
        this.bankEtelaati = bankEtelaati;
        return this;
    }

    public void setBankEtelaati(BankEtelaati bankEtelaati) {
        this.bankEtelaati = bankEtelaati;
    }

    public RafeIradat getRafeIradat() {
        return rafeIradat;
    }

    public FileHesabResi rafeIradat(RafeIradat rafeIradat) {
        this.rafeIradat = rafeIradat;
        return this;
    }

    public void setRafeIradat(RafeIradat rafeIradat) {
        this.rafeIradat = rafeIradat;
    }

    public MostaKhreje getMostaKhreje() {
        return mostaKhreje;
    }

    public FileHesabResi mostaKhreje(MostaKhreje mostaKhreje) {
        this.mostaKhreje = mostaKhreje;
        return this;
    }

    public void setMostaKhreje(MostaKhreje mostaKhreje) {
        this.mostaKhreje = mostaKhreje;
    }

    public BilanSeSalGhabl getBilanSeSalGhabl() {
        return bilanSeSalGhabl;
    }

    public FileHesabResi bilanSeSalGhabl(BilanSeSalGhabl bilanSeSalGhabl) {
        this.bilanSeSalGhabl = bilanSeSalGhabl;
        return this;
    }

    public void setBilanSeSalGhabl(BilanSeSalGhabl bilanSeSalGhabl) {
        this.bilanSeSalGhabl = bilanSeSalGhabl;
    }

    public MohasebeHazineMamooriat getMohasebeHazineMamooriat() {
        return mohasebeHazineMamooriat;
    }

    public FileHesabResi mohasebeHazineMamooriat(MohasebeHazineMamooriat mohasebeHazineMamooriat) {
        this.mohasebeHazineMamooriat = mohasebeHazineMamooriat;
        return this;
    }

    public void setMohasebeHazineMamooriat(MohasebeHazineMamooriat mohasebeHazineMamooriat) {
        this.mohasebeHazineMamooriat = mohasebeHazineMamooriat;
    }

    public ChekideGardeshKar getChekideGardeshKar() {
        return chekideGardeshKar;
    }

    public FileHesabResi chekideGardeshKar(ChekideGardeshKar chekideGardeshKar) {
        this.chekideGardeshKar = chekideGardeshKar;
        return this;
    }

    public void setChekideGardeshKar(ChekideGardeshKar chekideGardeshKar) {
        this.chekideGardeshKar = chekideGardeshKar;
    }

    public GozareshHozoor getGozareshHozoor() {
        return gozareshHozoor;
    }

    public FileHesabResi gozareshHozoor(GozareshHozoor gozareshHozoor) {
        this.gozareshHozoor = gozareshHozoor;
        return this;
    }

    public void setGozareshHozoor(GozareshHozoor gozareshHozoor) {
        this.gozareshHozoor = gozareshHozoor;
    }

    public BilanSalGhabl getBilanSalGhabl() {
        return bilanSalGhabl;
    }

    public FileHesabResi bilanSalGhabl(BilanSalGhabl bilanSalGhabl) {
        this.bilanSalGhabl = bilanSalGhabl;
        return this;
    }

    public void setBilanSalGhabl(BilanSalGhabl bilanSalGhabl) {
        this.bilanSalGhabl = bilanSalGhabl;
    }

    public Madarek getMadarek() {
        return madarek;
    }

    public FileHesabResi madarek(Madarek madarek) {
        this.madarek = madarek;
        return this;
    }

    public void setMadarek(Madarek madarek) {
        this.madarek = madarek;
    }

    public GardeshkarBarnameHesabresi getGardeshkarBarnameHesabresi() {
        return gardeshkarBarnameHesabresi;
    }

    public FileHesabResi gardeshkarBarnameHesabresi(GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi) {
        this.gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresi;
        return this;
    }

    public void setGardeshkarBarnameHesabresi(GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi) {
        this.gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresi;
    }

    public DastoorAmalEjraE getDastoorAmalEjraE() {
        return dastoorAmalEjraE;
    }

    public FileHesabResi dastoorAmalEjraE(DastoorAmalEjraE dastoorAmalEjraE) {
        this.dastoorAmalEjraE = dastoorAmalEjraE;
        return this;
    }

    public void setDastoorAmalEjraE(DastoorAmalEjraE dastoorAmalEjraE) {
        this.dastoorAmalEjraE = dastoorAmalEjraE;
    }

    public Nameh getNameh() {
        return nameh;
    }

    public FileHesabResi nameh(Nameh nameh) {
        this.nameh = nameh;
        return this;
    }

    public void setNameh(Nameh nameh) {
        this.nameh = nameh;
    }

    public KholaseGozaresh getKholaseGozaresh() {
        return kholaseGozaresh;
    }

    public FileHesabResi kholaseGozaresh(KholaseGozaresh kholaseGozaresh) {
        this.kholaseGozaresh = kholaseGozaresh;
        return this;
    }

    public void setKholaseGozaresh(KholaseGozaresh kholaseGozaresh) {
        this.kholaseGozaresh = kholaseGozaresh;
    }

    public GardeshKar getGardeshKar() {
        return gardeshKar;
    }

    public FileHesabResi gardeshKar(GardeshKar gardeshKar) {
        this.gardeshKar = gardeshKar;
        return this;
    }

    public void setGardeshKar(GardeshKar gardeshKar) {
        this.gardeshKar = gardeshKar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileHesabResi)) {
            return false;
        }
        return id != null && id.equals(((FileHesabResi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FileHesabResi{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", shomare=" + getShomare() +
            ", tarikhName='" + getTarikhName() + "'" +
            ", mozoo='" + getMozoo() + "'" +
            ", fileType='" + getFileType() + "'" +
            "}";
    }
}
