package ir.saha.service;

import ir.saha.service.dto.BarnameHesabResiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.BarnameHesabResi}.
 */
public interface BarnameHesabResiService {

    /**
     * Save a barnameHesabResi.
     *
     * @param barnameHesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    BarnameHesabResiDTO save(BarnameHesabResiDTO barnameHesabResiDTO);

    /**
     * Get all the barnameHesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BarnameHesabResiDTO> findAll(Pageable pageable);
    /**
     * Get all the BarnameHesabResiDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<BarnameHesabResiDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" barnameHesabResi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BarnameHesabResiDTO> findOne(Long id);

    /**
     * Delete the "id" barnameHesabResi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
