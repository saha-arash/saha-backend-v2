package ir.saha.service.impl;

import ir.saha.service.ChekideGardeshKarService;
import ir.saha.domain.ChekideGardeshKar;
import ir.saha.repository.ChekideGardeshKarRepository;
import ir.saha.service.dto.ChekideGardeshKarDTO;
import ir.saha.service.mapper.ChekideGardeshKarMapper;
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
 * Service Implementation for managing {@link ChekideGardeshKar}.
 */
@Service
@Transactional
public class ChekideGardeshKarServiceImpl implements ChekideGardeshKarService {

    private final Logger log = LoggerFactory.getLogger(ChekideGardeshKarServiceImpl.class);

    private final ChekideGardeshKarRepository chekideGardeshKarRepository;

    private final ChekideGardeshKarMapper chekideGardeshKarMapper;

    public ChekideGardeshKarServiceImpl(ChekideGardeshKarRepository chekideGardeshKarRepository, ChekideGardeshKarMapper chekideGardeshKarMapper) {
        this.chekideGardeshKarRepository = chekideGardeshKarRepository;
        this.chekideGardeshKarMapper = chekideGardeshKarMapper;
    }

    /**
     * Save a chekideGardeshKar.
     *
     * @param chekideGardeshKarDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ChekideGardeshKarDTO save(ChekideGardeshKarDTO chekideGardeshKarDTO) {
        log.debug("Request to save ChekideGardeshKar : {}", chekideGardeshKarDTO);
        ChekideGardeshKar chekideGardeshKar = chekideGardeshKarMapper.toEntity(chekideGardeshKarDTO);
        chekideGardeshKar = chekideGardeshKarRepository.save(chekideGardeshKar);
        return chekideGardeshKarMapper.toDto(chekideGardeshKar);
    }

    /**
     * Get all the chekideGardeshKars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChekideGardeshKarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChekideGardeshKars");
        return chekideGardeshKarRepository.findAll(pageable)
            .map(chekideGardeshKarMapper::toDto);
    }


    /**
     *  Get all the chekideGardeshKars where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ChekideGardeshKarDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all chekideGardeshKars where HesabResi is null");
        return StreamSupport
            .stream(chekideGardeshKarRepository.findAll().spliterator(), false)
            .filter(chekideGardeshKar -> chekideGardeshKar.getHesabResi() == null)
            .map(chekideGardeshKarMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one chekideGardeshKar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChekideGardeshKarDTO> findOne(Long id) {
        log.debug("Request to get ChekideGardeshKar : {}", id);
        return chekideGardeshKarRepository.findById(id)
            .map(chekideGardeshKarMapper::toDto);
    }

    /**
     * Delete the chekideGardeshKar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChekideGardeshKar : {}", id);
        chekideGardeshKarRepository.deleteById(id);
    }
}
