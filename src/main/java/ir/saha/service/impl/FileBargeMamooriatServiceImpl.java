package ir.saha.service.impl;

import ir.saha.domain.BargeMamooriat;
import ir.saha.repository.BargeMamooriatRepository;
import ir.saha.service.FileBargeMamooriatService;
import ir.saha.domain.FileBargeMamooriat;
import ir.saha.repository.FileBargeMamooriatRepository;
import ir.saha.service.dto.FileBargeMamooriatDTO;
import ir.saha.service.mapper.FileBargeMamooriatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FileBargeMamooriat}.
 */
@Service
@Transactional
public class FileBargeMamooriatServiceImpl implements FileBargeMamooriatService {

    private final Logger log = LoggerFactory.getLogger(FileBargeMamooriatServiceImpl.class);

    private final FileBargeMamooriatRepository fileBargeMamooriatRepository;

    private final FileBargeMamooriatMapper fileBargeMamooriatMapper;

    private final BargeMamooriatRepository bargeMamooriatRepository;

    public FileBargeMamooriatServiceImpl(FileBargeMamooriatRepository fileBargeMamooriatRepository, FileBargeMamooriatMapper fileBargeMamooriatMapper, BargeMamooriatRepository bargeMamooriatRepository) {
        this.fileBargeMamooriatRepository = fileBargeMamooriatRepository;
        this.fileBargeMamooriatMapper = fileBargeMamooriatMapper;
        this.bargeMamooriatRepository = bargeMamooriatRepository;
    }

    /**
     * Save a fileBargeMamooriat.
     *
     * @param fileBargeMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileBargeMamooriatDTO save(FileBargeMamooriatDTO fileBargeMamooriatDTO) {
        log.debug("Request to save FileBargeMamooriat : {}", fileBargeMamooriatDTO);
        BargeMamooriat bargeMamooriat = bargeMamooriatRepository.findById(fileBargeMamooriatDTO.getBargeMamooriatId()).get();
        FileBargeMamooriat fileBargeMamooriat = fileBargeMamooriatMapper.toEntity(fileBargeMamooriatDTO);
        bargeMamooriat.getMadareks().add(fileBargeMamooriat);
        bargeMamooriatRepository.save(bargeMamooriat);
        fileBargeMamooriat.setBargeMamooriat(bargeMamooriat);
        fileBargeMamooriat = fileBargeMamooriatRepository.save(fileBargeMamooriat);
        return fileBargeMamooriatMapper.toDto(fileBargeMamooriat);
    }

    /**
     * Get all the fileBargeMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileBargeMamooriatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileBargeMamooriats");
        return fileBargeMamooriatRepository.findAll(pageable)
            .map(fileBargeMamooriatMapper::toDto);
    }

    /**
     * Get one fileBargeMamooriat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FileBargeMamooriatDTO> findOne(Long id) {
        log.debug("Request to get FileBargeMamooriat : {}", id);
        return fileBargeMamooriatRepository.findById(id)
            .map(fileBargeMamooriatMapper::toDto);
    }

    /**
     * Delete the fileBargeMamooriat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileBargeMamooriat : {}", id);
        fileBargeMamooriatRepository.deleteById(id);
    }

    @Override
    public Page<FileBargeMamooriatDTO> fileBargeMamooriat(Long idbargeMamooriate, Pageable pageable) {
        return fileBargeMamooriatRepository.findAllByBargeMamooriat(bargeMamooriatRepository.findById(idbargeMamooriate).get(),pageable)
            .map(fileBargeMamooriatMapper::toDto);

    }

    @Override
    public FileBargeMamooriatDTO save(MultipartFile multipartFile, FileBargeMamooriatDTO fileBargeMamooriatDTO) {
        log.debug("Request to save FileBargeMamooriat : {}", fileBargeMamooriatDTO);
        BargeMamooriat bargeMamooriat = bargeMamooriatRepository.findById(fileBargeMamooriatDTO.getBargeMamooriatId()).get();
        FileBargeMamooriat fileBargeMamooriat = fileBargeMamooriatMapper.toEntity(fileBargeMamooriatDTO);
        try {
            fileBargeMamooriat.setMadarek(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileBargeMamooriat.setFileName(multipartFile.getOriginalFilename());
        bargeMamooriat.getMadareks().add(fileBargeMamooriat);
        bargeMamooriatRepository.save(bargeMamooriat);
        fileBargeMamooriat.setBargeMamooriat(bargeMamooriat);
        fileBargeMamooriat = fileBargeMamooriatRepository.save(fileBargeMamooriat);
        return fileBargeMamooriatMapper.toDto(fileBargeMamooriat);
    }
}
