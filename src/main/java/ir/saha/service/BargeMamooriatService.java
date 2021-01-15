package ir.saha.service;

import ir.saha.service.dto.BargeMamooriatDTO;

import ir.saha.service.dto.FilterBargeMamooriat;
import ir.saha.service.dto.TamamBargemamooriatHa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.BargeMamooriat}.
 */
public interface BargeMamooriatService {

    /**
     * Save a bargeMamooriat.
     *
     * @param bargeMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    BargeMamooriatDTO save(BargeMamooriatDTO bargeMamooriatDTO);

    /**
     * Get all the bargeMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BargeMamooriatDTO> findAll(Pageable pageable);
    List<BargeMamooriatDTO> findBySal(Integer saleMamooriat);

    /**
     * Get the "id" bargeMamooriat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BargeMamooriatDTO> findOne(Long id);

    /**
     * Delete the "id" bargeMamooriat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    TamamBargemamooriatHa getCurrentUserMamooriat(FilterBargeMamooriat bargeMamooriat, Pageable pageable);

    TamamBargemamooriatHa getUserMamooriat(FilterBargeMamooriat bargeMamooriat, Long userId, Pageable pageable);

    List<BargeMamooriatDTO> getCurrentUserMamooriat(FilterBargeMamooriat bargeMamooriat);

    List<BargeMamooriatDTO> getUserMamooriat(FilterBargeMamooriat bargeMamooriat, Long userId);
}
