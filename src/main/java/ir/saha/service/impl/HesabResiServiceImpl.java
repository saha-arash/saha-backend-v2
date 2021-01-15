package ir.saha.service.impl;

import ir.saha.service.HesabResiService;
import ir.saha.domain.HesabResi;
import ir.saha.repository.HesabResiRepository;
import ir.saha.service.dto.HesabResiDTO;
import ir.saha.service.mapper.HesabResiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HesabResi}.
 */
@Service
@Transactional
public class HesabResiServiceImpl implements HesabResiService {

    private final Logger log = LoggerFactory.getLogger(HesabResiServiceImpl.class);

    private final HesabResiRepository hesabResiRepository;

    private final HesabResiMapper hesabResiMapper;

    public HesabResiServiceImpl(HesabResiRepository hesabResiRepository, HesabResiMapper hesabResiMapper) {
        this.hesabResiRepository = hesabResiRepository;
        this.hesabResiMapper = hesabResiMapper;
    }

    /**
     * Save a hesabResi.
     *
     * @param hesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HesabResiDTO save(HesabResiDTO hesabResiDTO) {
        log.debug("Request to save HesabResi : {}", hesabResiDTO);
        HesabResi hesabResi = hesabResiMapper.toEntity(hesabResiDTO);
        hesabResi = hesabResiRepository.save(hesabResi);
        return hesabResiMapper.toDto(hesabResi);
    }

    /**
     * Get all the hesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HesabResiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HesabResis");
        return hesabResiRepository.findAll(pageable)
            .map(hesabResiMapper::toDto);
    }

    /**
     * Get one hesabResi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HesabResiDTO> findOne(Long id) {
        log.debug("Request to get HesabResi : {}", id);
        return hesabResiRepository.findById(id)
            .map(hesabResiMapper::toDto);
    }

    /**
     * Delete the hesabResi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HesabResi : {}", id);
        hesabResiRepository.deleteById(id);
    }
}
