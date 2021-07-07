package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.NamehDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nameh} and its DTO {@link NamehDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NamehMapper extends EntityMapper<NamehDTO, Nameh> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    Nameh toEntity(NamehDTO namehDTO);

    default Nameh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nameh nameh = new Nameh();
        nameh.setId(id);
        return nameh;
    }
}
