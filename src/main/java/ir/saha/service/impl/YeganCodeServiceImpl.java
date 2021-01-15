package ir.saha.service.impl;

import ir.saha.service.YeganCodeService;
import ir.saha.domain.YeganCode;
import ir.saha.repository.YeganCodeRepository;
import ir.saha.service.dto.YeganCodeDTO;
import ir.saha.service.mapper.YeganCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link YeganCode}.
 */
@Service
@Transactional
public class YeganCodeServiceImpl implements YeganCodeService {

    private final Logger log = LoggerFactory.getLogger(YeganCodeServiceImpl.class);

    private final YeganCodeRepository yeganCodeRepository;

    private final YeganCodeMapper yeganCodeMapper;

    public YeganCodeServiceImpl(YeganCodeRepository yeganCodeRepository, YeganCodeMapper yeganCodeMapper) {
        this.yeganCodeRepository = yeganCodeRepository;
        this.yeganCodeMapper = yeganCodeMapper;
    }

    /**
     * Save a yeganCode.
     *
     * @param yeganCodeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public YeganCodeDTO save(YeganCodeDTO yeganCodeDTO) {
        log.debug("Request to save YeganCode : {}", yeganCodeDTO);
        YeganCode yeganCode = yeganCodeMapper.toEntity(yeganCodeDTO);
        yeganCode = yeganCodeRepository.save(yeganCode);
        return yeganCodeMapper.toDto(yeganCode);
    }

    /**
     * Get all the yeganCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<YeganCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all YeganCodes");
        return yeganCodeRepository.findAll(pageable)
            .map(yeganCodeMapper::toDto);
    }

    /**
     * Get one yeganCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<YeganCodeDTO> findOne(Long id) {
        log.debug("Request to get YeganCode : {}", id);
        return yeganCodeRepository.findById(id)
            .map(yeganCodeMapper::toDto);
    }

    /**
     * Delete the yeganCode by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete YeganCode : {}", id);
        yeganCodeRepository.deleteById(id);
    }
}
