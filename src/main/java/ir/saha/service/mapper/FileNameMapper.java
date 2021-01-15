package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.FileNameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileName} and its DTO {@link FileNameDTO}.
 */
@Mapper(componentModel = "spring", uses = {PayamMapper.class})
public interface FileNameMapper extends EntityMapper<FileNameDTO, FileName> {

    @Mapping(source = "name.id", target = "nameId")
    FileNameDTO toDto(FileName fileName);

    @Mapping(source = "nameId", target = "name")
    FileName toEntity(FileNameDTO fileNameDTO);

    default FileName fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileName fileName = new FileName();
        fileName.setId(id);
        return fileName;
    }
}
