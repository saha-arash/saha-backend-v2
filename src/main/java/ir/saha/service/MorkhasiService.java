package ir.saha.service;

import ir.saha.service.dto.MorkhasiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Morkhasi}.
 */
public interface MorkhasiService {

    /**
     * Save a morkhasi.
     *
     * @param morkhasiDTO the entity to save.
     * @return the persisted entity.
     */
    MorkhasiDTO save(MorkhasiDTO morkhasiDTO);

    /**
     * Get all the morkhasis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MorkhasiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" morkhasi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MorkhasiDTO> findOne(Long id);

    /**
     * Delete the "id" morkhasi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
