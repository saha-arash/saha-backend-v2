package ir.saha.service;

import ir.saha.service.dto.KholaseGozareshDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.KholaseGozaresh}.
 */
public interface KholaseGozareshService {

    /**
     * Save a kholaseGozaresh.
     *
     * @param kholaseGozareshDTO the entity to save.
     * @return the persisted entity.
     */
    KholaseGozareshDTO save(KholaseGozareshDTO kholaseGozareshDTO);

    /**
     * Get all the kholaseGozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KholaseGozareshDTO> findAll(Pageable pageable);
    /**
     * Get all the KholaseGozareshDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<KholaseGozareshDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" kholaseGozaresh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KholaseGozareshDTO> findOne(Long id);

    /**
     * Delete the "id" kholaseGozaresh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
