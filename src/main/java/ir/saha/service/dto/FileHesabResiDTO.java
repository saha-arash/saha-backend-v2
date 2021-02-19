package ir.saha.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import ir.saha.domain.enumeration.FileType;

/**
 * A DTO for the {@link ir.saha.domain.FileHesabResi} entity.
 */
public class FileHesabResiDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] file;

    private String fileContentType;
    private Integer shomare;

    private Instant tarikhName;

    private String mozoo;

    private FileType fileType;


    private Long hesabResiId;

    private Long barnameHesabResiId;

    private Long bankEtelaatiId;

    private Long rafeIradatId;

    private Long mostaKhrejeId;

    private Long bilanSeSalGhablId;

    private Long mohasebeHazineMamooriatId;

    private Long chekideGardeshKarId;

    private Long gozareshHozoorId;

    private Long bilanSalGhablId;

    private Long madarekId;

    private Long gardeshkarBarnameHesabresiId;

    private Long dastoorAmalEjraEId;

    private Long namehId;

    private Long kholaseGozareshId;

    private Long gardeshKarId;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Integer getShomare() {
        return shomare;
    }

    public void setShomare(Integer shomare) {
        this.shomare = shomare;
    }

    public Instant getTarikhName() {
        return tarikhName;
    }

    public void setTarikhName(Instant tarikhName) {
        this.tarikhName = tarikhName;
    }

    public String getMozoo() {
        return mozoo;
    }

    public void setMozoo(String mozoo) {
        this.mozoo = mozoo;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getHesabResiId() {
        return hesabResiId;
    }

    public void setHesabResiId(Long hesabResiId) {
        this.hesabResiId = hesabResiId;
    }

    public Long getBarnameHesabResiId() {
        return barnameHesabResiId;
    }

    public void setBarnameHesabResiId(Long barnameHesabResiId) {
        this.barnameHesabResiId = barnameHesabResiId;
    }

    public Long getBankEtelaatiId() {
        return bankEtelaatiId;
    }

    public void setBankEtelaatiId(Long bankEtelaatiId) {
        this.bankEtelaatiId = bankEtelaatiId;
    }

    public Long getRafeIradatId() {
        return rafeIradatId;
    }

    public void setRafeIradatId(Long rafeIradatId) {
        this.rafeIradatId = rafeIradatId;
    }

    public Long getMostaKhrejeId() {
        return mostaKhrejeId;
    }

    public void setMostaKhrejeId(Long mostaKhrejeId) {
        this.mostaKhrejeId = mostaKhrejeId;
    }

    public Long getBilanSeSalGhablId() {
        return bilanSeSalGhablId;
    }

    public void setBilanSeSalGhablId(Long bilanSeSalGhablId) {
        this.bilanSeSalGhablId = bilanSeSalGhablId;
    }

    public Long getMohasebeHazineMamooriatId() {
        return mohasebeHazineMamooriatId;
    }

    public void setMohasebeHazineMamooriatId(Long mohasebeHazineMamooriatId) {
        this.mohasebeHazineMamooriatId = mohasebeHazineMamooriatId;
    }

    public Long getChekideGardeshKarId() {
        return chekideGardeshKarId;
    }

    public void setChekideGardeshKarId(Long chekideGardeshKarId) {
        this.chekideGardeshKarId = chekideGardeshKarId;
    }

    public Long getGozareshHozoorId() {
        return gozareshHozoorId;
    }

    public void setGozareshHozoorId(Long gozareshHozoorId) {
        this.gozareshHozoorId = gozareshHozoorId;
    }

    public Long getBilanSalGhablId() {
        return bilanSalGhablId;
    }

    public void setBilanSalGhablId(Long bilanSalGhablId) {
        this.bilanSalGhablId = bilanSalGhablId;
    }

    public Long getMadarekId() {
        return madarekId;
    }

    public void setMadarekId(Long madarekId) {
        this.madarekId = madarekId;
    }

    public Long getGardeshkarBarnameHesabresiId() {
        return gardeshkarBarnameHesabresiId;
    }

    public void setGardeshkarBarnameHesabresiId(Long gardeshkarBarnameHesabresiId) {
        this.gardeshkarBarnameHesabresiId = gardeshkarBarnameHesabresiId;
    }

    public Long getDastoorAmalEjraEId() {
        return dastoorAmalEjraEId;
    }

    public void setDastoorAmalEjraEId(Long dastoorAmalEjraEId) {
        this.dastoorAmalEjraEId = dastoorAmalEjraEId;
    }

    public Long getNamehId() {
        return namehId;
    }

    public void setNamehId(Long namehId) {
        this.namehId = namehId;
    }

    public Long getKholaseGozareshId() {
        return kholaseGozareshId;
    }

    public void setKholaseGozareshId(Long kholaseGozareshId) {
        this.kholaseGozareshId = kholaseGozareshId;
    }

    public Long getGardeshKarId() {
        return gardeshKarId;
    }

    public void setGardeshKarId(Long gardeshKarId) {
        this.gardeshKarId = gardeshKarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileHesabResiDTO fileHesabResiDTO = (FileHesabResiDTO) o;
        if (fileHesabResiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileHesabResiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileHesabResiDTO{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", shomare=" + getShomare() +
            ", tarikhName='" + getTarikhName() + "'" +
            ", mozoo='" + getMozoo() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", hesabResiId=" + getHesabResiId() +
            ", barnameHesabResiId=" + getBarnameHesabResiId() +
            ", bankEtelaatiId=" + getBankEtelaatiId() +
            ", rafeIradatId=" + getRafeIradatId() +
            ", mostaKhrejeId=" + getMostaKhrejeId() +
            ", bilanSeSalGhablId=" + getBilanSeSalGhablId() +
            ", mohasebeHazineMamooriatId=" + getMohasebeHazineMamooriatId() +
            ", chekideGardeshKarId=" + getChekideGardeshKarId() +
            ", gozareshHozoorId=" + getGozareshHozoorId() +
            ", bilanSalGhablId=" + getBilanSalGhablId() +
            ", madarekId=" + getMadarekId() +
            ", gardeshkarBarnameHesabresiId=" + getGardeshkarBarnameHesabresiId() +
            ", dastoorAmalEjraEId=" + getDastoorAmalEjraEId() +
            ", namehId=" + getNamehId() +
            ", kholaseGozareshId=" + getKholaseGozareshId() +
            ", gardeshKarId=" + getGardeshKarId() +
            "}";
    }
}
