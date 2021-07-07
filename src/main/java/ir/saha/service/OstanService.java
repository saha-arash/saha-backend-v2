package ir.saha.service;

import ir.saha.service.dto.OstanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Ostan}.
 */
public interface OstanService {

    /**
     * Save a ostan.
     *
     * @param ostanDTO the entity to save.
     * @return the persisted entity.
     */
    OstanDTO save(OstanDTO ostanDTO);

    /**
     * Get all the ostans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OstanDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ostan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OstanDTO> findOne(Long id);

    /**
     * Delete the "id" ostan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<OstanDTO> search(String name, Pageable pageable);
}
