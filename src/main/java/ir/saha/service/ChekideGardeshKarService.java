package ir.saha.service;

import ir.saha.service.dto.ChekideGardeshKarDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.ChekideGardeshKar}.
 */
public interface ChekideGardeshKarService {

    /**
     * Save a chekideGardeshKar.
     *
     * @param chekideGardeshKarDTO the entity to save.
     * @return the persisted entity.
     */
    ChekideGardeshKarDTO save(ChekideGardeshKarDTO chekideGardeshKarDTO);

    /**
     * Get all the chekideGardeshKars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChekideGardeshKarDTO> findAll(Pageable pageable);
    /**
     * Get all the ChekideGardeshKarDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<ChekideGardeshKarDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" chekideGardeshKar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChekideGardeshKarDTO> findOne(Long id);

    /**
     * Delete the "id" chekideGardeshKar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
