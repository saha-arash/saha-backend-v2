package ir.saha.service;

import ir.saha.service.dto.DarajeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Daraje}.
 */
public interface DarajeService {

    /**
     * Save a daraje.
     *
     * @param darajeDTO the entity to save.
     * @return the persisted entity.
     */
    DarajeDTO save(DarajeDTO darajeDTO);

    /**
     * Get all the darajes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DarajeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" daraje.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DarajeDTO> findOne(Long id);

    /**
     * Delete the "id" daraje.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
