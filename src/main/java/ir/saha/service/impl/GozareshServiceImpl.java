package ir.saha.service.impl;

import ir.saha.service.GozareshService;
import ir.saha.domain.Gozaresh;
import ir.saha.repository.GozareshRepository;
import ir.saha.service.dto.GozareshDTO;
import ir.saha.service.mapper.GozareshMapper;
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
 * Service Implementation for managing {@link Gozaresh}.
 */
@Service
@Transactional
public class GozareshServiceImpl implements GozareshService {

    private final Logger log = LoggerFactory.getLogger(GozareshServiceImpl.class);

    private final GozareshRepository gozareshRepository;

    private final GozareshMapper gozareshMapper;

    public GozareshServiceImpl(GozareshRepository gozareshRepository, GozareshMapper gozareshMapper) {
        this.gozareshRepository = gozareshRepository;
        this.gozareshMapper = gozareshMapper;
    }

    /**
     * Save a gozaresh.
     *
     * @param gozareshDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GozareshDTO save(GozareshDTO gozareshDTO) {
        log.debug("Request to save Gozaresh : {}", gozareshDTO);
        Gozaresh gozaresh = gozareshMapper.toEntity(gozareshDTO);
        gozaresh = gozareshRepository.save(gozaresh);
        return gozareshMapper.toDto(gozaresh);
    }

    /**
     * Get all the gozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GozareshDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gozareshes");
        return gozareshRepository.findAll(pageable)
            .map(gozareshMapper::toDto);
    }


    /**
     *  Get all the gozareshes where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<GozareshDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all gozareshes where HesabResi is null");
        return StreamSupport
            .stream(gozareshRepository.findAll().spliterator(), false)
            .filter(gozaresh -> gozaresh.getHesabResi() == null)
            .map(gozareshMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gozaresh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GozareshDTO> findOne(Long id) {
        log.debug("Request to get Gozaresh : {}", id);
        return gozareshRepository.findById(id)
            .map(gozareshMapper::toDto);
    }

    /**
     * Delete the gozaresh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gozaresh : {}", id);
        gozareshRepository.deleteById(id);
    }
}
