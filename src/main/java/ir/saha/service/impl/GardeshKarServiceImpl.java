package ir.saha.service.impl;

import ir.saha.service.GardeshKarService;
import ir.saha.domain.GardeshKar;
import ir.saha.repository.GardeshKarRepository;
import ir.saha.service.dto.GardeshKarDTO;
import ir.saha.service.mapper.GardeshKarMapper;
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
 * Service Implementation for managing {@link GardeshKar}.
 */
@Service
@Transactional
public class GardeshKarServiceImpl implements GardeshKarService {

    private final Logger log = LoggerFactory.getLogger(GardeshKarServiceImpl.class);

    private final GardeshKarRepository gardeshKarRepository;

    private final GardeshKarMapper gardeshKarMapper;

    public GardeshKarServiceImpl(GardeshKarRepository gardeshKarRepository, GardeshKarMapper gardeshKarMapper) {
        this.gardeshKarRepository = gardeshKarRepository;
        this.gardeshKarMapper = gardeshKarMapper;
    }

    /**
     * Save a gardeshKar.
     *
     * @param gardeshKarDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GardeshKarDTO save(GardeshKarDTO gardeshKarDTO) {
        log.debug("Request to save GardeshKar : {}", gardeshKarDTO);
        GardeshKar gardeshKar = gardeshKarMapper.toEntity(gardeshKarDTO);
        gardeshKar = gardeshKarRepository.save(gardeshKar);
        return gardeshKarMapper.toDto(gardeshKar);
    }

    /**
     * Get all the gardeshKars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GardeshKarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GardeshKars");
        return gardeshKarRepository.findAll(pageable)
            .map(gardeshKarMapper::toDto);
    }


    /**
     *  Get all the gardeshKars where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<GardeshKarDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all gardeshKars where HesabResi is null");
        return StreamSupport
            .stream(gardeshKarRepository.findAll().spliterator(), false)
            .filter(gardeshKar -> gardeshKar.getHesabResi() == null)
            .map(gardeshKarMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gardeshKar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GardeshKarDTO> findOne(Long id) {
        log.debug("Request to get GardeshKar : {}", id);
        return gardeshKarRepository.findById(id)
            .map(gardeshKarMapper::toDto);
    }

    /**
     * Delete the gardeshKar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GardeshKar : {}", id);
        gardeshKarRepository.deleteById(id);
    }
}
