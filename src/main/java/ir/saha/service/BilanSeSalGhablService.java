package ir.saha.service;

import ir.saha.service.dto.BilanSeSalGhablDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.BilanSeSalGhabl}.
 */
public interface BilanSeSalGhablService {

    /**
     * Save a bilanSeSalGhabl.
     *
     * @param bilanSeSalGhablDTO the entity to save.
     * @return the persisted entity.
     */
    BilanSeSalGhablDTO save(BilanSeSalGhablDTO bilanSeSalGhablDTO);

    /**
     * Get all the bilanSeSalGhabls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BilanSeSalGhablDTO> findAll(Pageable pageable);
    /**
     * Get all the BilanSeSalGhablDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<BilanSeSalGhablDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" bilanSeSalGhabl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BilanSeSalGhablDTO> findOne(Long id);

    /**
     * Delete the "id" bilanSeSalGhabl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
