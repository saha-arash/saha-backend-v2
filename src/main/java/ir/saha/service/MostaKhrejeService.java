package ir.saha.service;

import ir.saha.service.dto.MostaKhrejeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.MostaKhreje}.
 */
public interface MostaKhrejeService {

    /**
     * Save a mostaKhreje.
     *
     * @param mostaKhrejeDTO the entity to save.
     * @return the persisted entity.
     */
    MostaKhrejeDTO save(MostaKhrejeDTO mostaKhrejeDTO);

    /**
     * Get all the mostaKhrejes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MostaKhrejeDTO> findAll(Pageable pageable);
    /**
     * Get all the MostaKhrejeDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<MostaKhrejeDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" mostaKhreje.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MostaKhrejeDTO> findOne(Long id);

    /**
     * Delete the "id" mostaKhreje.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
