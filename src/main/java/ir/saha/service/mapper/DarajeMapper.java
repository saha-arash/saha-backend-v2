package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.DarajeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Daraje} and its DTO {@link DarajeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DarajeMapper extends EntityMapper<DarajeDTO, Daraje> {


    @Mapping(target = "karbars", ignore = true)
    @Mapping(target = "removeKarbar", ignore = true)
    Daraje toEntity(DarajeDTO darajeDTO);

    default Daraje fromId(Long id) {
        if (id == null) {
            return null;
        }
        Daraje daraje = new Daraje();
        daraje.setId(id);
        return daraje;
    }
}
