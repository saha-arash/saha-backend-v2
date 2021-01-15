package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.HesabResiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HesabResi} and its DTO {@link HesabResiDTO}.
 */
@Mapper(componentModel = "spring", uses = {GozareshMapper.class, BarnameHesabResiMapper.class})
public interface HesabResiMapper extends EntityMapper<HesabResiDTO, HesabResi> {

    @Mapping(source = "gozaresh.id", target = "gozareshId")
    @Mapping(source = "barnameHesabResi.id", target = "barnameHesabResiId")
    HesabResiDTO toDto(HesabResi hesabResi);

    @Mapping(source = "gozareshId", target = "gozaresh")
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
