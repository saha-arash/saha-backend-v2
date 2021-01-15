package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.NirooCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NirooCode} and its DTO {@link NirooCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NirooCodeMapper extends EntityMapper<NirooCodeDTO, NirooCode> {


    @Mapping(target = "yeganCodes", ignore = true)
    @Mapping(target = "removeYeganCode", ignore = true)
    @Mapping(target = "yegans", ignore = true)
    @Mapping(target = "removeYegan", ignore = true)
    NirooCode toEntity(NirooCodeDTO nirooCodeDTO);

    default NirooCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        NirooCode nirooCode = new NirooCode();
        nirooCode.setId(id);
        return nirooCode;
    }
}
