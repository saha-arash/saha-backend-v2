package ir.saha.service.impl;

import ir.saha.service.MostaKhrejeService;
import ir.saha.domain.MostaKhreje;
import ir.saha.repository.MostaKhrejeRepository;
import ir.saha.service.dto.MostaKhrejeDTO;
import ir.saha.service.mapper.MostaKhrejeMapper;
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
 * Service Implementation for managing {@link MostaKhreje}.
 */
@Service
@Transactional
public class MostaKhrejeServiceImpl implements MostaKhrejeService {

    private final Logger log = LoggerFactory.getLogger(MostaKhrejeServiceImpl.class);

    private final MostaKhrejeRepository mostaKhrejeRepository;

    private final MostaKhrejeMapper mostaKhrejeMapper;

    public MostaKhrejeServiceImpl(MostaKhrejeRepository mostaKhrejeRepository, MostaKhrejeMapper mostaKhrejeMapper) {
        this.mostaKhrejeRepository = mostaKhrejeRepository;
        this.mostaKhrejeMapper = mostaKhrejeMapper;
    }

    /**
     * Save a mostaKhreje.
     *
     * @param mostaKhrejeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MostaKhrejeDTO save(MostaKhrejeDTO mostaKhrejeDTO) {
        log.debug("Request to save MostaKhreje : {}", mostaKhrejeDTO);
        MostaKhreje mostaKhreje = mostaKhrejeMapper.toEntity(mostaKhrejeDTO);
        mostaKhreje = mostaKhrejeRepository.save(mostaKhreje);
        return mostaKhrejeMapper.toDto(mostaKhreje);
    }

    /**
     * Get all the mostaKhrejes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MostaKhrejeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MostaKhrejes");
        return mostaKhrejeRepository.findAll(pageable)
            .map(mostaKhrejeMapper::toDto);
    }


    /**
     *  Get all the mostaKhrejes where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MostaKhrejeDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all mostaKhrejes where HesabResi is null");
        return StreamSupport
            .stream(mostaKhrejeRepository.findAll().spliterator(), false)
            .filter(mostaKhreje -> mostaKhreje.getHesabResi() == null)
            .map(mostaKhrejeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mostaKhreje by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MostaKhrejeDTO> findOne(Long id) {
        log.debug("Request to get MostaKhreje : {}", id);
        return mostaKhrejeRepository.findById(id)
            .map(mostaKhrejeMapper::toDto);
    }

    /**
     * Delete the mostaKhreje by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MostaKhreje : {}", id);
        mostaKhrejeRepository.deleteById(id);
    }
}
