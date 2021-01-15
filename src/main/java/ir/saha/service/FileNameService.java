package ir.saha.service;

import ir.saha.service.dto.FileNameDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.FileName}.
 */
public interface FileNameService {

    /**
     * Save a fileName.
     *
     * @param fileNameDTO the entity to save.
     * @return the persisted entity.
     */
    FileNameDTO save(FileNameDTO fileNameDTO);

    /**
     * Get all the fileNames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileNameDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileName.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileNameDTO> findOne(Long id);

    /**
     * Delete the "id" fileName.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
