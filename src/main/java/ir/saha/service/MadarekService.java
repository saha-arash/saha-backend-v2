package ir.saha.service;

import ir.saha.service.dto.MadarekDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Madarek}.
 */
public interface MadarekService {

    /**
     * Save a madarek.
     *
     * @param madarekDTO the entity to save.
     * @return the persisted entity.
     */
    MadarekDTO save(MadarekDTO madarekDTO);

    /**
     * Get all the madareks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MadarekDTO> findAll(Pageable pageable);
    /**
     * Get all the MadarekDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<MadarekDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" madarek.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MadarekDTO> findOne(Long id);

    /**
     * Delete the "id" madarek.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
