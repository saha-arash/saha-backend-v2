package ir.saha.service.impl;

import ir.saha.service.FileGozareshService;
import ir.saha.domain.FileGozaresh;
import ir.saha.repository.FileGozareshRepository;
import ir.saha.service.dto.FileGozareshDTO;
import ir.saha.service.mapper.FileGozareshMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FileGozaresh}.
 */
@Service
@Transactional
public class FileGozareshServiceImpl implements FileGozareshService {

    private final Logger log = LoggerFactory.getLogger(FileGozareshServiceImpl.class);

    private final FileGozareshRepository fileGozareshRepository;

    private final FileGozareshMapper fileGozareshMapper;

    public FileGozareshServiceImpl(FileGozareshRepository fileGozareshRepository, FileGozareshMapper fileGozareshMapper) {
        this.fileGozareshRepository = fileGozareshRepository;
        this.fileGozareshMapper = fileGozareshMapper;
    }

    /**
     * Save a fileGozaresh.
     *
     * @param fileGozareshDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileGozareshDTO save(FileGozareshDTO fileGozareshDTO) {
        log.debug("Request to save FileGozaresh : {}", fileGozareshDTO);
        FileGozaresh fileGozaresh = fileGozareshMapper.toEntity(fileGozareshDTO);
        fileGozaresh = fileGozareshRepository.save(fileGozaresh);
        return fileGozareshMapper.toDto(fileGozaresh);
    }

    /**
     * Get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileGozareshDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileGozareshes");
        return fileGozareshRepository.findAll(pageable)
            .map(fileGozareshMapper::toDto);
    }

    /**
     * Get one fileGozaresh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileGozareshDTO> findOne(Long id) {
        log.debug("Request to get FileGozaresh : {}", id);
        return fileGozareshRepository.findById(id)
            .map(fileGozareshMapper::toDto);
    }

    /**
     * Delete the fileGozaresh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileGozaresh : {}", id);
        fileGozareshRepository.deleteById(id);
    }
}
