package ir.saha.service.impl;

import ir.saha.service.MantagheService;
import ir.saha.domain.Mantaghe;
import ir.saha.repository.MantagheRepository;
import ir.saha.service.dto.MantagheDTO;
import ir.saha.service.mapper.MantagheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Mantaghe}.
 */
@Service
@Transactional
public class MantagheServiceImpl implements MantagheService {

    private final Logger log = LoggerFactory.getLogger(MantagheServiceImpl.class);

    private final MantagheRepository mantagheRepository;

    private final MantagheMapper mantagheMapper;

    public MantagheServiceImpl(MantagheRepository mantagheRepository, MantagheMapper mantagheMapper) {
        this.mantagheRepository = mantagheRepository;
        this.mantagheMapper = mantagheMapper;
    }

    /**
     * Save a mantaghe.
     *
     * @param mantagheDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MantagheDTO save(MantagheDTO mantagheDTO) {
        log.debug("Request to save Mantaghe : {}", mantagheDTO);
        Mantaghe mantaghe = mantagheMapper.toEntity(mantagheDTO);
        mantaghe = mantagheRepository.save(mantaghe);
        return mantagheMapper.toDto(mantaghe);
    }

    /**
     * Get all the mantaghes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MantagheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Mantaghes");
        return mantagheRepository.findAll(pageable)
            .map(mantagheMapper::toDto);
    }

    /**
     * Get one mantaghe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MantagheDTO> findOne(Long id) {
        log.debug("Request to get Mantaghe : {}", id);
        return mantagheRepository.findById(id)
            .map(mantagheMapper::toDto);
    }

    /**
     * Delete the mantaghe by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mantaghe : {}", id);
        mantagheRepository.deleteById(id);
    }
}
