package ir.saha.service.mapper;


import ir.saha.domain.*;
import ir.saha.service.dto.FileHesabResiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileHesabResi} and its DTO {@link FileHesabResiDTO}.
 */
@Mapper(componentModel = "spring", uses = {HesabResiMapper.class, BarnameHesabResiMapper.class})
public interface FileHesabResiMapper extends EntityMapper<FileHesabResiDTO, FileHesabResi> {

    @Mapping(source = "hesabResi.id", target = "hesabResiId")
    @Mapping(source = "barnameHesabResi.id", target = "barnameHesabResiId")
    FileHesabResiDTO toDto(FileHesabResi fileHesabResi);

    @Mapping(source = "hesabResiId", target = "hesabResi")
    @Mapping(source = "barnameHesabResiId", target = "barnameHesabResi")
    FileHesabResi toEntity(FileHesabResiDTO fileHesabResiDTO);

    default FileHesabResi fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileHesabResi fileHesabResi = new FileHesabResi();
        fileHesabResi.setId(id);
        return fileHesabResi;
    }
}
