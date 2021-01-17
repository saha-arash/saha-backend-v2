package ir.saha.service.impl;

import ir.saha.service.RafeIradatService;
import ir.saha.domain.RafeIradat;
import ir.saha.repository.RafeIradatRepository;
import ir.saha.service.dto.RafeIradatDTO;
import ir.saha.service.mapper.RafeIradatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link RafeIradat}.
 */
@Service
@Transactional
public class RafeIradatServiceImpl implements RafeIradatService {

    private final Logger log = LoggerFactory.getLogger(RafeIradatServiceImpl.class);

    private final RafeIradatRepository rafeIradatRepository;

    private final RafeIradatMapper rafeIradatMapper;

    public RafeIradatServiceImpl(RafeIradatRepository rafeIradatRepository, RafeIradatMapper rafeIradatMapper) {
        this.rafeIradatRepository = rafeIradatRepository;
        this.rafeIradatMapper = rafeIradatMapper;
    }

    /**
     * Save a rafeIradat.
     *
     * @param rafeIradatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RafeIradatDTO save(RafeIradatDTO rafeIradatDTO) {
        log.debug("Request to save RafeIradat : {}", rafeIradatDTO);
        RafeIradat rafeIradat = rafeIradatMapper.toEntity(rafeIradatDTO);
        rafeIradat = rafeIradatRepository.save(rafeIradat);
        return rafeIradatMapper.toDto(rafeIradat);
    }

    /**
     * Get all the rafeIradats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RafeIradatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RafeIradats");
        return rafeIradatRepository.findAll(pageable)
            .map(rafeIradatMapper::toDto);
    }


    /**
     *  Get all the rafeIradats where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<RafeIradatDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all rafeIradats where HesabResi is null");
        return StreamSupport
            .stream(rafeIradatRepository.findAll().spliterator(), false)
            .filter(rafeIradat -> rafeIradat.getHesabResi() == null)
            .map(rafeIradatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rafeIradat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RafeIradatDTO> findOne(Long id) {
        log.debug("Request to get RafeIradat : {}", id);
        return rafeIradatRepository.findById(id)
            .map(rafeIradatMapper::toDto);
    }

    /**
     * Delete the rafeIradat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RafeIradat : {}", id);
        rafeIradatRepository.deleteById(id);
    }
}
