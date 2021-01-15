package ir.saha.service.impl;

import ir.saha.service.PayamService;
import ir.saha.domain.Payam;
import ir.saha.repository.PayamRepository;
import ir.saha.service.dto.PayamDTO;
import ir.saha.service.mapper.PayamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Payam}.
 */
@Service
@Transactional
public class PayamServiceImpl implements PayamService {

    private final Logger log = LoggerFactory.getLogger(PayamServiceImpl.class);

    private final PayamRepository payamRepository;

    private final PayamMapper payamMapper;

    public PayamServiceImpl(PayamRepository payamRepository, PayamMapper payamMapper) {
        this.payamRepository = payamRepository;
        this.payamMapper = payamMapper;
    }

    /**
     * Save a payam.
     *
     * @param payamDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PayamDTO save(PayamDTO payamDTO) {
        log.debug("Request to save Payam : {}", payamDTO);
        Payam payam = payamMapper.toEntity(payamDTO);
        payam = payamRepository.save(payam);
        return payamMapper.toDto(payam);
    }

    /**
     * Get all the payams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PayamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Payams");
        return payamRepository.findAll(pageable)
            .map(payamMapper::toDto);
    }

    /**
     * Get one payam by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PayamDTO> findOne(Long id) {
        log.debug("Request to get Payam : {}", id);
        return payamRepository.findById(id)
            .map(payamMapper::toDto);
    }

    /**
     * Delete the payam by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Payam : {}", id);
        payamRepository.deleteById(id);
    }
}
