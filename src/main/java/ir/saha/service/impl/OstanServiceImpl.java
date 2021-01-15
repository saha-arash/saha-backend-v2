package ir.saha.service.impl;

import ir.saha.service.OstanService;
import ir.saha.domain.Ostan;
import ir.saha.repository.OstanRepository;
import ir.saha.service.dto.OstanDTO;
import ir.saha.service.mapper.OstanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ostan}.
 */
@Service
@Transactional
public class OstanServiceImpl implements OstanService {

    private final Logger log = LoggerFactory.getLogger(OstanServiceImpl.class);

    private final OstanRepository ostanRepository;

    private final OstanMapper ostanMapper;

    public OstanServiceImpl(OstanRepository ostanRepository, OstanMapper ostanMapper) {
        this.ostanRepository = ostanRepository;
        this.ostanMapper = ostanMapper;
    }

    /**
     * Save a ostan.
     *
     * @param ostanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OstanDTO save(OstanDTO ostanDTO) {
        log.debug("Request to save Ostan : {}", ostanDTO);
        Ostan ostan = ostanMapper.toEntity(ostanDTO);
        ostan = ostanRepository.save(ostan);
        return ostanMapper.toDto(ostan);
    }

    /**
     * Get all the ostans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OstanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ostans");
        return ostanRepository.findAll(pageable)
            .map(ostanMapper::toDto);
    }

    /**
     * Get one ostan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OstanDTO> findOne(Long id) {
        log.debug("Request to get Ostan : {}", id);
        return ostanRepository.findById(id)
            .map(ostanMapper::toDto);
    }

    /**
     * Delete the ostan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ostan : {}", id);
        ostanRepository.deleteById(id);
    }
}
