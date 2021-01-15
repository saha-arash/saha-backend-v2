package ir.saha.service.impl;

import ir.saha.service.DarajeService;
import ir.saha.domain.Daraje;
import ir.saha.repository.DarajeRepository;
import ir.saha.service.dto.DarajeDTO;
import ir.saha.service.mapper.DarajeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Daraje}.
 */
@Service
@Transactional
public class DarajeServiceImpl implements DarajeService {

    private final Logger log = LoggerFactory.getLogger(DarajeServiceImpl.class);

    private final DarajeRepository darajeRepository;

    private final DarajeMapper darajeMapper;

    public DarajeServiceImpl(DarajeRepository darajeRepository, DarajeMapper darajeMapper) {
        this.darajeRepository = darajeRepository;
        this.darajeMapper = darajeMapper;
    }

    /**
     * Save a daraje.
     *
     * @param darajeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DarajeDTO save(DarajeDTO darajeDTO) {
        log.debug("Request to save Daraje : {}", darajeDTO);
        Daraje daraje = darajeMapper.toEntity(darajeDTO);
        daraje = darajeRepository.save(daraje);
        return darajeMapper.toDto(daraje);
    }

    /**
     * Get all the darajes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DarajeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Darajes");
        return darajeRepository.findAll(pageable)
            .map(darajeMapper::toDto);
    }

    /**
     * Get one daraje by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DarajeDTO> findOne(Long id) {
        log.debug("Request to get Daraje : {}", id);
        return darajeRepository.findById(id)
            .map(darajeMapper::toDto);
    }

    /**
     * Delete the daraje by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Daraje : {}", id);
        darajeRepository.deleteById(id);
    }
}
