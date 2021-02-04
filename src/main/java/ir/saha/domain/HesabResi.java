package ir.saha.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ir.saha.domain.enumeration.VaziateHesabResi;

/**
 * A HesabResi.
 */
@Entity
@Table(name = "hesab_resi")
public class HesabResi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sal")
    private Integer sal;

    @Column(name = "sal")
    private Long tedadRoozayeTatilSal;

    @Enumerated(EnumType.STRING)
    @Column(name = "vaziate_hesab_resi")
    private VaziateHesabResi vaziateHesabResi;

    @OneToOne
    @JoinColumn(unique = true)
    private Gozaresh gozaresh;

    @OneToOne
    @JoinColumn(unique = true)
    private BankEtelaati bankEtelaati;

    @OneToOne
    @JoinColumn(unique = true)
    private RafeIradat rafeIradat;

    @OneToOne
    @JoinColumn(unique = true)
    private MostaKhreje mostaKhreje;

    @OneToOne
    @JoinColumn(unique = true)
    private BilanSeSalGhabl bilanSeSalGhabl;

    @OneToOne
    @JoinColumn(unique = true)
    private MohasebeHazineMamooriat mohasebeHazineMamooriat;

    @OneToOne
    @JoinColumn(unique = true)
    private ChekideGardeshKar chekideGardeshKar;

    @OneToOne
    @JoinColumn(unique = true)
    private GozareshHozoor gozareshHozoor;

    @OneToOne
    @JoinColumn(unique = true)
    private BilanSalGhabl bilanSalGhabl;

    @OneToOne
    @JoinColumn(unique = true)
    private Madarek madarek;

    @OneToOne
    @JoinColumn(unique = true)
    private GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi;

    @OneToOne
    @JoinColumn(unique = true)
    private DastoorAmalEjraE dastoorAmalEjraE;

    @OneToOne
    @JoinColumn(unique = true)
    private Nameh nameh;

    @OneToOne
    @JoinColumn(unique = true)
    private KholaseGozaresh kholaseGozaresh;

    @OneToOne
    @JoinColumn(unique = true)
    private GardeshKar gardeshKar;

    @OneToOne
    @JoinColumn(unique = true)
    private BarnameHesabResi barnameHesabResi;

    @OneToMany(mappedBy = "hesabResi")
    private Set<BargeMamooriat> bargeMamooriats = new HashSet<>();

    @OneToMany(mappedBy = "hesabResi")
    private Set<FileHesabResi> filehas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSal() {
        return sal;
    }

    public HesabResi sal(Integer sal) {
        this.sal = sal;
        return this;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public VaziateHesabResi getVaziateHesabResi() {
        return vaziateHesabResi;
    }

    public HesabResi vaziateHesabResi(VaziateHesabResi vaziateHesabResi) {
        this.vaziateHesabResi = vaziateHesabResi;
        return this;
    }

    public void setVaziateHesabResi(VaziateHesabResi vaziateHesabResi) {
        this.vaziateHesabResi = vaziateHesabResi;
    }

    public Gozaresh getGozaresh() {
        return gozaresh;
    }

    public HesabResi gozaresh(Gozaresh gozaresh) {
        this.gozaresh = gozaresh;
        return this;
    }

    public void setGozaresh(Gozaresh gozaresh) {
        this.gozaresh = gozaresh;
    }

    public BankEtelaati getBankEtelaati() {
        return bankEtelaati;
    }

    public HesabResi bankEtelaati(BankEtelaati bankEtelaati) {
        this.bankEtelaati = bankEtelaati;
        return this;
    }

    public void setBankEtelaati(BankEtelaati bankEtelaati) {
        this.bankEtelaati = bankEtelaati;
    }

    public RafeIradat getRafeIradat() {
        return rafeIradat;
    }

    public HesabResi rafeIradat(RafeIradat rafeIradat) {
        this.rafeIradat = rafeIradat;
        return this;
    }

    public void setRafeIradat(RafeIradat rafeIradat) {
        this.rafeIradat = rafeIradat;
    }

    public MostaKhreje getMostaKhreje() {
        return mostaKhreje;
    }

    public HesabResi mostaKhreje(MostaKhreje mostaKhreje) {
        this.mostaKhreje = mostaKhreje;
        return this;
    }

    public void setMostaKhreje(MostaKhreje mostaKhreje) {
        this.mostaKhreje = mostaKhreje;
    }

    public BilanSeSalGhabl getBilanSeSalGhabl() {
        return bilanSeSalGhabl;
    }

    public HesabResi bilanSeSalGhabl(BilanSeSalGhabl bilanSeSalGhabl) {
        this.bilanSeSalGhabl = bilanSeSalGhabl;
        return this;
    }

    public void setBilanSeSalGhabl(BilanSeSalGhabl bilanSeSalGhabl) {
        this.bilanSeSalGhabl = bilanSeSalGhabl;
    }

    public MohasebeHazineMamooriat getMohasebeHazineMamooriat() {
        return mohasebeHazineMamooriat;
    }

    public HesabResi mohasebeHazineMamooriat(MohasebeHazineMamooriat mohasebeHazineMamooriat) {
        this.mohasebeHazineMamooriat = mohasebeHazineMamooriat;
        return this;
    }

    public void setMohasebeHazineMamooriat(MohasebeHazineMamooriat mohasebeHazineMamooriat) {
        this.mohasebeHazineMamooriat = mohasebeHazineMamooriat;
    }

    public ChekideGardeshKar getChekideGardeshKar() {
        return chekideGardeshKar;
    }

    public HesabResi chekideGardeshKar(ChekideGardeshKar chekideGardeshKar) {
        this.chekideGardeshKar = chekideGardeshKar;
        return this;
    }

    public void setChekideGardeshKar(ChekideGardeshKar chekideGardeshKar) {
        this.chekideGardeshKar = chekideGardeshKar;
    }

    public GozareshHozoor getGozareshHozoor() {
        return gozareshHozoor;
    }

    public HesabResi gozareshHozoor(GozareshHozoor gozareshHozoor) {
        this.gozareshHozoor = gozareshHozoor;
        return this;
    }

    public void setGozareshHozoor(GozareshHozoor gozareshHozoor) {
        this.gozareshHozoor = gozareshHozoor;
    }

    public BilanSalGhabl getBilanSalGhabl() {
        return bilanSalGhabl;
    }

    public HesabResi bilanSalGhabl(BilanSalGhabl bilanSalGhabl) {
        this.bilanSalGhabl = bilanSalGhabl;
        return this;
    }

    public void setBilanSalGhabl(BilanSalGhabl bilanSalGhabl) {
        this.bilanSalGhabl = bilanSalGhabl;
    }

    public Madarek getMadarek() {
        return madarek;
    }

    public HesabResi madarek(Madarek madarek) {
        this.madarek = madarek;
        return this;
    }

    public void setMadarek(Madarek madarek) {
        this.madarek = madarek;
    }

    public GardeshkarBarnameHesabresi getGardeshkarBarnameHesabresi() {
        return gardeshkarBarnameHesabresi;
    }

    public HesabResi gardeshkarBarnameHesabresi(GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi) {
        this.gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresi;
        return this;
    }

    public void setGardeshkarBarnameHesabresi(GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi) {
        this.gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresi;
    }

    public DastoorAmalEjraE getDastoorAmalEjraE() {
        return dastoorAmalEjraE;
    }

    public HesabResi dastoorAmalEjraE(DastoorAmalEjraE dastoorAmalEjraE) {
        this.dastoorAmalEjraE = dastoorAmalEjraE;
        return this;
    }

    public void setDastoorAmalEjraE(DastoorAmalEjraE dastoorAmalEjraE) {
        this.dastoorAmalEjraE = dastoorAmalEjraE;
    }

    public Nameh getNameh() {
        return nameh;
    }

    public HesabResi nameh(Nameh nameh) {
        this.nameh = nameh;
        return this;
    }

    public void setNameh(Nameh nameh) {
        this.nameh = nameh;
    }

    public KholaseGozaresh getKholaseGozaresh() {
        return kholaseGozaresh;
    }

    public HesabResi kholaseGozaresh(KholaseGozaresh kholaseGozaresh) {
        this.kholaseGozaresh = kholaseGozaresh;
        return this;
    }

    public void setKholaseGozaresh(KholaseGozaresh kholaseGozaresh) {
        this.kholaseGozaresh = kholaseGozaresh;
    }

    public GardeshKar getGardeshKar() {
        return gardeshKar;
    }

    public HesabResi gardeshKar(GardeshKar gardeshKar) {
        this.gardeshKar = gardeshKar;
        return this;
    }

    public void setGardeshKar(GardeshKar gardeshKar) {
        this.gardeshKar = gardeshKar;
    }

    public BarnameHesabResi getBarnameHesabResi() {
        return barnameHesabResi;
    }

    public HesabResi barnameHesabResi(BarnameHesabResi barnameHesabResi) {
        this.barnameHesabResi = barnameHesabResi;
        return this;
    }

    public void setBarnameHesabResi(BarnameHesabResi barnameHesabResi) {
        this.barnameHesabResi = barnameHesabResi;
    }

    public Set<BargeMamooriat> getBargeMamooriats() {
        return bargeMamooriats;
    }

    public HesabResi bargeMamooriats(Set<BargeMamooriat> bargeMamooriats) {
        this.bargeMamooriats = bargeMamooriats;
        return this;
    }

    public HesabResi addBargeMamooriat(BargeMamooriat bargeMamooriat) {
        this.bargeMamooriats.add(bargeMamooriat);
        bargeMamooriat.setHesabResi(this);
        return this;
    }

    public HesabResi removeBargeMamooriat(BargeMamooriat bargeMamooriat) {
        this.bargeMamooriats.remove(bargeMamooriat);
        bargeMamooriat.setHesabResi(null);
        return this;
    }

    public void setBargeMamooriats(Set<BargeMamooriat> bargeMamooriats) {
        this.bargeMamooriats = bargeMamooriats;
    }

    public Set<FileHesabResi> getFilehas() {
        return filehas;
    }

    public HesabResi filehas(Set<FileHesabResi> fileHesabResis) {
        this.filehas = fileHesabResis;
        return this;
    }

    public HesabResi addFileha(FileHesabResi fileHesabResi) {
        this.filehas.add(fileHesabResi);
        fileHesabResi.setHesabResi(this);
        return this;
    }

    public HesabResi removeFileha(FileHesabResi fileHesabResi) {
        this.filehas.remove(fileHesabResi);
        fileHesabResi.setHesabResi(null);
        return this;
    }

    public void setFilehas(Set<FileHesabResi> fileHesabResis) {
        this.filehas = fileHesabResis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HesabResi)) {
            return false;
        }
        return id != null && id.equals(((HesabResi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Long getTedadRoozayeTatilSal() {
        return tedadRoozayeTatilSal;
    }

    public void setTedadRoozayeTatilSal(Long tedadRoozayeTatilSal) {
        this.tedadRoozayeTatilSal = tedadRoozayeTatilSal;
    }

    @Override
    public String toString() {
        return "HesabResi{" +
            "id=" + getId() +
            ", sal=" + getSal() +
            ", vaziateHesabResi='" + getVaziateHesabResi() + "'" +
            "}";
    }
}
