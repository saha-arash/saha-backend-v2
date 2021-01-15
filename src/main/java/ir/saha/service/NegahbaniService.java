package ir.saha.service;

import ir.saha.service.dto.NegahbaniDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Negahbani}.
 */
public interface NegahbaniService {

    /**
     * Save a negahbani.
     *
     * @param negahbaniDTO the entity to save.
     * @return the persisted entity.
     */
    NegahbaniDTO save(NegahbaniDTO negahbaniDTO);

    /**
     * Get all the negahbanis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NegahbaniDTO> findAll(Pageable pageable);

    /**
     * Get the "id" negahbani.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NegahbaniDTO> findOne(Long id);

    /**
     * Delete the "id" negahbani.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
