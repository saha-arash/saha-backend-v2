package ir.saha.service;

import ir.saha.service.dto.BankEtelaatiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.BankEtelaati}.
 */
public interface BankEtelaatiService {

    /**
     * Save a bankEtelaati.
     *
     * @param bankEtelaatiDTO the entity to save.
     * @return the persisted entity.
     */
    BankEtelaatiDTO save(BankEtelaatiDTO bankEtelaatiDTO);

    /**
     * Get all the bankEtelaatis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BankEtelaatiDTO> findAll(Pageable pageable);
    /**
     * Get all the BankEtelaatiDTO where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    List<BankEtelaatiDTO> findAllWhereHesabResiIsNull();

    /**
     * Get the "id" bankEtelaati.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankEtelaatiDTO> findOne(Long id);

    /**
     * Delete the "id" bankEtelaati.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
