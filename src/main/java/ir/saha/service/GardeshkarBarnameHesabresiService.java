package ir.saha.service;

import ir.saha.service.dto.GardeshkarBarnameHesabresiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.GardeshkarBarnameHesabresi}.
 */
public interface GardeshkarBarnameHesabresiService {

    /**
     * Save a gardeshkarBarnameHesabresi.
     *
     * @param gardeshkarBarnameHesabresiDTO the entity to save.
     * @return the persisted entity.
     */
    GardeshkarBarnameHesabresiDTO save(GardeshkarBarnameHesabresiDTO gardeshkarBarnameHesabresiDTO);

    /**
     * Get all the gardeshkarBarnameHesabresis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GardeshkarBarnameHesabresiDTO> findAll(Pageable pageable);
    /**
     * Get all the GardeshkarBarnameHesabresiDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<GardeshkarBarnameHesabresiDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" gardeshkarBarnameHesabresi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GardeshkarBarnameHesabresiDTO> findOne(Long id);

    /**
     * Delete the "id" gardeshkarBarnameHesabresi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
