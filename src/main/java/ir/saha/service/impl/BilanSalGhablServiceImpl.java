package ir.saha.service.impl;

import ir.saha.service.BilanSalGhablService;
import ir.saha.domain.BilanSalGhabl;
import ir.saha.repository.BilanSalGhablRepository;
import ir.saha.service.dto.BilanSalGhablDTO;
import ir.saha.service.mapper.BilanSalGhablMapper;
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
 * Service Implementation for managing {@link BilanSalGhabl}.
 */
@Service
@Transactional
public class BilanSalGhablServiceImpl implements BilanSalGhablService {

    private final Logger log = LoggerFactory.getLogger(BilanSalGhablServiceImpl.class);

    private final BilanSalGhablRepository bilanSalGhablRepository;

    private final BilanSalGhablMapper bilanSalGhablMapper;

    public BilanSalGhablServiceImpl(BilanSalGhablRepository bilanSalGhablRepository, BilanSalGhablMapper bilanSalGhablMapper) {
        this.bilanSalGhablRepository = bilanSalGhablRepository;
        this.bilanSalGhablMapper = bilanSalGhablMapper;
    }

    /**
     * Save a bilanSalGhabl.
     *
     * @param bilanSalGhablDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BilanSalGhablDTO save(BilanSalGhablDTO bilanSalGhablDTO) {
        log.debug("Request to save BilanSalGhabl : {}", bilanSalGhablDTO);
        BilanSalGhabl bilanSalGhabl = bilanSalGhablMapper.toEntity(bilanSalGhablDTO);
        bilanSalGhabl = bilanSalGhablRepository.save(bilanSalGhabl);
        return bilanSalGhablMapper.toDto(bilanSalGhabl);
    }

    /**
     * Get all the bilanSalGhabls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BilanSalGhablDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BilanSalGhabls");
        return bilanSalGhablRepository.findAll(pageable)
            .map(bilanSalGhablMapper::toDto);
    }


    /**
     *  Get all the bilanSalGhabls where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BilanSalGhablDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all bilanSalGhabls where HesabResi is null");
        return StreamSupport
            .stream(bilanSalGhablRepository.findAll().spliterator(), false)
            .filter(bilanSalGhabl -> bilanSalGhabl.getHesabResi() == null)
            .map(bilanSalGhablMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bilanSalGhabl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BilanSalGhablDTO> findOne(Long id) {
        log.debug("Request to get BilanSalGhabl : {}", id);
        return bilanSalGhablRepository.findById(id)
            .map(bilanSalGhablMapper::toDto);
    }

    /**
     * Delete the bilanSalGhabl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BilanSalGhabl : {}", id);
        bilanSalGhablRepository.deleteById(id);
    }
}
