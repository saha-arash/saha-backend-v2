package ir.saha.service;

import ir.saha.domain.Karbar;
import ir.saha.service.dto.KarbarDTO;

import ir.saha.service.dto.PayamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.Karbar}.
 */
public interface KarbarService {

    /**
     * Save a karbar.
     *
     * @param karbarDTO the entity to save.
     * @return the persisted entity.
     */
    KarbarDTO save(KarbarDTO karbarDTO);

    /**
     * Get all the karbars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KarbarDTO> findAll(Pageable pageable);

    /**
     * Get all the karbars with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<KarbarDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" karbar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KarbarDTO> findOne(Long id);

    /**
     * Delete the "id" karbar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<List<KarbarDTO>> findByIds(List<Long> ids);

    Optional<List<KarbarDTO>> findByExample(Karbar karbar);

    Optional<List<KarbarDTO>> search(String name);

    Page<PayamDTO> getPayamVoroodi(Pageable pageable);


    Page<PayamDTO> getPayamKhoorooji(Pageable pageable);
}
