package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.PayamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payam} and its DTO {@link PayamDTO}.
 */
@Mapper(componentModel = "spring", uses = {KarbarMapper.class, YeganMapper.class})
public interface PayamMapper extends EntityMapper<PayamDTO, Payam> {

    @Mapping(source = "karbarErsalKonande.id", target = "karbarErsalKonandeId")
    @Mapping(source = "karbarDaryaftKonand.id", target = "karbarDaryaftKonandId")
    @Mapping(source = "yeganErsalKonanade.id", target = "yeganErsalKonanadeId")
    @Mapping(source = "yeganDaryaftKonanade.id", target = "yeganDaryaftKonanadeId")
    PayamDTO toDto(Payam payam);

    @Mapping(target = "madareks", ignore = true)
    @Mapping(target = "removeMadarek", ignore = true)
    @Mapping(source = "karbarErsalKonandeId", target = "karbarErsalKonande")
    @Mapping(source = "karbarDaryaftKonandId", target = "karbarDaryaftKonand")
    @Mapping(source = "yeganErsalKonanadeId", target = "yeganErsalKonanade")
    @Mapping(source = "yeganDaryaftKonanadeId", target = "yeganDaryaftKonanade")
    Payam toEntity(PayamDTO payamDTO);

    default Payam fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payam payam = new Payam();
        payam.setId(id);
        return payam;
    }
}
