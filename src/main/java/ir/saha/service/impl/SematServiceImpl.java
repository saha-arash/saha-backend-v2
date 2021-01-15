package ir.saha.service.impl;

import ir.saha.service.SematService;
import ir.saha.domain.Semat;
import ir.saha.repository.SematRepository;
import ir.saha.service.dto.SematDTO;
import ir.saha.service.mapper.SematMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Semat}.
 */
@Service
@Transactional
public class SematServiceImpl implements SematService {

    private final Logger log = LoggerFactory.getLogger(SematServiceImpl.class);

    private final SematRepository sematRepository;

    private final SematMapper sematMapper;

    public SematServiceImpl(SematRepository sematRepository, SematMapper sematMapper) {
        this.sematRepository = sematRepository;
        this.sematMapper = sematMapper;
    }

    /**
     * Save a semat.
     *
     * @param sematDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SematDTO save(SematDTO sematDTO) {
        log.debug("Request to save Semat : {}", sematDTO);
        Semat semat = sematMapper.toEntity(sematDTO);
        semat = sematRepository.save(semat);
        return sematMapper.toDto(semat);
    }

    /**
     * Get all the semats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SematDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Semats");
        return sematRepository.findAll(pageable)
            .map(sematMapper::toDto);
    }

    /**
     * Get one semat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SematDTO> findOne(Long id) {
        log.debug("Request to get Semat : {}", id);
        return sematRepository.findById(id)
            .map(sematMapper::toDto);
    }

    /**
     * Delete the semat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Semat : {}", id);
        sematRepository.deleteById(id);
    }
}
