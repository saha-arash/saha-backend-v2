package ir.saha.service;

import ir.saha.service.dto.GardeshKarDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.GardeshKar}.
 */
public interface GardeshKarService {

    /**
     * Save a gardeshKar.
     *
     * @param gardeshKarDTO the entity to save.
     * @return the persisted entity.
     */
    GardeshKarDTO save(GardeshKarDTO gardeshKarDTO);

    /**
     * Get all the gardeshKars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GardeshKarDTO> findAll(Pageable pageable);
    /**
     * Get all the GardeshKarDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<GardeshKarDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" gardeshKar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GardeshKarDTO> findOne(Long id);

    /**
     * Delete the "id" gardeshKar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
