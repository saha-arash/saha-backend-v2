package ir.saha.service;

import ir.saha.service.dto.DoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Dore}.
 */
public interface DoreService {

    /**
     * Save a dore.
     *
     * @param doreDTO the entity to save.
     * @return the persisted entity.
     */
    DoreDTO save(DoreDTO doreDTO);

    /**
     * Get all the dores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DoreDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DoreDTO> findOne(Long id);

    /**
     * Delete the "id" dore.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
