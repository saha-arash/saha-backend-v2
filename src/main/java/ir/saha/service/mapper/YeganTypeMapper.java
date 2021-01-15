package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.YeganTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link YeganType} and its DTO {@link YeganTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface YeganTypeMapper extends EntityMapper<YeganTypeDTO, YeganType> {


    @Mapping(target = "yegans", ignore = true)
    @Mapping(target = "removeYegan", ignore = true)
    YeganType toEntity(YeganTypeDTO yeganTypeDTO);

    default YeganType fromId(Long id) {
        if (id == null) {
            return null;
        }
        YeganType yeganType = new YeganType();
        yeganType.setId(id);
        return yeganType;
    }
}
