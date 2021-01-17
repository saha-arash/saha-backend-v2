package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.KholaseGozareshDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link KholaseGozaresh} and its DTO {@link KholaseGozareshDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KholaseGozareshMapper extends EntityMapper<KholaseGozareshDTO, KholaseGozaresh> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    KholaseGozaresh toEntity(KholaseGozareshDTO kholaseGozareshDTO);

    default KholaseGozaresh fromId(Long id) {
        if (id == null) {
            return null;
        }
        KholaseGozaresh kholaseGozaresh = new KholaseGozaresh();
        kholaseGozaresh.setId(id);
        return kholaseGozaresh;
    }
}
