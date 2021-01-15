package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.MorkhasiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Morkhasi} and its DTO {@link MorkhasiDTO}.
 */
@Mapper(componentModel = "spring", uses = {KarbarMapper.class})
public interface MorkhasiMapper extends EntityMapper<MorkhasiDTO, Morkhasi> {

    @Mapping(source = "karbar.id", target = "karbarId")
    MorkhasiDTO toDto(Morkhasi morkhasi);

    @Mapping(source = "karbarId", target = "karbar")
    Morkhasi toEntity(MorkhasiDTO morkhasiDTO);

    default Morkhasi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Morkhasi morkhasi = new Morkhasi();
        morkhasi.setId(id);
        return morkhasi;
    }
}
