package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.NegahbaniDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Negahbani} and its DTO {@link NegahbaniDTO}.
 */
@Mapper(componentModel = "spring", uses = {KarbarMapper.class})
public interface NegahbaniMapper extends EntityMapper<NegahbaniDTO, Negahbani> {

    @Mapping(source = "karbar.id", target = "karbarId")
    NegahbaniDTO toDto(Negahbani negahbani);

    @Mapping(source = "karbarId", target = "karbar")
    Negahbani toEntity(NegahbaniDTO negahbaniDTO);

    default Negahbani fromId(Long id) {
        if (id == null) {
            return null;
        }
        Negahbani negahbani = new Negahbani();
        negahbani.setId(id);
        return negahbani;
    }
}
