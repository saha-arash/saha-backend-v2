package ir.saha.service;

import ir.saha.service.dto.HesabResiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.HesabResi}.
 */
public interface HesabResiService {

    /**
     * Save a hesabResi.
     *
     * @param hesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    HesabResiDTO save(HesabResiDTO hesabResiDTO);

    HesabResiDTO update(HesabResiDTO hesabResiDTO);

    /**
     * Get all the hesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HesabResiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" hesabResi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HesabResiDTO> findOne(Long id);

    /**
     * Delete the "id" hesabResi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
