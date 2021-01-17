package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.MohasebeHazineMamooriatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MohasebeHazineMamooriat} and its DTO {@link MohasebeHazineMamooriatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MohasebeHazineMamooriatMapper extends EntityMapper<MohasebeHazineMamooriatDTO, MohasebeHazineMamooriat> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    MohasebeHazineMamooriat toEntity(MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO);

    default MohasebeHazineMamooriat fromId(Long id) {
        if (id == null) {
            return null;
        }
        MohasebeHazineMamooriat mohasebeHazineMamooriat = new MohasebeHazineMamooriat();
        mohasebeHazineMamooriat.setId(id);
        return mohasebeHazineMamooriat;
    }
}
