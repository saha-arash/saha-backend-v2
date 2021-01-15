package ir.saha.service.impl;

import ir.saha.service.FileNameService;
import ir.saha.domain.FileName;
import ir.saha.repository.FileNameRepository;
import ir.saha.service.dto.FileNameDTO;
import ir.saha.service.mapper.FileNameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FileName}.
 */
@Service
@Transactional
public class FileNameServiceImpl implements FileNameService {

    private final Logger log = LoggerFactory.getLogger(FileNameServiceImpl.class);

    private final FileNameRepository fileNameRepository;

    private final FileNameMapper fileNameMapper;

    public FileNameServiceImpl(FileNameRepository fileNameRepository, FileNameMapper fileNameMapper) {
        this.fileNameRepository = fileNameRepository;
        this.fileNameMapper = fileNameMapper;
    }

    /**
     * Save a fileName.
     *
     * @param fileNameDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileNameDTO save(FileNameDTO fileNameDTO) {
        log.debug("Request to save FileName : {}", fileNameDTO);
        FileName fileName = fileNameMapper.toEntity(fileNameDTO);
        fileName = fileNameRepository.save(fileName);
        return fileNameMapper.toDto(fileName);
    }

    /**
     * Get all the fileNames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileNameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileNames");
        return fileNameRepository.findAll(pageable)
            .map(fileNameMapper::toDto);
    }

    /**
     * Get one fileName by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileNameDTO> findOne(Long id) {
        log.debug("Request to get FileName : {}", id);
        return fileNameRepository.findById(id)
            .map(fileNameMapper::toDto);
    }

    /**
     * Delete the fileName by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileName : {}", id);
        fileNameRepository.deleteById(id);
    }
}
