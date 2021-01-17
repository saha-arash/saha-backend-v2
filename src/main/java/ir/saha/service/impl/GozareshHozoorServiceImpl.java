package ir.saha.service.impl;

import ir.saha.service.GozareshHozoorService;
import ir.saha.domain.GozareshHozoor;
import ir.saha.repository.GozareshHozoorRepository;
import ir.saha.service.dto.GozareshHozoorDTO;
import ir.saha.service.mapper.GozareshHozoorMapper;
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
 * Service Implementation for managing {@link GozareshHozoor}.
 */
@Service
@Transactional
public class GozareshHozoorServiceImpl implements GozareshHozoorService {

    private final Logger log = LoggerFactory.getLogger(GozareshHozoorServiceImpl.class);

    private final GozareshHozoorRepository gozareshHozoorRepository;

    private final GozareshHozoorMapper gozareshHozoorMapper;

    public GozareshHozoorServiceImpl(GozareshHozoorRepository gozareshHozoorRepository, GozareshHozoorMapper gozareshHozoorMapper) {
        this.gozareshHozoorRepository = gozareshHozoorRepository;
        this.gozareshHozoorMapper = gozareshHozoorMapper;
    }

    /**
     * Save a gozareshHozoor.
     *
     * @param gozareshHozoorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GozareshHozoorDTO save(GozareshHozoorDTO gozareshHozoorDTO) {
        log.debug("Request to save GozareshHozoor : {}", gozareshHozoorDTO);
        GozareshHozoor gozareshHozoor = gozareshHozoorMapper.toEntity(gozareshHozoorDTO);
        gozareshHozoor = gozareshHozoorRepository.save(gozareshHozoor);
        return gozareshHozoorMapper.toDto(gozareshHozoor);
    }

    /**
     * Get all the gozareshHozoors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GozareshHozoorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GozareshHozoors");
        return gozareshHozoorRepository.findAll(pageable)
            .map(gozareshHozoorMapper::toDto);
    }


    /**
     *  Get all the gozareshHozoors where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<GozareshHozoorDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all gozareshHozoors where HesabResi is null");
        return StreamSupport
            .stream(gozareshHozoorRepository.findAll().spliterator(), false)
            .filter(gozareshHozoor -> gozareshHozoor.getHesabResi() == null)
            .map(gozareshHozoorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gozareshHozoor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GozareshHozoorDTO> findOne(Long id) {
        log.debug("Request to get GozareshHozoor : {}", id);
        return gozareshHozoorRepository.findById(id)
            .map(gozareshHozoorMapper::toDto);
    }

    /**
     * Delete the gozareshHozoor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GozareshHozoor : {}", id);
        gozareshHozoorRepository.deleteById(id);
    }
}
