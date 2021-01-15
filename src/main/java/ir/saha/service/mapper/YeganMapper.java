package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.YeganDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Yegan} and its DTO {@link YeganDTO}.
 */
@Mapper(componentModel = "spring", uses = {NirooCodeMapper.class, ShahrMapper.class, YeganTypeMapper.class})
public interface YeganMapper extends EntityMapper<YeganDTO, Yegan> {

    @Mapping(source = "nirooCode.id", target = "nirooCodeId")
    @Mapping(source = "shahr.id", target = "shahrId")
    @Mapping(source = "yeganType.id", target = "yeganTypeId")
    YeganDTO toDto(Yegan yegan);

    @Mapping(target = "karbars", ignore = true)
    @Mapping(target = "removeKarbar", ignore = true)
    @Mapping(target = "sandoghVoroodis", ignore = true)
    @Mapping(target = "removeSandoghVoroodi", ignore = true)
    @Mapping(target = "snadoghKhoroojis", ignore = true)
    @Mapping(target = "removeSnadoghKhorooji", ignore = true)
    @Mapping(target = "bargeMamoorits", ignore = true)
    @Mapping(target = "removeBargeMamoorit", ignore = true)
    @Mapping(target = "removeZirYegan", ignore = true)
    @Mapping(target = "yeganCode", ignore = true)
    @Mapping(source = "nirooCodeId", target = "nirooCode")
    @Mapping(source = "shahrId", target = "shahr")
    @Mapping(source = "yeganTypeId", target = "yeganType")
    @Mapping(target = "yegans", ignore = true)
    @Mapping(target = "removeYegan", ignore = true)
    Yegan toEntity(YeganDTO yeganDTO);

    default Yegan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Yegan yegan = new Yegan();
        yegan.setId(id);
        return yegan;
    }
}
