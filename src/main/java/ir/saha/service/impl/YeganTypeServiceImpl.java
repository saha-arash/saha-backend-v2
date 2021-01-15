package ir.saha.service.impl;

import ir.saha.service.YeganTypeService;
import ir.saha.domain.YeganType;
import ir.saha.repository.YeganTypeRepository;
import ir.saha.service.dto.YeganTypeDTO;
import ir.saha.service.mapper.YeganTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link YeganType}.
 */
@Service
@Transactional
public class YeganTypeServiceImpl implements YeganTypeService {

    private final Logger log = LoggerFactory.getLogger(YeganTypeServiceImpl.class);

    private final YeganTypeRepository yeganTypeRepository;

    private final YeganTypeMapper yeganTypeMapper;

    public YeganTypeServiceImpl(YeganTypeRepository yeganTypeRepository, YeganTypeMapper yeganTypeMapper) {
        this.yeganTypeRepository = yeganTypeRepository;
        this.yeganTypeMapper = yeganTypeMapper;
    }

    /**
     * Save a yeganType.
     *
     * @param yeganTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public YeganTypeDTO save(YeganTypeDTO yeganTypeDTO) {
        log.debug("Request to save YeganType : {}", yeganTypeDTO);
        YeganType yeganType = yeganTypeMapper.toEntity(yeganTypeDTO);
        yeganType = yeganTypeRepository.save(yeganType);
        return yeganTypeMapper.toDto(yeganType);
    }

    /**
     * Get all the yeganTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<YeganTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all YeganTypes");
        return yeganTypeRepository.findAll(pageable)
            .map(yeganTypeMapper::toDto);
    }

    /**
     * Get one yeganType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<YeganTypeDTO> findOne(Long id) {
        log.debug("Request to get YeganType : {}", id);
        return yeganTypeRepository.findById(id)
            .map(yeganTypeMapper::toDto);
    }

    /**
     * Delete the yeganType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete YeganType : {}", id);
        yeganTypeRepository.deleteById(id);
    }
}
