package ir.saha.service;

import ir.saha.service.dto.YeganCodeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.YeganCode}.
 */
public interface YeganCodeService {

    /**
     * Save a yeganCode.
     *
     * @param yeganCodeDTO the entity to save.
     * @return the persisted entity.
     */
    YeganCodeDTO save(YeganCodeDTO yeganCodeDTO);

    /**
     * Get all the yeganCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<YeganCodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" yeganCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<YeganCodeDTO> findOne(Long id);

    /**
     * Delete the "id" yeganCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
