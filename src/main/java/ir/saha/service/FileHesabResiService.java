package ir.saha.service;

import ir.saha.service.dto.FileHesabResiDTO;

import ir.saha.service.dto.FileHesabResirequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.FileHesabResi}.
 */
public interface FileHesabResiService {

    /**
     * Save a fileHesabResi.
     *
     * @param fileHesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    FileHesabResiDTO save(FileHesabResiDTO fileHesabResiDTO);

    /**
     * Get all the fileHesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileHesabResiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileHesabResi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileHesabResiDTO> findOne(Long id);

    /**
     * Delete the "id" fileHesabResi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<FileHesabResiDTO> getFiles(Pageable pageable, FileHesabResirequest fileHesabResirequest);
}
