package ir.saha.service.impl;

import ir.saha.service.MohasebeHazineMamooriatService;
import ir.saha.domain.MohasebeHazineMamooriat;
import ir.saha.repository.MohasebeHazineMamooriatRepository;
import ir.saha.service.dto.MohasebeHazineMamooriatDTO;
import ir.saha.service.mapper.MohasebeHazineMamooriatMapper;
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
 * Service Implementation for managing {@link MohasebeHazineMamooriat}.
 */
@Service
@Transactional
public class MohasebeHazineMamooriatServiceImpl implements MohasebeHazineMamooriatService {

    private final Logger log = LoggerFactory.getLogger(MohasebeHazineMamooriatServiceImpl.class);

    private final MohasebeHazineMamooriatRepository mohasebeHazineMamooriatRepository;

    private final MohasebeHazineMamooriatMapper mohasebeHazineMamooriatMapper;

    public MohasebeHazineMamooriatServiceImpl(MohasebeHazineMamooriatRepository mohasebeHazineMamooriatRepository, MohasebeHazineMamooriatMapper mohasebeHazineMamooriatMapper) {
        this.mohasebeHazineMamooriatRepository = mohasebeHazineMamooriatRepository;
        this.mohasebeHazineMamooriatMapper = mohasebeHazineMamooriatMapper;
    }

    /**
     * Save a mohasebeHazineMamooriat.
     *
     * @param mohasebeHazineMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MohasebeHazineMamooriatDTO save(MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO) {
        log.debug("Request to save MohasebeHazineMamooriat : {}", mohasebeHazineMamooriatDTO);
        MohasebeHazineMamooriat mohasebeHazineMamooriat = mohasebeHazineMamooriatMapper.toEntity(mohasebeHazineMamooriatDTO);
        mohasebeHazineMamooriat = mohasebeHazineMamooriatRepository.save(mohasebeHazineMamooriat);
        return mohasebeHazineMamooriatMapper.toDto(mohasebeHazineMamooriat);
    }

    /**
     * Get all the mohasebeHazineMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MohasebeHazineMamooriatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MohasebeHazineMamooriats");
        return mohasebeHazineMamooriatRepository.findAll(pageable)
            .map(mohasebeHazineMamooriatMapper::toDto);
    }


    /**
     *  Get all the mohasebeHazineMamooriats where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MohasebeHazineMamooriatDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all mohasebeHazineMamooriats where HesabResi is null");
        return StreamSupport
            .stream(mohasebeHazineMamooriatRepository.findAll().spliterator(), false)
            .filter(mohasebeHazineMamooriat -> mohasebeHazineMamooriat.getHesabResi() == null)
            .map(mohasebeHazineMamooriatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mohasebeHazineMamooriat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MohasebeHazineMamooriatDTO> findOne(Long id) {
        log.debug("Request to get MohasebeHazineMamooriat : {}", id);
        return mohasebeHazineMamooriatRepository.findById(id)
            .map(mohasebeHazineMamooriatMapper::toDto);
    }

    /**
     * Delete the mohasebeHazineMamooriat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MohasebeHazineMamooriat : {}", id);
        mohasebeHazineMamooriatRepository.deleteById(id);
    }
}
