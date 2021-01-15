package ir.saha.service.dto;

import ir.saha.domain.enumeration.VaziatBargeMamooriat;

public class FilterBargeMamooriat {

    private VaziatBargeMamooriat vaziatBargeMamooriat;
    private Integer saleMamooriat;
    private Boolean hesabResiShode;

    public VaziatBargeMamooriat getVaziatBargeMamooriat() {
        return vaziatBargeMamooriat;
    }

    public void setVaziatBargeMamooriat(VaziatBargeMamooriat vaziatBargeMamooriat) {
        this.vaziatBargeMamooriat = vaziatBargeMamooriat;
    }

    public Integer getSaleMamooriat() {
        return saleMamooriat;
    }

    public void setSaleMamooriat(Integer saleMamooriat) {
        this.saleMamooriat = saleMamooriat;
    }

    public Boolean getHesabResiShode() {
        return hesabResiShode;
    }

    public void setHesabResiShode(Boolean hesabResiShode) {
        this.hesabResiShode = hesabResiShode;
    }
}
