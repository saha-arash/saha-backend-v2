package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.MostaKhrejeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MostaKhreje} and its DTO {@link MostaKhrejeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MostaKhrejeMapper extends EntityMapper<MostaKhrejeDTO, MostaKhreje> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    MostaKhreje toEntity(MostaKhrejeDTO mostaKhrejeDTO);

    default MostaKhreje fromId(Long id) {
        if (id == null) {
            return null;
        }
        MostaKhreje mostaKhreje = new MostaKhreje();
        mostaKhreje.setId(id);
        return mostaKhreje;
    }
}
