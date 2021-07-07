package ir.saha.service.impl;

import ir.saha.service.KholaseGozareshService;
import ir.saha.domain.KholaseGozaresh;
import ir.saha.repository.KholaseGozareshRepository;
import ir.saha.service.dto.KholaseGozareshDTO;
import ir.saha.service.mapper.KholaseGozareshMapper;
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
 * Service Implementation for managing {@link KholaseGozaresh}.
 */
@Service
@Transactional
public class KholaseGozareshServiceImpl implements KholaseGozareshService {

    private final Logger log = LoggerFactory.getLogger(KholaseGozareshServiceImpl.class);

    private final KholaseGozareshRepository kholaseGozareshRepository;

    private final KholaseGozareshMapper kholaseGozareshMapper;

    public KholaseGozareshServiceImpl(KholaseGozareshRepository kholaseGozareshRepository, KholaseGozareshMapper kholaseGozareshMapper) {
        this.kholaseGozareshRepository = kholaseGozareshRepository;
        this.kholaseGozareshMapper = kholaseGozareshMapper;
    }

    /**
     * Save a kholaseGozaresh.
     *
     * @param kholaseGozareshDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public KholaseGozareshDTO save(KholaseGozareshDTO kholaseGozareshDTO) {
        log.debug("Request to save KholaseGozaresh : {}", kholaseGozareshDTO);
        KholaseGozaresh kholaseGozaresh = kholaseGozareshMapper.toEntity(kholaseGozareshDTO);
        kholaseGozaresh = kholaseGozareshRepository.save(kholaseGozaresh);
        return kholaseGozareshMapper.toDto(kholaseGozaresh);
    }

    /**
     * Get all the kholaseGozareshes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KholaseGozareshDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KholaseGozareshes");
        return kholaseGozareshRepository.findAll(pageable)
            .map(kholaseGozareshMapper::toDto);
    }


    /**
     *  Get all the kholaseGozareshes where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<KholaseGozareshDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all kholaseGozareshes where HesabResi is null");
        return StreamSupport
            .stream(kholaseGozareshRepository.findAll().spliterator(), false)
            .filter(kholaseGozaresh -> kholaseGozaresh.getHesabResi() == null)
            .map(kholaseGozareshMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one kholaseGozaresh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KholaseGozareshDTO> findOne(Long id) {
        log.debug("Request to get KholaseGozaresh : {}", id);
        return kholaseGozareshRepository.findById(id)
            .map(kholaseGozareshMapper::toDto);
    }

    /**
     * Delete the kholaseGozaresh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KholaseGozaresh : {}", id);
        kholaseGozareshRepository.deleteById(id);
    }
}
