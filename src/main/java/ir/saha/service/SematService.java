package ir.saha.service;

import ir.saha.service.dto.SematDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Semat}.
 */
public interface SematService {

    /**
     * Save a semat.
     *
     * @param sematDTO the entity to save.
     * @return the persisted entity.
     */
    SematDTO save(SematDTO sematDTO);

    /**
     * Get all the semats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SematDTO> findAll(Pageable pageable);

    /**
     * Get the "id" semat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SematDTO> findOne(Long id);

    /**
     * Delete the "id" semat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
