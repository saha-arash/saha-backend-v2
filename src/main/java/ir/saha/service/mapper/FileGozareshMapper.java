package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.FileGozareshDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileGozaresh} and its DTO {@link FileGozareshDTO}.
 */
@Mapper(componentModel = "spring", uses = {GozareshMapper.class})
public interface FileGozareshMapper extends EntityMapper<FileGozareshDTO, FileGozaresh> {

    @Mapping(source = "hesabResi.id", target = "hesabResiId")
    FileGozareshDTO toDto(FileGozaresh fileGozaresh);

    @Mapping(source = "hesabResiId", target = "hesabResi")
    FileGozaresh toEntity(FileGozareshDTO fileGozareshDTO);

    default FileGozaresh fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileGozaresh fileGozaresh = new FileGozaresh();
        fileGozaresh.setId(id);
        return fileGozaresh;
    }
}
