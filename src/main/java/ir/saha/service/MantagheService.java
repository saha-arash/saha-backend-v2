package ir.saha.service;

import ir.saha.service.dto.MantagheDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Mantaghe}.
 */
public interface MantagheService {

    /**
     * Save a mantaghe.
     *
     * @param mantagheDTO the entity to save.
     * @return the persisted entity.
     */
    MantagheDTO save(MantagheDTO mantagheDTO);

    /**
     * Get all the mantaghes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MantagheDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mantaghe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MantagheDTO> findOne(Long id);

    /**
     * Delete the "id" mantaghe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
