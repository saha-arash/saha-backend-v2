package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.GozareshHozoorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GozareshHozoor} and its DTO {@link GozareshHozoorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GozareshHozoorMapper extends EntityMapper<GozareshHozoorDTO, GozareshHozoor> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    GozareshHozoor toEntity(GozareshHozoorDTO gozareshHozoorDTO);

    default GozareshHozoor fromId(Long id) {
        if (id == null) {
            return null;
        }
        GozareshHozoor gozareshHozoor = new GozareshHozoor();
        gozareshHozoor.setId(id);
        return gozareshHozoor;
    }
}
