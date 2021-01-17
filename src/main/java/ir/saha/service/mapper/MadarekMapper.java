package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.MadarekDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Madarek} and its DTO {@link MadarekDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MadarekMapper extends EntityMapper<MadarekDTO, Madarek> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    Madarek toEntity(MadarekDTO madarekDTO);

    default Madarek fromId(Long id) {
        if (id == null) {
            return null;
        }
        Madarek madarek = new Madarek();
        madarek.setId(id);
        return madarek;
    }
}
