package ir.saha.service;

import ir.saha.service.dto.FileGozareshDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.FileGozaresh}.
 */
public interface FileGozareshService {

    /**
     * Save a fileGozaresh.
     *
     * @param fileGozareshDTO the entity to save.
     * @return the persisted entity.
     */
    FileGozareshDTO save(FileGozareshDTO fileGozareshDTO);

    /**
     * Get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileGozareshDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileGozaresh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileGozareshDTO> findOne(Long id);

    /**
     * Delete the "id" fileGozaresh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
