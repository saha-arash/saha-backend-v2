package ir.saha.service;

import ir.saha.service.dto.RafeIradatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.RafeIradat}.
 */
public interface RafeIradatService {

    /**
     * Save a rafeIradat.
     *
     * @param rafeIradatDTO the entity to save.
     * @return the persisted entity.
     */
    RafeIradatDTO save(RafeIradatDTO rafeIradatDTO);

    /**
     * Get all the rafeIradats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RafeIradatDTO> findAll(Pageable pageable);
    /**
     * Get all the RafeIradatDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<RafeIradatDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" rafeIradat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RafeIradatDTO> findOne(Long id);

    /**
     * Delete the "id" rafeIradat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
