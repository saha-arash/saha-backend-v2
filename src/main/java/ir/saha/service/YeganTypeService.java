package ir.saha.service;

import ir.saha.service.dto.YeganTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.YeganType}.
 */
public interface YeganTypeService {

    /**
     * Save a yeganType.
     *
     * @param yeganTypeDTO the entity to save.
     * @return the persisted entity.
     */
    YeganTypeDTO save(YeganTypeDTO yeganTypeDTO);

    /**
     * Get all the yeganTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<YeganTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" yeganType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<YeganTypeDTO> findOne(Long id);

    /**
     * Delete the "id" yeganType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
