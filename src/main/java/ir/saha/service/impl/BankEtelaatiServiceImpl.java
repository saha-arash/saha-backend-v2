package ir.saha.service.impl;

import ir.saha.service.BankEtelaatiService;
import ir.saha.domain.BankEtelaati;
import ir.saha.repository.BankEtelaatiRepository;
import ir.saha.service.dto.BankEtelaatiDTO;
import ir.saha.service.mapper.BankEtelaatiMapper;
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
 * Service Implementation for managing {@link BankEtelaati}.
 */
@Service
@Transactional
public class BankEtelaatiServiceImpl implements BankEtelaatiService {

    private final Logger log = LoggerFactory.getLogger(BankEtelaatiServiceImpl.class);

    private final BankEtelaatiRepository bankEtelaatiRepository;

    private final BankEtelaatiMapper bankEtelaatiMapper;

    public BankEtelaatiServiceImpl(BankEtelaatiRepository bankEtelaatiRepository, BankEtelaatiMapper bankEtelaatiMapper) {
        this.bankEtelaatiRepository = bankEtelaatiRepository;
        this.bankEtelaatiMapper = bankEtelaatiMapper;
    }

    /**
     * Save a bankEtelaati.
     *
     * @param bankEtelaatiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BankEtelaatiDTO save(BankEtelaatiDTO bankEtelaatiDTO) {
        log.debug("Request to save BankEtelaati : {}", bankEtelaatiDTO);
        BankEtelaati bankEtelaati = bankEtelaatiMapper.toEntity(bankEtelaatiDTO);
        bankEtelaati = bankEtelaatiRepository.save(bankEtelaati);
        return bankEtelaatiMapper.toDto(bankEtelaati);
    }

    /**
     * Get all the bankEtelaatis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankEtelaatiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BankEtelaatis");
        return bankEtelaatiRepository.findAll(pageable)
            .map(bankEtelaatiMapper::toDto);
    }


    /**
     *  Get all the bankEtelaatis where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BankEtelaatiDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all bankEtelaatis where HesabResi is null");
        return StreamSupport
            .stream(bankEtelaatiRepository.findAll().spliterator(), false)
            .filter(bankEtelaati -> bankEtelaati.getHesabResi() == null)
            .map(bankEtelaatiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bankEtelaati by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankEtelaatiDTO> findOne(Long id) {
        log.debug("Request to get BankEtelaati : {}", id);
        return bankEtelaatiRepository.findById(id)
            .map(bankEtelaatiMapper::toDto);
    }

    /**
     * Delete the bankEtelaati by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankEtelaati : {}", id);
        bankEtelaatiRepository.deleteById(id);
    }
}
