package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.SematDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Semat} and its DTO {@link SematDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SematMapper extends EntityMapper<SematDTO, Semat> {


    @Mapping(target = "karbars", ignore = true)
    @Mapping(target = "removeKarbar", ignore = true)
    Semat toEntity(SematDTO sematDTO);

    default Semat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Semat semat = new Semat();
        semat.setId(id);
        return semat;
    }
}
