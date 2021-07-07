package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.BilanSalGhablDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BilanSalGhabl} and its DTO {@link BilanSalGhablDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BilanSalGhablMapper extends EntityMapper<BilanSalGhablDTO, BilanSalGhabl> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    BilanSalGhabl toEntity(BilanSalGhablDTO bilanSalGhablDTO);

    default BilanSalGhabl fromId(Long id) {
        if (id == null) {
            return null;
        }
        BilanSalGhabl bilanSalGhabl = new BilanSalGhabl();
        bilanSalGhabl.setId(id);
        return bilanSalGhabl;
    }
}
