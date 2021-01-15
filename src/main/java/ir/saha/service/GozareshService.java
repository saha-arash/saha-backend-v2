package ir.saha.service;

import ir.saha.service.dto.GozareshDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Gozaresh}.
 */
public interface GozareshService {

    /**
     * Save a gozaresh.
     *
     * @param gozareshDTO the entity to save.
     * @return the persisted entity.
     */
    GozareshDTO save(GozareshDTO gozareshDTO);

    /**
     * Get all the gozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GozareshDTO> findAll(Pageable pageable);
    /**
     * Get all the GozareshDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<GozareshDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" gozaresh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GozareshDTO> findOne(Long id);

    /**
     * Delete the "id" gozaresh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
