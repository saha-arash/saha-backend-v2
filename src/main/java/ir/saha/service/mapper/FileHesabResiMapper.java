package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.FileHesabResiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileHesabResi} and its DTO {@link FileHesabResiDTO}.
 */
@Mapper(componentModel = "spring", uses = {HesabResiMapper.class, BarnameHesabResiMapper.class, BankEtelaatiMapper.class, RafeIradatMapper.class, MostaKhrejeMapper.class, BilanSeSalGhablMapper.class, MohasebeHazineMamooriatMapper.class, ChekideGardeshKarMapper.class, GozareshHozoorMapper.class, BilanSalGhablMapper.class, MadarekMapper.class, GardeshkarBarnameHesabresiMapper.class, DastoorAmalEjraEMapper.class, NamehMapper.class, KholaseGozareshMapper.class, GardeshKarMapper.class})
public interface FileHesabResiMapper extends EntityMapper<FileHesabResiDTO, FileHesabResi> {

    @Mapping(source = "hesabResi.id", target = "hesabResiId")
    @Mapping(source = "barnameHesabResi.id", target = "barnameHesabResiId")
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
    FileHesabResiDTO toDto(FileHesabResi fileHesabResi);

    @Mapping(source = "hesabResiId", target = "hesabResi")
    @Mapping(source = "barnameHesabResiId", target = "barnameHesabResi")
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
    FileHesabResi toEntity(FileHesabResiDTO fileHesabResiDTO);

    default FileHesabResi fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileHesabResi fileHesabResi = new FileHesabResi();
        fileHesabResi.setId(id);
        return fileHesabResi;
    }
}
