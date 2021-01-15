package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.BargeMamooriatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BargeMamooriat} and its DTO {@link BargeMamooriatDTO}.
 */
@Mapper(componentModel = "spring", uses = {KarbarMapper.class, YeganMapper.class, HesabResiMapper.class})
public interface BargeMamooriatMapper extends EntityMapper<BargeMamooriatDTO, BargeMamooriat> {

    @Mapping(source = "sarparast.id", target = "sarparastId")
    @Mapping(source = "yegan.id", target = "yeganId")
    @Mapping(source = "hesabResi.id", target = "hesabResiId")
    BargeMamooriatDTO toDto(BargeMamooriat bargeMamooriat);

    @Mapping(target = "madareks", ignore = true)
    @Mapping(target = "removeMadarek", ignore = true)
    @Mapping(source = "sarparastId", target = "sarparast")
    @Mapping(source = "yeganId", target = "yegan")
    @Mapping(source = "hesabResiId", target = "hesabResi")
    @Mapping(target = "nafars", ignore = true)
    @Mapping(target = "removeNafar", ignore = true)
    @Mapping(target = "binandes", ignore = true)
    @Mapping(target = "removeBinande", ignore = true)
    BargeMamooriat toEntity(BargeMamooriatDTO bargeMamooriatDTO);

    default BargeMamooriat fromId(Long id) {
        if (id == null) {
            return null;
        }
        BargeMamooriat bargeMamooriat = new BargeMamooriat();
        bargeMamooriat.setId(id);
        return bargeMamooriat;
    }
}
