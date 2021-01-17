package ir.saha.service;

import ir.saha.service.dto.BilanSalGhablDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.BilanSalGhabl}.
 */
public interface BilanSalGhablService {

    /**
     * Save a bilanSalGhabl.
     *
     * @param bilanSalGhablDTO the entity to save.
     * @return the persisted entity.
     */
    BilanSalGhablDTO save(BilanSalGhablDTO bilanSalGhablDTO);

    /**
     * Get all the bilanSalGhabls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BilanSalGhablDTO> findAll(Pageable pageable);
    /**
     * Get all the BilanSalGhablDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<BilanSalGhablDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" bilanSalGhabl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BilanSalGhablDTO> findOne(Long id);

    /**
     * Delete the "id" bilanSalGhabl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
