package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.DoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dore} and its DTO {@link DoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {KarbarMapper.class})
public interface DoreMapper extends EntityMapper<DoreDTO, Dore> {

    @Mapping(source = "karbar.id", target = "karbarId")
    DoreDTO toDto(Dore dore);

    @Mapping(source = "karbarId", target = "karbar")
    Dore toEntity(DoreDTO doreDTO);

    default Dore fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dore dore = new Dore();
        dore.setId(id);
        return dore;
    }
}
