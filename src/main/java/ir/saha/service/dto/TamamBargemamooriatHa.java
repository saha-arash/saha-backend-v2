package ir.saha.service.dto;

import org.springframework.data.domain.Page;

public class TamamBargemamooriatHa {
    private Page<BargeMamooriatDTO> sarparast;
    private Page<BargeMamooriatDTO> binande;
    private Page<BargeMamooriatDTO> nafar;

    public Page<BargeMamooriatDTO> getSarparast() {
        return sarparast;
    }

    public void setSarparast(Page<BargeMamooriatDTO> sarparast) {
        this.sarparast = sarparast;
    }

    public Page<BargeMamooriatDTO> getBinande() {
        return binande;
    }

    public void setBinande(Page<BargeMamooriatDTO> binande) {
        this.binande = binande;
    }

    public Page<BargeMamooriatDTO> getNafar() {
        return nafar;
    }

    public void setNafar(Page<BargeMamooriatDTO> nafar) {
        this.nafar = nafar;
    }
}
