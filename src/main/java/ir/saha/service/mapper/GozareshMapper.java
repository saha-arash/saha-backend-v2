package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.GozareshDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Gozaresh} and its DTO {@link GozareshDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GozareshMapper extends EntityMapper<GozareshDTO, Gozaresh> {


    @Mapping(target = "filehayegozareshes", ignore = true)
    @Mapping(target = "removeFilehayegozaresh", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    Gozaresh toEntity(GozareshDTO gozareshDTO);

    default Gozaresh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gozaresh gozaresh = new Gozaresh();
        gozaresh.setId(id);
        return gozaresh;
    }
}
