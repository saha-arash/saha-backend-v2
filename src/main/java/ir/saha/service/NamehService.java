package ir.saha.service;

import ir.saha.service.dto.NamehDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Nameh}.
 */
public interface NamehService {

    /**
     * Save a nameh.
     *
     * @param namehDTO the entity to save.
     * @return the persisted entity.
     */
    NamehDTO save(NamehDTO namehDTO);

    /**
     * Get all the namehs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NamehDTO> findAll(Pageable pageable);
    /**
     * Get all the NamehDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<NamehDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" nameh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NamehDTO> findOne(Long id);

    /**
     * Delete the "id" nameh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
