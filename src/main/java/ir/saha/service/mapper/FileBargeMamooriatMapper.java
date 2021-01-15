package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.FileBargeMamooriatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileBargeMamooriat} and its DTO {@link FileBargeMamooriatDTO}.
 */
@Mapper(componentModel = "spring", uses = {BargeMamooriatMapper.class})
public interface FileBargeMamooriatMapper extends EntityMapper<FileBargeMamooriatDTO, FileBargeMamooriat> {

    @Mapping(source = "bargeMamooriat.id", target = "bargeMamooriatId")
    FileBargeMamooriatDTO toDto(FileBargeMamooriat fileBargeMamooriat);

    @Mapping(source = "bargeMamooriatId", target = "bargeMamooriat")
    FileBargeMamooriat toEntity(FileBargeMamooriatDTO fileBargeMamooriatDTO);

    default FileBargeMamooriat fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileBargeMamooriat fileBargeMamooriat = new FileBargeMamooriat();
        fileBargeMamooriat.setId(id);
        return fileBargeMamooriat;
    }
}
