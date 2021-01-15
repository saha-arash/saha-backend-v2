package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.MantagheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mantaghe} and its DTO {@link MantagheDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MantagheMapper extends EntityMapper<MantagheDTO, Mantaghe> {


    @Mapping(target = "ostans", ignore = true)
    @Mapping(target = "removeOstan", ignore = true)
    Mantaghe toEntity(MantagheDTO mantagheDTO);

    default Mantaghe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mantaghe mantaghe = new Mantaghe();
        mantaghe.setId(id);
        return mantaghe;
    }
}
