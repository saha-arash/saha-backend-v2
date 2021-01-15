package ir.saha.service;

import ir.saha.service.dto.PayamDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Payam}.
 */
public interface PayamService {

    /**
     * Save a payam.
     *
     * @param payamDTO the entity to save.
     * @return the persisted entity.
     */
    PayamDTO save(PayamDTO payamDTO);

    /**
     * Get all the payams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayamDTO> findAll(Pageable pageable);

    /**
     * Get the "id" payam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayamDTO> findOne(Long id);

    /**
     * Delete the "id" payam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
