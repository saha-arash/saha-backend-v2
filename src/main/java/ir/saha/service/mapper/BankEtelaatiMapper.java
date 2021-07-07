package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.BankEtelaatiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankEtelaati} and its DTO {@link BankEtelaatiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankEtelaatiMapper extends EntityMapper<BankEtelaatiDTO, BankEtelaati> {


    @Mapping(target = "failhas", ignore = true)
    @Mapping(target = "removeFailha", ignore = true)
    @Mapping(target = "hesabResi", ignore = true)
    BankEtelaati toEntity(BankEtelaatiDTO bankEtelaatiDTO);

    default BankEtelaati fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankEtelaati bankEtelaati = new BankEtelaati();
        bankEtelaati.setId(id);
        return bankEtelaati;
    }
}
