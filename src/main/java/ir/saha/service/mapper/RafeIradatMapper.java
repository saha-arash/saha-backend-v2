package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.RafeIradatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RafeIradat} and its DTO {@link RafeIradatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RafeIradatMapper extends EntityMapper<RafeIradatDTO, RafeIradat> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    RafeIradat toEntity(RafeIradatDTO rafeIradatDTO);

    default RafeIradat fromId(Long id) {
        if (id == null) {
            return null;
        }
        RafeIradat rafeIradat = new RafeIradat();
        rafeIradat.setId(id);
        return rafeIradat;
    }
}
