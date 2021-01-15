package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.ShahrDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shahr} and its DTO {@link ShahrDTO}.
 */
@Mapper(componentModel = "spring", uses = {OstanMapper.class})
public interface ShahrMapper extends EntityMapper<ShahrDTO, Shahr> {

    @Mapping(source = "ostan.id", target = "ostanId")
    ShahrDTO toDto(Shahr shahr);

    @Mapping(target = "yegans", ignore = true)
    @Mapping(target = "removeYegan", ignore = true)
    @Mapping(source = "ostanId", target = "ostan")
    Shahr toEntity(ShahrDTO shahrDTO);

    default Shahr fromId(Long id) {
        if (id == null) {
            return null;
        }
        Shahr shahr = new Shahr();
        shahr.setId(id);
        return shahr;
    }
}
