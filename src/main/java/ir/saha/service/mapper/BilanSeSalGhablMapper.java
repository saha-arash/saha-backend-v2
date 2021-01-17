package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.BilanSeSalGhablDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BilanSeSalGhabl} and its DTO {@link BilanSeSalGhablDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BilanSeSalGhablMapper extends EntityMapper<BilanSeSalGhablDTO, BilanSeSalGhabl> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    BilanSeSalGhabl toEntity(BilanSeSalGhablDTO bilanSeSalGhablDTO);

    default BilanSeSalGhabl fromId(Long id) {
        if (id == null) {
            return null;
        }
        BilanSeSalGhabl bilanSeSalGhabl = new BilanSeSalGhabl();
        bilanSeSalGhabl.setId(id);
        return bilanSeSalGhabl;
    }
}
