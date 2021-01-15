package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.OstanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ostan} and its DTO {@link OstanDTO}.
 */
@Mapper(componentModel = "spring", uses = {MantagheMapper.class})
public interface OstanMapper extends EntityMapper<OstanDTO, Ostan> {

    @Mapping(source = "mantaghe.id", target = "mantagheId")
    OstanDTO toDto(Ostan ostan);

    @Mapping(target = "shahrs", ignore = true)
    @Mapping(target = "removeShahr", ignore = true)
    @Mapping(source = "mantagheId", target = "mantaghe")
    Ostan toEntity(OstanDTO ostanDTO);

    default Ostan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ostan ostan = new Ostan();
        ostan.setId(id);
        return ostan;
    }
}
