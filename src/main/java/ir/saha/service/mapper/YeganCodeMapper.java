package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.YeganCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link YeganCode} and its DTO {@link YeganCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {YeganMapper.class, NirooCodeMapper.class})
public interface YeganCodeMapper extends EntityMapper<YeganCodeDTO, YeganCode> {

    @Mapping(source = "yegan.id", target = "yeganId")
    @Mapping(source = "nirooCode.id", target = "nirooCodeId")
    YeganCodeDTO toDto(YeganCode yeganCode);

    @Mapping(source = "yeganId", target = "yegan")
    @Mapping(target = "karbars", ignore = true)
    @Mapping(target = "removeKarbar", ignore = true)
    @Mapping(source = "nirooCodeId", target = "nirooCode")
    YeganCode toEntity(YeganCodeDTO yeganCodeDTO);

    default YeganCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        YeganCode yeganCode = new YeganCode();
        yeganCode.setId(id);
        return yeganCode;
    }
}
