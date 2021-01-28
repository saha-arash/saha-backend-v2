package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.KarbarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Karbar} and its DTO {@link KarbarDTO}.
 */
@Mapper(componentModel = "spring", uses = {BargeMamooriatMapper.class, YeganMapper.class, YeganCodeMapper.class, DarajeMapper.class, SematMapper.class})
public interface KarbarMapper extends EntityMapper<KarbarDTO, Karbar> {

    @Mapping(source = "yegan.id", target = "yeganId")
    @Mapping(source = "yeganCode.id", target = "yeganCodeId")
    @Mapping(source = "daraje.id", target = "darajeId")
    @Mapping(source = "semat.id", target = "sematId")
    @Mapping(target = "user", ignore = true)
    KarbarDTO toDto(Karbar karbar);

    @Mapping(target = "morkhasis", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "removeMorkhasi", ignore = true)
    @Mapping(target = "dores", ignore = true)
    @Mapping(target = "removeDore", ignore = true)
    @Mapping(target = "negahbanis", ignore = true)
    @Mapping(target = "removeNegahbani", ignore = true)
    @Mapping(target = "sarparestemamooriats", ignore = true)
    @Mapping(target = "removeSarparestemamooriat", ignore = true)
    @Mapping(target = "sandoghVoroodis", ignore = true)
    @Mapping(target = "removeSandoghVoroodi", ignore = true)
    @Mapping(target = "snadoghKhoroojis", ignore = true)
    @Mapping(target = "removeSnadoghKhorooji", ignore = true)
    @Mapping(target = "removeBargeMamoorit", ignore = true)
    @Mapping(target = "removeBinanadeBargeMamoorit", ignore = true)
    @Mapping(source = "yeganId", target = "yegan")
    @Mapping(source = "yeganCodeId", target = "yeganCode")
    @Mapping(source = "darajeId", target = "daraje")
    @Mapping(source = "sematId", target = "semat")
    Karbar toEntity(KarbarDTO karbarDTO);

    default Karbar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Karbar karbar = new Karbar();
        karbar.setId(id);
        return karbar;
    }
}
