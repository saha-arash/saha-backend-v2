package ir.saha.service;

import ir.saha.service.dto.DastoorAmalEjraEDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.DastoorAmalEjraE}.
 */
public interface DastoorAmalEjraEService {

    /**
     * Save a dastoorAmalEjraE.
     *
     * @param dastoorAmalEjraEDTO the entity to save.
     * @return the persisted entity.
     */
    DastoorAmalEjraEDTO save(DastoorAmalEjraEDTO dastoorAmalEjraEDTO);

    /**
     * Get all the dastoorAmalEjraES.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DastoorAmalEjraEDTO> findAll(Pageable pageable);
    /**
     * Get all the DastoorAmalEjraEDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<DastoorAmalEjraEDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" dastoorAmalEjraE.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DastoorAmalEjraEDTO> findOne(Long id);

    /**
     * Delete the "id" dastoorAmalEjraE.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
