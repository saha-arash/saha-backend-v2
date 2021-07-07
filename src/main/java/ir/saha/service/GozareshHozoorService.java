package ir.saha.service;

import ir.saha.service.dto.GozareshHozoorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.GozareshHozoor}.
 */
public interface GozareshHozoorService {

    /**
     * Save a gozareshHozoor.
     *
     * @param gozareshHozoorDTO the entity to save.
     * @return the persisted entity.
     */
    GozareshHozoorDTO save(GozareshHozoorDTO gozareshHozoorDTO);

    /**
     * Get all the gozareshHozoors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GozareshHozoorDTO> findAll(Pageable pageable);
    /**
     * Get all the GozareshHozoorDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<GozareshHozoorDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" gozareshHozoor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GozareshHozoorDTO> findOne(Long id);

    /**
     * Delete the "id" gozareshHozoor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
