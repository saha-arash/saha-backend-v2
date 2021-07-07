package ir.saha.service.impl;

import ir.saha.service.BilanSeSalGhablService;
import ir.saha.domain.BilanSeSalGhabl;
import ir.saha.repository.BilanSeSalGhablRepository;
import ir.saha.service.dto.BilanSeSalGhablDTO;
import ir.saha.service.mapper.BilanSeSalGhablMapper;
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
 * Service Implementation for managing {@link BilanSeSalGhabl}.
 */
@Service
@Transactional
public class BilanSeSalGhablServiceImpl implements BilanSeSalGhablService {

    private final Logger log = LoggerFactory.getLogger(BilanSeSalGhablServiceImpl.class);

    private final BilanSeSalGhablRepository bilanSeSalGhablRepository;

    private final BilanSeSalGhablMapper bilanSeSalGhablMapper;

    public BilanSeSalGhablServiceImpl(BilanSeSalGhablRepository bilanSeSalGhablRepository, BilanSeSalGhablMapper bilanSeSalGhablMapper) {
        this.bilanSeSalGhablRepository = bilanSeSalGhablRepository;
        this.bilanSeSalGhablMapper = bilanSeSalGhablMapper;
    }

    /**
     * Save a bilanSeSalGhabl.
     *
     * @param bilanSeSalGhablDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BilanSeSalGhablDTO save(BilanSeSalGhablDTO bilanSeSalGhablDTO) {
        log.debug("Request to save BilanSeSalGhabl : {}", bilanSeSalGhablDTO);
        BilanSeSalGhabl bilanSeSalGhabl = bilanSeSalGhablMapper.toEntity(bilanSeSalGhablDTO);
        bilanSeSalGhabl = bilanSeSalGhablRepository.save(bilanSeSalGhabl);
        return bilanSeSalGhablMapper.toDto(bilanSeSalGhabl);
    }

    /**
     * Get all the bilanSeSalGhabls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BilanSeSalGhablDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BilanSeSalGhabls");
        return bilanSeSalGhablRepository.findAll(pageable)
            .map(bilanSeSalGhablMapper::toDto);
    }


    /**
     *  Get all the bilanSeSalGhabls where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BilanSeSalGhablDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all bilanSeSalGhabls where HesabResi is null");
        return StreamSupport
            .stream(bilanSeSalGhablRepository.findAll().spliterator(), false)
            .filter(bilanSeSalGhabl -> bilanSeSalGhabl.getHesabResi() == null)
            .map(bilanSeSalGhablMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bilanSeSalGhabl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BilanSeSalGhablDTO> findOne(Long id) {
        log.debug("Request to get BilanSeSalGhabl : {}", id);
        return bilanSeSalGhablRepository.findById(id)
            .map(bilanSeSalGhablMapper::toDto);
    }

    /**
     * Delete the bilanSeSalGhabl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BilanSeSalGhabl : {}", id);
        bilanSeSalGhablRepository.deleteById(id);
    }
}
