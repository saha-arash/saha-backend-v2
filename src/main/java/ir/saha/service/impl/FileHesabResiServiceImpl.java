package ir.saha.service.impl;

import ir.saha.service.FileHesabResiService;
import ir.saha.domain.FileHesabResi;
import ir.saha.repository.FileHesabResiRepository;
import ir.saha.service.dto.FileHesabResiDTO;
import ir.saha.service.mapper.FileHesabResiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FileHesabResi}.
 */
@Service
@Transactional
public class FileHesabResiServiceImpl implements FileHesabResiService {

    private final Logger log = LoggerFactory.getLogger(FileHesabResiServiceImpl.class);

    private final FileHesabResiRepository fileHesabResiRepository;

    private final FileHesabResiMapper fileHesabResiMapper;

    public FileHesabResiServiceImpl(FileHesabResiRepository fileHesabResiRepository, FileHesabResiMapper fileHesabResiMapper) {
        this.fileHesabResiRepository = fileHesabResiRepository;
        this.fileHesabResiMapper = fileHesabResiMapper;
    }

    /**
     * Save a fileHesabResi.
     *
     * @param fileHesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileHesabResiDTO save(FileHesabResiDTO fileHesabResiDTO) {
        log.debug("Request to save FileHesabResi : {}", fileHesabResiDTO);
        FileHesabResi fileHesabResi = fileHesabResiMapper.toEntity(fileHesabResiDTO);
        fileHesabResi = fileHesabResiRepository.save(fileHesabResi);
        return fileHesabResiMapper.toDto(fileHesabResi);
    }

    /**
     * Get all the fileHesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileHesabResiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileHesabResis");
        return fileHesabResiRepository.findAll(pageable)
            .map(fileHesabResiMapper::toDto);
    }

    /**
     * Get one fileHesabResi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileHesabResiDTO> findOne(Long id) {
        log.debug("Request to get FileHesabResi : {}", id);
        return fileHesabResiRepository.findById(id)
            .map(fileHesabResiMapper::toDto);
    }

    /**
     * Delete the fileHesabResi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileHesabResi : {}", id);
        fileHesabResiRepository.deleteById(id);
    }
}
