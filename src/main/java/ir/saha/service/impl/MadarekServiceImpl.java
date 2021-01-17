package ir.saha.service.impl;

import ir.saha.service.MadarekService;
import ir.saha.domain.Madarek;
import ir.saha.repository.MadarekRepository;
import ir.saha.service.dto.MadarekDTO;
import ir.saha.service.mapper.MadarekMapper;
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
 * Service Implementation for managing {@link Madarek}.
 */
@Service
@Transactional
public class MadarekServiceImpl implements MadarekService {

    private final Logger log = LoggerFactory.getLogger(MadarekServiceImpl.class);

    private final MadarekRepository madarekRepository;

    private final MadarekMapper madarekMapper;

    public MadarekServiceImpl(MadarekRepository madarekRepository, MadarekMapper madarekMapper) {
        this.madarekRepository = madarekRepository;
        this.madarekMapper = madarekMapper;
    }

    /**
     * Save a madarek.
     *
     * @param madarekDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MadarekDTO save(MadarekDTO madarekDTO) {
        log.debug("Request to save Madarek : {}", madarekDTO);
        Madarek madarek = madarekMapper.toEntity(madarekDTO);
        madarek = madarekRepository.save(madarek);
        return madarekMapper.toDto(madarek);
    }

    /**
     * Get all the madareks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MadarekDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Madareks");
        return madarekRepository.findAll(pageable)
            .map(madarekMapper::toDto);
    }


    /**
     *  Get all the madareks where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MadarekDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all madareks where HesabResi is null");
        return StreamSupport
            .stream(madarekRepository.findAll().spliterator(), false)
            .filter(madarek -> madarek.getHesabResi() == null)
            .map(madarekMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one madarek by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MadarekDTO> findOne(Long id) {
        log.debug("Request to get Madarek : {}", id);
        return madarekRepository.findById(id)
            .map(madarekMapper::toDto);
    }

    /**
     * Delete the madarek by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Madarek : {}", id);
        madarekRepository.deleteById(id);
    }
}
