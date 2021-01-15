package ir.saha.service.impl;

import ir.saha.service.DoreService;
import ir.saha.domain.Dore;
import ir.saha.repository.DoreRepository;
import ir.saha.service.dto.DoreDTO;
import ir.saha.service.mapper.DoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dore}.
 */
@Service
@Transactional
public class DoreServiceImpl implements DoreService {

    private final Logger log = LoggerFactory.getLogger(DoreServiceImpl.class);

    private final DoreRepository doreRepository;

    private final DoreMapper doreMapper;

    public DoreServiceImpl(DoreRepository doreRepository, DoreMapper doreMapper) {
        this.doreRepository = doreRepository;
        this.doreMapper = doreMapper;
    }

    /**
     * Save a dore.
     *
     * @param doreDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DoreDTO save(DoreDTO doreDTO) {
        log.debug("Request to save Dore : {}", doreDTO);
        Dore dore = doreMapper.toEntity(doreDTO);
        dore = doreRepository.save(dore);
        return doreMapper.toDto(dore);
    }

    /**
     * Get all the dores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dores");
        return doreRepository.findAll(pageable)
            .map(doreMapper::toDto);
    }

    /**
     * Get one dore by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoreDTO> findOne(Long id) {
        log.debug("Request to get Dore : {}", id);
        return doreRepository.findById(id)
            .map(doreMapper::toDto);
    }

    /**
     * Delete the dore by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dore : {}", id);
        doreRepository.deleteById(id);
    }
}
