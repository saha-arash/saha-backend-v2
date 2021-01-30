package ir.saha.service.dto;

public class YeganFilter {
    private String name;
    private Long nirooCodeId;
    private Long ostanId;
    private Long shahrId;
    private Long mantagheId;
    private String code;
    private boolean jahateHesabResi;
    private boolean jahatePeygiri;
    private boolean hesabresiShode;
    private boolean hesabresiNashodeShode;
    private  boolean kharejAzMahdoode;

    public boolean isKharejAzMahdoode() {
        return kharejAzMahdoode;
    }

    public void setKharejAzMahdoode(boolean kharejAzMahdoode) {
        this.kharejAzMahdoode = kharejAzMahdoode;
    }

    public boolean isHesabresiShode() {
        return hesabresiShode;
    }

    public void setHesabresiShode(boolean hesabresiShode) {
        this.hesabresiShode = hesabresiShode;
    }

    public boolean isHesabresiNashodeShode() {
        return hesabresiNashodeShode;
    }

    public void setHesabresiNashodeShode(boolean hesabresiNashodeShode) {
        this.hesabresiNashodeShode = hesabresiNashodeShode;
    }

    public boolean isJahateHesabResi() {
        return jahateHesabResi;
    }

    public void setJahateHesabResi(boolean jahateHesabResi) {
        this.jahateHesabResi = jahateHesabResi;
    }

    public boolean isJahatePeygiri() {
        return jahatePeygiri;
    }

    public void setJahatePeygiri(boolean jahatePeygiri) {
        this.jahatePeygiri = jahatePeygiri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNirooCodeId() {
        return nirooCodeId;
    }

    public void setNirooCodeId(Long nirooCodeId) {
        this.nirooCodeId = nirooCodeId;
    }

    public Long getOstanId() {
        return ostanId;
    }

    public void setOstanId(Long ostanId) {
        this.ostanId = ostanId;
    }

    public Long getShahrId() {
        return shahrId;
    }

    public void setShahrId(Long shahrId) {
        this.shahrId = shahrId;
    }

    public Long getMantagheId() {
        return mantagheId;
    }

    public void setMantagheId(Long mantagheId) {
        this.mantagheId = mantagheId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
