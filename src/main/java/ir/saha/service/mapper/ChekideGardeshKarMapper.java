package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.ChekideGardeshKarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChekideGardeshKar} and its DTO {@link ChekideGardeshKarDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChekideGardeshKarMapper extends EntityMapper<ChekideGardeshKarDTO, ChekideGardeshKar> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    ChekideGardeshKar toEntity(ChekideGardeshKarDTO chekideGardeshKarDTO);

    default ChekideGardeshKar fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChekideGardeshKar chekideGardeshKar = new ChekideGardeshKar();
        chekideGardeshKar.setId(id);
        return chekideGardeshKar;
    }
}
