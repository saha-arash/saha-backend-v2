package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.GardeshKarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GardeshKar} and its DTO {@link GardeshKarDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GardeshKarMapper extends EntityMapper<GardeshKarDTO, GardeshKar> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    GardeshKar toEntity(GardeshKarDTO gardeshKarDTO);

    default GardeshKar fromId(Long id) {
        if (id == null) {
            return null;
        }
        GardeshKar gardeshKar = new GardeshKar();
        gardeshKar.setId(id);
        return gardeshKar;
    }
}
