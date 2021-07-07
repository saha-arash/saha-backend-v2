package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.HesabResiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HesabResi} and its DTO {@link HesabResiDTO}.
 */
@Mapper(componentModel = "spring", uses = {GozareshMapper.class, BankEtelaatiMapper.class, RafeIradatMapper.class, MostaKhrejeMapper.class, BilanSeSalGhablMapper.class, MohasebeHazineMamooriatMapper.class, ChekideGardeshKarMapper.class, GozareshHozoorMapper.class, BilanSalGhablMapper.class, MadarekMapper.class, GardeshkarBarnameHesabresiMapper.class, DastoorAmalEjraEMapper.class, NamehMapper.class, KholaseGozareshMapper.class, GardeshKarMapper.class, BarnameHesabResiMapper.class})
public interface HesabResiMapper extends EntityMapper<HesabResiDTO, HesabResi> {

    @Mapping(source = "gozaresh.id", target = "gozareshId")
    @Mapping(source = "bankEtelaati.id", target = "bankEtelaatiId")
    @Mapping(source = "rafeIradat.id", target = "rafeIradatId")
    @Mapping(source = "mostaKhreje.id", target = "mostaKhrejeId")
    @Mapping(source = "bilanSeSalGhabl.id", target = "bilanSeSalGhablId")
    @Mapping(source = "mohasebeHazineMamooriat.id", target = "mohasebeHazineMamooriatId")
    @Mapping(source = "chekideGardeshKar.id", target = "chekideGardeshKarId")
    @Mapping(source = "gozareshHozoor.id", target = "gozareshHozoorId")
    @Mapping(source = "bilanSalGhabl.id", target = "bilanSalGhablId")
    @Mapping(source = "madarek.id", target = "madarekId")
    @Mapping(source = "gardeshkarBarnameHesabresi.id", target = "gardeshkarBarnameHesabresiId")
    @Mapping(source = "dastoorAmalEjraE.id", target = "dastoorAmalEjraEId")
    @Mapping(source = "nameh.id", target = "namehId")
    @Mapping(source = "kholaseGozaresh.id", target = "kholaseGozareshId")
    @Mapping(source = "gardeshKar.id", target = "gardeshKarId")
    @Mapping(source = "barnameHesabResi.id", target = "barnameHesabResiId")
    HesabResiDTO toDto(HesabResi hesabResi);

    @Mapping(source = "gozareshId", target = "gozaresh")
    @Mapping(source = "bankEtelaatiId", target = "bankEtelaati")
    @Mapping(source = "rafeIradatId", target = "rafeIradat")
    @Mapping(source = "mostaKhrejeId", target = "mostaKhreje")
    @Mapping(source = "bilanSeSalGhablId", target = "bilanSeSalGhabl")
    @Mapping(source = "mohasebeHazineMamooriatId", target = "mohasebeHazineMamooriat")
    @Mapping(source = "chekideGardeshKarId", target = "chekideGardeshKar")
    @Mapping(source = "gozareshHozoorId", target = "gozareshHozoor")
    @Mapping(source = "bilanSalGhablId", target = "bilanSalGhabl")
    @Mapping(source = "madarekId", target = "madarek")
    @Mapping(source = "gardeshkarBarnameHesabresiId", target = "gardeshkarBarnameHesabresi")
    @Mapping(source = "dastoorAmalEjraEId", target = "dastoorAmalEjraE")
    @Mapping(source = "namehId", target = "nameh")
    @Mapping(source = "kholaseGozareshId", target = "kholaseGozaresh")
    @Mapping(source = "gardeshKarId", target = "gardeshKar")
    @Mapping(source = "barnameHesabResiId", target = "barnameHesabResi")
    @Mapping(target = "bargeMamooriats", ignore = true)
    @Mapping(target = "removeBargeMamooriat", ignore = true)
    @Mapping(target = "filehas", ignore = true)
    @Mapping(target = "removeFileha", ignore = true)
    HesabResi toEntity(HesabResiDTO hesabResiDTO);

    default HesabResi fromId(Long id) {
        if (id == null) {
            return null;
        }
        HesabResi hesabResi = new HesabResi();
        hesabResi.setId(id);
        return hesabResi;
    }
}
