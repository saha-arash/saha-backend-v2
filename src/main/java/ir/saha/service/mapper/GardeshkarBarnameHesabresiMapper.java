package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.GardeshkarBarnameHesabresiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GardeshkarBarnameHesabresi} and its DTO {@link GardeshkarBarnameHesabresiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GardeshkarBarnameHesabresiMapper extends EntityMapper<GardeshkarBarnameHesabresiDTO, GardeshkarBarnameHesabresi> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    GardeshkarBarnameHesabresi toEntity(GardeshkarBarnameHesabresiDTO gardeshkarBarnameHesabresiDTO);

    default GardeshkarBarnameHesabresi fromId(Long id) {
        if (id == null) {
            return null;
        }
        GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi = new GardeshkarBarnameHesabresi();
        gardeshkarBarnameHesabresi.setId(id);
        return gardeshkarBarnameHesabresi;
    }
}
