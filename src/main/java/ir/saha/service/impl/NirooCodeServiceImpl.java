package ir.saha.service.impl;

import ir.saha.service.NirooCodeService;
import ir.saha.domain.NirooCode;
import ir.saha.repository.NirooCodeRepository;
import ir.saha.service.dto.NirooCodeDTO;
import ir.saha.service.mapper.NirooCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NirooCode}.
 */
@Service
@Transactional
public class NirooCodeServiceImpl implements NirooCodeService {

    private final Logger log = LoggerFactory.getLogger(NirooCodeServiceImpl.class);

    private final NirooCodeRepository nirooCodeRepository;

    private final NirooCodeMapper nirooCodeMapper;

    public NirooCodeServiceImpl(NirooCodeRepository nirooCodeRepository, NirooCodeMapper nirooCodeMapper) {
        this.nirooCodeRepository = nirooCodeRepository;
        this.nirooCodeMapper = nirooCodeMapper;
    }

    /**
     * Save a nirooCode.
     *
     * @param nirooCodeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NirooCodeDTO save(NirooCodeDTO nirooCodeDTO) {
        log.debug("Request to save NirooCode : {}", nirooCodeDTO);
        NirooCode nirooCode = nirooCodeMapper.toEntity(nirooCodeDTO);
        nirooCode = nirooCodeRepository.save(nirooCode);
        return nirooCodeMapper.toDto(nirooCode);
    }

    /**
     * Get all the nirooCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NirooCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NirooCodes");
        return nirooCodeRepository.findAll(pageable)
            .map(nirooCodeMapper::toDto);
    }

    /**
     * Get one nirooCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NirooCodeDTO> findOne(Long id) {
        log.debug("Request to get NirooCode : {}", id);
        return nirooCodeRepository.findById(id)
            .map(nirooCodeMapper::toDto);
    }

    /**
     * Delete the nirooCode by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NirooCode : {}", id);
        nirooCodeRepository.deleteById(id);
    }
}
