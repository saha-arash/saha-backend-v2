package ir.saha.service;

import ir.saha.service.dto.MohasebeHazineMamooriatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.MohasebeHazineMamooriat}.
 */
public interface MohasebeHazineMamooriatService {

    /**
     * Save a mohasebeHazineMamooriat.
     *
     * @param mohasebeHazineMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    MohasebeHazineMamooriatDTO save(MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO);

    /**
     * Get all the mohasebeHazineMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MohasebeHazineMamooriatDTO> findAll(Pageable pageable);
    /**
     * Get all the MohasebeHazineMamooriatDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<MohasebeHazineMamooriatDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" mohasebeHazineMamooriat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MohasebeHazineMamooriatDTO> findOne(Long id);

    /**
     * Delete the "id" mohasebeHazineMamooriat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
