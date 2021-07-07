package ir.saha.service;

import ir.saha.domain.Yegan;
import ir.saha.service.dto.FiltereYeganBarresiNashode;
import ir.saha.service.dto.YeganDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Yegan}.
 */
public interface YeganService {

    /**
     * Save a yegan.
     *
     * @param yeganDTO the entity to save.
     * @return the persisted entity.
     */
    YeganDTO save(YeganDTO yeganDTO);

    /**
     * Get all the yegans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<YeganDTO> findAll(Pageable pageable);
    List<Yegan> findAll();
    /**
     * Get all the YeganDTO where YeganCode is {@code null}.
     *
     * @return the list of entities.
     */
    List<YeganDTO> findAllWhereYeganCodeIsNull();

    /**
     * Get all the yegans with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<YeganDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" yegan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<YeganDTO> findOne(Long id);

    /**
     * Delete the "id" yegan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<List<YeganDTO>> findYeganBarresiNashode(FiltereYeganBarresiNashode filtereYeganBarresiNashode);

    Optional<List<YeganDTO>> search(String name);
}
