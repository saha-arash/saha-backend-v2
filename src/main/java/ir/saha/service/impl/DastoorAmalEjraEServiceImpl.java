package ir.saha.service.impl;

import ir.saha.service.DastoorAmalEjraEService;
import ir.saha.domain.DastoorAmalEjraE;
import ir.saha.repository.DastoorAmalEjraERepository;
import ir.saha.service.dto.DastoorAmalEjraEDTO;
import ir.saha.service.mapper.DastoorAmalEjraEMapper;
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
 * Service Implementation for managing {@link DastoorAmalEjraE}.
 */
@Service
@Transactional
public class DastoorAmalEjraEServiceImpl implements DastoorAmalEjraEService {

    private final Logger log = LoggerFactory.getLogger(DastoorAmalEjraEServiceImpl.class);

    private final DastoorAmalEjraERepository dastoorAmalEjraERepository;

    private final DastoorAmalEjraEMapper dastoorAmalEjraEMapper;

    public DastoorAmalEjraEServiceImpl(DastoorAmalEjraERepository dastoorAmalEjraERepository, DastoorAmalEjraEMapper dastoorAmalEjraEMapper) {
        this.dastoorAmalEjraERepository = dastoorAmalEjraERepository;
        this.dastoorAmalEjraEMapper = dastoorAmalEjraEMapper;
    }

    /**
     * Save a dastoorAmalEjraE.
     *
     * @param dastoorAmalEjraEDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DastoorAmalEjraEDTO save(DastoorAmalEjraEDTO dastoorAmalEjraEDTO) {
        log.debug("Request to save DastoorAmalEjraE : {}", dastoorAmalEjraEDTO);
        DastoorAmalEjraE dastoorAmalEjraE = dastoorAmalEjraEMapper.toEntity(dastoorAmalEjraEDTO);
        dastoorAmalEjraE = dastoorAmalEjraERepository.save(dastoorAmalEjraE);
        return dastoorAmalEjraEMapper.toDto(dastoorAmalEjraE);
    }

    /**
     * Get all the dastoorAmalEjraES.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DastoorAmalEjraEDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DastoorAmalEjraES");
        return dastoorAmalEjraERepository.findAll(pageable)
            .map(dastoorAmalEjraEMapper::toDto);
    }


    /**
     *  Get all the dastoorAmalEjraES where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<DastoorAmalEjraEDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all dastoorAmalEjraES where HesabResi is null");
        return StreamSupport
            .stream(dastoorAmalEjraERepository.findAll().spliterator(), false)
            .filter(dastoorAmalEjraE -> dastoorAmalEjraE.getHesabResi() == null)
            .map(dastoorAmalEjraEMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dastoorAmalEjraE by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DastoorAmalEjraEDTO> findOne(Long id) {
        log.debug("Request to get DastoorAmalEjraE : {}", id);
        return dastoorAmalEjraERepository.findById(id)
            .map(dastoorAmalEjraEMapper::toDto);
    }

    /**
     * Delete the dastoorAmalEjraE by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DastoorAmalEjraE : {}", id);
        dastoorAmalEjraERepository.deleteById(id);
    }
}
