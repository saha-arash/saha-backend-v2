package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.BarnameHesabResiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BarnameHesabResi} and its DTO {@link BarnameHesabResiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BarnameHesabResiMapper extends EntityMapper<BarnameHesabResiDTO, BarnameHesabResi> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    BarnameHesabResi toEntity(BarnameHesabResiDTO barnameHesabResiDTO);

    default BarnameHesabResi fromId(Long id) {
        if (id == null) {
            return null;
        }
        BarnameHesabResi barnameHesabResi = new BarnameHesabResi();
        barnameHesabResi.setId(id);
        return barnameHesabResi;
    }
}
