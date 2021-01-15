package ir.saha.service;

import ir.saha.service.dto.FileBargeMamooriatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.saha.domain.FileBargeMamooriat}.
 */
public interface FileBargeMamooriatService {

    /**
     * Save a fileBargeMamooriat.
     *
     * @param fileBargeMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    FileBargeMamooriatDTO save(FileBargeMamooriatDTO fileBargeMamooriatDTO);

    /**
     * Get all the fileBargeMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileBargeMamooriatDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileBargeMamooriat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileBargeMamooriatDTO> findOne(Long id);

    /**
     * Delete the "id" fileBargeMamooriat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<FileBargeMamooriatDTO> fileBargeMamooriat(Long idbargeMamooriate, Pageable pageable);

    FileBargeMamooriatDTO save(MultipartFile multipartFile, FileBargeMamooriatDTO fileBargeMamooriatDTO);
}
