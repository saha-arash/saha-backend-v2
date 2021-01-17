package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.DastoorAmalEjraEDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DastoorAmalEjraE} and its DTO {@link DastoorAmalEjraEDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DastoorAmalEjraEMapper extends EntityMapper<DastoorAmalEjraEDTO, DastoorAmalEjraE> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    DastoorAmalEjraE toEntity(DastoorAmalEjraEDTO dastoorAmalEjraEDTO);

    default DastoorAmalEjraE fromId(Long id) {
        if (id == null) {
            return null;
        }
        DastoorAmalEjraE dastoorAmalEjraE = new DastoorAmalEjraE();
        dastoorAmalEjraE.setId(id);
        return dastoorAmalEjraE;
    }
}
