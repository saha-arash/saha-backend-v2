package ir.saha.service;

import ir.saha.service.dto.ShahrDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Shahr}.
 */
public interface ShahrService {

    /**
     * Save a shahr.
     *
     * @param shahrDTO the entity to save.
     * @return the persisted entity.
     */
    ShahrDTO save(ShahrDTO shahrDTO);

    /**
     * Get all the shahrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShahrDTO> findAll(Pageable pageable);

    /**
     * Get the "id" shahr.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShahrDTO> findOne(Long id);

    /**
     * Delete the "id" shahr.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ShahrDTO> search(String name);
}
