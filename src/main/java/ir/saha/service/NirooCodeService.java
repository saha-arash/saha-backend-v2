package ir.saha.service;

import ir.saha.service.dto.NirooCodeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.NirooCode}.
 */
public interface NirooCodeService {

    /**
     * Save a nirooCode.
     *
     * @param nirooCodeDTO the entity to save.
     * @return the persisted entity.
     */
    NirooCodeDTO save(NirooCodeDTO nirooCodeDTO);

    /**
     * Get all the nirooCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NirooCodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nirooCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NirooCodeDTO> findOne(Long id);

    /**
     * Delete the "id" nirooCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
