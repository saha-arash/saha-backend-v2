package ir.saha.service.impl;

import ir.saha.service.GardeshkarBarnameHesabresiService;
import ir.saha.domain.GardeshkarBarnameHesabresi;
import ir.saha.repository.GardeshkarBarnameHesabresiRepository;
import ir.saha.service.dto.GardeshkarBarnameHesabresiDTO;
import ir.saha.service.mapper.GardeshkarBarnameHesabresiMapper;
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
 * Service Implementation for managing {@link GardeshkarBarnameHesabresi}.
 */
@Service
@Transactional
public class GardeshkarBarnameHesabresiServiceImpl implements GardeshkarBarnameHesabresiService {

    private final Logger log = LoggerFactory.getLogger(GardeshkarBarnameHesabresiServiceImpl.class);

    private final GardeshkarBarnameHesabresiRepository gardeshkarBarnameHesabresiRepository;

    private final GardeshkarBarnameHesabresiMapper gardeshkarBarnameHesabresiMapper;

    public GardeshkarBarnameHesabresiServiceImpl(GardeshkarBarnameHesabresiRepository gardeshkarBarnameHesabresiRepository, GardeshkarBarnameHesabresiMapper gardeshkarBarnameHesabresiMapper) {
        this.gardeshkarBarnameHesabresiRepository = gardeshkarBarnameHesabresiRepository;
        this.gardeshkarBarnameHesabresiMapper = gardeshkarBarnameHesabresiMapper;
    }

    /**
     * Save a gardeshkarBarnameHesabresi.
     *
     * @param gardeshkarBarnameHesabresiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GardeshkarBarnameHesabresiDTO save(GardeshkarBarnameHesabresiDTO gardeshkarBarnameHesabresiDTO) {
        log.debug("Request to save GardeshkarBarnameHesabresi : {}", gardeshkarBarnameHesabresiDTO);
        GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresiMapper.toEntity(gardeshkarBarnameHesabresiDTO);
        gardeshkarBarnameHesabresi = gardeshkarBarnameHesabresiRepository.save(gardeshkarBarnameHesabresi);
        return gardeshkarBarnameHesabresiMapper.toDto(gardeshkarBarnameHesabresi);
    }

    /**
     * Get all the gardeshkarBarnameHesabresis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GardeshkarBarnameHesabresiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GardeshkarBarnameHesabresis");
        return gardeshkarBarnameHesabresiRepository.findAll(pageable)
            .map(gardeshkarBarnameHesabresiMapper::toDto);
    }


    /**
     *  Get all the gardeshkarBarnameHesabresis where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<GardeshkarBarnameHesabresiDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all gardeshkarBarnameHesabresis where HesabResi is null");
        return StreamSupport
            .stream(gardeshkarBarnameHesabresiRepository.findAll().spliterator(), false)
            .filter(gardeshkarBarnameHesabresi -> gardeshkarBarnameHesabresi.getHesabResi() == null)
            .map(gardeshkarBarnameHesabresiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gardeshkarBarnameHesabresi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GardeshkarBarnameHesabresiDTO> findOne(Long id) {
        log.debug("Request to get GardeshkarBarnameHesabresi : {}", id);
        return gardeshkarBarnameHesabresiRepository.findById(id)
            .map(gardeshkarBarnameHesabresiMapper::toDto);
    }

    /**
     * Delete the gardeshkarBarnameHesabresi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GardeshkarBarnameHesabresi : {}", id);
        gardeshkarBarnameHesabresiRepository.deleteById(id);
    }
}
